package com.el.edp.util;

import lombok.Getter;
import lombok.val;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件名收集器
 *
 * @author neo.pan
 * @since 2018/04/26
 */
public class EdpFilenameCollector implements FileVisitor<Path> {

    @Getter
    private final List<String> filenames = new ArrayList<>();

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        val filename = file.getFileName().toString();
        if (match(filename)) {
            filenames.add(filename);
        }
        return FileVisitResult.CONTINUE;
    }

    /**
     * @param filename 文件名
     * @return true if match
     */
    protected boolean match(String filename) {
        return true;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        return FileVisitResult.CONTINUE;
    }

}
