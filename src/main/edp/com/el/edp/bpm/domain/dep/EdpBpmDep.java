package com.el.edp.bpm.domain.dep;

import com.el.core.util.TimeUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.camunda.bpm.engine.repository.Deployment;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author neo.pan
 * @since 17/5/26
 */
@Data
@EqualsAndHashCode(of = "id")
public class EdpBpmDep {

    private String id;

    private String name;

    /**
     * 部署时间
     */
    private LocalDateTime time;

    /**
     * 部署源
     */
    private String src;

    /**
     * 资源列表
     */
    private List<EdpBpmDepResource> resources;

    public static EdpBpmDep of(Deployment deployment) {
        EdpBpmDep dep = new EdpBpmDep();
        dep.id = deployment.getId();
        dep.name = deployment.getName();
        dep.time = TimeUtil.toLocalDateTime(deployment.getDeploymentTime());
        dep.src = deployment.getSource();
        return dep;
    }

}
