package com.t4er.board.mapper;

<<<<<<< HEAD
import com.t4er.board.model.BoardPagingVO;
import com.t4er.board.model.BoardVO;

=======
>>>>>>> refs/remotes/origin/master
import java.util.List;
import java.util.Map;

import com.t4er.board.model.BoardPagingVO;
import com.t4er.board.model.BoardVO;

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
<<<<<<< HEAD
=======
    // 댓글 리스트
   
>>>>>>> refs/remotes/origin/master

    // 내가 쓴 글 조회
    List<BoardVO> selMyBoard_old(Integer idx);

    int myTotalCountBoard(BoardPagingVO paging);

    List<BoardVO> selMyBoard(BoardPagingVO paging);
}
