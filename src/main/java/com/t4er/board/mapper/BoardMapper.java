package com.t4er.board.mapper;

import com.t4er.board.model.BoardPagingVO;
import com.t4er.board.model.BoardVO;

import java.util.List;
import java.util.Map;

public interface BoardMapper {

    int insertBoard(BoardVO board);

    List<BoardVO> selectBoardAll(Map<String, Integer> map);
    List<BoardVO> selectBoardAllPaging(BoardPagingVO paging);

    int getTotalCount();

    int updateReadnum(Integer bnum);

    BoardVO selectBoardBybnum(Integer bnum);

    int deleteBoard(Integer bnum);

    int updateBoard(BoardVO board);

    BoardVO selectReferLevSunbun(Integer bnum);

    int updateSunbun(BoardVO parent);

    int rewriteBoard(BoardVO board);

    int getTotalCountPaging(BoardPagingVO paging);
}
