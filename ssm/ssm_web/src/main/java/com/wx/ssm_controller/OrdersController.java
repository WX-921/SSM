package com.wx.ssm_controller;

import com.github.pagehelper.PageInfo;
import com.wx.ssm_domain.Orders;
import com.wx.ssm_domain.Product;
import com.wx.ssm_service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private IOrdersService ordersService;


    //查询全部订单---不分页
    /*@RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception{
        ModelAndView mv = new ModelAndView();
        List<Orders> orders = ordersService.findAll();
        mv.addObject("ordersList",orders);
        mv.setViewName("orders-list");
        return mv;
    }*/
    @RequestMapping("/findAll.do")
    @Secured("ROLE_USER")
    public ModelAndView findAll(@RequestParam(name = "page", required = true, defaultValue = "1") Integer page,
                                @RequestParam(name = "size",required = true, defaultValue = "4") Integer size)
            throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Orders> orders = ordersService.findAll(page, size);
        //PageInfo为分页的Bean
        PageInfo pageInfo = new PageInfo(orders);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("orders-page-list");
        return mv;
    }

    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "id",required = true)String ordersId)throws Exception{
        ModelAndView mv = new ModelAndView();
        Orders orders = ordersService.findById(ordersId);
        mv.addObject("orders",orders);
        mv.setViewName("orders-show");
        return mv;
    }
}
