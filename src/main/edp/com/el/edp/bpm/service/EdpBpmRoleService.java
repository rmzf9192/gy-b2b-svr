package com.el.edp.bpm.service;

import java.util.Map;

/**
 * @author Simon.Hu
 * @since 18/3/29
 */
public interface EdpBpmRoleService {

    /**
     * @param defKey 流程定义
     * @return 验证流程角色设置
     */
    boolean checkBpmRoleSettleCorrectly(String defKey);

    /**
     * @param defKey 流程定义
     * @return 任务角色映射
     */
    Map<String, Object> taskRoleMapping(String defKey);

}
