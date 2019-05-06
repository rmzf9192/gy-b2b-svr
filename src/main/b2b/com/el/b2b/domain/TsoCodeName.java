package com.el.b2b.domain;

import com.el.core.domain.CodeName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "id")
public class TsoCodeName implements CodeName {
    private Long id;
    private String code;
    private String name;
}
