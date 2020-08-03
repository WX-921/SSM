package com.wx.ssm_service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wx.ssm_dao.IRoleDao;
import com.wx.ssm_domain.Permission;
import com.wx.ssm_domain.Role;
import com.wx.ssm_service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private IRoleDao roleDao;


    @Override
    public List<Role> findAll(int page,int size) throws Exception {
        PageHelper.startPage(page,size);
        return roleDao.findAll();
    }

    @Override
    public void save(Role role) throws Exception {

        //获取32位随机数
        String replace = UUID.randomUUID().toString().replace("-","").toUpperCase();
        role.setId(replace);
        roleDao.save(role);
    }

    @Override
    public Role findById(String roleId) throws Exception {
        return roleDao.findById(roleId);
    }

    @Override
    public List<Permission> findOtherPermissions(String roleId) throws Exception {
        return roleDao.findOtherPermissions(roleId);
    }

    @Override
    public void addPermissionToRole(String roleId, String[] permissionIds) throws Exception {
        for (String permissionId : permissionIds){
            roleDao.addPermissionToRole(roleId,permissionId);
        }
    }
}
