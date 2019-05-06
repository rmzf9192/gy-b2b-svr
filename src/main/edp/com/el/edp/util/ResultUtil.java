package com.el.edp.util;


import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 返回对象工具类
 * Created by yangyang.lian
 * 2018/04/10
 */
@AllArgsConstructor
@Data
public class ResultUtil {
    /**
     * 是否执行成功
     */
    private boolean success;

    /**
     * 提示信息
     */
    private String message;

    /**
     * 返回数据
     */
    private Object data;

    /**
     * 检索总条数
     */
    private int size;

    public static ResultUtil result(boolean success, String message, Object data, int size) {
        return new ResultUtil(success, message, data, size);
    }

    public static ResultUtil result(boolean success, int size) {
        return new ResultUtil(success, null, null, size);
    }

    public static ResultUtil result(boolean success, String message) {
        return result(success, message, null, 0);
    }

    public static ResultUtil result(boolean success, String message, Object data) {
        return result(success, message, data, 0);
    }

    public static ResultUtil fail(String message) {
        return new ResultUtil(false, message, null, 0);
    }

    public static ResultUtil fail(Object data) {
        return new ResultUtil(false, null, data, 0);
    }

    public static ResultUtil fail() {
        return fail(null);
    }

    public static ResultUtil success(String message, Object data) {
        return result(true, message, data, 0);
    }

    public static ResultUtil success(String message, Object data, int size) {
        return result(Boolean.TRUE, message, data, size);
    }

    public static ResultUtil success(Object data) {
        return result(true, null, data, 0);
    }

    public static ResultUtil success(String message) {
        return result(true, message, null, 0);
    }

    public static ResultUtil success() {
        return result(true, null, null, 0);
    }

    public static ResultUtil isSuccess(int saveResultCount) {
        return saveResultCount > 0 ? success() : fail();
    }
}
