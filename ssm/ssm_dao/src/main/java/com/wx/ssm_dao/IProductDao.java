package com.wx.ssm_dao;

import com.wx.ssm_domain.Product;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface IProductDao {
    /**
     * 根据 id查询产品
     * @param id
     * @return
     * @throws Exception
     */
    @Select("select * from product where id = #{id}")
    public Product findById(String id) throws Exception;

    /**
     * 查询所有产品信息
     * @return
     * @throws Exception
     */
    @Select("select * from product")
    public List<Product> findAll() throws Exception;

    /**
     * 添加保存数据
     * @param product
     * @return
     * @throws Exception
     */
    @Insert("insert into product(id,productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus)" +
            "values(#{id},#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    void save(Product product) throws Exception;


    @Delete("delete from product where id=#{id}")
    void delete(String id) throws Exception;
}
