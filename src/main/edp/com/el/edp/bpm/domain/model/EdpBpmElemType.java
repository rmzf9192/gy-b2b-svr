package com.el.edp.bpm.domain.model;

import lombok.Value;

/**
 * @author neo.pan
 * @since 2018/04/23
 */
@Value(staticConstructor = "of")
public class EdpBpmElemType {
    private String ns;
    private String name;
}
