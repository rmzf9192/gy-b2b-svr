package com.el.edp.ews.service;

import com.el.core.domain.YesNoFlag;
import com.el.core.web.OpResult;
import com.el.edp.ews.mapper.EdpReminderMapper;
import com.el.edp.sec.entity.EdpReminderEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Simon.Hu
 * @since 18/3/29
 */
@Profile("edp")
@Slf4j
@Service
@RequiredArgsConstructor
public class EdpReminderServiceImpl implements EdpReminderService {

    private final EdpReminderMapper reminderMapper;

    @Transactional
    @Override
    public OpResult saveReminder(EdpReminderEntity entity) {
        if (reminderMapper.reminderIfExists(entity)) {
            return EdpReminderOp.NG_REMINDER_EXISTS;
        }

        if (entity.isNew()) {
            reminderMapper.insertReminder(entity);
        } else {
            reminderMapper.updateReminder(entity);
        }
        return EdpReminderOp.OK;
    }

    @Transactional
    @Override
    public OpResult disableReminder(long id) {
        if (reminderMapper.reminderInService(id)) {
            return EdpReminderOp.NG_REMINDER_IN_SERVICE;
        }
        reminderMapper.toggleReminder(id, String.valueOf(YesNoFlag.Y.getVal()));
        return EdpReminderOp.OK;
    }

    @Transactional
    @Override
    public OpResult enableReminder(long id) {
        reminderMapper.toggleReminder(id, String.valueOf(YesNoFlag.N.getVal()));
        return EdpReminderOp.OK;
    }
}
