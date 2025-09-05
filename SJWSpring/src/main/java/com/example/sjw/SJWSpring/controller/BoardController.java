package com.example.sjw.SJWSpring.controller;

import com.example.sjw.SJWSpring.board.bean.BoardBean;
import com.example.sjw.SJWSpring.board.service.BoardService;
import com.example.sjw.SJWSpring.member.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
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
    private Map<String, Object> updateBoard(HttpServletRequest request, BoardBean bean) {

        //토큰값을 빼서 userId 값을 취득하겠다.
        String userId = (String)request.getSession().getAttribute("userId");
        System.out.println("userId값: " + userId);

        //토큰에서 취득한 userId 값을 실어서 Mapper 까지 보낸다.
        bean.setUserId( userId );

        return boardService.updateBoard(bean);
    }


}
