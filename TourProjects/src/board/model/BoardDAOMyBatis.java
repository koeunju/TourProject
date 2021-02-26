package board.model;

import java.util.*;

import common.model.DAOMyBatisBase;

public class BoardDAOMyBatis extends DAOMyBatisBase {

    private final String NS = "common.mapper.BoardMapper";

    public BoardVO getBoard(String bnum) {
        try {
            ses = getSessionFactory().openSession();
            BoardVO vo = ses.selectOne(NS + ".getBoard", bnum);
            return vo;
        } finally {
            close();
        }
    }//----------------------------------

    public int getBoardTotalCount() { // 글 전체수(필요할까)
        try {
            ses = this.getSessionFactory().openSession();
            int cnt = ses.selectOne(NS + ".totalCount");
            return cnt;
        } finally {
            close();
        }
    }//---------------------------------------------

    public List<BoardVO> getBoardList() { // 글 목록
        try {
            ses = this.getSessionFactory().openSession();
            List<BoardVO> arr = ses.selectList(NS + ".getBoardList");
            return arr;
        } finally {
            close();
        }
    }//----------------------------------------------

    public int insertBoard(BoardVO board) { // 글 입력
        try {
            ses = this.getSessionFactory().openSession(true);
            int n = ses.insert(NS + ".insertBoard", board);
            return n;
        } finally {
            close();
        }
    }//----------------------------------

    public boolean updateReadnum(String idx) { // 조회수 증가
        try {
            ses = getSessionFactory().openSession(true);
            int n = ses.update(NS + ".updateReadnum", idx);
            return (n > 0) ? true : false;
        } finally {
            close();
        }
    }//----------------------------------

    public int deleteBoard(String bnum) { // 글 삭제
        try {
            ses = this.getSessionFactory().openSession(true);
            int n = ses.delete(NS + ".deleteBoard", bnum);
            return n;
        } finally {
            close();
        }
    }// ----------------------------------

}
