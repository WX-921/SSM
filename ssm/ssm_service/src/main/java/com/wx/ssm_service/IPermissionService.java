package com.wx.ssm_service;

import com.wx.ssm_domain.Permission;

import java.util.List;

public interface IPermissionService {

    public List<Permission> findAll() throws Exception;

    public void save(Permission permission) throws Exception;
}
