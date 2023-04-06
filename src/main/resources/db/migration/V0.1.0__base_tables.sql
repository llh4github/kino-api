CREATE TABLE "public"."auth_user"
(
    "id"           serial4                                     NOT NULL,
    "username"     VARCHAR(150) COLLATE "pg_catalog"."default" NOT NULL,
    "password"     VARCHAR(70) COLLATE "pg_catalog"."default"     NOT NULL,
    "created_time" TIMESTAMP(6)                                NOT NULL DEFAULT now(),
    "updated_time" TIMESTAMP(6)                                NOT NULL DEFAULT now(),
    "updated_by"   INT4,
    "created_by"   INT4,
    CONSTRAINT "auth_user_pkey" PRIMARY KEY ("id")
);
ALTER TABLE "public"."auth_user"
    OWNER TO "postgres";
COMMENT
    ON COLUMN "public"."auth_user"."username" IS '用户名';
COMMENT
    ON COLUMN "public"."auth_user"."password" IS '密码';
COMMENT
    ON COLUMN "public"."auth_user"."created_time" IS '创建时间';
COMMENT
    ON COLUMN "public"."auth_user"."updated_time" IS '更新时间';
COMMENT
    ON COLUMN "public"."auth_user"."updated_by" IS '更新者';
COMMENT
    ON COLUMN "public"."auth_user"."created_by" IS '创建者';
COMMENT
    ON TABLE "public"."auth_user" IS '用户表';


CREATE TABLE "public"."auth_role"
(
    "id"           serial4                                     NOT NULL,
    "code"         varchar(150) COLLATE "pg_catalog"."default" NOT NULL,
    "name"         varchar(150) COLLATE "pg_catalog"."default" NOT NULL,
    "created_time" timestamp(6)                                NOT NULL DEFAULT now(),
    "updated_time" timestamp(6)                                NOT NULL DEFAULT now(),
    "updated_by"   int4,
    "created_by"   int4,
    CONSTRAINT "auth_role_pk" PRIMARY KEY ("id")
)
;

ALTER TABLE "public"."auth_role"
    OWNER TO "postgres";

CREATE UNIQUE INDEX "auth_role_code_uindex" ON "public"."auth_role" USING btree (
                                                                                 "code" COLLATE "pg_catalog"."default"
                                                                                 "pg_catalog"."text_ops" ASC NULLS LAST
    );

COMMENT
    ON COLUMN "public"."auth_role"."code" IS '角色代号';

COMMENT
    ON COLUMN "public"."auth_role"."name" IS '角色名';

COMMENT
    ON COLUMN "public"."auth_role"."created_time" IS '创建时间';

COMMENT
    ON COLUMN "public"."auth_role"."updated_time" IS '更新时间';

COMMENT
    ON COLUMN "public"."auth_role"."updated_by" IS '更新者';

COMMENT
    ON COLUMN "public"."auth_role"."created_by" IS '创建者';

COMMENT
    ON TABLE "public"."auth_role" IS '角色表';

CREATE TABLE "public"."link_user_role"
(
    "id"      serial4 NOT NULL,
    "user_id" int4    NOT NULL,
    "role_id" int4    NOT NULL,
    CONSTRAINT "link_user_role_pkey" PRIMARY KEY ("id"),
    CONSTRAINT "link_user_role_role_id_fkey" FOREIGN KEY ("role_id") REFERENCES "public"."auth_role" ("id") ON DELETE CASCADE ON UPDATE NO ACTION,
    CONSTRAINT "link_user_role_user_id_fkey" FOREIGN KEY ("user_id") REFERENCES "public"."auth_user" ("id") ON DELETE CASCADE ON UPDATE NO ACTION
)
;

ALTER TABLE "public"."link_user_role"
    OWNER TO "postgres";
COMMENT
    ON TABLE "public"."link_user_role" IS '用户-角色关联表';



CREATE TABLE "public"."auth_permission"
(
    "id"           serial4                                     NOT NULL,
    "code"         VARCHAR(150) COLLATE "pg_catalog"."default" NOT NULL,
    "name"         VARCHAR(150) COLLATE "pg_catalog"."default" NOT NULL,
    "created_time" TIMESTAMP(6)                                NOT NULL DEFAULT now(),
    "updated_time" TIMESTAMP(6)                                NOT NULL DEFAULT now(),
    "updated_by"   INT4,
    "created_by"   INT4,
    CONSTRAINT "auth_permission_pk" PRIMARY KEY ("id")
);
ALTER TABLE "public"."auth_permission"
    OWNER TO "postgres";
