-- 场景表
CREATE TABLE `scene` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `scene_code` varchar(32) NOT NULL COMMENT '场景码',
  `delete_status` tinyint NOT NULL DEFAULT '1' COMMENT '删除标识 0未被删除 1已被删除',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` bigint NOT NULL COMMENT '创建人ID',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新人ID',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_scene_code` (`scene_code`) USING BTREE COMMENT '场景码唯一索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT '场景表';

-- 渠道表
CREATE TABLE `channel` (
  `id` bigint NOT NULL COMMENT '主键ID',
	`channel_code` varchar(32) NOT NULL COMMENT '渠道码',
  `delete_status` tinyint NOT NULL DEFAULT '1' COMMENT '删除标识 0未被删除 1已被删除',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` bigint NOT NULL COMMENT '创建人ID',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新人ID',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_channel_code` (`channel_code`) USING BTREE COMMENT '渠道码唯一索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT '渠道表';

-- 场景渠道关系表
CREATE TABLE `scene_channel_relation` (
  `scene_id` bigint NOT NULL COMMENT '场景ID',
	`channel_id` bigint NOT NULL COMMENT '渠道ID',
  UNIQUE KEY `uniq_scene_id_channel_id` (`scene_id`,`channel_id`) USING BTREE COMMENT '场景渠道唯一索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT '场景渠道关系表';

-- 网关表
CREATE TABLE `gateway` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `channel_id` bigint NOT NULL COMMENT '渠道ID(关联渠道)',
  `gateway_code` varchar(32) NOT NULL COMMENT '网关码',
  `gateway_account` varchar(32) NOT NULL COMMENT '账号',
  `gateway_password` varchar(64) NOT NULL COMMENT '密码',
  `gateway_sign` varchar(16) DEFAULT NULL COMMENT '签名',
  `delete_status` tinyint NOT NULL DEFAULT '1' COMMENT '删除标识 0未被删除 1已被删除',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` bigint NOT NULL COMMENT '创建人ID',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新人ID',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_gateway_code_gateway_account` (`gateway_code`,`gateway_account`) USING BTREE COMMENT '网关码账号唯一索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='网关表';


-- 模板表
CREATE TABLE `template` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `scene_id` bigint NOT NULL COMMENT '场景ID',
  `channel_id` bigint NOT NULL COMMENT '渠道ID',
  `gateway_id` bigint NOT NULL COMMENT '网关ID',
  `template_code` varchar(64) NOT NULL COMMENT '模板码',
  `template_content` text COMMENT '模板内容',
  `delete_status` tinyint NOT NULL DEFAULT '1' COMMENT '删除标识 0未被删除 1已被删除',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` bigint NOT NULL COMMENT '创建人ID',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新人ID',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_template_code` (`template_code`) USING BTREE COMMENT '模板码唯一索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='模板表';

-- 任务表
CREATE TABLE `task` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `scene_id` bigint NOT NULL COMMENT '场景ID',
  `channel_id` bigint NOT NULL COMMENT '渠道ID',
  `param_json` text COMMENT '参数json字符串',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '任务创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='任务表';

-- 日志流水
CREATE TABLE `log` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `scene_id` bigint NOT NULL COMMENT '场景ID',
  `channel_id` bigint NOT NULL COMMENT '渠道ID',
  `gateway_id` bigint NOT NULL COMMENT '通道ID',
  `template_id` bigint NOT NULL COMMENT '模板ID',
  `message_content` text COMMENT '消息文本',
  `message_status` tinyint NOT NULL COMMENT '消息状态 1待发送 2发送中 3发送成功 4发送失败 5接收成功 6接收失败',
  `to_be_send_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '待发送时间',
  `sending_time` timestamp NULL DEFAULT NULL COMMENT '发送中时间',
  `sent_time` timestamp NULL DEFAULT NULL COMMENT '发送完成时间',
  `receive_time` timestamp NULL DEFAULT NULL COMMENT '接收完成时间',
  `message_tag` varchar(255) DEFAULT '' COMMENT '三方消息唯一标识',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='日志表';

