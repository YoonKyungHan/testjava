-- 회원 테이블 (po 고유키)
CREATE TABLE MEMBER (
    PO          VARCHAR(20)  NOT NULL,  -- pp2026-001
    MEMBER_ID   VARCHAR(50),
    MEMBER_NAME VARCHAR(100) NOT NULL,
    EMAIL       VARCHAR(200),
    PHONE       VARCHAR(20),
    AGE         VARCHAR(10),
    GENDER      VARCHAR(1),
    ADDR        VARCHAR(300),
    DEPT_CD     VARCHAR(20),
    USE_YN      VARCHAR(1),
    JOIN_DT     DATE,
    PRIMARY KEY (PO)
);
