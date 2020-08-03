package com.wx.ssm_service;

import com.wx.ssm_domain.Orders;

import java.util.List;

public interface IOrdersService {
    //不分页
    // List<Orders> findAll() throws Exception;

    //分页
    List<Orders> findAll(int page,int size) throws Exception;

    Orders findById(String ordersId) throws Exception;

    Integer findNum() throws Exception;
}
