package com.el.edp.bpm.config;

import java.util.stream.Stream;

/**
 * @author neo.pan
 * @since 17/5/27
 */
public interface BpmUserResolver {

    /**
     * @return 当前流程用户
     */
    String user();

    /**
     * @param uid 系统用户ID
     * @return 流程用户
     */
    default String userOf(Object uid) {
        return uid == null ? null : String.valueOf(uid);
    }

    /**
     * @param uids 系统用户ID列表
     * @return 当前流程用户ID列表
     */
    default Stream<String> usersOf(Object... uids) {
        return Stream.of(uids)
            .map(this::userOf);
    }

    /**
     * @return 当前流程用户所在的组列表
     */
    Stream<String> groups();

    /**
     * @param gid 系统用户组/角色ID
     * @return 流程用户组
     */
    default String groupOf(Object gid) {
        return gid == null ? null : String.valueOf(gid);
    }

    /**
     * @param gids 系统用户组/角色ID列表
     * @return 流程用户组列表
     */
    default Stream<String> groupsOf(Object... gids) {
        return Stream.of(gids)
            .map(this::groupOf);
    }
}
