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


LittleProxy
https://github.com/adamfisk/LittleProxy
https://www.getlantern.org

Nginx、Apache Httpd


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





