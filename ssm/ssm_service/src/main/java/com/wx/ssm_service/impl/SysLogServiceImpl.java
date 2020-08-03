package com.wx.ssm_service.impl;

import com.wx.ssm_dao.ISyslogDao;
import com.wx.ssm_domain.Syslog;
import com.wx.ssm_service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SysLogServiceImpl implements ISysLogService {

    @Autowired
    private ISyslogDao syslogDao;

    @Override
    public void save(Syslog syslog) throws Exception {
        syslogDao.save(syslog);
    }

    @Override
    public List<Syslog> findAll() throws Exception {
        return syslogDao.findAll();
    }
}
