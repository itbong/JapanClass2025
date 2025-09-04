package com.example.sjw.SJWSpring.board.bean;

import lombok.Data;

@Data
public class BoardBean {

    private String boardNo;    // 게시물 고유번호
    private String boardTitle; // 게시글 제목
    private String userId;     // User 테이블의 id 컬럼값과 연결
    private String contents;   // 게시글 내용
    private String regDt;      // 등록날짜
    private String updDt;      // 마지막 수정일

}
