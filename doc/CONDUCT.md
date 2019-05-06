# 开发规范

## API 规范

- Rs  : GET  /srm/api/sec/users           query = { offset: 10, limit: 10, q: 'Hello', ... }
- R1  : GET  /srm/api/sec/users/1
- Rs  : GET  /srm/api/sec/users/1/roles
- R1  : GET  /srm/api/sec/users/1/roles/1

- C/U : POST /srm/api/sec/users           payload = { id: 1?/*不给出即为C*/, name: 'World', ... }
- Ds  : POST /srm/api/sec/users/x         ids = [1, 2]
- D   : POST /srm/api/sec/users/1

## 模型规范

- 入参校验必须在API层完成
- 用户上下文通过`com.el.cfg.security.EdpPrincipalService`获取
- 所有实体类必须继承 `com.el.cfg.jdbc.EdpEntity`
- 所有查询条件在API层必须采用`@Validated XxxQuery query`进行接收
- 所有分页查询条件必须继承 `com.el.core.domain.PagingQuery`
- 所有分页查询结果必须使用 `com.el.core.domain.PagingResult`
- 所有列表查询结果必须使用 `com.el.core.domain.ListingResult`
- 所有更新数据必须通过`body`传递，服务端API层采用`@Validated @RequestBody XxxPayload payload`接收
- 用户上下文的获取只能在API层完成并取得其中的用户信息`getUser()`传递到服务层
