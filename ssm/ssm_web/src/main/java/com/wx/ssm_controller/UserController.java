package com.wx.ssm_controller;

import com.wx.ssm_domain.Role;
import com.wx.ssm_domain.UserInfo;
import com.wx.ssm_service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping("/findAll.do")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView findAll() throws Exception{
        ModelAndView mv = new ModelAndView();
        List<UserInfo> ui = userService.findAll();
        mv.addObject("userList",ui);
        mv.setViewName("user-list");
        return mv;
    }

    @RequestMapping("/save.do")
    @PreAuthorize("authentication.principal.username == 'wx'")
    public String save(UserInfo user) throws Exception{
        userService.save(user);
        return "redirect:findAll.do";
    }

    @RequestMapping("/findById.do")
    public ModelAndView findById(String id)throws Exception{
        ModelAndView mv = new ModelAndView();
        UserInfo users = userService.findById(id);
        mv.addObject("user",users);
        mv.setViewName("user-show");
        return mv;
    }

    /**
     * 查询用户以及用户可以添加的角色
     */
    @RequestMapping("/findUserByIdAndAllRole.do")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name = "id",required = true) String userId) throws Exception{

        ModelAndView mv = new ModelAndView();
        // 1 根据用户id查询用户
        UserInfo userInfo = userService.findById(userId);
        // 2 根据用户id查询可以添加的角色
        List<Role> roles = userService.findOtherRole(userId);
        mv.addObject("user",userInfo);
        mv.addObject("roleList",roles);
        mv.setViewName("user-role-add");
        return mv;
    }

    @RequestMapping("/addRoleToUser.do")
    public String addRoleToUser(@RequestParam(name="userId",required=true) String userId,@RequestParam(name="ids",required=true) String[] roleIds) throws Exception{
        userService.addRoleToUser(userId,roleIds);
        return "redirect:findAll.do";
    }
}

