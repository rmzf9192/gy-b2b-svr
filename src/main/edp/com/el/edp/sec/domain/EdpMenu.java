package com.el.edp.sec.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "menuId")
public class EdpMenu {
    /**
     *  菜单码
     */
    private String menuId;

    /**
     *  父菜单码
     */
    private String upperId;

    /**
     *  菜单名
     */
    private String menuName;

    /**
     *  画面前端路由
     */
    private String menuUrl;

    /**
     *  菜单图标(fontawesome)
     */
    private String menuIcon;

    /**
     *  菜单类型
     */
    private String menuType;
}
