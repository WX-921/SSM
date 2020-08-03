package com.wx.ssm_service.impl;

import com.wx.ssm_dao.IPermissionDao;
import com.wx.ssm_domain.Permission;
import com.wx.ssm_service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class PermissionServiceImpl implements IPermissionService {

    @Autowired
    private IPermissionDao permissionDao;

    @Override
    public List<Permission> findAll() throws Exception {
        return permissionDao.findAll();
    }

    @Override
    public void save(Permission permission) throws Exception {
        //生成随机数
        String s = UUID.randomUUID().toString().replace("-", "").toUpperCase();
        permission.setId(s);
        permissionDao.save(permission);
    }
}
