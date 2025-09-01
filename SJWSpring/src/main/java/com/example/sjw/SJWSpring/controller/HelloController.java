package com.example.sjw.SJWSpring.controller;

import com.example.sjw.SJWSpring.member.bean.MemberBean;
import com.example.sjw.SJWSpring.member.dao.MemberDao;
import com.example.sjw.SJWSpring.member.service.MemberService;
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

    @Autowired
    private MemberService memberService;

    //1명의 유져 정보를 취득하는 API 이다.
    @GetMapping("/getUsers")
    private Map<String, Object> getUsers() {

        Map<String, Object> data = new HashMap<>();
        data.put("result", "fail");
        data.put("resultMsg", "처리가 실패 하였습니다.");

        List<MemberBean> list = memberService.getUserList();
        data.put("memberList", list);

        return data;
    };

    //회원가입
    @PostMapping("/addUser")
    private Map<String, Object> addUser(MemberBean bean) {
        Map<String, Object> data = new HashMap<>();
        data.put("result", "fail");
        data.put("resultMsg", "회원가입에 실패 하였습니다.");

        try {
            boolean isSucc = memberService.insertUser(bean);
            if(isSucc) {
                data.put("result", "ok");
                data.put("resultMsg", "회원가입에 성공 하였습니다.");
            }
        } catch(Exception e) {
            e.printStackTrace();
            data.put("resultMsg", "회원가입에 실패 하였습니다. ==> " + e.getMessage());
        }

        return data;
    }

    //회원수정
    @PostMapping("/updateUser")
    private Map<String, Object> updateUser() {
        Map<String, Object> data = new HashMap<>();

        return data;
    }






}
