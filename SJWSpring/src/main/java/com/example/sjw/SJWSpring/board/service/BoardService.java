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
            if(row >= 1) {
                data.put("result", "ok");
                data.put("resultMsg", "게시글 작성에 성공 하였습니다.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public Map<String, Object> updateBoard(BoardBean bean) {
        Map<String, Object> data = new HashMap<>();
        data.put("result", "fail");
        data.put("resultMsg", "게시글 수정에 실패 하였습니다.");

        try {
            //userId ==> 토큰에서 빼와야 한다.
            int row = boardDao.updateBoard(bean);
            //실패
            if(row == 0) {
                data.put("resultMsg", "게시글의 소유자가 아니어서 게시글 수정에 실패 하였습니다.");
                return data;
            }
            //성공
            else if(row >= 1) {
                data.put("result", "ok");
                data.put("resultMsg", "게시글 수정에 성공 하였습니다.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

}
