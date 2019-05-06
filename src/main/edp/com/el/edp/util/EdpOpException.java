package com.el.edp.util;

import com.el.core.web.OpResult;
import lombok.Getter;

/**
 * @author neo.pan
 * @since 2018/04/19
 */
public class EdpOpException extends RuntimeException {

    @Getter
    private final OpResult result;

    public EdpOpException(OpResult result) {
        this.result = result;
    }

}
