项目地址  
https://github.com/youngzil/quickstart-gateway


---------------------------------------------------------------------------------------------------------------------  
- [API服务网关](#API服务网关)
    - [Open Service Broker API](#Open-Service-Broker-API)
    - [SpringGateway和Zuul比较](#SpringGateway和Zuul比较)
    - [京东京麦开放平台网关与消息高可用架构](#京东京麦开放平台网关与消息高可用架构)
    - [如何设计一个安全的对外接口](#如何设计一个安全的对外接口)
    - [浙江网管微服务管控平台](#浙江网管微服务管控平台)
    - [网关之降级、熔断、限流、隔离、幂等性验证、超时重试机制](#网关之降级-熔断-限流-隔离-幂等性验证-超时重试机制)
    - [API网关](#API网关)
    - [软硬件负载均衡](#软硬件负载均衡)
    - [错误码如何设计才合理](#错误码如何设计才合理)
- [常见的API网关开放平台](#常见的API网关开放平台)
    - [阿里云API网关](#阿里云API网关)
    - [能力开放平台](#能力开放平台)
    - [抖音开放平台](#抖音开放平台)
    - [京东到家开放平台](#京东到家开放平台)
- [API网关框架](#API网关框架)
    - [Tengine](#Tengine)
        - [tengine学习](#tengine学习)
        - [tengine部署](#tengine部署)
    - [Nginx](#Nginx)
        - [Ngin学习](#Ngin学习)
        - [Nginx部署](#Nginx部署)
        - [谈谈Nginx快的原因](#谈谈Nginx快的原因)
    - [Zuul](#Zuul)
    - [Zuul2](#Zuul2)
    - [常见API网关](#常见API网关)

---------------------------------------------------------------------------------------------------------------------  
## API服务网关

### [Open-Service-Broker-API](docs/gateway/Open Service Broker API.md)
### [SpringGateway和Zuul比较](docs/gateway/SpringGateway和Zuul比较.md)
### [京东京麦开放平台网关与消息高可用架构](docs/gateway/京东京麦开放平台网关与消息高可用架构.pdf)
### [如何设计一个安全的对外接口](docs/gateway/如何设计一个安全的对外接口.md)
### [浙江网管微服务管控平台](docs/gateway/浙江网管微服务管控平台.md)
### [网关之降级-熔断-限流-隔离-幂等性验证-超时重试机制](docs/gateway/网关之降级、熔断、限流、隔离、幂等性验证、超时重试机制.md)
### [API服务网关](docs/API服务网关.md)
### [能力开放平台](docs/OpenPlatform/能力开放平台.md)
### [软硬件负载均衡](docs/gateway/软件、硬件负载均衡.md)
### [错误码如何设计才合理](docs/gateway/错误码如何设计才合理.md)


---------------------------------------------------------------------------------------------------------------------  
## 常见的API网关开放平台

### [阿里云API网关](docs/gateway/阿里云API网关.md)

### [抖音开放平台](docs/OpenPlatform/抖音开放平台)
### [京东到家开放平台](docs/OpenPlatform/京东到家开放平台)


---------------------------------------------------------------------------------------------------------------------  

## API网关框架


### Tengine
#### [tengine学习](docs/tengine/tengine学习.md)
#### [tengine部署](docs/tengine/tengine部署.md)

### Nginx
#### [Ngin学习](docs/nginx/Ngin学习.md)
#### [Nginx部署](docs/nginx/Nginx部署.md)
#### [谈谈Nginx快的原因](docs/nginx/谈谈Nginx快的原因.md)



### [Zuul](docs/nginx/Zuul.md)
### [Zuul2](docs/nginx/Zuul2.md)
### [OpenResty](docs/nginx/OpenResty.md)
### [常见API网关](docs/nginx/常见API网关.md)


---------------------------------------------------------------------------------------------------------------------  

限流框架：Sentinel、resilience4j、Hystrix、SnowJena
参考  
quickstart-netflix/quickstart-hystrix





API Gateway:
APIGateway 即API网关，所有请求首先会经过这个网关，然后到达后端服务，有点类似于Facade模式。API网关作为系统接口对外的统一出口，可以减少调用方对服务实现的感知。 
http://blog.csdn.net/Tredemere/article/details/78246413?locationNum=10&fps=1


API Gateway/Backend for Front-End 作为一种目前非常流行并且经过验证的 Pattern，不论是在 Netflix/Amazon 还是 BAT 都得到了广泛的应用。在 Microservice architecture pattern 大行其道的当下，API Gateway 的建设显得尤为重要
https://mp.weixin.qq.com/s?__biz=MzIwMzg1ODcwMw==&mid=2247487357&idx=1&sn=794b57e6bae5f3639ed38e017a67a6ed&chksm=96c9b91da1be300b8722df814b28d68a58fcbd993c24f6800333f2bbe855cc872f62f9453f31#rd


业界开源的方案有 Netflix/zuul，Kong/kong，openresty/openresty + 自研，TykTechnologies/tyk，Netty + 自研。

zuul
https://github.com/Netflix/zuul

Kong
https://konghq.com/install
https://github.com/Kong/kong

Spring-cloud-gateway


LittleProxy
https://github.com/adamfisk/LittleProxy
https://www.getlantern.org

Nginx、Apache Httpd

openresty
https://openresty.org/cn/


Projects Using Zuul:
https://cloud.spring.io/
https://jhipster.github.io/

http://blog.csdn.net/cwenao/article/details/54572648
https://github.com/cwenao/springboot_cwenao/tree/master/apigateway-bootcwenao

https://github.com/chengdedeng/waf


Pattern: API Gateway / Backend for Front-End
Context
http://microservices.io/patterns/apigateway.html


Zuul和Nginx的性能报告对比：
http://instea.sk/2015/04/netflix-zuul-vs-nginx-performance/
http://blog.didispace.com/zuul-vs-nginx-performance/ 
https://medium.com/netflix-techblog/zuul-2-the-netflix-journey-to-asynchronous-non-blocking-systems-45947377fb5c


各种开源API网关、微服务网关对比测试
http://blog.zollty.com/b/archive/the-research-of-opensource-api-gateways.html


High-Performance Java API Gateway 
https://dromara.org
https://github.com/Dromara/soul


基于apache apisix的全流量api网关


