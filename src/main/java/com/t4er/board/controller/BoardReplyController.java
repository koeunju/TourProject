package com.t4er.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.t4er.board.model.BoardReplyVO;
import com.t4er.board.service.BoardReplyService;

import lombok.extern.log4j.Log4j;

@RequestMapping("/replies")
@RestController
@Log4j
public class BoardReplyController {

    @Autowired
    private BoardReplyService service;

    @PostMapping(value="/new",
            consumes = "application/json",
            produces = {MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<String> create(@RequestBody BoardReplyVO vo) {
        log.info("BoardReplyVO: "+ vo);
        int insertCount = service.register(vo);
        log.info("Reply INSERT COUNT : " + insertCount);
        return insertCount==1
                ? new ResponseEntity<>("success", HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value="/pages/{bnum}/{page}",
            produces = {
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_UTF8_VALUE })
    public ResponseEntity<List<BoardReplyVO>> getList(
            @PathVariable("page") int page,
            @PathVariable("bnum") int bnum) {

        log.info("getList.........");

        return new ResponseEntity<>(service.getList(bnum), HttpStatus.OK);

    }
    //조회
    @GetMapping(value="/{rnum}",
            produces = {
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_UTF8_VALUE })
    public ResponseEntity<BoardReplyVO> get(@PathVariable("rnum") int rnum ) {
        log.info("get: " + rnum);
        return new ResponseEntity<>(service.get(rnum), HttpStatus.OK);
    }
    //삭제
    @DeleteMapping(value= "/{rnum}", produces= {MediaType.TEXT_PLAIN_VALUE })
    public ResponseEntity<String> remove(@PathVariable("rnum") int rnum) {
        log.info("remove : : " + rnum);
        return service.remove(rnum)==1
                ? new ResponseEntity<>("success", HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //수정
    @RequestMapping(method = {RequestMethod.PUT, RequestMethod.PATCH },
            value = "/{rnum}",
            consumes = "application/json",
            produces = { MediaType.TEXT_PLAIN_VALUE })
    public ResponseEntity<String> modify(
            @RequestBody BoardReplyVO vo,
            @PathVariable("rnum") int rnum) {
        vo.setRnum(rnum);

        log.info("rnum : " + rnum);

        log.info("modify : : " + vo);
        return service.modify(vo)==1
                ? new ResponseEntity<>("success", HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
