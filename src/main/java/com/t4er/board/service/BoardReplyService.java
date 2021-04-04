package com.t4er.board.service;

import java.util.List;

import com.t4er.board.model.BoardReplyVO;

public interface BoardReplyService {

	public int register(BoardReplyVO vo);

	public BoardReplyVO get(Integer rnum);

	public int modify(BoardReplyVO vo);

	public int remove(Integer rnum);

	public List<BoardReplyVO> getList(Integer bnum);
}
