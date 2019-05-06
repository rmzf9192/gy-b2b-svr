package com.el.edp.bpm.domain.dep;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.camunda.bpm.engine.repository.Resource;

/**
 * @author neo.pan
 * @since 17/5/26
 */
@Data
@EqualsAndHashCode(of = "id")
public class EdpBpmDepResource {

    private String id;
    private String name;
    private String depId;

    public static EdpBpmDepResource of(Resource resource) {
        EdpBpmDepResource res = new EdpBpmDepResource();
        res.id = resource.getId();
        res.name = resource.getName();
        res.depId = resource.getDeploymentId();
        return res;
    }

    public String getIdName() {
        return id + "," + name;
    }

}
