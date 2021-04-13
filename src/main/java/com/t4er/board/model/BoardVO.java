package com.t4er.board.model;

import lombok.Data;

import java.sql.Date;

@Data
public class BoardVO {

    private String mode; // 글쓰기:insert, 글수정:edit, 답변쓰기:rewrite

    private Integer bnum; //글번호
    private String btitle; //글제목
    private String bcontent; //글내용

    private Date bdate; //작성일시

    private String binquiry; //조회수
    private String filename; //파일명
    private String originFilename; // 원본 파일명=>업로드파일명.확장자
    private long filesize; // 파일크기

    private Integer brecommend; //추천
    private Integer idx; //회원번호
    private String nick; //닉네임
    private String cg_num; //카테고리번호

    private int refer; // 동일한 글 그룹번호
    private int lev; // 답변 레벨
    private int sunbun; // 같은 글그룹 내의 순서

    private int newImg; // new이미지
    private int isFile; // 첨부파일 유무(있으면 1, 없으면 0)
    
}
