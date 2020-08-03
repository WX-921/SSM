package com.wx.ssm_service.impl;

import com.github.pagehelper.PageHelper;
import com.wx.ssm_dao.IProductDao;
import com.wx.ssm_domain.Product;
import com.wx.ssm_service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional //事务
public class ProductServiceImpl implements IProductService {

    @Autowired
    private IProductDao productDao;

    @Override
    public Product findById() throws Exception {
        return productDao.findById("#{id}");
    }

    @Override
    public List<Product> findAll(int page,int size) throws Exception {
        PageHelper.startPage(page,size);
        return productDao.findAll();
    }

    @Override
    public void save(Product product) throws Exception {
        //获取32位随机数
        String replace = UUID.randomUUID().toString().replace("-","").toUpperCase();
        product.setId(replace);
        productDao.save(product);
    }

    @Override
    public void delete(String id) throws Exception {
        productDao.delete(id);
    }


}
