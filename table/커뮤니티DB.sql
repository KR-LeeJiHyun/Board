CREATE SEQUENCE SEQ_POST_ID
START WITH 1
INCREMENT BY 1;

CREATE SEQUENCE SEQ_COMMENT_ID
START WITH 1
INCREMENT BY 1;

CREATE TABLE MEMBER(
    NAME NVARCHAR2(20) NOT NULL,
    ID VARCHAR2(20) PRIMARY KEY,
    PASSWORD VARCHAR2(100) NOT NULL,
    NICKNAME NVARCHAR2(20) NOT NULL UNIQUE,
    EMAIL VARCHAR2(320) NOT NULL,
    BIRTHDAY DATE NOT NULL,
    ROLE NUMBER DEFAULT 1
);

CREATE TABLE POST
(
    POST_ID NUMBER PRIMARY KEY,
    MEMBER_ID VARCHAR2(20) NOT NULL,
    WRITER NVARCHAR2(20) NOT NULL,
    TITLE NVARCHAR2(50) NOT NULL,
    CONTENT NCLOB NOT NULL,
    REGDATE DATE DEFAULT SYSDATE,
    "LIKE" NUMBER DEFAULT 0,
    UNLIKE NUMBER DEFAULT 0,
    HIT NUMBER DEFAULT 0,
    CATEGORY NVARCHAR2(20),
);

CREATE TABLE "COMMENT"
(
    COMMENT_ID NUMBER PRIMARY KEY,
    MEMBER_ID VARCHAR2(20) NOT NULL,
    WRITER NVARCHAR2(20) NOT NULL,
    CONTENT NCLOB NOT NULL,
    REGDATE DATE DEFAULT SYSDATE,
    POST_ID NUMBER NOT NULL,
    PARENT_ID NUMBER DEFAULT 0,
);

CREATE TABLE PERSISTENCE_LOGINS
(
    TOKEN VARCHAR2(32) PRIMARY KEY,
    MEMBER_ID VARCHAR2(20) NOT NULL,
    EXPIRATION_DATE TIMESTAMP NOT NULL
);

CREATE OR REPLACE PROCEDURE REMOVE_EXPIRED_LOGINS 
IS
BEGIN
    DELETE FROM PERSISTENCE_LOGINS WHERE EXPIRATION_DATE <= SYSTIMESTAMP;
END;

BEGIN
    DBMS_SCHEDULER.CREATE_JOB
    (
    JOB_NAME => 'REMOVE_EXPIRED_LOGINS_JOB',
    JOB_TYPE => 'STORED_PROCEDURE',
    JOB_ACTION => 'REMOVE_EXPIRED_LOGINS',
    REPEAT_INTERVAL => 'FREQ = MONTHLY; BYMONTHDAY = 1;', --1달에 1번
    COMMENTS => '매월 1일에 한번씩 유효하지 않은 로그인 유지 삭제'
    );
END;

BEGIN
    DBMS_SCHEDULER.ENABLE ('REMOVE_EXPIRED_LOGINS_JOB');
END;