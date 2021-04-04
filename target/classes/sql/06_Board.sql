ALTER TABLE BOARD
   DROP
      CONSTRAINT FK_MEMBER_TO_BOARD
      CASCADE;

ALTER TABLE BOARD
   DROP
      CONSTRAINT FK_BOARD_CATEGORY_TO_BOARD
      CASCADE;

ALTER TABLE BOARD
   DROP
      PRIMARY KEY
      CASCADE
      KEEP INDEX;

DROP INDEX PK_BOARD;

/* 게시판 */
DROP TABLE BOARD
   CASCADE CONSTRAINTS;

/* 게시판 */
CREATE TABLE BOARD (
    BNUM NUMBER(10) NOT NULL, /* 게시판번호 */
    BTITLE VARCHAR2(200) NOT NULL, /* 글제목 */
    BCONTENT VARCHAR2(3000) NOT NULL, /* 글내용 */
    BINQUIRY NUMBER(10) default 0, /* 조회수 */
    BDATE DATE DEFAULT SYSDATE, /* 작성일 */
    FILENAME VARCHAR2(500),--첨부파일명[년월일시분초_a.jpg]
    ORIGINFILENAME VARCHAR2(500), --원본파일명[a.jpg]
    FILESIZE NUMBER(10), --첨부파일 크기
    REFER NUMBER(10),--글 그룹번호
    LEV NUMBER(10), --답변 레벨
    SUNBUN NUMBER(10), --같은 글 그룹내의 순서 정렬
    BRECOMMEND NUMBER(10), /* 추천 */
    IDX NUMBER(10), /* 회원번호 */
    CG_NUM NUMBER(3) NOT NULL /* 게시판카테고리번호 */
);

CREATE UNIQUE INDEX PK_BOARD
   ON BOARD (
      BNUM ASC
   );

ALTER TABLE BOARD
   ADD
      CONSTRAINT PK_BOARD
      PRIMARY KEY (
         BNUM
      );

ALTER TABLE BOARD
   ADD
      CONSTRAINT FK_MEMBER_TO_BOARD
      FOREIGN KEY (
         IDX
      )
      REFERENCES MEMBER (
         IDX
      );

ALTER TABLE BOARD
   ADD
      CONSTRAINT FK_BOARD_CATEGORY_TO_BOARD
      FOREIGN KEY (
         CG_NUM
      )
      REFERENCES BOARD_CATEGORY (
         CG_NUM
      );

drop sequence board_seq;
create sequence board_seq nocache;