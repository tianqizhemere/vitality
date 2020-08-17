CREATE TABLE `delay_task` (
   `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modify_time` datetime NOT NULL COMMENT '修改时间',
  `delay_task_id` bigint(20) NOT NULL COMMENT '队列唯一标识id',
  `trigger_time` datetime NOT NULL COMMENT '执行时间',
  `task` varchar(255) NOT NULL COMMENT '任务类，Json字符串',
  `status` int(11) NOT NULL COMMENT '完成状态',
  `class_id` int(11) DEFAULT NULL COMMENT '类的字节码对象编码id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='延迟任务表';
-- 队列唯一标识id delay_task_id
-- task任务类 转 json---> 存放
-- 执行时间(秒)