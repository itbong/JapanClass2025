package com.example.sjw.SJWSpring.controller;

import com.example.sjw.SJWSpring.member.bean.MemberBean;
import com.example.sjw.SJWSpring.member.dao.MemberDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class HelloController {

    private Map<String, String> users = new HashMap<>();

    @Autowired
    private MemberDao memberDao;

    //1명의 유져 정보를 취득하는 API 이다.
    @GetMapping("/getUsers")
    private Map<String, Object> getUsers() {

        Map<String, Object> data = new HashMap<>();
        data.put("result", "fail");
        data.put("resultMsg", "처리가 실패 하였습니다.");

        List<MemberBean> list =  memberDao.selectMemberList(null);
        data.put("memberList", list);

        return data;
    };


    //유져생성
    @PostMapping("/addUser")
    private Map<String, Object> addUser(String name) {

        Map<String, Object> data = new HashMap<>();
        data.put("result", "fail");
        data.put("resultMsg", "처리가 실패 하였습니다.");

        System.out.println("name: " + name);

        if( !StringUtils.isEmpty(name) ) {
        //if( name != null && !name.equals("") ) {
            //key ==> 나노시간, 키값이 겹치지 않게 하기 위해
            //value ==> 이름, 클라언트로 부터 받은 이름
            users.put(System.nanoTime()+"", name);
            data.put("result", "ok");
            data.put("resultMsg", "회원가입에 성공 하였습니다.");
        }

        data.put("users", users);

        return data;
    }

    //유져수정
    @PostMapping("/updateUser")
    private Map<String, String> updateUser(String no, String name) {

        //no 값이 빈문자열이나 null 이 아닐때 if 문 실행.
        if( !StringUtils.isEmpty(no) ) {
            //no 값으로 해당 유져를 찾는다.
            String nameV = users.get(no);
            if( nameV == null ) {
                users.put("msg", "해당 번호의 유져가 존재하지 않습니다.");
            } else {
                //값이 있을경우는 name 값으로 덮어써준다.(수정해준다)
                users.put(no, name);
            }
        }

        return users;
    }


    //유져삭제
    @PostMapping("/delUser")
    private Map<String, Object> delUser(String no) {

        Map<String, Object> data = new HashMap<>();
        data.put("result", "fail");
        data.put("resultMsg", "처리가 실패 하였습니다.");

        //no 값이 빈문자열이나 null 이 아닐때 if 문 실행.
        if( !StringUtils.isEmpty(no) ) {
            //no 값으로 해당 유져를 찾는다.
            String nameV = users.get(no);

            if( nameV == null ) {
                data.put("resultMsg", "해당 번호의 유져가 존재하지 않아 삭제되지 않았습니다.");
            } else {
                //값이 있을경우는 no 값으로 삭제한다.
                users.remove(no);
                data.put("result", "ok");
                data.put("resultMsg", no + " 유져가 정상적으로 삭제 되었습니다.");
            }
        }
        data.put("users", users);

        return data;
    }


}
