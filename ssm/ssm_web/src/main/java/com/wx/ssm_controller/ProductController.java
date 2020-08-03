package com.wx.ssm_controller;

import com.github.pagehelper.PageInfo;
import com.wx.ssm_domain.Product;
import com.wx.ssm_service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private IProductService productService;

    /**
     * 查询所有产品
     * @return
     * @throws Exception
     */
    @RequestMapping("/findAll.do")
    @RolesAllowed("ADMIN")
    public ModelAndView findAll(@RequestParam(name = "page", required = true, defaultValue = "1") Integer page,
                                @RequestParam(name = "size",required = true, defaultValue = "4") Integer size)
            throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Product> ps = productService.findAll(page,size);
        //分页
        PageInfo pageInfo = new PageInfo(ps);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("product-list");
        return mv;
    }

    /**
     * 产品添加
     * @param product
     */
    @RequestMapping("/save.do")
    public String save(Product product) throws Exception{
        productService.save(product);
        return "redirect:findAll.do";
    }

    @RequestMapping("/delete.do")
    public String delete(@RequestParam(name = "id") String id) throws Exception{
        System.out.println("id是：" +id);
        productService.delete(id);
        return "redirect:findAll.do";
    }
}
