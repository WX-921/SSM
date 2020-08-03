package com.wx.ssm_service;

import com.wx.ssm_domain.Syslog;

import java.util.List;

public interface ISysLogService {

    public void save(Syslog syslog) throws Exception;

    List<Syslog> findAll() throws Exception;
}
