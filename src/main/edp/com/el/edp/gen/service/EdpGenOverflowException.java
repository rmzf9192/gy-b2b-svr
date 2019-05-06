package com.el.edp.gen.service;

import com.el.edp.gen.domain.EdpGenSeq;

/**
 * @author neo.pan
 * @since 2018/04/12
 */
public class EdpGenOverflowException extends RuntimeException {

    public EdpGenOverflowException(EdpGenSeq genSeq) {
        super(String.format(
            "[EDP-SEQ] generator instance (%s) OVERFLOW: %d > %d",
            genSeq, genSeq.getCntValNext(), genSeq.getGenDef().getCntMax()));
    }

}
