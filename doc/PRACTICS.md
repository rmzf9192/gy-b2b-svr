# Recommended Practices

## Java

### String is emtpy ?

```java
StringUtils.isEmpty(...)
```

### String is not emtpy ?

```java
StringUtils.hasText(...)
```

DONT USE

```java
!StringUtils.isEmpty(...)
```
