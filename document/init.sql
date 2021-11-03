DROP SCHEMA IF EXISTS account_book;
CREATE SCHEMA account_book;
USE account_book;

DROP TABLE IF EXISTS bill;
CREATE TABLE bill
(
    id         BIGINT UNSIGNED AUTO_INCREMENT,
    user_id    BIGINT        NOT NULL,
    type_id    BIGINT        NOT NULL,
    created_at DATE          NOT NULL,
    cost       DECIMAL(6, 2) NOT NULL COMMENT '消费金额',
    status     INT UNSIGNED  NOT NULL DEFAULT 1 COMMENT '0：不计入，1：计入',
    note       VARCHAR(255)  NULL,

    PRIMARY KEY (id),
    KEY key_user_time (user_id, created_at),
    KEY key_type_id (type_id)
) COMMENT '账单表';

DROP TABLE IF EXISTS bill_type;
CREATE TABLE bill_type
(
    id      BIGINT UNSIGNED AUTO_INCREMENT,
    user_id BIGINT       NOT NULL,
    name    VARCHAR(20)  NOT NULL,
    kind    INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '0：支出，1：收入',
    status  INT UNSIGNED NOT NULL DEFAULT 1 COMMENT '0：不可见，1：可见',

    PRIMARY KEY (id),
    KEY uniq_user (user_id)
) COMMENT '账单类型表';

DROP TABLE IF EXISTS sys_user;
CREATE TABLE sys_user
(
    id         BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    username   VARCHAR(16)     NOT NULL,
    nickname   VARCHAR(16)     NOT NULL DEFAULT 'piggy',
    password   VARCHAR(256)    NOT NULL,
    status     INT UNSIGNED    NOT NULL DEFAULT 1 COMMENT '锁定：0，可用：1',
    created_at TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP       NULL     DEFAULT CURRENT_TIMESTAMP,
    login_at   TIMESTAMP       NULL,

    PRIMARY KEY (id),
    UNIQUE INDEX uniq_username (username)
) COMMENT '用户表';

DROP TABLE IF EXISTS sys_role;
CREATE TABLE sys_role
(
    id      BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    user_id BIGINT          NOT NULL,
    code    VARCHAR(16)     NOT NULL,
    name    VARCHAR(16)     NOT NULL,

    PRIMARY KEY (id),
    UNIQUE KEY uniq_code (user_id, code)
) COMMENT '用户权限表';

INSERT INTO sys_user(id, username, nickname, password)
VALUES (1, 'Tom', 'Tommy', '$2a$10$Vb85ex7I5XZAjvMnQT1FgejZ0a/YIYyCb0YkQmboJkgl.n5TAdxMC'),
       (2, 'Jack', 'Jacky', '$2a$10$XZliJgLifuvaEKhhERg78OZmLO4E03yMVi3hb4b/appUp1XGxmBoe');
INSERT INTO sys_role(id, user_id, code, name)
VALUES (1, 1, 'ROLE_admin', '管理员'),
       (2, 1, 'ROLE_user', '用户'),
       (3, 2, 'ROLE_user', '用户');

