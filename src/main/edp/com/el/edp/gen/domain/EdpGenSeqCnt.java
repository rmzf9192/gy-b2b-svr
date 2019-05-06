package com.el.edp.gen.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

/**
 * 发号的计数键值
 *
 * @author neo.pan
 * @since 2018/04/13
 */
@Data
@EqualsAndHashCode(of = "key")
@AllArgsConstructor(staticName = "of")
@RequiredArgsConstructor
public class EdpGenSeqCnt {
    private String key;
    private Long val;
}
