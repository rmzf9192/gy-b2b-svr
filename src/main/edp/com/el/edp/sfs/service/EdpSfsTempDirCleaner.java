package com.el.edp.sfs.service;

import com.el.edp.sfs.mapper.EdpSfsMapper;
import com.el.edp.util.EdpIOUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.io.IOException;
import java.nio.file.Files;

/**
 * @author neo.pan
 * @since 2018/04/27
 */
@Slf4j
@RequiredArgsConstructor
public class EdpSfsTempDirCleaner implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        // DO NOTHING
    }

    private final EdpSfsMapper sfsMapper;

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        val session = se.getSession();
        val sessionId = session.getId();
        EdpIOUtil.getTempDirectory(session).ifPresent(path -> {
            sfsMapper.cancelTempItems(sessionId);
            if (Files.isDirectory(path)) {
                try {
                    EdpIOUtil.deleteDirectory(path);
                    log.info("[EDP-SFS] TEMP directory of s-{} CLEANED: {}", sessionId, path);
                } catch (IOException e) {
                    log.warn("[EDP-SFS] TEMP directory of s-{} clean FAILED: {}", sessionId, path);
                }
            }
        });
    }

}
