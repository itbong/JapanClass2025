package com.example.sjw.SJWSpring.member.dao;

import com.example.sjw.SJWSpring.member.bean.MemberBean;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface MemberDao {

     List<MemberBean> selectMemberList(MemberBean memberBean);

     int insertUser(MemberBean memberBean);

    int updateUser(MemberBean memberBean);

    int deleteUser(MemberBean memberBean);

}
