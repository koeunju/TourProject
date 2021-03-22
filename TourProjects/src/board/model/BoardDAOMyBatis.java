package board.model;

import java.util.*;

import common.model.DAOMyBatisBase;

public class BoardDAOMyBatis extends DAOMyBatisBase {

    private final String NS = "common.mapper.BoardMapper";


    public int getBoardTotalCount() { // 글 전체수(필요할까)
        try {
            ses = this.getSessionFactory().openSession();
            int cnt = ses.selectOne(NS + ".totalCount");
            return cnt;
        } finally {
            close();
        }
    }// ---------------------------------------------

    public BoardVO getBoard(String bnum) {
        try {
            ses = getSessionFactory().openSession();
            BoardVO vo = ses.selectOne(NS + ".getBoard", bnum);
            return vo;
        } finally {
            close();
        }
    }// ----------------------------------

    public List<BoardVO> getBoardList(int start, int end) {
        try {
            Map<String, Integer> map = new HashMap<>();
            map.put("start", start);
            map.put("end", end);

            ses = this.getSessionFactory().openSession();
            List<BoardVO> arr = ses.selectList(NS + ".getBoardList", map);
            return arr;
        } finally {
            close();
        }
    }// ----------------------------------------------

    public List<BoardVO> getBoardList2(int start, int end) { //고객센터
        try {
            Map<String, Integer> map = new HashMap<>();
            map.put("start", start);
            map.put("end", end);

            ses = this.getSessionFactory().openSession();
            List<BoardVO> arr = ses.selectList(NS + ".getBoardList2", map);
            return arr;
        } finally {
            close();
        }
    }// ----------------------------------------------

    public int insertBoard(BoardVO board) { // 글 입력
        try {
            ses = this.getSessionFactory().openSession(true);
            int n = ses.insert(NS + ".insertBoard", board);
            return n;
        } finally {
            close();
        }
    }// ----------------------------------

    public boolean updateReadnum(String bnum) { // 조회수 증가
        try {
            ses = getSessionFactory().openSession(true);
            int n = ses.update(NS + ".updateReadnum", bnum);
            return (n > 0) ? true : false;
        } finally {
            close();
        }
    }// ----------------------------------

    public int deleteBoard(String bnum) { //글 삭제
        try {
            ses = this.getSessionFactory().openSession(true);
            int n = ses.delete(NS + ".deleteBoard", bnum);
            return n;
        } finally {
            close();
        }
    }// ----------------------------------

    public int editBoard(BoardVO board) { //글 수정
        try {
            ses = this.getSessionFactory().openSession(true);
            int n = ses.delete(NS + ".editBoard", board);
            return n;
        } finally {
            close();
        }
    }// ----------------------------------

    public int getFindTotalCount(String findType, String findKeyword) { //검색글 카운트
        try {
            ses = this.getSessionFactory().openSession();
            Map<String, String> map = new HashMap<>();
            map.put("findType", findType);
            map.put("findKeyword", findKeyword);
            return ses.selectOne(NS + ".getFindTotalCount", map);
        } finally {
            close();
        }
    }

    public List<BoardVO> getFindList(int start, int end, String findType, String findKeyword) { // 검색글 찾기
        try {
            ses = this.getSessionFactory().openSession();
            Map<String, Object> map = new HashMap<>();
            map.put("findType", findType);
            map.put("findKeyword", findKeyword);
            map.put("start", start);
            map.put("end", end);
            return ses.selectList(NS + ".getFindList", map);
        } finally {
            close();
        }
    }

    public List<CategoryVO> getCategory() {
        try {
            ses = this.getSessionFactory().openSession();
            List<CategoryVO> cList = ses.selectList(NS + ".getCategory");
            return cList;
        } finally {
            close();
        }
    }//----------------------------------

    public List<CategoryVO> selectByCategory(String Code) {
        try {
            ses = this.getSessionFactory().openSession();
            List<CategoryVO> bList
                    = ses.selectList(NS + ".selectByCategory", Code);
            return bList;
        } finally {
            close();
        }
    }//--------------------------------
    
    //마이페이지 글 확인
    
	public List<BoardVO> selectMyWrite(int idx) {
		try {
			ses=this.getSessionFactory().openSession();
			return ses.selectList(NS+".selectMyWrite", idx);
		} finally {
			close();
		}
	}
}
