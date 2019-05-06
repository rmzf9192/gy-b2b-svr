package com.el.dev;

import com.el.core.util.TimeUtil;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Conditional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author neo.pan
 * @since 2018/04/09
 */
@Conditional(DevEdsCondition.class)
@Slf4j
@RestController
@RequestMapping("/dev/json")
public class DevJsonApi {

    @GetMapping("/bad")
    public BadResult outputJsonWithException() {
        return BadResult.create();
    }

    @RequiredArgsConstructor(staticName = "create")
    public static class BadResult {
        // it will throw exception when json serialization
        public LocalDate cannotSerialization() {
            return LocalDate.ofYearDay(0, 0);
        }
    }

    @GetMapping("/good")
    public GoodResult hello() {
        return GoodResult.of(date(), time());
    }

    @Value(staticConstructor = "of")
    public static class GoodResult {
        @TimeUtil.DATE
        private final LocalDate date;
        @TimeUtil.TIME
        private final LocalDateTime time;
    }

    @GetMapping("/good2")
    public GoodResult2 hello2() {
        return GoodResult2.of(date(), time());
    }

    @Value(staticConstructor = "of")
    public static class GoodResult2 {
        private final LocalDate date;
        private final LocalDateTime time;
    }

    @GetMapping("/date")
    public LocalDate date() {
        return LocalDate.now();
    }

    @GetMapping("/time")
    public LocalDateTime time() {
        return LocalDateTime.now();
    }

}