CREATE UNIQUE INDEX "auth_permission_code_uindex" ON "public"."auth_permission" USING btree ("code"
                                                                                             COLLATE "pg_catalog"."default"
                                                                                             "pg_catalog"."text_ops" ASC
                                                                                             NULLS LAST);
COMMENT ON COLUMN "public"."auth_permission"."code" IS '权限代号';
COMMENT ON COLUMN "public"."auth_permission"."name" IS '权限名';
COMMENT ON COLUMN "public"."auth_permission"."created_time" IS '创建时间';
COMMENT ON COLUMN "public"."auth_permission"."updated_time" IS '更新时间';
COMMENT ON COLUMN "public"."auth_permission"."updated_by" IS '更新者';
COMMENT ON COLUMN "public"."auth_permission"."created_by" IS '创建者';
COMMENT ON TABLE "public"."auth_permission" IS '权限表';

CREATE TABLE "public"."link_role_permission"
(
    "id"      serial4 NOT NULL,
    "permission_id" int4    NOT NULL,
    "role_id" int4    NOT NULL,
    CONSTRAINT "link_role_permission_pkey" PRIMARY KEY ("id"),
    CONSTRAINT "link_role_permission_permission_id_fkey" FOREIGN KEY ("permission_id") REFERENCES "public"."auth_permission" ("id") ON DELETE CASCADE ON UPDATE NO ACTION,
    CONSTRAINT "link_role_permission_role_id_fkey" FOREIGN KEY ("role_id") REFERENCES "public"."auth_role" ("id") ON DELETE CASCADE ON UPDATE NO ACTION
)
;
ALTER TABLE "public"."link_role_permission"
    OWNER TO "postgres";

CREATE TABLE "public"."inner_inside_url"
(
    "id"           serial4                                     NOT NULL,
    "created_time" TIMESTAMP(6)                                NOT NULL DEFAULT now(),
    "updated_time" TIMESTAMP(6)                                NOT NULL DEFAULT now(),
    "updated_by"   INT4,
    "created_by"   INT4,
    "method" varchar(11) COLLATE "pg_catalog"."default" NOT NULL,
    "url" text NOT NULL,
    "remark" text COLLATE "pg_catalog"."default" NOT NULL DEFAULT ''::text,
    permission_or_mode boolean      default false    not null,
    CONSTRAINT "inner_inside_url_url_key" UNIQUE ("url"),
    CONSTRAINT "inner_inside_url_pkey" PRIMARY KEY ("id")
);
ALTER TABLE "public"."inner_inside_url"
    OWNER TO "postgres";
comment on column inner_inside_url.permission_or_mode is '权限校验模式';
COMMENT ON COLUMN "public"."inner_inside_url"."method" IS '请求方法。通配地址请求方法可以为空。';
COMMENT ON COLUMN "public"."inner_inside_url"."url" IS 'url，须以/开头';
COMMENT ON COLUMN "public"."inner_inside_url"."remark" IS '备注';
COMMENT ON TABLE "public"."inner_inside_url" IS '应用内部URL列表';

CREATE TABLE "public"."link_url_permission" (
                                                "id"           serial4                                     NOT NULL,
                                                "permission_id" int4 NOT NULL,
                                                "url_id" int4 NOT NULL,
                                                CONSTRAINT "link_url_permission_pkey" PRIMARY KEY ("id"),
                                                CONSTRAINT "link_url_permission_permission_id_fkey" FOREIGN KEY ("permission_id") REFERENCES "public"."auth_permission" ("id") ON DELETE CASCADE ON UPDATE NO ACTION,
                                                CONSTRAINT "link_url_permission_url_id_fkey" FOREIGN KEY ("url_id") REFERENCES "public"."inner_inside_url" ("id") ON DELETE CASCADE ON UPDATE NO ACTION
)
;

ALTER TABLE "public"."link_url_permission"
    OWNER TO "postgres";

COMMENT ON TABLE "public"."link_url_permission" IS '应用内部url对应的权限关系表';
