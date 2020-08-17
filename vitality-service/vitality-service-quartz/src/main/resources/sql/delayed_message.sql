CREATE TABLE `delayed_message` (
  `id` bigint(20) NOT NULL COMMENT '主键id',
  `topic` varchar(64) NOT NULL,
  `tag` varchar(64) NOT NULL,
  `body` varchar(1024) NOT NULL,
  `key` varchar(128) NOT NULL,
  `queue_id` varchar(32) DEFAULT NULL COMMENT '队列id',
  `queue_name` varchar(64) DEFAULT NULL COOMENT '队列名称',
  `expect_tm` bigint(20) NOT NULL COMMENT '消息过期时间',
  `delete_tm` datetime DEFAULT NULL COMMENT '删除时间',
  `ins_tm` datetime NOT NULL COMMENT '消息插入时间',
  `delete_flag` int(11) NOT NULL DEFAULT COMMENT '删除标志位 0表示未删除 1表示已删除',
  `uniq_id` varchar(256) DEFAULT NULL COMMENT '业务端的唯一id',
  PRIMARY KEY (`id`),
  KEY `idx_uniqid` (`uniq_id`),
  KEY `idx_expectm_deleteflag` (`expect_tm`,`delete_flag`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- oracle 创建自增索引
-- drop sequence delayed_message_seq;
create sequence delayed_message_seq
	minvalue 1 maxvalue 9999999999999999999
	increment by 1
	start with 1;

create or replace trigger delayed_message_tri before insert on delayed_message
for each row
begin
	select delayed_message_seq.nextval into :new.id from dual;
end;

