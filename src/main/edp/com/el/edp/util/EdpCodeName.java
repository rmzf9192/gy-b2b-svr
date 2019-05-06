package com.el.edp.util;

import com.el.core.domain.CodeName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author neo.pan
 * @since 2018/04/11
 */
@Data
@EqualsAndHashCode(of = "id")
public class EdpCodeName implements CodeName {
    private Long id;
    private String code;
    private String name;
}
