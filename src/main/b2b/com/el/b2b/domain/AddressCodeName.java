package com.el.b2b.domain;

import com.el.core.domain.CodeName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "id")
public class AddressCodeName implements CodeName {
    private Long id;
    private String code;
    private String name;
    /**
     * PB_OU表
     * 公司名称
     */
    private String ouName;
    /**
     * PB_ADDRESS表
     * 收货人姓名
     */
    private String contPerson;
    /**
     * PB_ADDRESS表
     * 收货人手机号
     */
    private String mobile;

    /**
     * 联系人code
     */
    private String custCode;

}
