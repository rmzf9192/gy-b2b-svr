package com.el.edp.bpm.api;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author neo.pan
 * @since 2018/04/23
 */
@Data
public class EdpBpmModelPayload {
    /**
     * 流程定义ID
     */
    @NotBlank
    private String defId;
    /**
     * 流程模型内容
     */
    @NotBlank
    private String defXml;

    public static EdpBpmModelPayload of(String defId, String defXml) {
        EdpBpmModelPayload payload = new EdpBpmModelPayload();
        payload.defId = defId;
        payload.defXml = defXml;
        return payload;
    }

}
