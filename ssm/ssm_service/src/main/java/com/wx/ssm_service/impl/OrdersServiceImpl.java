package com.wx.ssm_service.impl;

import com.github.pagehelper.PageHelper;
import com.wx.ssm_dao.IOrdersDao;
import com.wx.ssm_domain.Orders;
import com.wx.ssm_service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrdersServiceImpl implements IOrdersService {

    @Autowired
    private IOrdersDao ordersDao;

   /* 不分页

   @Override
    public List<Orders> findAll() throws Exception {
        //表示每页显示5条信息（必须与调用方法代码在一起 中间不能插入其它语句）
        PageHelper.startPage(1,5);
        return ordersDao.findAll();
    }*/

    /**
     * 分页
     * @param page
     * @param size
     * @return
     * @throws Exception
     */
    @Override
    public List<Orders> findAll(int page, int size) throws Exception {
        PageHelper.startPage(page,size);
        return ordersDao.findAll();
    }

    @Override
    public Orders findById(String ordersId) throws Exception {
        return ordersDao.findById(ordersId);
    }

    @Override
    public Integer findNum() throws Exception {
        return ordersDao.findNum();
    }
}
