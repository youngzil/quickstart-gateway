1、下载
2、编译安装和运行
   nginx.conf文件的结构
3、负载均衡和健康检查


---------------------------------------------------------------------------------------------------------------------
下载

http://nginx.org/
https://nginx.org/en/docs/

https://www.nginx.com/
https://www.nginx.com/resources/wiki/


下载
https://nginx.org/en/download.html


https://github.com/nginx/nginx
https://github.com/dockerfile/nginx
http://dockerfile.github.io/#/nginx


依赖下载

pcre下载
http://www.pcre.org/

zlib下载
http://www.zlib.net/


下载
https://www.openssl.org/source/
http://distfiles.macports.org/openssl/

OpenSSL下载安装
https://www.jianshu.com/p/12a1dc4ab7a0






下载 Nginx服务器全部历史版本的链接：http://nginx.org/download/

加压tar包后，部分文件和目录介绍：
        src 目录存放了 Nginx软件的所有源代码。
        man 目录中存放了Nginx 软件的帮助文档，Nginx 安装完成后，使用 man 命令可以查看。
        html 目录中存放了l 两个后缀名为.html的静态网页文件。这两个文件与 Nginx服务器的运行相关。
        conf 目录存放的是Nginx 服务器的配置文件，包含 Nginx服务器的基本配置文件和对部分特性的配置文件。
        auto 目录中存放了大量脚本文件，和configure脚本程序有关。
        configure文件是 Nginx软件的自动脚本程序。运行 configure自动脚本一般会完成两项工作：一是检查环境，根据环境检查结果生成 C 代码；二是生成编译代码需要的Makefile 文件。


---------------------------------------------------------------------------------------------------------------------
编译安装和运行：./configure  &&  make  &&  make  install


1、进入根目录，使用以下命令配置并生成Makefile 文件和 objs文件夹：
./configure --prefix=/Users/yangzl/mysoft/nginx1.17.3 --with-pcre=/Users/yangzl/mysoft/pcre-8.43


./configure --prefix=/Users/yangzl/mysoft/nginx1.17.3 --with-pcre=/Users/yangzl/mysoft/pcre-8.43


./configure --prefix=/home/aifgw/nginx-gateway  --with-pcre=/home/aifgw/pcre-8.43 --with-zlib=/home/aifgw/zlib-1.2.11

安装路径是/Users/yangzl/Downloads/nginx1.17.3
其他的是编译需要的依赖，比如pcre、zlib等，安装过程可能会找不到依赖的情况
在线安装所需依赖工具：yum -y install gcc gcc-c++ automake pcre pcre-devel zlib zlib-devel open openssl-devel
生成的 Nginx 软件的Makefile文件就保存在当前的工作目录。
常用的依赖：PCRE library、OpenSSL library、system zlib



make && make install


2、得到了 Nginx 的 Makefile 文件后，在/app/nginx/nginx-1.6.3-compile/nginx-1.6.3目录下使用 make 文件进行编译：
[root@localhost nginx-1.6.3]# make


3、编译完成后，使用 make 的 install命令安装 nginx软件：
[root@localhost nginx-1.6.3]# make install


4、命令运行完成后，将当前目录定位到之前指定的安装目录/nginx1.17.3下，可以对 Nginx服务器安装后的全部资源进行查看：
    conf 目录存放了所有配置文件。其中，nginx.conf文件是 Nginx服务器的主配置文件，其它配置文件是用来配置Nginx 的相关功能的。在此目录下，所有的配置文件都提供了以.default结尾的默认配置文件，方便我们将配置过的.conf文件恢复到初始状态。
    html 目录存放了Nginx服务器在运行过程中调用的一些 html 文件。
    logs 目录用来存放Nginx服务器日志的。
    sbin 目录，目前只有nginx一个文件，这就是Nginx服务器的主程序了。

5、进入sbin目录下
./sbin/nginx -V 查看所有加载的模块
./sbin/nginx -h   查看使用帮助
./sbin/nginx   启动
/usr/local/nginx/sbin/nginx -c /usr/local/nginx/conf/nginx.conf


/usr/local/nginx/sbin/nginx start|stop|restart 启动和关闭ngix服务
 访问 http://ip地址

 5》nginx命令参数
   nginx -m 显示所有加载的模块
   nginx -l 显示所有可以使用的指令
   nginx -t 检查nginx的配置文件是否正确
   nginx 启动nginx
   nginx -s reload 重启nginx
   nginx -s stop 停止nginx



6、默认使用80端口


7、Nginx 服务的停止
停止 Nginx 有两种方法：一种是快速停止；一种是平缓停止。
快速停止是指立即停止当前Nginx 服务正在处理的所有网络请求，马上丢弃连接，停止工作；
平缓停止是指允许 Nginx服务将当前正在处理的网络请求处理完成，但不再接受新的请求，之后关闭连接，停止工作。

查看进程号
 ps aux|grep nginx
 
 发送停止进程信号
 kill -INT 9630
 kill -TERM 9639
 kill -QUIT 9646
 
 TERM 和 INT 信号用于快速停止，QUIT 用于平缓停止。



nginx.conf文件的结构
同样的配置，内层的更详细的配置为准
http块：server 块是多个监听端口服务，location 块是对应的path处理模块


nginx.conf 文件的基本结构为：（在同一配置块中嵌套的配置块，各个之间不存在次序关系。）
...                            #全局块
events{                        #events 块
    ...
}
http{                          #http 块
    ...                        #http 全局块
    server{                    #server 块
        ...                    #server 全局块
        location [PATTERN]{    #location 块
            ...
        }
        location [PATTERN]{    #location 块
            ...
        }
    }
    server{                    #server 块
        ...
    }
    ...                        #http 全局块
}

