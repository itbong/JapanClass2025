package com.example.sjw.SJWSpring.controller;

import com.example.sjw.SJWSpring.member.bean.MemberBean;
import com.example.sjw.SJWSpring.member.dao.MemberDao;
import com.example.sjw.SJWSpring.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    private Map<String, Object> getUsers(String page) {
        Map<String, Object> data = new HashMap<>();
        data.put("result", "fail");
        data.put("resultMsg", "처리가 실패 하였습니다.");

        int iPage = 0;
        try {
            iPage = Integer.parseInt(page);
        } catch(Exception e){
            e.printStackTrace();
            data.put("resultMsg", "page 값이 숫자가 아닙니다. 숫자로만 입력해 주세요.");
            return data;
        }

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
    public Map<String, Object> updateUser(MemberBean bean) {

        //Service 에 접근 했는데, 서비스에서 에러가 났다.
        //Map<String, Object> data = memberService.updateUser(bean);

        return memberService.updateUser(bean);
    }




}
