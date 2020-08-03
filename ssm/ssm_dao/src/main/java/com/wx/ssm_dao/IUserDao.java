package com.wx.ssm_dao;

import com.wx.ssm_domain.Role;
import com.wx.ssm_domain.UserInfo;
import org.apache.ibatis.annotations.*;


import java.util.List;

public interface IUserDao {
    @Select("select * from users where username=#{username}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "email",column = "email"),
            @Result(property = "password",column = "password"),
            @Result(property = "phoneNum",column = "phoneNum"),
            @Result(property = "status",column = "status"),
            @Result(property = "roles",column ="id",javaType = java.util.List.class,
                    many=@Many(select = "com.wx.ssm_dao.IRoleDao.findRoleByUserId")),
    })
    public UserInfo findByUsername(String username) throws Exception;

    @Select("select * from users")
    public List<UserInfo> findAll() throws Exception;

    @Insert("insert into users(id,email,username,password,phoneNum,status) values(#{id},#{email},#{username},#{password},#{phoneNum},#{status})")
    public void save(UserInfo user) throws Exception;

    @Select("select * from users where id=#{d}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "email",column = "email"),
            @Result(property = "password",column = "password"),
            @Result(property = "phoneNum",column = "phoneNum"),
            @Result(property = "status",column = "status"),
            @Result(property = "roles",column = "id",javaType = java.util.List.class,many = @Many(select = "com.wx.ssm_dao.IRoleDao.findRoleByUserId"))
    })
    public UserInfo findById(String iid) throws Exception;


    @Select("select * from role where id not in(select roleId from users_role where userId=#{userId})")
    List<Role> findOtherRole(String userId) throws Exception;


    @Insert("insert into users_role(userId,roleId) values(#{userId},#{roleId})")
    public void addRoleToUser(@Param("userId") String userId, @Param("roleId") String roleId) throws Exception;
}
