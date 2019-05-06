package com.el.b2b.domain;

import lombok.Data;

/**
 * Created by arthur.xu
 * on 2018/9/25.
 */
@Data
public class CertCheckDomain {
    //CCCO,CCE8ZZLXA,CCEV01,CCEV02,CCEV03,CCEV04
    private String co;
    private String e8zzlxa;
    private String ev01;
    private String ev02;
    private String ev03;
    private String ev04;

//    public static void main(String[] args) {
//        String ou = "00101";
//        System.out.println(String.valueOf(Integer.parseInt(ou)));
//    }

}
