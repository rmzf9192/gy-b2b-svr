package com.el.edp.ews.api;

import com.el.core.domain.PagingResult;
import com.el.edp.ews.domain.EdpReminder;
import com.el.edp.ews.mapper.EdpReminderMapper;
import com.el.edp.ews.service.EdpReminderService;
import com.el.edp.sec.entity.EdpReminderEntity;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.el.core.web.WebUtil.toResponseEntity;

/**
 * @author Simon.Hu
 * @since 2018/04/20
 */
@Profile("edp")
@RestController
@RequestMapping("/edp/ews")
@AllArgsConstructor
public class EdpReminderApi {

    private final EdpReminderMapper reminderMapper;

    private final EdpReminderService reminderService;

    @GetMapping("/reminders")
    public PagingResult<EdpReminder> remindersBy(EdpReminderQuery query) {
        val rows = reminderMapper.remindersBy(query);
        val total = reminderMapper.reminderCountBy(query);
        return PagingResult.of(rows, total);
    }

    @GetMapping("/reminders/{id}")
    public EdpReminder reminder(@PathVariable("id") long id) {
        return reminderMapper.reminderBy(id);
    }

    @PostMapping("/reminders")
    public ResponseEntity saveReminder(@RequestBody @Validated EdpReminderEntity entity) {
        return toResponseEntity(reminderService.saveReminder(entity));
    }

    @PostMapping("/reminders/{id}/x")
    public ResponseEntity disableReminder(@PathVariable("id") long id) {
        return toResponseEntity(reminderService.disableReminder(id));
    }

    @PostMapping("/reminders/{id}/o")
    public ResponseEntity enableReminder(@PathVariable("id") long id) {
        return toResponseEntity(reminderService.enableReminder(id));
    }
}
