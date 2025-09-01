package com.example.sjw.SJWSpring.member.service;

import com.example.sjw.SJWSpring.member.bean.MemberBean;
import com.example.sjw.SJWSpring.member.dao.MemberDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
public class MemberService {

    @Autowired
    private MemberDao memberDao;

    //private RuleDao ruleDao;

    /**
     * 회원가입된 유저 리스트를 취득한다.
     */
    public List<MemberBean> getUserList() {

        return memberDao.selectMemberList(null);
    }

}
