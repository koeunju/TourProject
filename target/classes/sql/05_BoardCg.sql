ALTER TABLE BOARD_CATEGORY
	DROP
		PRIMARY KEY
		CASCADE
		KEEP INDEX;

DROP INDEX PK_BOARD_CATEGORY;

/* 게시판카테고리 */
DROP TABLE BOARD_CATEGORY
	CASCADE CONSTRAINTS;

/* 게시판카테고리 */
CREATE TABLE BOARD_CATEGORY (
	CG_NUM NUMBER(3) NOT NULL, /* 게시판카테고리번호 */
	CG_NAME VARCHAR2(20) /* 게시판카테고리이름 */
);

COMMENT ON TABLE BOARD_CATEGORY IS '게시판카테고리';

COMMENT ON COLUMN BOARD_CATEGORY.CG_NUM IS '게시판카테고리번호';

COMMENT ON COLUMN BOARD_CATEGORY.CG_NAME IS '게시판카테고리이름';

CREATE UNIQUE INDEX PK_BOARD_CATEGORY
	ON BOARD_CATEGORY (
		CG_NUM ASC
	);

ALTER TABLE BOARD_CATEGORY
	ADD
		CONSTRAINT PK_BOARD_CATEGORY
		PRIMARY KEY (
			CG_NUM
		);