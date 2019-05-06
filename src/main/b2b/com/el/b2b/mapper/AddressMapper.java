package com.el.b2b.mapper;

import com.el.b2b.api.AddressQuery;
import com.el.b2b.domain.Address;
import com.el.b2b.domain.AddressCodeName;
import com.el.core.jdbc.Sql;
import com.el.core.util.SqlUtil;
import com.el.edp.util.StringUtils;
import com.el.mbg.mapper.TPbAddressMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * @Auther: shuaiyin.shang
 * @Date: 2018/5/14
 * @Description: 地址簿地址信息Mapper
 */
public interface AddressMapper extends TPbAddressMapper {

    class SqlBuilder extends Sql {
        private void SELECT_ALL() {
            SELECT(" T.ID AS id");
            SELECT(" T.TENANT_ID AS tenantId");
            SELECT(" T.ADDR_NO AS addrNo");
            SELECT(" T.LINE_NO AS lineNo");
            SELECT(" T.ADDRESS_TYPE AS addressType");
            SELECT(" T.DEFAULT_FLAG AS defaultFlag");
            SELECT(" T.ADDRESS_STATUS AS addressStatus");
            SELECT(" T.CONT_PERSON AS contPerson");
            SELECT(" T.POSITION AS position");
            SELECT(" T.PID AS pid");
            SELECT(" T.MOBILE AS mobile");
            SELECT(" T.MOBILE2 AS mobile2");
            SELECT(" T.TEL AS tel");
            SELECT(" T.TEL2 AS tel2");
            SELECT(" T.FAX AS fax");
            SELECT(" T.EMAIL AS email");
            SELECT(" T.EMAIL2 AS email2");
            SELECT(" T.COUNTRY AS country");
            SELECT(" T.PROVINCE AS province");
            SELECT(" T.CITY AS city");
            SELECT(" T.PREFECTURE AS prefecture");
            SELECT(" T.DISTRICT AS district");
            SELECT(" T.DETAILADDR AS detailaddr");
            SELECT(" T.ZIPCODE AS zipcode");
            SELECT(" T.WEBADDRESS AS webaddress");
            SELECT(" T.WEIBO AS weibo");
            SELECT(" T.WECHAT_MP AS wechatMp");
            SELECT(" T.SNS AS sns");
            SELECT(" T.SNS2 AS sns2");
            SELECT(" T.SNS3 AS sns3");
            SELECT(" T.REMARK AS remark");
            SELECT(" T.CREATE_USER_ID AS createUserId");
            SELECT(" T.CREATE_TIME AS createTime");
            SELECT(" T.MODIFY_USER_ID AS modifyUserId");
            SELECT(" T.MODIFY_TIME AS modifyTime");
            SELECT(" T.AUDIT_DATA_VERSION AS auditDataVersion");
            SELECT(" T.DELETE_FLAG AS deleteFlag");
            SELECT(" T.ADDR_NAME AS addrName");
            SELECT(" T.OU_CODE AS ouCode");
            SELECT(" O.OU_NAME AS ouName");
            SELECT(" T.CUST_CODE AS custCode");
        }

        private void FROM() {
            FROM("PB_ADDRESS T");
            JOIN("PB_OU O ON O.OU_CODE = T.OU_CODE");
        }

        private void WHERE_DELETEFLAG() {
            WHERE(" (T.DELETE_FLAG <> 1 OR T.DELETE_FLAG IS NULL) ");
            WHERE(" (O.DELETE_FLAG <> 1 OR O.DELETE_FLAG IS NULL) ");
        }

        private void FILTER_BY(AddressQuery query) {
            //过滤客户
            if (!StringUtils.isEmpty(query.getCustCode())) {
                WHERE(" T.CUST_CODE =#{custCode} ");
            }
            //过滤公司
            if (!StringUtils.isEmpty(query.getOuCode())) {
                WHERE(" T.OU_CODE =#{ouCode} ");
            }
            //根据地址筛选
            if (!StringUtils.isEmpty(query.getAddrName())) {
                WHERE("T.ADDR_NAME LIKE " + SqlUtil.toSqlLikeString(query.getAddrName()));
            }
            //详细地址筛选
            if (!StringUtils.isEmpty(query.getDetailaddr())) {
                WHERE(" T.DETAILADDR LIKE " + SqlUtil.toSqlLikeString(query.getDetailaddr()));
            }
        }

