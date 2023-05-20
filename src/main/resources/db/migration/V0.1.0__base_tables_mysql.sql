CREATE TABLE `auth_user`
(
    `id`           int unsigned NOT NULL AUTO_INCREMENT,
    `username`     varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
    `password`     varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL COMMENT '密码',
    `created_time` datetime(2) DEFAULT NULL COMMENT '创建时间',
    `updated_time` datetime(2) DEFAULT NULL COMMENT '更新时间',
    `updated_by`   int                                                                    DEFAULT NULL COMMENT '更新者',
    `created_by`   int                                                                    DEFAULT NULL COMMENT '创建者',
    `nickname`     varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '昵称',
    `status`       tinyint unsigned NOT NULL DEFAULT '1' COMMENT '用户状态',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `uniq_username` (`username`) USING BTREE
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

CREATE TABLE `auth_role`
(
    `id`           int unsigned NOT NULL AUTO_INCREMENT,
    `code`         varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色代号',
    `name`         varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色名',
    `created_time` datetime(2) NOT NULL COMMENT '创建时间',
    `updated_time` datetime(2) NOT NULL COMMENT '更新时间',
    `updated_by`   int DEFAULT NULL COMMENT '更新者',
    `created_by`   int DEFAULT NULL COMMENT '创建者',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `uniq_code` (`code`) USING BTREE
) ENGINE=InnoDB   DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='角色表';


CREATE TABLE `auth_permission`
(
    `id`           int unsigned NOT NULL AUTO_INCREMENT,
    `code`         varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '权限代号',
    `name`         varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '权限名',
    `created_time` datetime(2) DEFAULT NULL COMMENT '创建时间',
    `updated_time` datetime(2) DEFAULT NULL COMMENT '更新时间',
    `updated_by`   int DEFAULT NULL COMMENT '更新者',
    `created_by`   int DEFAULT NULL COMMENT '创建者',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `uniq_code` (`code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='权限表';

CREATE TABLE `link_role_permission`
(
    `id`            int unsigned NOT NULL AUTO_INCREMENT,
    `permission_id` int unsigned NOT NULL,
    `role_id`       int NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `unique_data` (`permission_id`,`role_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `link_user_role`
(
    `id`      int unsigned NOT NULL AUTO_INCREMENT,
    `user_id` int unsigned NOT NULL,
    `role_id` int unsigned NOT NULL,
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `unique_data` (`user_id`,`role_id`) USING BTREE,
    KEY       `role_id` (`role_id`) USING BTREE,
    CONSTRAINT `link_user_role_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `auth_user` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
    CONSTRAINT `link_user_role_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `auth_role` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE=InnoDB   DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='用户-角色关联表';
