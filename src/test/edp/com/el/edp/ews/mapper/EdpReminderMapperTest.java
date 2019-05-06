package com.el.edp.ews.mapper;

import com.el.edp.EdpTest;
import com.el.edp.ews.api.EdpReminderQuery;
import com.el.edp.sec.entity.EdpReminderEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Simon.Hu
 * @since 2018/04/20
 */
@Slf4j
public class EdpReminderMapperTest extends EdpTest {

    @Autowired
    private EdpReminderMapper reminderMapper;

    private EdpReminderQuery query = new EdpReminderQuery() {{
        setReminderLike("test");
    }};

    private EdpReminderEntity entity = new EdpReminderEntity() {{
        setRemindClass("1");
        setRemindType("2");
        setRemindDays1(1l);
        setRemindDays2(1l);
        setRemindDays3(1l);
        setRemindDays4(1l);
        setRemindDays5(1l);
    }};

    @Test
    public void reminderCountBy() {
        log.info("[TEST] reminderCountBy={}", reminderMapper.reminderCountBy(query));
    }

    @Test
    public void remindersBy() {
        log.info("[TEST] remindersBy={}", reminderMapper.remindersBy(query));
    }

    @Test
    public void reminderBy() {
        log.info("[TEST] reminderBy={}", reminderMapper.reminderBy(1));
    }

    @Test
    public void reminderIfExists() {
        log.info("[TEST] reminderIfExists={}", reminderMapper.reminderIfExists(entity));
    }

    @Test
    public void insertReminder() {
        log.info("[TEST] insertReminder={}", reminderMapper.insertReminder(entity));
    }

    @Test
    public void updateReminder() {
        log.info("[TEST] updateReminder={}", reminderMapper.updateReminder(entity));
    }

    @Test
    public void toggleReminder() {
        log.info("[TEST] toggleReminder={}", reminderMapper.toggleReminder(1, "0"));
    }

    @Test
    public void reminderInService() {
        log.info("[TEST] reminderInService={}", reminderMapper.reminderInService(1));
    }

    @Test
    public void reminders() {
        log.info("[TEST] reminders={}", reminderMapper.reminders());
    }
}
