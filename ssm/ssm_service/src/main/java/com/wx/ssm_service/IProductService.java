package com.wx.ssm_service;

import com.wx.ssm_domain.Product;

import java.util.List;

/**
 * 服务层
 */
public interface IProductService {
    public Product findById() throws Exception;

    public List<Product> findAll(int page,int size) throws Exception;

    void save(Product product) throws Exception;

    void delete(String id) throws Exception;
}
