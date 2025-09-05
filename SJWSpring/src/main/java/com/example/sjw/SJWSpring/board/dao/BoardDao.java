package com.example.sjw.SJWSpring.board.dao;

import com.example.sjw.SJWSpring.board.bean.BoardBean;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardDao {

    int insertBoard(BoardBean bean);

    int updateBoard(BoardBean bean);

}
