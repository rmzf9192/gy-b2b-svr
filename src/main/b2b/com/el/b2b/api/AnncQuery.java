package com.el.b2b.api;

import com.el.core.domain.PagingQuery;
import com.el.core.util.TimeUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @Auther: shuaiyin.shang
 * @Date: 2018/6/20
 * @Description:
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class AnncQuery extends PagingQuery {

    /**
     * 公司Code
     */
    private String ouCode;

    /**
     * 主题 - PB_ANNC.ANNC_SUBJECT
     */
    private String anncSubject;

    /**
     * 发布时间 - PB_ANNC.PUBLISH_TIME
     */
    @TimeUtil.TIME
    private LocalDateTime publishTime;


}
