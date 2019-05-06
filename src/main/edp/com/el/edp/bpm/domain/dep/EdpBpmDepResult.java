package com.el.edp.bpm.domain.dep;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author neo.pan
 * @since 17/6/5
 */
@Data
@EqualsAndHashCode(of = "dep")
public class EdpBpmDepResult {

    /**
     * 若部署成功则为部署信息，反之则为null。
     */
    private EdpBpmDep dep;

    /**
     * 数组长度为上传的资源个数，对于每一个资源若部署成功则为null，反之则为失败原因。
     */
    private Map<String, String> causes = new HashMap<>();

    public void addError(String fileName, String cause) {
        causes.put(fileName, cause);
    }

    @JsonIgnore
    public boolean ok() {
        return causes.isEmpty();
    }

}