各个块的作用如下：同样的配置，内层的更详细的配置为准
　　1.全局块
　　全局块是默认配置文件从开始到events块之间的一部分内容，主要设置一些影响Nginx服务器整体运行的配置指令，因此，这些指令的作用域是Nginx服务器全局。
　　通常包括配置运行Nginx 服务器的用户（组）、允许生成的worker process数、Nginx 进程 PID存放路径、日志的存放路径和类型以及配置文件引入等。
　　2.events 块
　　events 块涉及的指令主要影响Nginx 服务器与用户的网络连接。常用到的设置包括是否开启对多worker process下的网络连接进行序列化，是否允许同时接收多个网络连接，选取哪种事件驱动模型处理连接请求，每个 worker process可以同时支持的最大连接数等。
　　这一部分指令对 Nginx服务器的性能影响较大，在实际配置中应该根据实际情况灵活调整。
　　3.http 块
　　http块是Nginx 服务器配置中的重要部分，代理、缓存和日志定义等绝大多数的功能和第三方模块的配置都可以放在这个模块。可以在 http全局块中配置的指令包括文件引入、MIME-Type定义、日志自定义、是否使用sendfile传输文件、连接超时时间、单连接q 请求数上限等。
　　4.server 块
　　server 块和“虚拟主机”的概念有密切联系。在 server 全局块中，最常见的两个配置项是本虚拟主机的监听配置和本虚拟主机的名称或 IP 配置。
　　5.location 块
　　location 块的主要作用是，基于Nginx服务器接受到的q 请求字符串，对除虚拟主机名称之外的字符串j 进行匹配，对特定的请求进行处理。地址定向、数据缓存和应答控制等功能都是在这部分实现。许多第三方模块的配置也是在location块中提供功能。



参考
https://www.jianshu.com/p/320a48fcef57

配置讲解参考
https://blog.csdn.net/tjcyjd/article/details/50695922

---------------------------------------------------------------------------------------------------------------------
3、负载均衡和健康检查


Nginx负载均衡策略
1、轮询	默认方式
2、weight	权重方式
3、ip_hash	依据ip分配方式
4、least_conn	最少连接方式
5、fair（第三方）	响应时间方式
6、url_hash（第三方）	依据URL分配方式

1、轮询（round-robin）
这是nginx默认的负载均衡策略
有如下参数：
fail_timeout	与max_fails结合使用。
max_fails	 设置在fail_timeout参数设置的时间内最大失败次数，如果在这个时间内，所有针对该服务器的请求都失败了，那么认为该服务器会被认为是停机了，
fail_time	服务器会被认为停机的时间长度,默认为10s。
backup	标记该服务器为备用服务器。当主服务器停止时，请求会被发送到它这里。
down	标记服务器永久停机了。


2、weight	权重方式
#动态服务器组
    upstream dynamic_zuoyu {
        server localhost:8080   weight=2;  #tomcat 7.0
        server localhost:8081;  #tomcat 8.0
        server localhost:8082   backup;  #tomcat 8.5
        server localhost:8083   max_fails=3 fail_timeout=20s;  #tomcat 9.0
    }

3、ip_hash	依据ip分配方式
#动态服务器组
    upstream dynamic_zuoyu {
        ip_hash;    #保证每个访客固定访问一个后端服务器
        server localhost:8080   weight=2;  #tomcat 7.0
        server localhost:8081;  #tomcat 8.0
        server localhost:8082;  #tomcat 8.5
        server localhost:8083   max_fails=3 fail_timeout=20s;  #tomcat 9.0
    }

4、least_conn	最少连接方式，最少连接（least-connected）

#动态服务器组
    upstream dynamic_zuoyu {
        least_conn;    #把请求转发给连接数较少的后端服务器
        server localhost:8080   weight=2;  #tomcat 7.0
        server localhost:8081;  #tomcat 8.0
        server localhost:8082 backup;  #tomcat 8.5
        server localhost:8083   max_fails=3 fail_timeout=20s;  #tomcat 9.0
    }

5、fair（第三方）	响应时间方式
按照服务器端的响应时间来分配请求，响应时间短的优先分配。
    #动态服务器组
    upstream dynamic_zuoyu {
        server localhost:8080;  #tomcat 7.0
        server localhost:8081;  #tomcat 8.0
        server localhost:8082;  #tomcat 8.5
        server localhost:8083;  #tomcat 9.0
        fair;    #实现响应时间短的优先分配
    }


6、url_hash（第三方）	依据URL分配方式
 #动态服务器组
    upstream dynamic_zuoyu {
        hash $request_uri;    #实现每个url定向到同一个后端服务器
        server localhost:8080;  #tomcat 7.0
        server localhost:8081;  #tomcat 8.0
        server localhost:8082;  #tomcat 8.5
        server localhost:8083;  #tomcat 9.0
    }
    


1.17.3 没有安装健康检查模块，配置check报错，但是会自动检测


负载均衡策略参考
https://www.cnblogs.com/1214804270hacker/p/9325150.html


健康检测参考
https://blog.csdn.net/yanggd1987/article/details/84191746
https://blog.csdn.net/wsxxdwwzjdy/article/details/79203829
https://blog.csdn.net/liaomin416100569/article/details/72897641






---------------------------------------------------------------------------------------------------------------------






