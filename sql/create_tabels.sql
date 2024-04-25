-- auto-generated definition
create table user
(
    id           bigint(32) auto_increment comment '主键ID'
        primary key,
    username     varchar(255)      null comment '用户名',
    userAccount  varchar(255)      null comment '账号',
    userPassword varchar(255)      null comment '密码',
    gender       tinyint           null comment '性别',
    avatarUrl    varchar(255)      null comment '头像',
    email        varchar(255)      null comment '邮箱',
    phone        varchar(255)      null comment '电话',
    userStatus   int     default 0 null comment '用户状态',
    createTime   timestamp         null on update CURRENT_TIMESTAMP comment '创建时间',
    updateTime   timestamp         null comment '修改时间',
    isDeleted    tinyint default 0 null comment '是否逻辑删除',
    userRole     int               null comment '用户角色 0-普通 1-管理员',
    planetCode   varchar(255)      null comment '编号'
);

