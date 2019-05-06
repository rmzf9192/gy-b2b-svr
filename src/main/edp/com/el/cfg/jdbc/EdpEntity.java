package com.el.cfg.jdbc;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.domain.Persistable;

/**
 * EDP 实体基类
 *
 * @author neo.pan
 * @since 2018/03/22
 */
@Data
@EqualsAndHashCode(of = "id")
public abstract class EdpEntity implements JdbcRevisional, Persistable<Long> {

    @Override
    public boolean isNew() {
        return id == null || id == 0L;
    }

    /**
     * ID
     */
    private Long id;
    /**
     * 修订版本
     */
    private Long revision;

}
