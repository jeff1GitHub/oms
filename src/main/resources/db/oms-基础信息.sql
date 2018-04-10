CREATE TABLE "t_contract" (
"id" serial4,
"type" int4,
"path" varchar,
"start_time" timestamp,
"end_time" timestamp,
"producer_id" int4,
"signature_company" varchar,
"signature_person" varchar,
"remark" varchar,
"create_user" varchar,
"create_time" timestamp,
"update_user" varchar,
"update_time" timestamp,
"is_del" int4
);

COMMENT ON COLUMN "t_contract"."id" IS '流水编号';
COMMENT ON COLUMN "t_contract"."type" IS '合同类型';
COMMENT ON COLUMN "t_contract"."path" IS '合同保存路径';
COMMENT ON COLUMN "t_contract"."start_time" IS '合同开始时间';
COMMENT ON COLUMN "t_contract"."end_time" IS '合同结束时间';
COMMENT ON COLUMN "t_contract"."producer_id" IS '供应商id';
COMMENT ON COLUMN "t_contract"."signature_company" IS '签章公司';
COMMENT ON COLUMN "t_contract"."signature_person" IS '签章人';
COMMENT ON COLUMN "t_contract"."remark" IS '备注';
COMMENT ON COLUMN "t_contract"."create_user" IS '创建人';
COMMENT ON COLUMN "t_contract"."create_time" IS '创建时间';
COMMENT ON COLUMN "t_contract"."update_user" IS '修改人';
COMMENT ON COLUMN "t_contract"."update_time" IS '修改时间';
COMMENT ON COLUMN "t_contract"."is_del" IS '是否删除 0:正常 1:删除';

CREATE TABLE "t_producer" (
"id" int4 NOT NULL,
"name" varchar(255),
"role_id" int4,
"company_name" varchar,
"signature_person" varchar,
"ali_pay" varchar(255),
"bank_id" int4,
"bank_account" varchar,
"login_domain" varchar,
"login_account" varchar,
"login_password" varchar,
"is_del" int4,
PRIMARY KEY ("id") 
);

COMMENT ON COLUMN "t_producer"."id" IS '供应商id';
COMMENT ON COLUMN "t_producer"."name" IS '名称';
COMMENT ON COLUMN "t_producer"."role_id" IS '角色id';
COMMENT ON COLUMN "t_producer"."company_name" IS '公司名称';
COMMENT ON COLUMN "t_producer"."signature_person" IS '签章人';
COMMENT ON COLUMN "t_producer"."ali_pay" IS '支付宝帐号';
COMMENT ON COLUMN "t_producer"."bank_id" IS '银行id';
COMMENT ON COLUMN "t_producer"."bank_account" IS '银行帐号';
COMMENT ON COLUMN "t_producer"."login_domain" IS '登录域名';
COMMENT ON COLUMN "t_producer"."login_account" IS '登录帐号';
COMMENT ON COLUMN "t_producer"."login_password" IS '登录密码';
COMMENT ON COLUMN "t_producer"."is_del" IS '是否删除 0:正常 1:删除';

CREATE TABLE "t_linkman" (
"id" serial4 NOT NULL,
"name" varchar,
"prodoucer_id" int4,
"default" int4,
"is_del" int4,
PRIMARY KEY ("id") 
);

COMMENT ON COLUMN "t_linkman"."id" IS '联系人id';
COMMENT ON COLUMN "t_linkman"."name" IS '联系人名称';
COMMENT ON COLUMN "t_linkman"."prodoucer_id" IS '所属供应商';
COMMENT ON COLUMN "t_linkman"."default" IS '是否默认 0:正常 1:默认';
COMMENT ON COLUMN "t_linkman"."is_del" IS '是否删除 0:正常 1:删除';

CREATE TABLE "t_linkman_information" (
"id" serial4 NOT NULL,
"type" int4,
"information" varchar,
"default" int4,
"is_del" int4,
PRIMARY KEY ("id") 
);

COMMENT ON COLUMN "t_linkman_information"."id" IS '联系方式id';
COMMENT ON COLUMN "t_linkman_information"."type" IS '联系类型';
COMMENT ON COLUMN "t_linkman_information"."information" IS '联系信息';
COMMENT ON COLUMN "t_linkman_information"."default" IS '是否默认 0:正常 1:默认';
COMMENT ON COLUMN "t_linkman_information"."is_del" IS '是否删除 0:正常 1:删除';

CREATE TABLE "t_pay" (
"id" serial4 NOT NULL,
PRIMARY KEY ("id") 
);

COMMENT ON COLUMN "t_pay"."id" IS '支付方式id';

CREATE TABLE "t_serverroom" (
);

