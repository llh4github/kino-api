CREATE TABLE "public"."auth_user"
(
    "id"           serial4                                     NOT NULL,
    "username"     VARCHAR(150) COLLATE "pg_catalog"."default" NOT NULL,
    "password"     CHAR(70) COLLATE "pg_catalog"."default"     NOT NULL,
    "created_time" TIMESTAMP(6)                                NOT NULL DEFAULT now(),
    "updated_time" TIMESTAMP(6)                                NOT NULL DEFAULT now(),
    "updated_by"   INT4,
    "created_by"   INT4,
    CONSTRAINT "auth_user_pkey" PRIMARY KEY ("id")
);
ALTER TABLE "public"."auth_user" OWNER TO "postgres";
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
    "id"           serial4                                     NOT NULL DEFAULT nextval('auth_role_id_seq'::regclass),
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
    "code" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
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
