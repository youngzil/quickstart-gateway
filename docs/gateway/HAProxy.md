- [HAProxy的八种负载均衡策略](#HAProxy的八种负载均衡策略)





---------------------------------------------------------------------------------------------------------------------

HAProxy是一个使用C语言编写的自由及开放源代码软件，其提供高可用性、负载均衡，以及基于TCP和HTTP的应用程序代理。

HAProxy是支持虚拟主机的，HAProxy的优点能够补充Nginx的一些缺点，比如支持Session的保持，Cookie的引导。同时支持通过获取指定的url来检测后端服务器的状态。

HAProxy跟LVS类似，本身就只是一款负载均衡软件，单纯从效率上来讲HAProxy会比Nginx有更出色的负载均衡速度，在并发处理上也是优于Nginx的。

HAProxy支持TCP协议的负载均衡转发，可以对MySQL读进行负载均衡，对后端的MySQL节点进行检测和负载均衡，大家可以用LVS+Keepalived对MySQL主从做负载均衡。



---------------------------------------------------------------------------------------------------------------------

## HAProxy的八种负载均衡策略

①roundrobin，表示简单的轮询，这个不多说，这个是负载均衡基本都具备的；

② static-rr，表示根据权重，建议关注；

③leastconn，表示最少连接者先处理，建议关注；

④ source，表示根据请求源IP，这个跟Nginx的IP_hash机制类似我们用其作为解决session问题的一种方法，建议关注；

⑤uri，表示根据请求的URI,做cdn需使用;

⑥url_param，表示根据请求的URl参数’balance url_param’ requires an URL parameter name；

⑦hdr(name)，表示根据HTTP请求头来锁定每一次HTTP请求；

⑧rdp-cookie(name)，表示根据据cookie(name)来锁定并哈希每一次TCP请求。



[haproxy的八种负载均衡策略](https://blog.csdn.net/braincer/article/details/73866124)  
[Haproxy（二）之负载均衡配置详解](https://www.jianshu.com/p/a7e3199a0a09)  



---------------------------------------------------------------------------------------------------------------------











