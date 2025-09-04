package com.example.sjw.SJWSpring.controller;

import com.example.sjw.SJWSpring.board.bean.BoardBean;
import com.example.sjw.SJWSpring.board.service.BoardService;
import com.example.sjw.SJWSpring.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class BoardController {

    @Autowired
    private BoardService boardService;

    @Autowired
    private MemberService memberService;

    //회원가입
    @PostMapping("/board/writeBoard")
    private Map<String, Object> writeBoard(BoardBean bean) {
        return boardService.writeBoard(bean);
    }

}
