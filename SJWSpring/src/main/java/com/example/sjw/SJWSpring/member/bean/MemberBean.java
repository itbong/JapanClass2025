package com.example.sjw.SJWSpring.member.bean;

import lombok.Builder;
import lombok.Data;

@Data
public class MemberBean {

    private String id;
    private String username;
    private String password;
    private String email;
    private String roleId;

    //대외적으로 공개된 변수
    private String page = "1";

    //아래는 내부에서만 사용하는 변수
    //건너뛸 갯수 (ex: LIMIT 0, 10 ; -- 12개를 건너뛰고 1개 가져와라는 뜻)
    private int offset;

    public int getOffset() {
        if(page == null) page = "1";
        //공식
        return Integer.parseInt(page) - 1 * limit;
    }

    //가져올 갯수
    private int limit = 10;  //기본값 10



}
