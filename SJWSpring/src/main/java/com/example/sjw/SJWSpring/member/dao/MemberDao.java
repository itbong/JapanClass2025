package com.example.sjw.SJWSpring.member.dao;

import com.example.sjw.SJWSpring.member.bean.MemberBean;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface MemberDao {

    public List<MemberBean> selectMemberList(MemberBean memberBean);

    public int insertUser(MemberBean memberBean);

}
