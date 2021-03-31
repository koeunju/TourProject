package com.t4er.board.service;

import java.util.List;
import java.util.Map;

import com.t4er.board.model.BoardReplyVO;
import org.springframework.stereotype.Repository;

import com.t4er.board.model.BoardPagingVO;
import com.t4er.board.model.BoardVO;

@Repository
public interface BoardService {

    int insertBoard(BoardVO board);

    int insertBoard2(BoardVO board);

    List<BoardVO> selectBoardAll(Map<String, Integer> map);// 게시목록 가져오기

    List<BoardVO> selectBoardAllPaging(BoardPagingVO paging);

    List<BoardVO> selectBoardAllPaging2(BoardPagingVO paging);

    List<BoardVO> findBoard(BoardPagingVO paging);// 검색목록 가져오기

    //int getTotalCount();// 총 게시글 수 가져오기

    int getTotalCount(BoardPagingVO paging);// 검색한 총 게시글 수 가져오기

    BoardVO selectBoardBybnum(Integer bnum);// 글번호에 해당하는 글 가져오기

    int updateReadnum(Integer bnum);// 조회수 증가하기

    int deleteBoard(Integer bnum);// 글삭제

    int updateBoard(BoardVO board);// 글수정

    // 답변형(계층형) 게시판에서 답변글 달기
    int reInsertBoard(BoardVO board); // [답변형]

    BoardVO selectRefLevSunbun(Integer bnum);// [답변형]

    int updateSunbun(BoardVO parent);// [답변형]

    // 댓글 리스트

    List<BoardReplyVO> getReplyList(Integer bnum) throws Exception;

    int saveReply(BoardReplyVO replyVO) throws Exception;

    int updateReply(BoardReplyVO replyVO) throws Exception;

    int deleteReply(Integer rnum) throws Exception;
}
