package com.el.dev;

import com.el.edp.gen.domain.EdpGenDef;
import com.el.edp.gen.service.EdpGenManager;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Conditional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @author neo.pan
 * @since 2018/04/09
 */
@Conditional(DevEdsCondition.class)
@Slf4j
@RestController
@RequestMapping("/dev/gens")
@RequiredArgsConstructor
public class DevGenSeqApi {

    private final EdpGenManager genManager;

    @ToString
    private static class MyGenContext {
        @Getter
        private String entCode = "EL";
        @Getter
        private LocalDateTime buyTime = LocalDateTime.of(
            2018, 5, 1, 2, 3, 4);
    }

    private static final MyGenContext genCtx = new MyGenContext();

    @GetMapping
    public Map<String, EdpGenDef> genDefs() {
        return genManager.genDefs();
    }

    @GetMapping("/{gen}")
    public String nextSeq(@PathVariable("gen") String genCode) {
        return genManager.nextSeq(genCode, genCtx);
    }

    @GetMapping("/{gen}/{cnt}")
    public List<String> nextSeqs(@PathVariable("gen") String genCode,
                                 @PathVariable("cnt") int genCnt) {
        return genManager.nextSeqs(genCode, genCnt, genCtx);
    }

}
