# SFS - Simple File  Service（简单文件存储服务）

## 需求

1. 提供实施设置项目
  - 指定上传场所: build
  - 是否名称散列: false
  - 是否内容加密: false
  - 会话工作目录: _SW_/
  - 提供资料库设置: 类型(any=任意|doc=文档|img=图片|aud=音频|vid=视频)、扩展名列表、文件最大字节数、路径（支持系统路径）、WEB访问路径
  - 提供图片分辨率检查配置、缩略图配置、以及小图片DB存储
1. 提供系统设置项目: 最大上传字节数、最大上传单文件字节数
1. 提供文件上传、下载服务

## 关于文件上传

1. 资料项路径为: <REPO_CODE>/path/to/item/file.ext
1. 缩略图路径为: <REPO_CODE>/path/to/item/file_<SUFFIX>.ext
1. 会话工作目录(_SW_/<SESSION_ID>/)，回话结束时清除

文件件上传以后，先在会话工作目录中保存文件:

- _SW_/<SESSION_ID>/path/to/item/file
- _SW_/<SESSION_ID>/path/to/item/file_<SUFFIX>.ext
- ...

接下去分以下两种情况:

### 1) 相关的业务数据未创建

1. 返回下载路径或根据需求给出指定缩略图路径

### 2) 相关的业务数据已创建

1. 若该资料库有图片检查设置，则进行图片检查
1. 若该资料库有缩略处理设置，则进行缩略处理
1. 移动`_SW_/<SESSION_ID>/path/to/item/`到`<REPO_CODE>/path/to/data/`
1. 建立`PS_SFS_ITEM`或`PS_SFS_EMBED_ITEM`数据，需要做`try/catch`处理
1. 返回下载路径或根据需求给出指定缩略图路径
