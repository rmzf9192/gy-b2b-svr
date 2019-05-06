-- CHAR/VARCHAR 类型字节数参考: http://dev.mysql.com/doc/refman/5.7/en/charset-unicode-utf8.html

/*------------+
|  DB: MySQL  |
+============*/

-- 创建 用户
--DROP USER xxx;
--CREATE USER 'dev'@'%' IDENTIFIED BY 'Dev.1234';

-- 创建 DB
--DROP DATABASE xxx;
CREATE DATABASE xxx DEFAULT CHARACTER SET utf8 DEFAULT COLLATE utf8_general_ci;
GRANT ALL PRIVILEGES ON xxx.* TO 'dev'@'%';
--DROP DATABASE xxx_test;
CREATE DATABASE xxx_test DEFAULT CHARACTER SET utf8 DEFAULT COLLATE utf8_general_ci;
GRANT ALL PRIVILEGES ON xxx_test.* TO 'dev'@'%';

-- CLONE DB
-- mysqldump fun -udev -p | mysql xxx -udev -p
