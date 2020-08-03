package com.wx.ssm_service;

import com.wx.ssm_domain.Role;
import com.wx.ssm_domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService {

    public List<UserInfo> findAll() throws Exception;

    void save(UserInfo user) throws Exception;

    public UserInfo findById(String d) throws Exception;

    List<Role> findOtherRole(String userId) throws Exception;

    void addRoleToUser(String userId, String[] roleIds) throws Exception;
}
