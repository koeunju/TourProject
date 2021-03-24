package com.t4er.board.mapper;

import com.t4er.board.model.BoardReplyVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BoardReplyMapper {

    public int insert(BoardReplyVO vo);

    public BoardReplyVO read(Integer bnum);

    public int delete(Integer rnum);

    public int update(BoardReplyVO reply);

    public List<BoardReplyVO> getList(
            /* @Param("cri") Criteria cri, */
            @Param("bnum") Integer bnum
    );
}
