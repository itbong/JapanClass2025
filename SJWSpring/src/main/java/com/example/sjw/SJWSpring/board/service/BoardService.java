package com.example.sjw.SJWSpring.board.service;

import com.example.sjw.SJWSpring.board.bean.BoardBean;
import com.example.sjw.SJWSpring.board.dao.BoardDao;
import com.example.sjw.SJWSpring.member.dao.MemberDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor //@AutoWired 를 생략할 수 있다.
public class BoardService {

    private final MemberDao memberDao;
    private final BoardDao boardDao;

    public Map<String, Object> writeBoard(BoardBean bean) {
        Map<String, Object> data = new HashMap<>();
        data.put("result", "fail");
        data.put("resultMsg", "게시글 작성에 실패 하였습니다.");

        try {
            int row = boardDao.insertBoard(bean);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

}
