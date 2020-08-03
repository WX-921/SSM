package com.wx.ssm_dao;

import com.wx.ssm_domain.Syslog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ISyslogDao {

    @Insert("insert into syslog(visitTime,username,ip,url,executionTime,method) values(#{visitTime},#{username},#{ip},#{url},#{executionTime},#{method})")
    public void save(Syslog syslog) throws Exception;

    @Select("select * from syslog")
    List<Syslog> findAll() throws Exception;
}
