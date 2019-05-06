package com.el.edp.gen.service;

import com.el.core.DevError;
import com.el.core.OpsError;
import com.el.edp.gen.domain.EdpGenDef;
import com.el.edp.gen.domain.EdpGenSeq;
import com.el.edp.gen.mapper.EdpGenMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author neo.pan
 * @since 2018/04/12
 */
@Slf4j
@RequiredArgsConstructor
public class EdpGenServiceImpl implements EdpGenService {

    private final EdpGenMapper genMapper;

    @Override
    public List<EdpGenDef> genDefs(long tenantId, String appCode) {
        return genMapper.genDefs(tenantId, appCode);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public boolean updateCnt(EdpGenSeq genSeq) {
        val cntOld = genMapper.getCntAndLockSeq(genSeq);
        genSeq.updateAndCheckCntVal(cntOld);
        if (cntOld == null) {
            log.trace("[EDP-SEQ] generator instance (%s) NOT FOUND", genSeq);
            return false;
        } else {
            if (0 == genMapper.updateCnt(genSeq)) {
                throw new OpsError(String.format(
                    "[EDP-SEQ] generator instance (%s) UPDATE FAIL", genSeq));
            }
            return true;
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void addNewSeq(EdpGenSeq genSeq) {
        val genCode = genSeq.getGenDef().getCode();
        if (genMapper.lockDef(genCode) == null) {
            throw DevError.unexpected("[EDP-SEQ] generator NOT FOUND: " + genCode);
        }
        if (0 == genMapper.addNewSeq(genSeq)) {
            throw new OpsError(String.format(
                "[EDP-SEQ] NEW generator instance (%s) ADD FAIL", genSeq));
        }
    }

}
