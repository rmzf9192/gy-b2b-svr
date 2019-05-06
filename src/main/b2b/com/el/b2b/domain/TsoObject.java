package com.el.b2b.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

/**
 * Created by jerry.feng
 * on 2018/5/30.
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode
public class TsoObject {
    /**
     * tsod
     */
    private List<TsodDomain> tsodDomain;

}
