package com.el.edp.gen.mapper;

import com.el.edp.gen.domain.EdpGenDef;
import com.el.edp.gen.domain.EdpGenSeq;
import com.el.edp.gen.domain.EdpGenSeqCnt;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author neo.pan
 * @since 2018/04/12
 */
public interface EdpGenMapper {

    /**
     * @param tenantId 租户ID
     * @param appCode  应用代码
     * @return 指定租客应用的发号器定义列表
     */
    @Select({
        "select GEN_CODE code, GEN_NAME name, SEQ_FMT seqFmt, SEQ_KEY seqKey",
        ", CNT_KEY cntKey, CNT_LEN cntLen, CNT_PAD cntPad",
        ", CNT_MIN cntMin, CNT_MAX cntMax",
        "from PS_GEN_DEF where TENANT_ID = #{tid} and APP_CODE = #{app}",
    })
    List<EdpGenDef> genDefs(@Param("tid") long tenantId, @Param("app") String appCode);

    /**
     * 锁定指定发号器实例 (用于更新下一发号值)
     *
     * @param genSeq 发号上下文
     * @return 下一发号值
     */
    @Select({
        "select CNT_KEY \"key\", CNT_VAL val from PS_GEN_SEQ",
        " where GEN_CODE = #{genDef.code} and SEQ_KEY = #{seqKey}",
        " for update",
    })
    EdpGenSeqCnt getCntAndLockSeq(EdpGenSeq genSeq);

    /**
     * 更新下一个发号值
     *
     * @param genSeq 发号上下文
     * @return 更新记录数(如果为零则需新增发号器)
     */
    @Update({
        "update PS_GEN_SEQ set CNT_KEY = #{cntKey}, CNT_VAL = #{cntValNext}",
        " where GEN_CODE = #{genDef.code} and SEQ_KEY = #{seqKey}",
    })
    int updateCnt(EdpGenSeq genSeq);

    /**
     * 锁定指定发号器定义 (用于新增发号器)
     *
     * @param genCode 发号器代码
     * @return 1
     */
    @Select({
        "select 1 from PS_GEN_DEF",
        " where GEN_CODE = #{gen}",
        " for update",
    })
    Integer lockDef(@Param("gen") String genCode);

    /**
     * @param genSeq 发号上下文
     * @return 更新记录数
     */
    @Insert({
        "insert into PS_GEN_SEQ (",
        "  GEN_CODE, SEQ_KEY, CNT_KEY, CNT_VAL",
        ") values (",
        "  #{genDef.code}, #{seqKey}, #{cntKey}, #{cntValNext}",
        ")",
    })
    int addNewSeq(EdpGenSeq genSeq);

}
