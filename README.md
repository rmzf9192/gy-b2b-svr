# JAVA 后端架构

## 后端技术栈

- [Spring Boot 1.5+](http://projects.spring.io/spring-boot/)
- [MyBatis 3.4+](http://www.mybatis.org/mybatis-3/)
- [Shiro 1.3+](http://shiro.apache.org/)
- [Camunda 7+](https://camunda.org/)


## 开发工具

- [Gradle](http://gradle.org/)
- [Ant](http://ant.apache.org/)
- [MyBatis Generator](http://www.mybatis.org/generator/)
- [JetBrains Intelli IDEA](https://www.jetbrains.com/idea/)
- [FindBugs](https://docs.gradle.org/current/userguide/findbugs_plugin.html)


## 测试工具

- [JUnit4](https://junit.org/junit4/)
- [Spock](http://spockframework.org/)
- [JMeter](http://jmeter.apache.org/)


## 编码规范

- [EditorConfig](http://editorconfig.org/)
- [Lombok](https://projectlombok.org/features/index.html)
- [Java Generics FAQs - Type Arguments](http://www.angelikalanger.com/GenericsFAQ/FAQSections/TypeArguments.html)


## 编码实践

- [Best Naming Practices](https://google.github.io/styleguide/javaguide.html#s5-naming)

> 提示：对于使用IDEA的开发人员：尽量使用IDEA命名提示，就差不多了。

- [Programming Practices](https://google.github.io/styleguide/javaguide.html#s6-programming-practices)

  - 对于重载的方法总是加上`@Override`。
  - 不要捕捉异常，除非你知道怎么处理。

- 关于 JDE 儒略历的处理

> 儒略历(DB) <-> Java Date Object(Domain) <-> Formatted String(View)
>
> 使用: JdeUtil 和 DateUtil

- 关于数值的计算

    1. 一般用于商业计算的 Domain 中数值类型为：`BigDecimal`。
    2. 在计算过程中需要将`BigDecimal`转换成`double`投入计算。
    3. 计算结果一般有两种输出去向：持久化存储或格式化显示，此时需要将`double`转换成`BigDecimal`并设置精度。

- 关于数值的比较

    1. 与计算相反，数值比较需要在固定精度下才能进行，所以需要写成这样：`Math.abs(a - b) <= accuracy`

    > 参考：[精度问题](https://en.wikipedia.org/wiki/Floating_point#Accuracy_problems)


## 关于 Spring Boot DevTools 的 Build/Restart 的不足之处及应对方法

进入IoC容器内对象发生修改时依赖于该对象的依赖注入会失败, 对应最简单办法是修改`resoureces`下的任一配置文件即可触发reload，
此时该注入就OK了（其实修改任意非容器对象或接口再编译也能触发重新加载）。


## 开发环境

1. hosts
```
192.168.0.151 elpdtgit
192.168.0.152 elpdtman
192.168.0.152 elpdtmvn
```
2. Install Gradle and IDEA (You can download from [ownCloud](http://elpdtman/owncloud/))
3. Run `git clone -b dev git@elpdtgit:ELSRMSyn/srm-svr.git`
4. Run `gradle idea`
5. Open `srm-svr.ipr` file in IDEA

### 开发

1. Run `gradle bootRun`
2. Open `http://127.0.0.1:9000/xxx/`

### 检查

> [FindBugs](https://docs.gradle.org/current/userguide/findbugs_plugin.html)

1. Run `gradle check`
2. Output `build/reports/findbugs/`

### 测试

1. Run `gradle test`
2. Output `build/reports/tests/`

### 打包

1. Run `/build-war.sh`
2. Output `build/libs/xxx-x.y.z.war`


## 参考资料

- [Effective Java 2](https://raw.githubusercontent.com/andrewpage/programming-ebooks/master/Java/Effective%20Java%20\(2nd%20Edition\).pdf)
- [MyBatis3 FAQ](https://github.com/mybatis/mybatis-3/wiki/FAQ)
