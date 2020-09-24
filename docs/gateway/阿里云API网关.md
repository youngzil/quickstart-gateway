API网关新版本升级、新增错误码映射、参数访问控制、参数流控、断路器等功能

适用客户
开发者，企业

发布功能
API网关发布新版本，新增以下功能
- 文件与数组功能的映射与校验
- 新增泛域名支持
- 新增客户端CA证书认证，

新增并更新以下插件
- `流控插件`允许用户通过灵活定制的参数来执行流控规则
- `访问控制`插件允许用户通过灵活定制的参数来执行网关测的业务规则限制
- `错误码映射`插件允许用户根据不同的后端返回来变更返回状态码
- `断路器`插件允许用户定制后端熔断的条件，以及熔断后的行为


- API网关对于不符合RFC3986的非法输入，直接返回错误码:I400PH: Invalid Request Path
- API网关支持的最大RequestUri长度为128KBytes, 超过这个限制时会返回错误码:I413RL: Request Url too Large



产品文档
https://help.aliyun.com/document_detail/154721.html
https://help.aliyun.com/document_detail/103215.html
https://help.aliyun.com/document_detail/153405.html
https://help.aliyun.com/document_detail/103221.html
https://help.aliyun.com/document_detail/154200.html
https://help.aliyun.com/document_detail/148586.html
https://help.aliyun.com/document_detail/154199.html




阿里API网关使用总结
https://www.jianshu.com/p/f04f5bd8f84b



