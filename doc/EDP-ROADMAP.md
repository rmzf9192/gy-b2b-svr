# 工程规划 - EDP 三步走

## Ph1) 领域服务内部解耦

> **目的：领域业务解耦**

### EDP SDK 包(`src/main/edp/`)的目录结构:

```txt
com/el/
  cfg/          # EDP 配置
  edp/          # EDP 类库
  edi/          # EDS SDK
    sv1a/       # 业务领域1a的接口
      exports/  # 领域输出的服务接口
      payload/  # 接口相关的数据模型
    sv1b/       # 业务领域1b的接口
      exports/  # 领域输出的服务接口
      payload/  # 接口相关的数据模型
```

### EDS-xxx 服务实现(`src/main/eds-xxx/`)的目录结构:

```txt
com/el/eds/
  xxx/          # Ph2之后独立部署
    service/
    mapper/
    entity/
    domain/
```

### 应用部分(`src/main/srm`)的目录结构:

```txt
com/el/
  App           # 应用 bootstrap
  cfg/          # 应用配置
  srm/
    feature1/   # 应用的功能模块1
      api/
      service/
      mapper/
      entity/
      domain/
    feature2/   # 应用的功能模块2
      api/
      service/
      mapper/
      entity/
      domain/
```

### 套路

1. 应用负责认证授权、控制入口
1. 用户上下文信息通过参数传递

### 领域演进路线

```txt
(ap1/feature1, ap2/feature1) -> (ap1/thin-feature1, ap2/thin-feature1) + service/sv1
(ap1/feature2, ap3/feature2) -> (ap1/thin-feature2, ap3/thin-feature2) + service/sv2
```

## Ph2) 领域服务分布式部署

> **目的：领域数据逐步结耦、各包工程分离、各领域服务独立部署**

应用工程的`com.el`下的目录结构:

```txt
com/el/
  App           # 应用 bootstrap
  cfg/          # 应用配置
  srm/

  + el-java-core.jar, el-java-web.jar
  + el-java-edp.jar
```

领域服务`sv1`工程`el-java-eds-sv1`的目录结构如下:

```txt
com/el/
  App           # 应用 bootstrap
  eds/sv1/
    service/
    mapper/
    entity/
    domain/

  + el-java-core.jar, el-java-web.jar
  + el-java-edp.jar
```

## Ph3) 部署微服务化

TODO
