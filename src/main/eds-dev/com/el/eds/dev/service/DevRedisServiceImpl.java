package com.el.eds.dev.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.stereotype.Service;

/**
 * @author neo.pan
 * @since 2018/04/13
 */
@Profile("eds")
@Service
@RequiredArgsConstructor
public class DevRedisServiceImpl implements DevRedisService {

    private final RedisConnectionFactory redisConnectionFactory;

    @Override
    public void flushAll() {
        redisConnectionFactory.getConnection().flushAll();
    }

}
