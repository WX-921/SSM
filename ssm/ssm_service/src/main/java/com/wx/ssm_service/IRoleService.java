package com.wx.ssm_service;

import com.wx.ssm_domain.Permission;
import com.wx.ssm_domain.Role;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IRoleService {

    public List<Role> findAll(int page,int size) throws Exception;

    public void save(Role role) throws Exception;

    public Role findById(String roleId) throws Exception;

    List<Permission> findOtherPermissions(String roleId) throws Exception;

    void addPermissionToRole(String roleId,String[] permissionIds) throws Exception;
}
