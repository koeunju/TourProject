package com.t4er.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.t4er.board.mapper.BoardReplyMapper;
import com.t4er.board.model.BoardReplyVO;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;


@Log4j
@AllArgsConstructor
@Service
public class BoardReplyServiceImpl implements BoardReplyService {
	
	@Autowired
	private BoardReplyMapper mapper;
	
	@Override
	public int register(BoardReplyVO vo) {
		log.info("register...." + vo);
		return mapper.insert(vo);
	}
	
	@Override
	public BoardReplyVO get(Integer rnum) {
		log.info("get...." + rnum);
		return mapper.read(rnum);
	}
	
	@Override
	public int modify(BoardReplyVO vo) {
		log.info("modify...." + vo);
		return mapper.update(vo);
	}
	
	@Override
	public int remove(Integer rnum) {
		log.info("remove...." + rnum);
		return mapper.delete(rnum);
	}
	
	@Override
	public List<BoardReplyVO> getList(Integer rnum) {
		log.info("get Reply list" + rnum);
		return mapper.getList(rnum);
	}

}
