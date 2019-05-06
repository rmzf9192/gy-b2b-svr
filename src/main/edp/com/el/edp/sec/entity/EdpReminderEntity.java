package com.el.edp.sec.entity;

import com.el.cfg.jdbc.EdpEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * @author Simon.Hu
 * @since 2018/04/20
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class EdpReminderEntity extends EdpEntity {
    /**
     *  提醒种别 - PB_REMIND_SET.REMIND_CLASS
     */
    private String remindClass;

    /**
     *  提醒类型 - PB_REMIND_SET.REMIND_TYPE
     */
    @NotNull
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
}
