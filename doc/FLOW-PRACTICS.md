## 工作流开发步骤-后端
1. 在业务主表(绩效评分:SRM_SUPP_PERFORMANCE)增加维护字段PROC_INST_ID(NVARCHAR2(64))

2. 在保存接口的Entity上添加接口EdpFlowForm，声明新的属性如下：
```
/**
 * 是否提交操作
 */
private boolean submitted;
/**
 * 流程定义Key
 */``````
private String defKey = PERFORMANCE.getKey();
/**
 * 流程实例编号
 */
private String prcId;
/**
 * @return 流程关联业务主键
 */
public String businessKey() {
    return super.getId() == null ? null : String.valueOf(super.getId());
}
```

3. 在保存接口上增加返回类型定义ResponseEntity，接收Service的返回
```
/**
 * 更新绩效
 *
 * @param performance 绩效信息
 */
@PostMapping("/performances/put")
public ResponseEntity updatePerformanceScore(@RequestBody @Validated PerformanceEntity performance) {
    log.debug("[APP-SRM-PERFORMANCE] updatePerformanceScore, param={}", performance);
    return WebUtil.toResponseEntity(performanceService.updatePerformanceScore(performance));
}
```

4. 保存接口的Service增加启动流程实现，注入FlowStartService
```
/**
 * 发起流程
 * @param performance 流程表单
 * @return 启动反馈
 */
private EdpFlowOp startWorkflow(PerformanceEntity performance) {
    return startService.checkInitParam(performance, (flowForm) -> {
        performance.setPrcId(startService.startWorkflow(flowForm));
        updateBusinessForm(performance);
    });
}
/**
 * 同步更新业务表单
 * @param performance 业务表单
 */
private void updateBusinessForm(PerformanceEntity performance) {
    // TODO 更新业务关联的流程实例编号【重要】
    performanceMapper.updateFlowInstance(performance);

    // TODO 这里还可以包含其他流程启动之后对业务表的更新操作
}
```

5. Mapper层增加更新关联流程实例的方法定义
```
/**
 * 更新关联流程实例
 * @param performance 绩效信息
 */
@Update({
    "update SRM_SUPP_PERFORMANCE",
    "   set PROC_INST_ID = #{prcId}",
    " where ID = #{id}"
})
void updateFlowInstance(PerformanceEntity performance);
```

6. 详情接口View增加查询属性，Mapper增加查询字段prcId，在Api层给taskId赋值（注入EdpFlowTaskService），
```
/**
 * 流程实例编号
 */
private String prcId;
/**
 * 流程任务编号
 */
private String taskId;
```

## 工作流开发步骤-前端
1. 在详情保存接口，增加参数submitted，区分保存和修改操作；prcId直接从详情查询接口数据回写。

2. 在model增加两个effects函数approve/reject用于审批，请参考绩效评分前端实现。
```
/edp/flow/examine        //流程审批接口
```
