package com.el.b2b.sys.mapper;

import com.el.b2b.sys.TableMeta;
import com.el.core.util.SqlUtil;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.jdbc.SQL;

import java.time.LocalDateTime;
import java.util.List;

/**.
 * 公共表处理
 * Created by jerry.feng
 * 2018/05/17
 */
public interface TableBathMapper {
    final class SqlBuilder extends SQL {
        private static final String BATH_DELETE = "bathDelete";

        public String bathDelete(@Param("tableMeta") TableMeta tableMeta, @Param("idList") List<Long> idList) {
            UPDATE(tableMeta.getName());
            SET("DELETE_FLAG = 1");
            WHERE("id in " + SqlUtil.toSqlNumberSet(idList));
            return toString();
        }

        private static final String BATH_DELETE_STATUS = "bathDeleteStatus";

        public String bathDeleteStatus(@Param("tableMeta") TableMeta tableMeta, @Param("idList") List<Long> idList, @Param("status") String status , @Param("modifyId") Long modifyId, @Param("modifyTime") LocalDateTime modifyTime) {
            UPDATE(tableMeta.getName());
            SET("DELETE_FLAG = 1");
            SET("STATUS = #{status}");
//           SET("MODIFY_USER_ID = #{modifyId}");
            SET("MODIFY_TIME = #{modifyTime}");
            WHERE("id in " + SqlUtil.toSqlNumberSet(idList));
            return toString();
        }


    }

    //批量业务逻辑删除
//    @DeleteProvider(type = SqlBuilder.class, method = SqlBuilder.BATH_DELETE)
//    int deleteBath(@Param("tableMeta") TableMeta tableMeta, @Param("idList") List<Long> idList);

    @UpdateProvider(type = SqlBuilder.class, method = SqlBuilder.BATH_DELETE)
    int deleteBath(@Param("tableMeta") TableMeta tableMeta, @Param("idList") List<Long> idList);

    //批量业务逻辑删除 同时修改数据状态
//    @DeleteProvider(type = SqlBuilder.class, method = SqlBuilder.BATH_DELETE_STATUS)
//    int deleteBathStatus(@Param("tableMeta") TableMeta tableMeta, @Param("idList") List<Long> idList, @Param("status") String status, @Param("modifyTime") LocalDateTime modifyTime);

    //批量业务逻辑删除 同时修改数据状态
    @UpdateProvider(type = SqlBuilder.class, method = SqlBuilder.BATH_DELETE_STATUS)
    int deleteBathStatus(@Param("tableMeta") TableMeta tableMeta, @Param("idList") List<Long> idList, @Param("status") String status, @Param("modifyTime") LocalDateTime modifyTime);


}
