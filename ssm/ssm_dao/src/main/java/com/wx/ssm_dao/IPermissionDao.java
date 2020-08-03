package com.wx.ssm_dao;

import com.wx.ssm_domain.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IPermissionDao {

    @Select("select * from permission where id in (select permissionId from role_permission where roleId=#{id})")
    public List<Permission> findByRoleId(String id) throws Exception;

    @Select("select * from permission")
    public List<Permission> findAll() throws Exception;

    @Insert("insert into permission(id,permissionName,url) values(#{id},#{permissionName},#{url})")
    public void save(Permission permission) throws Exception;
}
