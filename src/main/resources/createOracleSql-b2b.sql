    /*
    *商品主文件
    */
    create table  PB_ITEM
    (
           ID                NUMBER(18) not null,
           TENANT_ID         NUMBER(18) not null,
           OU_ID             NUMBER(18) not null,
           ORG_ID            NUMBER(18),
           ITEM_CODE         VARCHAR(40) not null,
           ITEM_NAME         VARCHAR(100) not null,
           ITEM_ABBR         VARCHAR(4000),
           ITEM_TITLE        VARCHAR(200),
           ITEM_DESC         VARCHAR(2000),
           SUB_TITLE         VARCHAR(200),
           MEM_CODE          VARCHAR(4000),
           STORE_TYPE        VARCHAR(40),
           PINYIN            VARCHAR(200),
           PINYIN_SH         VARCHAR(100),
           PROMOTION_INFO    VARCHAR(100),
           KEYWORDS          VARCHAR(100),
           MARKS             VARCHAR(100),
           ITEM_STATUS       VARCHAR(4000),
           ITEM_STATUS2      VARCHAR(4000),
           LISTING_STATUS    VARCHAR(4000),
           C1_CODE           VARCHAR(40),
           C2_CODE           VARCHAR(40),
           C3_CODE           VARCHAR(40),
           UOM               VARCHAR(10),
           UOM2              VARCHAR(10),
           UOM3              VARCHAR(10),
           UOM4              VARCHAR(10),
           UOM5              VARCHAR(10),
           UOM6              VARCHAR(10),
           UOM7              VARCHAR(10),
           UOM8              VARCHAR(10),
           UOM9              VARCHAR(10),
           UOM10             VARCHAR(10),
           SHIP_UOM          VARCHAR(10),
           GROSS_WEIGHT      NUMERIC(20,4),
           NET_WEIGHT        NUMERIC(20,4),
           WEIGHT_UOM        VARCHAR(10),
           VOLUME            NUMERIC(20,4),
           MOQ               NUMERIC(20,4),
           SAFE_QTY          NUMERIC(20,4),
           MAX_QTY           NUMERIC(20,4),
           SALE_PRICE        NUMERIC(20,2),
           PRICE_GROUP       VARCHAR(40),
           PRICE_PERIOD      VARCHAR(4000),
           BRAND             VARCHAR(40),
           MATERIAL          VARCHAR(40),
           GRADE             VARCHAR(40),
           STANDARD          VARCHAR(4000),
           SURFACE           VARCHAR(4000),
           ORIGIN            VARCHAR(4000),
           SPEC              VARCHAR(4000),
           P1                VARCHAR(4000),
           P2                VARCHAR(4000),
           P3                VARCHAR(4000),
           P4                VARCHAR(4000),
           P5                VARCHAR(4000),
           P6                VARCHAR(4000),
           P7                VARCHAR(4000),
           P8                VARCHAR(4000),
           REMARK            VARCHAR(2000),
           OUTER_CODE        VARCHAR(4000),
           CREATE_USER_ID    NUMBER(18),
           CREATE_TIME       DATE,
           MODIFY_USER_ID    NUMBER(18),
           MODIFY_TIME       DATE,
           AUDIT_DATA_VERSION NUMBER,
           DELETE_FLAG       NUMERIC(1),
           ITEM_STAKE_TYPE   VARCHAR(1),
           ITEM_PROP         VARCHAR(1),
           REP_ITEM_ID       NUMBER(18),
           OTHER_PROP1       VARCHAR(4000),
           OTHER_PROP2       VARCHAR(4000),
           OTHER_PROP3       VARCHAR(4000),
           OTHER_PROP4       VARCHAR(4000),
           OTHER_PROP5       VARCHAR(4000),
           OTHER_PROP6       VARCHAR(4000),
           OTHER_PROP7       VARCHAR(4000),
           OTHER_PROP8       VARCHAR(4000),
           OTHER_PROP9       VARCHAR(4000),
           OTHER_PROP10      VARCHAR(4000),
           OTHER_PROP11      VARCHAR(4000),
           OTHER_PROP12      VARCHAR(4000),
           OTHER_PROP13      VARCHAR(4000),
           OTHER_PROP14      VARCHAR(4000),
           OTHER_PROP15      VARCHAR(4000),
           OTHER_PROP16      VARCHAR(4000),
           OTHER_PROP17      VARCHAR(4000),
           OTHER_PROP18      VARCHAR(4000),
           OU_CODE           VARCHAR(40) not null,
    );
    alter  table PB_ITEM     add constraint PK_PB_ITEM_ID primary key (ID);

    /*
    *商品价格表
    */
    create table  PB_ITEM_SALEPRICE
    (
           ID                NUMBER(18) not null,
           TENANT_ID         NUMBER(18) not null,
           OU_ID             NUMBER(18) not null,
           ORG_ID            NUMBER(18),
           PRICE_TYPE        VARCHAR(4000),
           PRICE_TYPE2       VARCHAR(4000),
           PRICE_TYPE3       VARCHAR(4000),
           ITEM_ID           NUMBER(18),
           ITEM_C1           VARCHAR(40),
           ITEM_C2           VARCHAR(40),
           ITEM_C3           VARCHAR(40),
           CUST_GROUP        VARCHAR(40),
           CUST_ID           NUMBER(18),
           SALE_REGION       VARCHAR(4000),
           PRICE_GROUP       VARCHAR(40),
           SHIPTO_ADDR_NO    NUMBER,
           PRICE             NUMERIC(20,2),
           UOM               VARCHAR(10),
           PRICE_UOM         VARCHAR(10),
           CURR_CODE         VARCHAR(10),
           VALID_FROM        DATE,
           VALID_TO          DATE,
           FROM_QTY          NUMERIC(20,4),
           TO_QTY            NUMERIC(20,4),
           TOLERANCE         NUMERIC(20,2),
           REMARK            VARCHAR(2000),
           CREATE_USER_ID    NUMBER(18),
           CREATE_TIME       DATE,
           MODIFY_USER_ID    NUMBER(18),
           MODIFY_TIME       DATE,
           AUDIT_DATA_VERSION NUMBER,
           DELETE_FLAG       NUMERIC(1),
           ITEM_CODE         VARCHAR(40),
           CUST_CODE         VARCHAR(40)
    );
    alter  table PB_ITEM_SALEPRICE add constraint PK_PB_ITEM_SALEPRICE_ID primary key (ID);

    /*
    *公司主表
    */
    create table  PB_OU
    (
           ID                NUMBER(18) not null,
           TENANT_ID         NUMBER(18) not null,
           OU_CODE           VARCHAR(40) not null,
           OU_NAME           VARCHAR(200) not null,
           OU_ABBR           VARCHAR(100),
           OU_TYPE           VARCHAR(40),
           OU_STATUS         VARCHAR(40),
           PID               NUMBER(18),
           OU_CURR           VARCHAR(10),
           ADDR_NO           NUMBER,
           REGION            VARCHAR(4000),
           REMARK            VARCHAR(2000),
           OUTER_CODE        VARCHAR(4000),
           CREATE_USER_ID    NUMBER(18),
           CREATE_TIME       DATE,
           MODIFY_USER_ID    NUMBER(18),
           MODIFY_TIME       DATE,
           AUDIT_DATA_VERSION NUMBER,
           DELETE_FLAG       NUMERIC(1)
    );
    alter  table PB_OU add constraint PK_PB_OU_ID primary key (ID);
    create index IDX_PB_OU_OU_CODE on PB_OU(OU_CODE);
    create index IDX_PB_OU_OU_NAME on PB_OU(OU_NAME);
    create index IDX_PB_OU_PID on PB_OU(PID);


    create table  PB_ADDR
    (
           ID                NUMBER(18) not null,
           TENANT_ID         NUMBER(18) not null,
           ADDR_NO           NUMBER not null,
           ADDR_CODE         VARCHAR(4000),
           ADDR_TYPE         VARCHAR(4000) not null,
           ADDR_NAME         VARCHAR(200),
           ADDR_STATUS       VARCHAR(4000),
           REMARK            VARCHAR(2000),
           OUTER_CODE        VARCHAR(4000),
           CREATE_USER_ID    NUMBER(18),
           CREATE_TIME       DATE,
           MODIFY_USER_ID    NUMBER(18),
           MODIFY_TIME       DATE,
           AUDIT_DATA_VERSION NUMBER,
           DELETE_FLAG       NUMERIC(1)
    );
    alter  table PB_ADDR  add constraint PK_PB_ADDR_ID primary key (ID);

    create table  PB_ADDRESS
    (
           ID                NUMBER(18) not null,
           TENANT_ID         NUMBER(18) not null,
           ADDR_NO           NUMBER not null,
           LINE_NO           NUMBER,
           ADDRESS_TYPE      VARCHAR(40),
           DEFAULT_FLAG      NUMERIC(1),
           ADDRESS_STATUS    VARCHAR(4000),
           CONT_PERSON       VARCHAR(4000),
           POSITION          VARCHAR(40),
           PID               NUMBER(18),
           MOBILE            VARCHAR(40),
           MOBILE2           VARCHAR(40),
           TEL               VARCHAR(40),
           TEL2              VARCHAR(40),
           FAX               VARCHAR(40),
           EMAIL             VARCHAR(40),
           EMAIL2            VARCHAR(40),
           COUNTRY           VARCHAR(40),
           PROVINCE          VARCHAR(10),
           CITY              VARCHAR(10),
           PREFECTURE        VARCHAR(40),
           DISTRICT          VARCHAR(40),
           DETAILADDR        VARCHAR(100),
           ZIPCODE           VARCHAR(40),
           WEBADDRESS        VARCHAR(40),
           WEIBO             VARCHAR(40),
           WECHAT_MP         VARCHAR(40),
           SNS               VARCHAR(40),
           SNS2              VARCHAR(40),
           SNS3              VARCHAR(40),
           REMARK            VARCHAR(2000),
           CREATE_USER_ID    NUMBER(18),
           CREATE_TIME       DATE,
           MODIFY_USER_ID    NUMBER(18),
           MODIFY_TIME       DATE,
           AUDIT_DATA_VERSION NUMBER,
           DELETE_FLAG       NUMERIC(1),
           ADDR_NAME         VARCHAR(200),
           CUST_CODE         VARCHAR(40),,
           OU_CODE           VARCHAR(40),,
    );
    alter  table PB_ADDRESS   add constraint PK_PB_ADDRESS_ID primary key (ID);

    /*
    *客户基础信息
    */
    create table  PB_CUST
    (
           ID                NUMBER(18) not null,
           TENANT_ID         NUMBER(18) not null,
           OU_ID             NUMBER(18) not null,
           ORG_ID            NUMBER(18),
           CUST_CODE         VARCHAR(40) not null,
           CUST_NAME         VARCHAR(200) not null,
           CUST_ABBR         VARCHAR(100),
           CUST_TYPE         VARCHAR(40),
           CUST_STATUS       VARCHAR(40),
           PID               NUMBER(18),
           ADDR_NO           NUMBER,
           PINYIN            VARCHAR(800),
           PINYIN_SH         VARCHAR(200),
           COMP_CAPITAL      VARCHAR(40),
           AGENT_EMP_ID      NUMBER(18),
           CUST_CURR         VARCHAR(4000),
           CREDIT_LIMIT      NUMBER(18),
           CREDIT_BAL        NUMBER(18),
           PAY_METHOD        VARCHAR(4000),
           PAYMENT_TERM      VARCHAR(4000),
           SETTLE_TYPE       VARCHAR(4000),
           RECON_PERIOD      VARCHAR(4000),
           SETTLE_MONTHLY_DAY NUMBER,
           DEF_ORG_ID        NUMBER(18),
           DEF_WH_ID         NUMBER(18),
           C1                VARCHAR(40),
           C2                VARCHAR(40),
           C3                VARCHAR(40),
           COUNTRY           VARCHAR(40),
           POSTCODE          VARCHAR(40),
           REGION            VARCHAR(4000),
           CUST_LEVEL        VARCHAR(4000),
           CUST_GROUP        VARCHAR(4000),
           VIP_NO            VARCHAR(40),
           VIP_LEVEL         VARCHAR(10),
           VIP_GROUP         VARCHAR(20),
           CUST_POINT_FLAG   NUMERIC(1),
           POINT             NUMERIC(10,2),
           CUST_SOURCE       VARCHAR(20),
           VALID_FROM        DATE,
           VALID_TO          DATE,
           TAXPAYER_TYPE     VARCHAR(4000),
           INV_TYPE          VARCHAR(40),
           INV_TITLE         VARCHAR(40),
           INV_TAXNO         VARCHAR(100),
           TAX_CODE          VARCHAR(4000),
           IC_REGISTER_NO    VARCHAR(4000),
           REGISTER_DATE     DATE,
           REGISTER_ADDRESS  VARCHAR(200),
           REGISTER_TRADEMARK_NO VARCHAR(4000),
           REGISTER_FUND     VARCHAR(4000),
           REGISTER_FUND_CURRY VARCHAR(4000),
           REPR              VARCHAR(40),
           COMP_NAME         VARCHAR(40),
           COMP_PROP         VARCHAR(4000),
           COMP_SCALE        VARCHAR(4000),
           COMP_BUSSADDR     VARCHAR(40),
           COMP_MAINBUSS     VARCHAR(40),
           COMP_BUSSRANGE    VARCHAR(100),
           REMARK            VARCHAR(2000),
           OUTER_CODE        VARCHAR(4000),
           CREATE_USER_ID    NUMBER(18),
           CREATE_TIME       DATE,
           MODIFY_USER_ID    NUMBER(18),
           MODIFY_TIME       DATE,
           AUDIT_DATA_VERSION NUMBER,
           DELETE_FLAG       NUMERIC(1),
           OU_CODE           VARCHAR(40) not null
    );
    alter  table PB_CUST  add constraint PK_PB_CUST_ID primary key (ID);

    /*
    *客户证照信息
    */
    create table  PB_ADDR_QUALIFY
    (
           ID                NUMBER(18) not null,
           TENANT_ID         NUMBER(18) not null,
           ADDR_NO           NUMBER not null,
           LINE_NO           NUMBER,
           QUALIFY_TYPE      VARCHAR(4000) not null,
           QUALIFY_NAME      VARCHAR(4000) not null,
           QUALIFY_NO        VARCHAR(4000),
           VALID_FROM        DATE,
           VALID_TO          DATE,
           NEXT_CHECK_DATE   DATE,
           ATTACH_REPO       VARCHAR(100),
           REMARK            VARCHAR(2000),
           CREATE_USER_ID    NUMBER(18),
           CREATE_TIME       DATE,
           MODIFY_USER_ID    NUMBER(18),
           MODIFY_TIME       DATE,
           AUDIT_DATA_VERSION NUMBER,
           DELETE_FLAG       NUMERIC(1),
           VALID_LV1        VARCHAR(40),
           VALID_LV2        VARCHAR(40),
           VALID_LV3        VARCHAR(40),
           CERT_STATUS      NVARCHAR2(40)


    );
    alter  table PB_ADDR_QUALIFY  add constraint PK_PB_ADDR_QUALIFY_ID primary key (ID);

    /*
    *销售订单
    */
    create table  B2B_SO
    (
           ID                NUMBER(18) not null,
           TENANT_ID         NUMBER(18),
           OU_ID             NUMBER(18),
           ORG_ID            NUMBER(18),
           DOC_NO            VARCHAR(4000),
           DOC_TYPE          VARCHAR(4000),
           DOC_STATUS        VARCHAR(4000),
           DOC_TIME          DATE,
           SO_BATCH_ID       NUMBER(18),
           DOC_NO2           VARCHAR(4000),
           DOC_TYPE2         VARCHAR(4000),
           SO_SOURCE         VARCHAR(1),
           SALE_REGION       VARCHAR(4000),
           DELIVER_REGION    VARCHAR(4000),
           CUST_ID           NUMBER(18),
           CUST_CODE         VARCHAR(4000),
           SO_EMP_ID         NUMBER(18),
           AGENT_EMP_ID      NUMBER(18),
           CURR_CODE         VARCHAR(40),
           TAX_CODE          VARCHAR(40),
           APPR_STATUS       VARCHAR(4000),
           APPR_TIME         DATE,
           APPR_USER_ID      NUMBER(18),
           PAY_STATUS        VARCHAR(4000),
           PAY_TIME          DATE,
           PAY_TRANS_ID      NUMBER(18),
           PAY_METHOD        VARCHAR(4000),
           PAYMENT_TERM      VARCHAR(40),
           PL_DATE           DATE,
           DELIVER_STATUS    VARCHAR(4000),
           DELIVER_WAY       VARCHAR(40),
           DELIVER_CONTACT   VARCHAR(4000),
           DELIVER_ADDRESS_ID NUMBER(18),
           DELIVER_ADDRESS   VARCHAR(200),
           SHIP_TIME         DATE,
           DELIVER_TIME      DATE,
           RECEIVE_TIME      DATE,
           RETURN_STATUS     VARCHAR(4000),
           RETURN_APPLY_TIME DATE,
           RETURN_APPR_TIME  DATE,
           COMMENT_STATUS    VARCHAR(4000),
           COMMENT_TYPE      VARCHAR(4000),
           COMMENT_TIME      DATE,
           COMMENT_CONTENT   VARCHAR(2000),
           CANCEL_TIME       DATE,
           CANCEL_REASON     VARCHAR(4000),
           REFUND_STATUS     VARCHAR(4000),
           REFUND_TIME       DATE,
           REFUND_AMT        NUMERIC(20,2),
           DISC_AMT          NUMERIC(20,2),
           TAX_RATE          NUMERIC(20,8),
           TAX_AMT           NUMERIC(20,2),
           NET_AMT           NUMERIC(20,2),
           FREIGHT_FEE       NUMERIC(20,2),
           AMT               NUMERIC(20,2),
           TSO_ID            NUMBER(18),
           REMARK            VARCHAR(2000),
           OUTER_CODE        VARCHAR(4000),
           CREATE_USER_ID    NUMBER(18),
           CREATE_TIME       DATE,
           MODIFY_USER_ID    NUMBER(18),
           MODIFY_TIME       DATE,
           AUDIT_DATA_VERSION NUMBER,
           DELETE_FLAG       NUMERIC(1)
    );
    alter  table B2B_SO  add constraint PK_B2B_SO_ID primary key (ID);

    /*
    *销售订单明细
    */
    create table  B2B_SO_D
    (
           ID                NUMBER(18) not null,
           TENANT_ID         NUMBER(18),
           SO_ID             NUMBER(14),
           LINE_NO           NUMERIC(20,2),
           WH_ID             NUMBER(18),
           ITEM_ID           NUMBER(18),
           ITEM_CODE         VARCHAR(40),
           ITEM_NAME         VARCHAR(100),
           SKU_ID            NUMBER(18),
           LINE_TYPE         VARCHAR(4000),
           LINE_STATUS       VARCHAR(4000),
           PAY_STATUS        VARCHAR(4000),
           QTY               NUMERIC(20,4),
           UOM               VARCHAR(4000),
           WEIGHT            NUMERIC(20,4),
           WEIGHT_UOM        VARCHAR(4000),
           WEIGHT_RATIO      NUMERIC(20,8),
           BASE_PRICE        NUMERIC(20,2),
           PRICE_TYPE        VARCHAR(4000),
           PRICE             NUMERIC(20,2),
           DISC_TYPE         VARCHAR(4000),
           DISC_RATIO        NUMERIC(20,2),
           DISC_AMT          NUMERIC(20,2),
           TAX_RATE          NUMERIC(20,8),
           TAX_AMT           NUMERIC(20,2),
           NET_AMT           NUMERIC(20,2),
           AMT               NUMERIC(20,2),
           REMARK            VARCHAR(2000),
           CREATE_USER_ID    NUMBER(18),
           CREATE_TIME       DATE,
           MODIFY_USER_ID    NUMBER(18),
           MODIFY_TIME       DATE,
           AUDIT_DATA_VERSION NUMBER,
           DELETE_FLAG       NUMERIC(1)
    );
    alter  table B2B_SO_D  add constraint PK_B2B_SO_D_ID primary key (ID);

    /*
    *销售订单模板
    */
    create table  B2B_TSO
    (
           ID                NUMBER(18) not null,
           TENANT_ID         NUMBER(18),
           OU_ID             NUMBER(18),
           ORG_ID            NUMBER(18),
           DOC_NO            VARCHAR(4000),
           DOC_TYPE          VARCHAR(4000),
           DOC_STATUS        VARCHAR(4000),
           DOC_TIME          DATE,
           CUST_ID           NUMBER(18),
           PAYMENT_TERM      VARCHAR(40),
           CURR_CODE         VARCHAR(40),
           TAX_CODE          VARCHAR(40),
           DELIVER_WAY       VARCHAR(40),
           DELIVER_CONTACT   VARCHAR(4000),
           DELIVER_ADDRESS_ID NUMBER(18),
           REMARK            VARCHAR(2000),
           CREATE_USER_ID    NUMBER(18),
           CREATE_TIME       DATE,
           MODIFY_USER_ID    NUMBER(18),
           MODIFY_TIME       DATE,
           AUDIT_DATA_VERSION INTEGER,
           DELETE_FLAG       NUMERIC(1)
    );
    alter  table B2B_TSO  add constraint PK_B2B_TSO_ID primary key (ID);

    /*
    *销售订单模板明细
    */
    create table  B2B_TSO_D
    (
           ID                NUMBER(18) not null,
           TENANT_ID         NUMBER(18),
           TSO_ID            NUMBER(14),
           LINE_NO           NUMERIC(20,2),
           WH_ID             NUMBER(18),
           ITEM_ID           NUMBER(18),
           SKU_ID            NUMBER(18),
           LINE_TYPE         VARCHAR(4000),
           QTY               NUMERIC(20,4),
           UOM               VARCHAR(4000),
           REMARK            VARCHAR(2000),
           CREATE_USER_ID    NUMBER(18),
           CREATE_TIME       DATE,
           MODIFY_USER_ID    NUMBER(18),
           MODIFY_TIME       DATE,
           AUDIT_DATA_VERSION NUMBER(18),
           DELETE_FLAG       NUMERIC(1)
    );
    alter  table B2B_TSO_D  add constraint PK_B2B_TSO_D_ID primary key (ID);

    /** 创建自增序列**/
    CREATE SEQUENCE SEQ_PB_OU  INCREMENT BY 1 START WITH 1 MINVALUE 1 MAXVALUE 999999999999999999999999999;
    CREATE SEQUENCE SEQ_PB_ITEM_SALEPRICE  INCREMENT BY 1 START WITH 1 MINVALUE 1 MAXVALUE 999999999999999999999999999;
    CREATE SEQUENCE SEQ_PB_ITEM  INCREMENT BY 1 START WITH 1 MINVALUE 1 MAXVALUE 999999999999999999999999999;
    CREATE SEQUENCE SEQ_PB_CUST  INCREMENT BY 1 START WITH 1 MINVALUE 1 MAXVALUE 999999999999999999999999999;
    CREATE SEQUENCE SEQ_PB_ADDR_QUALIFY  INCREMENT BY 1 START WITH 1 MINVALUE 1 MAXVALUE 999999999999999999999999999;

    CREATE SEQUENCE SEQ_PB_ADDRESS  INCREMENT BY 1 START WITH 1 MINVALUE 1 MAXVALUE 999999999999999999999999999;
    CREATE SEQUENCE SEQ_PB_ADDR  INCREMENT BY 1 START WITH 1 MINVALUE 1 MAXVALUE 999999999999999999999999999;
    CREATE SEQUENCE SEQ_B2B_SO_D  INCREMENT BY 1 START WITH 1 MINVALUE 1 MAXVALUE 999999999999999999999999999;
    CREATE SEQUENCE SEQ_B2B_SO  INCREMENT BY 1 START WITH 1 MINVALUE 1 MAXVALUE 999999999999999999999999999;
    CREATE SEQUENCE SEQ_B2B_TSO  INCREMENT BY 1 START WITH 1 MINVALUE 1 MAXVALUE 999999999999999999999999999;
    CREATE SEQUENCE SEQ_B2B_TSO_D  INCREMENT BY 1 START WITH 1 MINVALUE 1 MAXVALUE 999999999999999999999999999;

   --没用 CREATE SEQUENCE SEQ_DOC_NO  INCREMENT BY 1 START WITH 1 MINVALUE 1 MAXVALUE 999999999999999999999999999;


    /**以下为表备注**/
    comment on table PB_ITEM is '商品主文件';
    comment on column  PB_ITEM.TENANT_ID is '租户ID';
    comment on column  PB_ITEM.OU_ID is '公司ID';
    comment on column  PB_ITEM.ORG_ID is '组织ID';
    comment on column  PB_ITEM.ITEM_CODE is '品项编号';
    comment on column  PB_ITEM.ITEM_NAME is '品项名称';
    comment on column  PB_ITEM.ITEM_ABBR is '品项简称';
    comment on column  PB_ITEM.ID is '主键';
    comment on column  PB_ITEM.ITEM_TITLE is '品项标题';
    comment on column  PB_ITEM.ITEM_DESC is '品项描述';
    comment on column  PB_ITEM.SUB_TITLE is '副标题';
    comment on column  PB_ITEM.MEM_CODE is '助记码';
    comment on column  PB_ITEM.STORE_TYPE is '存储类型';
    comment on column  PB_ITEM.PINYIN is '拼音';
    comment on column  PB_ITEM.PINYIN_SH is '拼音简称';
    comment on column  PB_ITEM.PROMOTION_INFO is '促销信息';
    comment on column  PB_ITEM.KEYWORDS is '关键词';
    comment on column  PB_ITEM.MARKS is '标记';
    comment on column  PB_ITEM.ITEM_STATUS is '品项状态';
    comment on column  PB_ITEM.ITEM_STATUS2 is '品项状态2';
    comment on column  PB_ITEM.LISTING_STATUS is '上架状态';
    comment on column  PB_ITEM.C1_CODE is '大类编号';
    comment on column  PB_ITEM.C2_CODE is '中类编号';
    comment on column  PB_ITEM.C3_CODE is '小类编号';
    comment on column  PB_ITEM.UOM is '主计量单位';
    comment on column  PB_ITEM.UOM2 is '计量单位2';
    comment on column  PB_ITEM.UOM3 is '计量单位3';
    comment on column  PB_ITEM.UOM4 is '计量单位4';
    comment on column  PB_ITEM.UOM5 is '计量单位5';
    comment on column  PB_ITEM.UOM6 is '计量单位6';
    comment on column  PB_ITEM.UOM7 is '计量单位7';
    comment on column  PB_ITEM.UOM8 is '计量单位8';
    comment on column  PB_ITEM.UOM9 is '计量单位9';
    comment on column  PB_ITEM.UOM10 is '计量单位10';
    comment on column  PB_ITEM.SHIP_UOM is '发运计量单位';
    comment on column  PB_ITEM.GROSS_WEIGHT is '毛重';
    comment on column  PB_ITEM.NET_WEIGHT is '净重';
    comment on column  PB_ITEM.WEIGHT_UOM is '重量单位';
    comment on column  PB_ITEM.VOLUME is '体积';
    comment on column  PB_ITEM.MOQ is '最小起订量';
    comment on column  PB_ITEM.SAFE_QTY is '安全库存';
    comment on column  PB_ITEM.MAX_QTY is '最大库存量';
    comment on column  PB_ITEM.SALE_PRICE is '默认售价';
    comment on column  PB_ITEM.PRICE_GROUP is '价格组';
    comment on column  PB_ITEM.PRICE_PERIOD is '调价周期';
    comment on column  PB_ITEM.BRAND is '品牌';
    comment on column  PB_ITEM.MATERIAL is '材质';
    comment on column  PB_ITEM.GRADE is '等级';
    comment on column  PB_ITEM.STANDARD is '标准';
    comment on column  PB_ITEM.SURFACE is '表面处理';
    comment on column  PB_ITEM.ORIGIN is '产地';
    comment on column  PB_ITEM.SPEC is '规格';
    comment on column  PB_ITEM.P1 is '属性1';
    comment on column  PB_ITEM.P2 is '属性2';
    comment on column  PB_ITEM.P3 is '属性3';
    comment on column  PB_ITEM.P4 is '属性4';
    comment on column  PB_ITEM.P5 is '属性5';
    comment on column  PB_ITEM.P6 is '属性6';
    comment on column  PB_ITEM.P7 is '属性7';
    comment on column  PB_ITEM.P8 is '属性8';
    comment on column  PB_ITEM.REMARK is '备注';
    comment on column  PB_ITEM.OUTER_CODE is '外部编码';
    comment on column  PB_ITEM.CREATE_USER_ID is '创建人ID';
    comment on column  PB_ITEM.CREATE_TIME is '创建时间';
    comment on column  PB_ITEM.MODIFY_USER_ID is '最后编辑人ID';
    comment on column  PB_ITEM.MODIFY_TIME is '最后编辑时间';
    comment on column  PB_ITEM.AUDIT_DATA_VERSION is '数据版本号';
    comment on column  PB_ITEM.DELETE_FLAG is '是否删除';
    comment on column  PB_ITEM.ITEM_STAKE_TYPE is '盘点类型';
    comment on column  PB_ITEM.ITEM_PROP is '物料属性';
    comment on column  PB_ITEM.REP_ITEM_ID is '替代原料ID';
    comment on column  PB_ITEM.OTHER_PROP1 is '预留字段1';
    comment on column  PB_ITEM.OTHER_PROP2 is '预留字段2';
    comment on column  PB_ITEM.OTHER_PROP3 is '预留字段3';
    comment on column  PB_ITEM.OTHER_PROP4 is '预留字段4';
    comment on column  PB_ITEM.OTHER_PROP5 is '预留字段5';
    comment on column  PB_ITEM.OTHER_PROP6 is '预留字段6';
    comment on column  PB_ITEM.OTHER_PROP7 is '预留字段7';
    comment on column  PB_ITEM.OTHER_PROP8 is '预留字段8';
    comment on column  PB_ITEM.OTHER_PROP9 is '预留字段9';
    comment on column  PB_ITEM.OTHER_PROP10 is '预留字段10';
    comment on column  PB_ITEM.OTHER_PROP11 is '预留字段11';
    comment on column  PB_ITEM.OTHER_PROP12 is '预留字段12';
    comment on column  PB_ITEM.OTHER_PROP13 is '预留字段13';
    comment on column  PB_ITEM.OTHER_PROP14 is '预留字段14';
    comment on column  PB_ITEM.OTHER_PROP15 is '预留字段15';
    comment on column  PB_ITEM.OTHER_PROP16 is '预留字段16';
    comment on column  PB_ITEM.OTHER_PROP17 is '预留字段17';
    comment on column  PB_ITEM.OTHER_PROP18 is '预留字段18';
    comment on column  PB_ITEM.OU_CODE is '公司编码';


    comment on table PB_ITEM_SALEPRICE is '商品价格表';
    comment on column  PB_ITEM_SALEPRICE.ID is '主键';
    comment on column  PB_ITEM_SALEPRICE.TENANT_ID is '租户ID';
    comment on column  PB_ITEM_SALEPRICE.OU_ID is '公司ID';
    comment on column  PB_ITEM_SALEPRICE.ORG_ID is '组织ID';
    comment on column  PB_ITEM_SALEPRICE.PRICE_TYPE is '价格类型';
    comment on column  PB_ITEM_SALEPRICE.PRICE_TYPE2 is '价格类型2';
    comment on column  PB_ITEM_SALEPRICE.PRICE_TYPE3 is '价格类型3';
    comment on column  PB_ITEM_SALEPRICE.ITEM_ID is '品项ID';
    comment on column  PB_ITEM_SALEPRICE.ITEM_C1 is '品项大类编号';
    comment on column  PB_ITEM_SALEPRICE.ITEM_C2 is '品项中类编号';
    comment on column  PB_ITEM_SALEPRICE.ITEM_C3 is '品项小类编号';
    comment on column  PB_ITEM_SALEPRICE.CUST_GROUP is '客户组别';
    comment on column  PB_ITEM_SALEPRICE.CUST_ID is '客户ID';
    comment on column  PB_ITEM_SALEPRICE.SALE_REGION is '销售区域';
    comment on column  PB_ITEM_SALEPRICE.PRICE_GROUP is '价格组';
    comment on column  PB_ITEM_SALEPRICE.SHIPTO_ADDR_NO is '配送地址号';
    comment on column  PB_ITEM_SALEPRICE.PRICE is '价格';
    comment on column  PB_ITEM_SALEPRICE.UOM is '单位';
    comment on column  PB_ITEM_SALEPRICE.PRICE_UOM is '价格单位';
    comment on column  PB_ITEM_SALEPRICE.CURR_CODE is '币种';
    comment on column  PB_ITEM_SALEPRICE.VALID_FROM is '生效时间';
    comment on column  PB_ITEM_SALEPRICE.VALID_TO is '失效时间';
    comment on column  PB_ITEM_SALEPRICE.FROM_QTY is '从数量';
    comment on column  PB_ITEM_SALEPRICE.TO_QTY is '到数量';
    comment on column  PB_ITEM_SALEPRICE.TOLERANCE is '调价允差';
    comment on column  PB_ITEM_SALEPRICE.REMARK is '备注';
    comment on column  PB_ITEM_SALEPRICE.CREATE_USER_ID is '创建人ID';
    comment on column  PB_ITEM_SALEPRICE.CREATE_TIME is '创建时间';
    comment on column  PB_ITEM_SALEPRICE.MODIFY_USER_ID is '最后编辑人ID';
    comment on column  PB_ITEM_SALEPRICE.MODIFY_TIME is '最后编辑时间';
    comment on column  PB_ITEM_SALEPRICE.AUDIT_DATA_VERSION is '数据版本号';
    comment on column  PB_ITEM_SALEPRICE.DELETE_FLAG is '是否删除';
    comment on column  PB_ITEM_SALEPRICE.ITEM_CODE is '商品编码';
    comment on column  PB_ITEM_SALEPRICE.CUST_CODE is '客户编码';


    comment on table PB_OU is '公司主表';
    comment on column  PB_OU.ID is '主键';
    comment on column  PB_OU.TENANT_ID is '租户ID';
    comment on column  PB_OU.OU_CODE is '公司编号';
    comment on column  PB_OU.OU_NAME is '公司名称';
    comment on column  PB_OU.OU_ABBR is '公司简称';
    comment on column  PB_OU.OU_TYPE is '公司类型';
    comment on column  PB_OU.OU_STATUS is '公司状态';
    comment on column  PB_OU.PID is '上级ID';
    comment on column  PB_OU.OU_CURR is '本位币';
    comment on column  PB_OU.ADDR_NO is '地址号';
    comment on column  PB_OU.REGION is '区域';
    comment on column  PB_OU.REMARK is '备注';
    comment on column  PB_OU.OUTER_CODE is '外部编码';
    comment on column  PB_OU.CREATE_USER_ID is '创建人ID';
    comment on column  PB_OU.CREATE_TIME is '创建时间';
    comment on column  PB_OU.MODIFY_USER_ID is '最后编辑人ID';
    comment on column  PB_OU.MODIFY_TIME is '最后编辑时间';
    comment on column  PB_OU.AUDIT_DATA_VERSION is '数据版本号';
    comment on column  PB_OU.DELETE_FLAG is '是否删除';


    comment on table PB_ADDR is '地址簿';
    comment on column  PB_ADDR.ID is '主键';
    comment on column  PB_ADDR.TENANT_ID is '租户ID';
    comment on column  PB_ADDR.ADDR_NO is '地址号';
    comment on column  PB_ADDR.ADDR_CODE is '地址代码';
    comment on column  PB_ADDR.ADDR_TYPE is '地址簿类型';
    comment on column  PB_ADDR.ADDR_NAME is '地址簿名称';
    comment on column  PB_ADDR.ADDR_STATUS is '地址簿状态';
    comment on column  PB_ADDR.REMARK is '备注';
    comment on column  PB_ADDR.OUTER_CODE is '外部编码';
    comment on column  PB_ADDR.CREATE_USER_ID is '创建人ID';
    comment on column  PB_ADDR.CREATE_TIME is '创建时间';
    comment on column  PB_ADDR.MODIFY_USER_ID is '最后编辑人ID';
    comment on column  PB_ADDR.MODIFY_TIME is '最后编辑时间';
    comment on column  PB_ADDR.AUDIT_DATA_VERSION is '数据版本号';
    comment on column  PB_ADDR.DELETE_FLAG is '是否删除';


    comment on table PB_ADDRESS is '地址簿地址信息';
    comment on column  PB_ADDRESS.ID is '主键';
    comment on column  PB_ADDRESS.TENANT_ID is '租户ID';
    comment on column  PB_ADDRESS.ADDR_NO is '地址号';
    comment on column  PB_ADDRESS.LINE_NO is '行号';
    comment on column  PB_ADDRESS.ADDRESS_TYPE is '地址类型';
    comment on column  PB_ADDRESS.DEFAULT_FLAG is '是否默认';
    comment on column  PB_ADDRESS.ADDRESS_STATUS is '地址状态';
    comment on column  PB_ADDRESS.CONT_PERSON is '联系人姓名';
    comment on column  PB_ADDRESS.POSITION is '职位';
    comment on column  PB_ADDRESS.PID is '上级联系人ID';
    comment on column  PB_ADDRESS.MOBILE is '手机';
    comment on column  PB_ADDRESS.MOBILE2 is '手机2';
    comment on column  PB_ADDRESS.TEL is '电话';
    comment on column  PB_ADDRESS.TEL2 is '电话2';
    comment on column  PB_ADDRESS.FAX is '传真';
    comment on column  PB_ADDRESS.EMAIL is '电邮';
    comment on column  PB_ADDRESS.EMAIL2 is '电邮2';
    comment on column  PB_ADDRESS.COUNTRY is '国家';
    comment on column  PB_ADDRESS.PROVINCE is '省';
    comment on column  PB_ADDRESS.CITY is '市';
    comment on column  PB_ADDRESS.PREFECTURE is '县／区／镇';
    comment on column  PB_ADDRESS.DISTRICT is '区／街道／村';
    comment on column  PB_ADDRESS.DETAILADDR is '详细地址';
    comment on column  PB_ADDRESS.ZIPCODE is '邮编';
    comment on column  PB_ADDRESS.WEBADDRESS is '网址';
    comment on column  PB_ADDRESS.WEIBO is '微博';
    comment on column  PB_ADDRESS.WECHAT_MP is '微信公众号';
    comment on column  PB_ADDRESS.SNS is '社交';
    comment on column  PB_ADDRESS.SNS2 is '社交2';
    comment on column  PB_ADDRESS.SNS3 is '社交3';
    comment on column  PB_ADDRESS.REMARK is '备注';
    comment on column  PB_ADDRESS.CREATE_USER_ID is '创建人ID';
    comment on column  PB_ADDRESS.CREATE_TIME is '创建时间';
    comment on column  PB_ADDRESS.MODIFY_USER_ID is '最后编辑人ID';
    comment on column  PB_ADDRESS.MODIFY_TIME is '最后编辑时间';
    comment on column  PB_ADDRESS.AUDIT_DATA_VERSION is '数据版本号';
    comment on column  PB_ADDRESS.DELETE_FLAG is '是否删除';
    comment on column  PB_ADDRESS.ADDR_NAME is '地址';
    comment on column  PB_ADDRESS.CUST_CODE is '客户号';
    comment on column  PB_ADDRESS.OU_CODE is '公司号';


    comment on table PB_CUST is '客户基础信息';
    comment on column  PB_CUST.ID is '主键';
    comment on column  PB_CUST.TENANT_ID is '租户ID';
    comment on column  PB_CUST.OU_ID is '公司ID';
    comment on column  PB_CUST.ORG_ID is '组织ID';
    comment on column  PB_CUST.CUST_CODE is '客户编号';
    comment on column  PB_CUST.CUST_NAME is '客户名称';
    comment on column  PB_CUST.CUST_ABBR is '客户简称';
    comment on column  PB_CUST.CUST_TYPE is '客户类型';
    comment on column  PB_CUST.CUST_STATUS is '客户状态';
    comment on column  PB_CUST.PID is '上级ID';
    comment on column  PB_CUST.ADDR_NO is '地址号';
    comment on column  PB_CUST.PINYIN is '拼音';
    comment on column  PB_CUST.PINYIN_SH is '拼音简称';
    comment on column  PB_CUST.COMP_CAPITAL is '资金能力';
    comment on column  PB_CUST.AGENT_EMP_ID is '业务员员工ID';
    comment on column  PB_CUST.CUST_CURR is '结算币种';
    comment on column  PB_CUST.CREDIT_LIMIT is '信用额度';
    comment on column  PB_CUST.CREDIT_BAL is '信用余额';
    comment on column  PB_CUST.PAY_METHOD is '付款方式';
    comment on column  PB_CUST.PAYMENT_TERM is '支付条款';
    comment on column  PB_CUST.SETTLE_TYPE is '结算方式';
    comment on column  PB_CUST.RECON_PERIOD is '对账周期';
    comment on column  PB_CUST.SETTLE_MONTHLY_DAY is '月结日期';
    comment on column  PB_CUST.DEF_ORG_ID is '默认组织ID';
    comment on column  PB_CUST.DEF_WH_ID is '默认仓库ID';
    comment on column  PB_CUST.C1 is '行业大类';
    comment on column  PB_CUST.C2 is '行业中类';
    comment on column  PB_CUST.C3 is '行业小类';
    comment on column  PB_CUST.COUNTRY is '国家';
    comment on column  PB_CUST.POSTCODE is '邮编';
    comment on column  PB_CUST.REGION is '区域';
    comment on column  PB_CUST.CUST_LEVEL is '客户等级';
    comment on column  PB_CUST.CUST_GROUP is '客户组别';
    comment on column  PB_CUST.VIP_NO is '会员号码';
    comment on column  PB_CUST.VIP_LEVEL is '会员级别';
    comment on column  PB_CUST.VIP_GROUP is '会员组别';
    comment on column  PB_CUST.CUST_POINT_FLAG is '是否积分';
    comment on column  PB_CUST.POINT is '积分余额';
    comment on column  PB_CUST.CUST_SOURCE is '客户来源';
    comment on column  PB_CUST.VALID_FROM is '生效日期';
    comment on column  PB_CUST.VALID_TO is '失效日期';
    comment on column  PB_CUST.TAXPAYER_TYPE is '纳税人类型';
    comment on column  PB_CUST.INV_TYPE is '发票类型';
    comment on column  PB_CUST.INV_TITLE is '开票抬头';
    comment on column  PB_CUST.INV_TAXNO is '发票税号';
    comment on column  PB_CUST.TAX_CODE is '税码';
    comment on column  PB_CUST.IC_REGISTER_NO is '工商登记号';
    comment on column  PB_CUST.REGISTER_DATE is '注册日期';
    comment on column  PB_CUST.REGISTER_ADDRESS is '注册地址';
    comment on column  PB_CUST.REGISTER_TRADEMARK_NO is '注册商标编号';
    comment on column  PB_CUST.REGISTER_FUND is '注册资金';
    comment on column  PB_CUST.REGISTER_FUND_CURRY is '注册资金货币';
    comment on column  PB_CUST.REPR is '法人代表';
    comment on column  PB_CUST.COMP_NAME is '公司名称';
    comment on column  PB_CUST.COMP_PROP is '公司性质';
    comment on column  PB_CUST.COMP_SCALE is '公司规模';
    comment on column  PB_CUST.COMP_BUSSADDR is '经营地点';
    comment on column  PB_CUST.COMP_MAINBUSS is '主营业务';
    comment on column  PB_CUST.COMP_BUSSRANGE is '经营范围';
    comment on column  PB_CUST.REMARK is '备注';
    comment on column  PB_CUST.OUTER_CODE is '';
    comment on column  PB_CUST.CREATE_USER_ID is '创建人ID';
    comment on column  PB_CUST.CREATE_TIME is '创建时间';
    comment on column  PB_CUST.MODIFY_USER_ID is '最后编辑人ID';
    comment on column  PB_CUST.MODIFY_TIME is '最后编辑时间';
    comment on column  PB_CUST.AUDIT_DATA_VERSION is '数据版本号';
    comment on column  PB_CUST.DELETE_FLAG is '是否删除';
    comment on column  PB_CUST.OU_CODE is '公司编码';


    comment on table PB_ADDR_QUALIFY is '客户证照信息';
    comment on column  PB_ADDR_QUALIFY.ID is '主键';
    comment on column  PB_ADDR_QUALIFY.TENANT_ID is '租户ID';
    comment on column  PB_ADDR_QUALIFY.ADDR_NO is '地址号';
    comment on column  PB_ADDR_QUALIFY.LINE_NO is '行号';
    comment on column  PB_ADDR_QUALIFY.QUALIFY_TYPE is '资质类型';
    comment on column  PB_ADDR_QUALIFY.QUALIFY_NAME is '资质名称';
    comment on column  PB_ADDR_QUALIFY.QUALIFY_NO is '资质文件编号';
    comment on column  PB_ADDR_QUALIFY.VALID_FROM is '生效时间';
    comment on column  PB_ADDR_QUALIFY.VALID_TO is '失效时间';
    comment on column  PB_ADDR_QUALIFY.NEXT_CHECK_DATE is '下次检验日期';
    comment on column  PB_ADDR_QUALIFY.ATTACH_REPO is '附件仓库名称';
    comment on column  PB_ADDR_QUALIFY.REMARK is '备注';
    comment on column  PB_ADDR_QUALIFY.CREATE_USER_ID is '创建人ID';
    comment on column  PB_ADDR_QUALIFY.CREATE_TIME is '创建时间';
    comment on column  PB_ADDR_QUALIFY.MODIFY_USER_ID is '最后编辑人ID';
    comment on column  PB_ADDR_QUALIFY.MODIFY_TIME is '最后编辑时间';
    comment on column  PB_ADDR_QUALIFY.AUDIT_DATA_VERSION is '数据版本号';
    comment on column  PB_ADDR_QUALIFY.DELETE_FLAG is '是否删除';
    comment on column  PB_ADDR_QUALIFY.VALID_LV1 is 'I类经营范围';
    comment on column  PB_ADDR_QUALIFY.VALID_LV2 is 'II类经营范围';
    comment on column  PB_ADDR_QUALIFY.VALID_LV3 is 'III类经营范围';
    comment on column  PB_ADDR_QUALIFY.CERT_STATUS is '证照状态';


    comment on table B2B_SO is '订单表头';
    comment on column  B2B_SO.ID is '主键';
    comment on column  B2B_SO.TENANT_ID is '租户ID';
    comment on column  B2B_SO.OU_ID is '公司ID';
    comment on column  B2B_SO.ORG_ID is '组织ID';
    comment on column  B2B_SO.DOC_NO is '单据编号';
    comment on column  B2B_SO.DOC_TYPE is '单据类型';
    comment on column  B2B_SO.DOC_STATUS is '单据状态';
    comment on column  B2B_SO.DOC_TIME is '下单时间';
    comment on column  B2B_SO.SO_BATCH_ID is '订单批ID';
    comment on column  B2B_SO.DOC_NO2 is '单据编号2';
    comment on column  B2B_SO.DOC_TYPE2 is '单据类型2';
    comment on column  B2B_SO.SO_SOURCE is '下单渠道';
    comment on column  B2B_SO.SALE_REGION is '销售区域';
    comment on column  B2B_SO.DELIVER_REGION is '配送区域';
    comment on column  B2B_SO.CUST_ID is '客户ID';
    comment on column  B2B_SO.CUST_CODE is '客户编号';
    comment on column  B2B_SO.SO_EMP_ID is '下单员工ID';
    comment on column  B2B_SO.AGENT_EMP_ID is '业务员员工ID';
    comment on column  B2B_SO.CURR_CODE is '货币码';
    comment on column  B2B_SO.TAX_CODE is '税率码';
    comment on column  B2B_SO.APPR_STATUS is '审核状态';
    comment on column  B2B_SO.APPR_TIME is '审核时间';
    comment on column  B2B_SO.APPR_USER_ID is '审核人ID';
    comment on column  B2B_SO.PAY_STATUS is '支付状态';
    comment on column  B2B_SO.PAY_TIME is '支付时间';
    comment on column  B2B_SO.PAY_TRANS_ID is '支付交易ID';
    comment on column  B2B_SO.PAY_METHOD is '付款方式';
    comment on column  B2B_SO.PAYMENT_TERM is '支付条款';
    comment on column  B2B_SO.PL_DATE is '拣货时间';
    comment on column  B2B_SO.DELIVER_STATUS is '配送状态';
    comment on column  B2B_SO.DELIVER_WAY is '送货方式';
    comment on column  B2B_SO.DELIVER_CONTACT is '配送联系人';
    comment on column  B2B_SO.DELIVER_ADDRESS_ID is '配送地址ID';
    comment on column  B2B_SO.DELIVER_ADDRESS is '配送地址';
    comment on column  B2B_SO.SHIP_TIME is '出货时间';
    comment on column  B2B_SO.DELIVER_TIME is '配送送达时间';
    comment on column  B2B_SO.RECEIVE_TIME is '确认收货时间';
    comment on column  B2B_SO.RETURN_STATUS is '退货状态';
    comment on column  B2B_SO.RETURN_APPLY_TIME is '退货申请时间';
    comment on column  B2B_SO.RETURN_APPR_TIME is '退货批准时间';
    comment on column  B2B_SO.COMMENT_STATUS is '评价状态';
    comment on column  B2B_SO.COMMENT_TYPE is '评价类型';
    comment on column  B2B_SO.COMMENT_TIME is '评价时间';
    comment on column  B2B_SO.COMMENT_CONTENT is '评价内容';
    comment on column  B2B_SO.CANCEL_TIME is '取消时间';
    comment on column  B2B_SO.CANCEL_REASON is '取消原因';
    comment on column  B2B_SO.REFUND_STATUS is '退款状态';
    comment on column  B2B_SO.REFUND_TIME is '退款时间';
    comment on column  B2B_SO.REFUND_AMT is '退款金额';
    comment on column  B2B_SO.DISC_AMT is '折扣金额';
    comment on column  B2B_SO.TAX_RATE is '税率';
    comment on column  B2B_SO.TAX_AMT is '税额';
    comment on column  B2B_SO.NET_AMT is '不含税金额';
    comment on column  B2B_SO.FREIGHT_FEE is '运费金额';
    comment on column  B2B_SO.AMT is '订单金额';
    comment on column  B2B_SO.TSO_ID is '模板ID';
    comment on column  B2B_SO.REMARK is '备注';
    comment on column  B2B_SO.OUTER_CODE is '外部编码';
    comment on column  B2B_SO.CREATE_USER_ID is '创建人ID';
    comment on column  B2B_SO.CREATE_TIME is '创建时间';
    comment on column  B2B_SO.MODIFY_USER_ID is '最后编辑人ID';
    comment on column  B2B_SO.MODIFY_TIME is '最后编辑时间';
    comment on column  B2B_SO.AUDIT_DATA_VERSION is '数据版本号';
    comment on column  B2B_SO.DELETE_FLAG is '是否删除';


    comment on table B2B_SO_D is '订单明细';
    comment on column  B2B_SO_D.ID is '主键';
    comment on column  B2B_SO_D.SO_ID is '订单ID';
    comment on column  B2B_SO_D.LINE_NO is '行号';
    comment on column  B2B_SO_D.TENANT_ID is '租户ID';
    comment on column  B2B_SO_D.WH_ID is '出库仓库ID';
    comment on column  B2B_SO_D.ITEM_ID is '品项ID';
    comment on column  B2B_SO_D.ITEM_CODE is '品项编号';
    comment on column  B2B_SO_D.ITEM_NAME is '品项名称';
    comment on column  B2B_SO_D.SKU_ID is 'SKUID';
    comment on column  B2B_SO_D.LINE_TYPE is '行类型';
    comment on column  B2B_SO_D.LINE_STATUS is '行状态';
    comment on column  B2B_SO_D.PAY_STATUS is '支付状态';
    comment on column  B2B_SO_D.QTY is '数量';
    comment on column  B2B_SO_D.UOM is '单位';
    comment on column  B2B_SO_D.WEIGHT is '重量';
    comment on column  B2B_SO_D.WEIGHT_UOM is '重量单位';
    comment on column  B2B_SO_D.WEIGHT_RATIO is '重量转换率';
    comment on column  B2B_SO_D.BASE_PRICE is '面价';
    comment on column  B2B_SO_D.PRICE_TYPE is '价格类型';
    comment on column  B2B_SO_D.PRICE is '价格';
    comment on column  B2B_SO_D.DISC_TYPE is '折扣类型';
    comment on column  B2B_SO_D.DISC_RATIO is '折扣率';
    comment on column  B2B_SO_D.DISC_AMT is '折扣额';
    comment on column  B2B_SO_D.TAX_RATE is '税率';
    comment on column  B2B_SO_D.TAX_AMT is '税额';
    comment on column  B2B_SO_D.NET_AMT is '不含税金额';
    comment on column  B2B_SO_D.AMT is '金额';
    comment on column  B2B_SO_D.REMARK is '备注';
    comment on column  B2B_SO_D.CREATE_USER_ID is '创建人ID';
    comment on column  B2B_SO_D.CREATE_TIME is '创建时间';
    comment on column  B2B_SO_D.MODIFY_USER_ID is '最后编辑人ID';
    comment on column  B2B_SO_D.MODIFY_TIME is '最后编辑时间';
    comment on column  B2B_SO_D.AUDIT_DATA_VERSION is '数据版本号';
    comment on column  B2B_SO_D.DELETE_FLAG is '是否删除';


    comment on table B2B_TSO is '销售订单模板';
    comment on column B2B_TSO.ID is '主键';
    comment on column B2B_TSO.TENANT_ID is '租户ID';
    comment on column B2B_TSO.OU_ID is '公司ID';
    comment on column B2B_TSO.ORG_ID is '组织ID';
    comment on column B2B_TSO.DOC_NO is '模板编号';
    comment on column B2B_TSO.DOC_TYPE is '模板类型';
    comment on column B2B_TSO.DOC_STATUS is '模板状态';
    comment on column B2B_TSO.DOC_TIME is '生成时间';
    comment on column B2B_TSO.CUST_ID is '客户ID';
    comment on column B2B_TSO.PAYMENT_TERM is '支付条款';
    comment on column B2B_TSO.CURR_CODE is '货币码';
    comment on column B2B_TSO.TAX_CODE is '税率码';
    comment on column B2B_TSO.DELIVER_WAY is '送货方式';
    comment on column B2B_TSO.DELIVER_CONTACT is '配送联系人';
    comment on column B2B_TSO.DELIVER_ADDRESS_ID is '配送地址ID';
    comment on column B2B_TSO.REMARK is '备注';
    comment on column B2B_TSO.CREATE_USER_ID is '创建人ID';
    comment on column B2B_TSO.CREATE_TIME is '创建时间';
    comment on column B2B_TSO.MODIFY_USER_ID is '最后编辑人ID';
    comment on column B2B_TSO.MODIFY_TIME is '最后编辑时间';
    comment on column B2B_TSO.AUDIT_DATA_VERSION is '数据版本号';
    comment on column B2B_TSO.DELETE_FLAG is '是否删除';


    comment on table B2B_TSO_D is '销售订单模板明细';
    comment on column  B2B_TSO_D.ID   is '主键';
    comment on column  B2B_TSO_D.TENANT_ID    is '租户ID';
    comment on column  B2B_TSO_D.TSO_ID       is '模板ID';
    comment on column  B2B_TSO_D.LINE_NO      is '行号';
    comment on column  B2B_TSO_D.WH_ID        is '出库仓库ID';
    comment on column  B2B_TSO_D.ITEM_ID      is '品项ID';
    comment on column  B2B_TSO_D.SKU_ID       is 'SKUID';
    comment on column  B2B_TSO_D.LINE_TYPE    is '行类型';
    comment on column  B2B_TSO_D.QTY          is '数量';
    comment on column  B2B_TSO_D.UOM          is '单位';
    comment on column  B2B_TSO_D.REMARK       is '备注';
    comment on column  B2B_TSO_D.CREATE_USER_ID    is '创建人ID';
    comment on column  B2B_TSO_D.CREATE_TIME  is '创建时间';
    comment on column  B2B_TSO_D.MODIFY_USER_ID    is '最后编辑人ID';
    comment on column  B2B_TSO_D.MODIFY_TIME  is '最后编辑时间';
    comment on column  B2B_TSO_D.AUDIT_DATA_VERSION is '数据版本号';
    comment on column  B2B_TSO_D.DELETE_FLAG  is '是否删除';


    /**删除所以的表**/
    -- select 'drop table '||table_name||';' from cat where table_type='TABLE';

    -- drop table PB_OU;
    -- drop table PB_ITEM_SALEPRICE;
    -- drop table PB_ITEM;
    -- drop table PB_CUST;
    -- drop table PB_ADDR_QUALIFY;
    -- drop table PB_ADDRESS;
    -- drop table PB_ADDR;
    -- drop table B2B_TSO_D;
    -- drop table B2B_TSO;
    -- drop table B2B_SO_D;
    -- drop table B2B_SO;
