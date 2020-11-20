- [Nginx rewrite使用](#Nginx-rewrite使用)
- [Nginx内置变量](#Nginx内置变量)
- [Nginx几种转发策略](#Nginx几种转发策略)


[Nginx介绍](../../quickstart-nginx/README.md)
---------------------------------------------------------------------------------------------------------------------


## Nginx rewrite使用


https://blog.csdn.net/tjcyjd/article/details/50897959
https://www.cnblogs.com/likwo/p/6513117.html
https://www.jianshu.com/p/82ad48bc85ab

---------------------------------------------------------------------------------------------------------------------
## Nginx内置变量


官网地址  
http://nginx.org/en/docs/varindex.html  


参考
https://www.jianshu.com/p/2379e619030c
https://www.cnblogs.com/pyng/p/10451295.html
https://blog.csdn.net/leonnew/article/details/82732120



---------------------------------------------------------------------------------------------------------------------
## Nginx几种转发策略

nginx的upstream目前支持的5种方式的分配
- 1、轮询（默认）  
  每个请求按时间顺序逐一分配到不同的后端服务器，如果后端服务器down掉，能自动剔除。  
  upstream backserver {  
  server 192.168.0.14;  
  server 192.168.0.15;  
  }  

- 2、指定权重  
  指定轮询几率，weight和访问比率成正比，用于后端服务器性能不均的情况。  
  upstream backserver {  
  server 192.168.0.14 weight=10;  
  server 192.168.0.15 weight=10;  
  }   
- 3、IP绑定 ip_hash  
  每个请求按访问ip的hash结果分配，这样每个访客固定访问一个后端服务器，可以解决session的问题。  
  upstream backserver {  
  ip_hash;  
  server 192.168.0.14:88;  
  server 192.168.0.15:80;  
  } 
- 4、fair（第三方）  
  按后端服务器的响应时间来分配请求，响应时间短的优先分配。  
  upstream backserver {  
  server server1;  
  server server2;  
  fair;  
  } 
- 5、url_hash（第三方）  
  按访问url的hash结果来分配请求，使每个url定向到同一个后端服务器，后端服务器为缓存时比较有效。  
  upstream backserver {  
  server squid1:3128;  
  server squid2:3128;  
  hash $request_uri;  
  hash_method crc32;  
  }  




参考  
[Nginx几种转发策略](https://blog.csdn.net/hlg1995/article/details/84074749)  


