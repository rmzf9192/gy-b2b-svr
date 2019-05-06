package com.el.edp.sec.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.function.Function.identity;

@Data
@EqualsAndHashCode(of = "code")
public class EdpPerm {

    /**
     *  权限码
     */
    private String code;

    /**
     *  父权限码
     */
    private String pcode;

    /**
     *  权限名
     */
    private String name;

    /**
     *  权限控制APIs
     */
    private String apis;

    /**
     *  权限类型
     */
    private String type;

    /**
     *  下级权限
     */
    private List<EdpPerm> children;

    /**
     * 添加下级权限
     */
    private void addChildren(EdpPerm perm) {
        if (children == null) {
            children = new ArrayList<>(0);
        }
        children.add(perm);
    }

    /**
     * @return 生成权限树
     */
    public static List<EdpPerm> toTrees(List<EdpPerm> perms) {
        final EdpPerm root = new EdpPerm();

        Map<String, EdpPerm> treeMap = perms.stream()
            .collect(Collectors.toMap(EdpPerm::getCode, identity()));

        perms.forEach(perm -> treeMap.getOrDefault(perm.getPcode(), root).addChildren(perm));

        return root.children;
    }
}
