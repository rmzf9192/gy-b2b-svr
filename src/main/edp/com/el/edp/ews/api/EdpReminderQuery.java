package com.el.edp.ews.api;

import com.el.core.domain.PagingQuery;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Simon.Hu
 * @since 2018/04/20
 */
@Getter
@Setter
public class EdpReminderQuery extends PagingQuery {

    /**
     * 模糊匹配：预警提醒编码、名称
     */
    private String reminderLike;
}
