package com.example.sjw.SJWSpring.member.service;

import com.example.sjw.SJWSpring.member.bean.MemberBean;
import com.example.sjw.SJWSpring.member.dao.MemberDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MemberService {

    @Autowired
    private MemberDao memberDao;

    //private RuleDao ruleDao;

    /**
     * 회원가입된 유저 리스트를 취득한다.
     */
    public List<MemberBean> getUserList(MemberBean bean) {
        return memberDao.selectMemberList(bean);
    }

    /**
     * 회원 가입
     * @return true: 회원가입 성공, false: 회원가입 실패
     */
    public boolean insertUser(MemberBean bean) {
        int row = memberDao.insertUser(bean);
        if( row >= 1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 회원수정
     */
    public Map<String, Object> updateUser(MemberBean bean) {
        Map<String, Object> data = new HashMap<>();

        if( StringUtils.isEmpty(bean.getId()) ) {
            data.put("result", "fail");
            data.put("resultMsg", "id 값이 존재하지 않아 업데이트 할 수 없습니다.");
            return data;
        }

        int row = 0;
        try {
            row = memberDao.updateUser(bean);
            if( row >= 1 ) {
                data.put("result", "ok");
                data.put("resultMsg", "회원수정에 성공 하였습니다.");
            } else {
                data.put("result", "fail");
                data.put("resultMsg", "회원수정에 실패 하였습니다. 원인: 해당되는 id 값이 존재하지 않습니다.");
            }
        } catch (DuplicateKeyException dke) {
            dke.printStackTrace();
            data.put("result", "fail");
            data.put("resultMsg", "해당 username 이 이미 존재 합니다.");
        } catch (DataIntegrityViolationException dve) {
            dve.printStackTrace();
            data.put("result", "fail");
            data.put("resultMsg", "필수입력값이 누락되었습니다.");
        } catch(Exception e) {
            e.printStackTrace();
            data.put("result", "fail");
            data.put("resultMsg", "시스템 에러가 발생 했습니다. 관리자에게 문의해 주세요.");
        }

        return data;
    }

    /**
     * 회원삭제
     */
    public Map<String, Object> deleteUser(MemberBean bean) {
        Map<String, Object> data = new HashMap<>();

        if( StringUtils.isEmpty(bean.getId()) ) {
            data.put("result", "fail");
            data.put("resultMsg", "id 값이 존재하지 않아 삭제 할 수 없습니다.");
            return data;
        }

        int row = memberDao.deleteUser(bean);
        if( row >= 1 ) {
            data.put("result", "ok");
            data.put("resultMsg", "회원삭제에 성공 하였습니다.");
        } else {
            data.put("result", "fail");
            data.put("resultMsg", "회원삭제에 실패 하였습니다. 원인: 해당되는 id 값이 존재하지 않습니다.");
        }

        return data;
    }

    //회원의 username, password 로 조회
    public MemberBean selectLoginMember(MemberBean bean) {
        return memberDao.selectLoginMember(bean);
    }

};
