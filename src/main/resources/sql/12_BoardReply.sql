create table board_reply (

rnum number(10),
bnum number(10) not null,
reply varchar2(1000) not null,
idx number(10) not null,
replyDate date default sysdate,
updateDate date default sysdate

);

create sequence seq_reply;
alter table board_reply add constraint pk_reply primary key (rnum);

ALTER TABLE board_reply   ADD   CONSTRAINT FK_reply_board
      FOREIGN KEY (bnum) REFERENCES board (bnum);

ALTER TABLE board_reply   ADD   CONSTRAINT FK_reply_board_IDX
      FOREIGN KEY (IDX)   REFERENCES USER (IDX);