package com.t4er.board.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.t4er.board.mapper.BoardMapper;
import com.t4er.board.model.BoardPagingVO;
import com.t4er.board.model.BoardVO;

import lombok.extern.log4j.Log4j;

@Service("boardService")
@Log4j
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardMapper boardMapper; // 영속성계층

    @Override
    public int insertBoard(BoardVO board) {
        log.info("boardMapper===" + boardMapper);
        return boardMapper.insertBoard(board);
    }

    @Override
    public List<BoardVO> selectBoardAll(Map<String, Integer> map) {

        return this.boardMapper.selectBoardAll(map);
    }

    @Override
    public List<BoardVO> selectBoardAllPaging(BoardPagingVO paging) {
        return this.boardMapper.selectBoardAllPaging(paging);
    }

    @Override
    public List<BoardVO> findBoard(BoardPagingVO paging) {
        return null;
    }

    @Override
    public int getTotalCount() {
        return this.boardMapper.getTotalCount();
    }

    @Override
    public int getTotalCount(BoardPagingVO paging) {
        return this.boardMapper.getTotalCountPaging(paging);
    }

    @Override
    public BoardVO selectBoardBybnum(Integer bnum) {
        return this.boardMapper.selectBoardBybnum(bnum);
    }

    @Override
    public int updateReadnum(Integer bnum) {
        return this.boardMapper.updateReadnum(bnum);
    }

    @Override
    public int deleteBoard(Integer bnum) {

        return this.boardMapper.deleteBoard(bnum);
    }

    @Override
    public int updateBoard(BoardVO board) {
        return this.boardMapper.updateBoard(board);
    }

    @Override
    public int reInsertBoard(BoardVO board) {
        BoardVO parent = this.boardMapper.selectReferLevSunbun(board.getBnum());

        int n1 = this.boardMapper.updateSunbun(parent);

        board.setRefer(parent.getRefer());// 부모글과 같은 글그룹 번호로 설정
        board.setLev(parent.getLev() + 1);// 부모lev+1
        board.setSunbun(parent.getSunbun() + 1);// 부모sunbun+1
        int n = this.boardMapper.reInsertBoard(board);
        return n;
    }


    @Override
    public BoardVO selectRefLevSunbun(Integer bnum) {

        return null;
    }

    @Override
    public int updateSunbun(BoardVO parent) {

        return 0;
    }

}