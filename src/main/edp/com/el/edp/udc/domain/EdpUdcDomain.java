package com.el.edp.udc.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper=true)
public class EdpUdcDomain extends UdcDomain{

    /**
     * UDC明细
     */
    private List<UdcItemDomain> items;

}
