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

    //게시글 작성
    @PostMapping("/board/writeBoard")
    private Map<String, Object> writeBoard(BoardBean bean) {
        return boardService.writeBoard(bean);
    }


    //게시글 수정
    @PostMapping("/board/updateBoard")
    private Map<String, Object> updateBoard(BoardBean bean) {
        return boardService.updateBoard(bean);
    }


}
