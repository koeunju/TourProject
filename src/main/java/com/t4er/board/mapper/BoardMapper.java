package com.t4er.board.mapper;

import com.t4er.board.model.BoardPagingVO;
import com.t4er.board.model.BoardVO;

import java.util.List;
import java.util.Map;

public interface BoardMapper {

    int insertBoard(BoardVO board);

    int insertBoard2(BoardVO board);

    List<BoardVO> selectBoardAll(Map<String, Integer> map);

    List<BoardVO> selectBoardAllPaging(BoardPagingVO paging);

    List<BoardVO> selectBoardAllPaging2(BoardPagingVO paging);

    //int getTotalCount();

    int updateReadnum(Integer bnum);

    BoardVO selectBoardBybnum(Integer bnum);

    int deleteBoard(Integer bnum);

    int updateBoard(BoardVO board);

    BoardVO selectReferLevSunbun(Integer bnum);

    int updateSunbun(BoardVO parent);

    int reInsertBoard(BoardVO board);

    int getTotalCount(BoardPagingVO paging);

    // 내가 쓴 글 조회
    List<BoardVO> selMyBoard_old(Integer idx);

    int myTotalCountBoard(BoardPagingVO paging);

    List<BoardVO> selMyBoard(BoardPagingVO paging);
}
