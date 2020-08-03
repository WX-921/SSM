package com.wx.ssm_dao;

import com.wx.ssm_domain.Member;

import org.apache.ibatis.annotations.Select;

public interface IMemberDao {

    /**
     * 根据 id查询会员
     * @param id
     * @return
     * @throws Exception
     */
    @Select("select * from member where id = #{id}")
    public Member findById(String id) throws Exception;
}