        static final String FIND_COUNT = "findCount";

        public String findCount(AddressQuery query) {
            SELECT(" COUNT(1) n ");
            FROM();
            WHERE_DELETEFLAG();
            FILTER_BY(query);
            return toString();
        }

        static final String FIND_PAGE = "findPage";

        public String findPage(AddressQuery query) {
            SELECT_ALL();
            FROM();
            WHERE_DELETEFLAG();
            FILTER_BY(query);
            ORDER_BY(" T.ID DESC");
            return SqlUtil.paging(toString(), query);
        }


        private static final String FIND_BY_ID = "findById";

        public String findById() {
            SELECT_ALL();
            FROM();
            WHERE_DELETEFLAG();
            WHERE(" T.ID = #{id}");
            return toString();
        }

        private static final String CHECK_DELETE_FLAG = "checkDeleteFlag";

        public String checkDeleteFlag() {
            SELECT(" T.DELETE_FLAG as status");
            FROM();
            WHERE_DELETEFLAG();
            WHERE(" T.id = #{id}");
            return toString();
        }

        private static final String GET_ADDRESS_LIST = "getAddressList";

        public String getAddressList(AddressQuery query) {
            SELECT(" T.ID as id");
            SELECT(" T.CUST_CODE AS custCode");
            SELECT(" T.DETAILADDR AS name");
            SELECT(" T.ADDR_NO as code");
            SELECT(" T.MOBILE AS mobile");
            SELECT(" T.CONT_PERSON AS contPerson");
            SELECT(" O.OU_NAME AS ouName");

            FROM("PB_ADDRESS T");
            JOIN("PB_OU O ON O.OU_CODE = T.OU_CODE");
            JOIN("PB_CUST C ON (C.CUST_CODE = T.CUST_CODE AND C.OU_CODE=T.OU_CODE) ");
            WHERE(" (T.DELETE_FLAG <> 1 OR T.DELETE_FLAG IS NULL) ");
            WHERE(" (O.DELETE_FLAG <> 1 OR O.DELETE_FLAG IS NULL) ");
            WHERE(" (C.DELETE_FLAG <> 1 OR C.DELETE_FLAG IS NULL) ");
            WHERE(" T.OU_CODE = #{ouCode}");
            WHERE(" T.CUST_CODE = #{custCode}");
            ORDER_BY(" T.DEFAULT_FLAG DESC ");
            return toString();
        }
    }

    /**
     * 分页查询条目
     *
     * @param query 查询条件
     * @return 返回结果条数
     */
    @SelectProvider(type = SqlBuilder.class, method = SqlBuilder.FIND_COUNT)
    int findCount(AddressQuery query);

    /**
     * 分页查询
     *
     * @param query 查询条件
     * @return
     */
    @SelectProvider(type = SqlBuilder.class, method = SqlBuilder.FIND_PAGE)
    List<Address> findPage(AddressQuery query);

    /**
     * 按ID查询
     *
     * @param id
     * @return
     */
    @SelectProvider(type = SqlBuilder.class, method = SqlBuilder.FIND_BY_ID)
    Address findById(@Param("id") long id);


    /**
     * @param query
     * @return 地址列表
     */
    @SelectProvider(type = SqlBuilder.class, method = SqlBuilder.GET_ADDRESS_LIST)
    List<AddressCodeName> getAddressList(AddressQuery query);

    /**
     * 查询该 ID 地址 是否失效
     *
     * @param id
     * @return
     */
    @SelectProvider(type = SqlBuilder.class, method = SqlBuilder.CHECK_DELETE_FLAG)
    int checkDeleteFlag(@Param("id") long id);
}
