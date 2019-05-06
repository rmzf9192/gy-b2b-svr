package com.el.edp.bpm.service;

import com.el.core.DevError;
import com.el.edp.bpm.domain.model.EdpBpmViewMode;
import com.el.edp.util.EdpIOUtil;
import com.el.edp.util.EdpSimpleFilenameCollector;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static com.el.edp.util.EdpIOUtil.EDP_CHARSET;

/**
 * @author neo.pan
 * @since 2018/04/23
 */
@Slf4j
public abstract class EdpBpmUtil {

    /**
     * 流程表单视图配置 classpath 路径
     */
    private static final Path BPM_VIEW_CONF_PATH = EdpIOUtil.classpathToPath("bpm/view-conf");
    /**
     * 流程表单视图配置文件扩展名
     */
    private static final String BPM_VIEW_CONF_EXT = ".json";
    /**
     * 流程表单视图配置文件分隔符
     */
    private static final String BPM_VIEW_CONF_DLT = "-";

    /**
     * @param procDefId 流程定义ID
     * @return 流程定义Key
     */
    public static String toProcDefKey(String procDefId) {
        val pos = procDefId.indexOf(':');
        if (pos == -1) throw new IllegalArgumentException(
            "[EDP-BPM] invalid process definition id: " + procDefId);
        return procDefId.substring(0, pos);
    }

    /**
     * 获取流程表单的缺省视图配置
     *
     * @param procDefId 流程定义ID
     * @return 视图配置
     */
    public static String loadProcDefViewConf(String procDefId, String taskDefId, EdpBpmViewMode viewMode) {
        val procDefKey = toProcDefKey(procDefId);
        String viewConf;
        Path confPath = BPM_VIEW_CONF_PATH.resolve(procDefKey + BPM_VIEW_CONF_DLT + taskDefId
            + BPM_VIEW_CONF_DLT + viewMode + BPM_VIEW_CONF_EXT);
        if (!Files.exists(confPath)) {
            confPath = BPM_VIEW_CONF_PATH.resolve(procDefKey + BPM_VIEW_CONF_DLT + taskDefId + BPM_VIEW_CONF_EXT);
        }
        if (!Files.exists(confPath)) {
            confPath = BPM_VIEW_CONF_PATH.resolve(procDefKey + BPM_VIEW_CONF_EXT);
        }
        if (!Files.exists(confPath)) {
            throw DevError.unexpected("[EDP-BPM] view-conf NOT FOUND: " + procDefKey);
        }
        try {
            viewConf = new String(Files.readAllBytes(confPath), EDP_CHARSET);
            log.info("[EDP-BPM] view-conf LOADED: {}", confPath);
        } catch (IOException e) {
            throw DevError.unexpected("[EDP-BPM] view-conf READ FAIL: " + confPath);
        }
        return viewConf;
    }

    /**
     * @return 流程表单视图配置文件一览
     * @throws IOException IO异常发生
     */
    public static List<String> getViewConfFiles(String procDefKey) throws IOException {
        val visitor = EdpSimpleFilenameCollector.of(procDefKey, BPM_VIEW_CONF_EXT);
        Files.walkFileTree(BPM_VIEW_CONF_PATH, visitor);
        return visitor.getFilenames();
    }

}
