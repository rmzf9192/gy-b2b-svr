package com.el.edp.gen.service;

import com.el.edp.gen.domain.EdpGenDef;
import com.el.edp.gen.domain.EdpGenSeq;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author neo.pan
 * @since 2018/04/12
 */
@Slf4j
public class EdpGenManagerImpl implements EdpGenManager {

    private final EdpGenService genService;
    private final Map<String, EdpGenDef> genDefMap;

    public EdpGenManagerImpl(long tenantId, String appCode, EdpGenService genService) {
        this.genService = genService;
        this.genDefMap = genService.genDefs(tenantId, appCode).stream()
            .collect(Collectors.toMap(EdpGenDef::getCode, Function.identity()));
    }

    @Override
    public Map<String, EdpGenDef> genDefs() {
        return genDefMap;
    }

    @Override
    public String nextSeq(String genCode, Object genCtx) {
        return updateCnt(toGenSeq(genCode, 1, genCtx)).buildSeq();
    }

    @Override
    public List<String> nextSeqs(String genCode, int genCnt, Object genCtx) {
        return updateCnt(toGenSeq(genCode, genCnt, genCtx))
            .buildSeqs().collect(Collectors.toList());
    }

    private EdpGenSeq toGenSeq(String genCode, int genCnt, Object genCtx) {
        val genDef = genDefMap.get(genCode);
        if (genDef == null) {
            throw new IllegalArgumentException("[EDP-SEQ] Invalid generator code: " + genCode);
        }
        return new EdpGenSeq(genDef, genCnt, genCtx);
    }

    private EdpGenSeq updateCnt(EdpGenSeq genSeq) {
        if (!genService.updateCnt(genSeq)) {
            genService.addNewSeq(genSeq);
            log.info("[EDP-SEQ] NEW generator instance ({}) CREATED", genSeq);
        }
        return genSeq;
    }

}
