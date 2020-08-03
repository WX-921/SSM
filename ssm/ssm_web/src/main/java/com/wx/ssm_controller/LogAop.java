package com.wx.ssm_controller;


import com.wx.ssm_domain.Syslog;
import com.wx.ssm_service.ISysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Date;

@Component
@Aspect
public class LogAop {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ISysLogService sysLogService;

    private Date visitTime; //开始时间
    private Class clazz; //访问的类
    private Method method; //访问的方法


    /**
     * 前置通知
     *  主要获取开始时间，执行的类是哪一个，执行的是哪一个方法
     * @param jp
     */
    @Before("execution(* com.wx.ssm_controller.*.*(..))")
    public void doBefoer(JoinPoint jp) throws NoSuchMethodException {
        //当前时间为开始访问的时间
        visitTime = new Date();
        //获得具体要访问的类对象
        clazz = jp.getTarget().getClass();
        //获得方法的名称
        String methodName = jp.getSignature().getName();
        //获取访问方法的参数
        Object[] args = jp.getArgs();
        if (args == null||args.length == 0) {
            //获取无参方法
            method = clazz.getMethod(methodName);
        }else {
            //获取带参方法
            Class[] classes = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                classes[i] = args[i].getClass();
            }
            clazz.getMethod(methodName,classes);
        }

    }

    @After("execution(* com.wx.ssm_controller.*.*(..))")
    public void doAfter(JoinPoint jp) throws Exception{
        //获取访问时长
        long time = new Date().getTime()-visitTime.getTime();

        //获取url
        String url = "";

        if (clazz!=null&&method!=null&&clazz!= LogAop.class){
            // 1 获取类上的 @RequestMapping("/orders")
            RequestMapping clazzAnnotation = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
            if (clazzAnnotation!=null){
                String[] clazzValue = clazzAnnotation.value();
                //获取方法上的@RequestMapping("/xxxxx")
                RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
                if (methodAnnotation!=null){
                    String[] methodValue = methodAnnotation.value();

                    url = clazzValue[0]+methodValue[0];
                    //获取ip地址
                    String ip = request.getRemoteAddr();

                    //获取当前操作的用户
                    SecurityContext context = SecurityContextHolder.getContext();//从上下文中获取当前登录的用户
                    User user =(User) context.getAuthentication().getPrincipal();
                    String username = user.getUsername();

                    //将日志相关信息封装到Syslog对象中
                    Syslog syslog = new Syslog();
                    syslog.setExecutionTime(time);
                    syslog.setIp(ip);
                    syslog.setMethod("类名："+clazz.getName()+"方法名："+method.getName());
                    syslog.setUrl(url);
                    syslog.setUsername(username);
                    syslog.setVisitTime(visitTime);

                    //调用Service
                    sysLogService.save(syslog);
                }
            }

        }

    }

}
