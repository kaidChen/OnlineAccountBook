# DROP SCHEMA IF EXISTS account_book;
# CREATE SCHEMA account_book;
# USE account_book;

DROP TABLE IF EXISTS bill;
CREATE TABLE bill
(
    id      BIGINT UNSIGNED AUTO_INCREMENT,
    cost    DECIMAL(6, 2)   NOT NULL,
    time    TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    note    VARCHAR(32)     NULL,
    type_id BIGINT UNSIGNED NOT NULL,
    user_id BIGINT UNSIGNED NOT NULL,
    cycle   BIGINT UNSIGNED NOT NULL,
    source  INT UNSIGNED    NOT NULL DEFAULT 0 COMMENT '来源 现金支出:0，借款支出:1',
    primary key (id),
    INDEX type_id_idx (type_id ASC) VISIBLE,
    INDEX user_cycle_idx (user_id, cycle ASC) VISIBLE
);

DROP TABLE IF EXISTS type;
CREATE TABLE type
(
    id      BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    name    VARCHAR(16)     NOT NULL,
    kind    INT UNSIGNED    NOT NULL DEFAULT 0 COMMENT '消费：0，收入：1，存储：2，理财：3',
    user_id BIGINT UNSIGNED NOT NULL,
    status  INT UNSIGNED    NOT NULL DEFAULT 1 COMMENT '不可见：0，可见1',
    PRIMARY KEY (id),
    UNIQUE INDEX name_idx (user_id, name) VISIBLE
);

DROP TABLE IF EXISTS sys_user;
CREATE TABLE sys_user
(
    id       BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    username VARCHAR(16)     NOT NULL,
    nickname VARCHAR(16)     NOT NULL DEFAULT 'nickname',
    password VARCHAR(256)    NOT NULL,
    status   INT UNSIGNED    NOT NULL DEFAULT 1 COMMENT '锁定：0，可用：1',
    cycle    BIGINT UNSIGNED NOT NULL DEFAULT 1,
    PRIMARY KEY (id),
    UNIQUE INDEX (username) VISIBLE
);

DROP TABLE IF EXISTS sys_role;
CREATE TABLE sys_role
(
    id   BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    code VARCHAR(16)     NOT NULL,
    name VARCHAR(16)     NOT NULL,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS sys_user_role;
CREATE TABLE sys_user_role
(
    user_id BIGINT UNSIGNED NOT NULL,
    role_id BIGINT UNSIGNED NOT NULL,
    PRIMARY KEY (user_id, role_id)
);

INSERT INTO sys_user(id, username, nickname, password)
VALUES (1, 'Tom', 'Tommy', '$2a$10$Vb85ex7I5XZAjvMnQT1FgejZ0a/YIYyCb0YkQmboJkgl.n5TAdxMC'),
       (2, 'Jack', 'Jacky', '$2a$10$XZliJgLifuvaEKhhERg78OZmLO4E03yMVi3hb4b/appUp1XGxmBoe');
INSERT INTO sys_role(id, code, name)
VALUES (1, 'ROLE_admin', '管理员'),
       (2, 'ROLE_user', '用户');
INSERT INTO sys_user_role(user_id, role_id)
VALUES (1, 1),
       (2, 2);
