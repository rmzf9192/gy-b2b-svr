package com.el.edp.ews.domain;

import lombok.Data;

@Data
public class EdpReminder {
    /**
     *  ID - PB_REMIND_SET.ID
     */
    private Long id;

    /**
     *  提醒种别 - PB_REMIND_SET.REMIND_CLASS
     */
    private String remindClass;

    /**
     *  提醒类型 - PB_REMIND_SET.REMIND_TYPE
     */
    private String remindType;

    /**
     *  提醒天数1 - PB_REMIND_SET.REMIND_DAYS_1
     */
    private Long remindDays1;

    /**
     *  提醒天数2 - PB_REMIND_SET.REMIND_DAYS_2
     */
    private Long remindDays2;

    /**
     *  提醒天数3 - PB_REMIND_SET.REMIND_DAYS_3
     */
    private Long remindDays3;

    /**
     *  提醒天数4 - PB_REMIND_SET.REMIND_DAYS_4
     */
    private Long remindDays4;

    /**
     *  提醒天数5 - PB_REMIND_SET.REMIND_DAYS_5
     */
    private Long remindDays5;

    /**
     *  是否删除 - PB_REMIND_SET.DELETE_FLAG
     */
    private boolean deleteFlag;
}
