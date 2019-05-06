package com.el.edp.sec.mapper;

import com.el.edp.EdpTest;
import com.el.edp.sec.api.EdpAuthUserQuery;
import com.el.edp.sec.domain.EdpAuthLayer;
import com.el.edp.sec.domain.EdpEntType;
import com.el.edp.sec.entity.EdpAuthUserEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author neo.pan
 * @since 17/11/8
 */
@Slf4j
public class AuthUserMapperTest extends EdpTest {

    @Autowired
    private EdpAuthUserMapper authUserMapper;

    private EdpAuthUserQuery query = new EdpAuthUserQuery() {{
//        setUserLike("130");
    }};

    private EdpAuthLayer layer = EdpAuthLayer.E;

    private EdpAuthUserEntity payload = new EdpAuthUserEntity(){{
        setId(0L);
        setRevision(2L);
        setLayer("T");
        setField("HO");
        setName("九毛九");
        setEntType("supp");
        setEntCode("E001000");
        setEmpCode("E001000");
        setEmail("test@dev.com");
        setPhone("13123456789");
    }};

    @Test
    public void userCountBy() throws Exception {
        log.info("[TEST] userCountBy={}", authUserMapper.userCountBy(query, layer));
    }

    @Test
    public void usersBy() throws Exception {
        log.info("[TEST] usersBy={}", authUserMapper.usersBy(query, layer));
    }

    @Test
    public void userBy() throws Exception {
        log.info("[TEST] userBy={}", authUserMapper.userBy(1));
    }

    @Test
    public void nameIfExists() throws Exception {
        log.info("[TEST] nameIfExists={}", authUserMapper.nameIfExists(payload));
    }

    @Test
    public void insertUser() throws Exception {
        log.info("[TEST] insertUser={}", authUserMapper.insertUser(payload));
    }

    @Test
    public void updateUser() throws Exception {
        log.info("[TEST] updateUser={}", authUserMapper.updateUser(payload));
    }

    @Test
    public void toggleUser() throws Exception {
        log.info("[TEST] toggleUser={}", authUserMapper.toggleUser(1, "1"));
    }

    @Test
    public void authRoles() throws Exception {
        log.info("[TEST] authRoles={}", authUserMapper.authRoles(1));
    }

    @Test
    public void insertAuthUserRole() throws Exception {
        log.info("[TEST] insertAuthUserRole={}", authUserMapper.insertAuthUserRole(1, 20));
    }

    @Test
    public void deleteAuthUserRole() throws Exception {
        log.info("[TEST] deleteAuthUserRole={}", authUserMapper.deleteAuthUserRole(1));
    }

    @Test
    public void bpmRoles() throws Exception {
        log.info("[TEST] bpmRoles={}", authUserMapper.bpmRoles(1));
    }

    @Test
    public void insertBpmUserRole() throws Exception {
        log.info("[TEST] insertBpmUserRole={}", authUserMapper.insertBpmUserRole(1, 20));
    }

    @Test
    public void deleteBpmUserRole() throws Exception {
        log.info("[TEST] deleteBpmUserRole={}", authUserMapper.deleteBpmUserRole(1));
    }
}
