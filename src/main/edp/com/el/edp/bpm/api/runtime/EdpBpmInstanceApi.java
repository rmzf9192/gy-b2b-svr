package com.el.edp.bpm.api.runtime;

import com.el.core.domain.PagingResult;
import com.el.edp.bpm.domain.runtime.EdpBpmInstance;
import com.el.edp.bpm.mapper.EdpBpmInstanceMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.camunda.bpm.engine.RuntimeService;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.*;

/**
 * @author Simon.Hu
 * @since 17/07/12
 */
@Profile("bpm")
@Slf4j
@RestController
@RequestMapping("/edp/bpm")
@AllArgsConstructor
public class EdpBpmInstanceApi {

    private final EdpBpmInstanceMapper flowInstanceMapper;

    private final RuntimeService runtimeService;

    /**
     * 查询 流程实例 列表
     *
     * @param query 搜索条件
     * @return 列表
     */
    @GetMapping("/instances")
    public PagingResult<EdpBpmInstance> instances(EdpBpmInstanceQuery query) {
        val rows = flowInstanceMapper.instancesBy(query);
        val total = flowInstanceMapper.instanceCountBy(query);
        return PagingResult.of(rows, total);
    }

    /**
     * 删除 流程实例
     *
     * @param prcId 流程实例编号
     */
    @PostMapping("/instances/{id}")
    public void deleteInstance(@PathVariable("id") String prcId) {
        runtimeService.deleteProcessInstance(prcId, "under-iam");
    }
}
