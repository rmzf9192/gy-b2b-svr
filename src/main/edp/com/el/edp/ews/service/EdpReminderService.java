package com.el.edp.ews.service;

import com.el.core.web.OpResult;
import com.el.edp.sec.entity.EdpReminderEntity;
import lombok.Getter;

/**
 * @author Simon.Hu
 * @since 18/3/29
 */
public interface EdpReminderService {

    enum EdpReminderOp implements OpResult {
        OK("成功"),
        NG_REMINDER_EXISTS("预警提醒已被定义"),
        NG_REMINDER_IN_SERVICE("预警提醒正在使用中");

        @Override
        public String getCode() {
            return name();
        }

        @Getter
        private final String desc;

        EdpReminderOp(String desc) {
            this.desc = desc;
        }
    }

    OpResult saveReminder(EdpReminderEntity entity);

    OpResult disableReminder(long id);

    OpResult enableReminder(long id);
}
