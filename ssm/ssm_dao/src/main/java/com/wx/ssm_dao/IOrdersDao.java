package com.wx.ssm_dao;

import com.wx.ssm_domain.Member;
import com.wx.ssm_domain.Orders;
import com.wx.ssm_domain.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IOrdersDao {
    /**
     * 查询所有订单
     * @return
     * @throws Exception
     */
    @Select("select * from orders")
    @Results({
            @Result(id=true,property = "id",column = "id"),
            @Result(property = "orderNum",column = "orderNum"),
            @Result(property = "orderTime",column = "orderTime"),
            @Result(property = "orderStatus",column = "orderStatus"),
            @Result(property = "peopleCount",column = "peopleCount"),
            @Result(property = "payType",column = "payType"),
            @Result(property = "orderDesc",column = "orderDesc"),
            @Result(property = "product",column = "productId",javaType = Product.class,one = @One(select = "com.wx.ssm_dao.IProductDao.findById")),
    })
    public List<Orders> findAll() throws Exception;

    @Select("select count(*) from orders")
    Integer findNum() throws Exception;


    //多表操作
    @Select("select * from orders where id=#{ordersId}")
    @Results({
            @Result(id=true,property = "id",column = "id"),
            @Result(property = "orderNum",column = "orderNum"),
            @Result(property = "orderTime",column = "orderTime"),
            @Result(property = "orderStatus",column = "orderStatus"),
            @Result(property = "peopleCount",column = "peopleCount"),
            @Result(property = "payType",column = "payType"),
            @Result(property = "orderDesc",column = "orderDesc"),
            @Result(property = "product",column = "productId",javaType = Product.class,one = @One(select = "com.wx.ssm_dao.IProductDao.findById")),
            @Result(property = "member",column = "memberId",javaType = Member.class,one = @One(select = "com.wx.ssm_dao.IMemberDao.findById")),
            @Result(property = "travellers",column = "id",javaType =java.util.List.class,many = @Many(select = "com.wx.ssm_dao.ITravellerDao.findByOrdersId"))
    })
    public Orders findById(String ordersId) throws Exception;

}
