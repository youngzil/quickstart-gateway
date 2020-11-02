- [下载、编译安装和运行](#下载、编译安装和运行)
- [健康检测模块编译和配置](#健康检测模块编译和配置)


---------------------------------------------------------------------------------------------------------------------
## 下载、编译安装和运行


参考[Nginx部署](../nginx/Nginx部署.md) ，基本是一样的

下载
http://tengine.taobao.org/download_cn.html



编译安装和运行：./configure  &&  make  &&  make  install


1、进入根目录，使用以下命令配置并生成Makefile 文件和 objs文件夹：

./configure --prefix=/Users/yangzl/mysoft/tengine2.3.2 --with-pcre=/Users/yangzl/mysoft/pcre-8.43 --with-openssl=/Users/yangzl/mysoft/openssl-1.1.1d


./configure --prefix=/Users/yangzl/mysoft/tengine2.3.2 --with-http_upstream_check_module --with-pcre=/Users/yangzl/mysoft/pcre-8.43 --with-openssl=/Users/yangzl/mysoft/openssl-1.1.1d


./configure --prefix=$HOME/tengine-gateway --with-pcre=$HOME/pcre-8.43 --with-openssl=$HOME/openssl-1.1.1d --with-zlib=$HOME/zlib-1.2.11


./configure --prefix=/Users/yangzl/mysoft/tengine2.3.2 --add-module=./modules/ngx_http_upstream_check_module --with-pcre=/Users/yangzl/mysoft/pcre-8.43 --with-openssl=/Users/yangzl/mysoft/openssl-1.1.1d --with-zlib=/Users/yangzl/mysoft/zlib-1.2.11


2、编译安装：
make && make install


3、进入sbin目录下
./sbin/nginx -h   查看使用帮助
./sbin/nginx   启动
/usr/local/nginx/sbin/nginx -c /usr/local/nginx/conf/nginx.conf

/usr/local/webserver/nginx/sbin/nginx -s reload            # 重新载入配置文件
/usr/local/webserver/nginx/sbin/nginx -s reopen            # 重启 Nginx
/usr/local/webserver/nginx/sbin/nginx -s stop              # 停止 Nginx


4、Nginx 服务的停止
查看进程号
 ps aux|grep nginx
 
 发送停止进程信号
 kill -INT 9630
 kill -TERM 9639
 kill -QUIT 9646
 
 TERM 和 INT 信号用于快速停止，QUIT 用于平缓停止。
 
 
 
 cd $HOME/tengine-2.3.2
 
 ./configure --prefix=/home/aifgw/tengine2.3.2 --with-pcre=/home/aifgw/pcre-8.43 --with-openssl=/home/aifgw/openssl-1.1.1d --with-zlib=/home/aifgw/zlib-1.2.11
 
 make && make install
 
 
 ps aux|grep sbin/nginx | grep master
 kill -QUIT 9646
 
 


---------------------------------------------------------------------------------------------------------------------

## 健康检测模块编译和配置


编译的时候新增健康检测模块
--add-module=./modules/ngx_http_upstream_check_module

如
./configure --prefix=/Users/yangzl/mysoft/tengine2.3.2 --add-module=./modules/ngx_http_upstream_check_module --with-pcre=/Users/yangzl/mysoft/pcre-8.43 --with-openssl=/Users/yangzl/mysoft/openssl-1.1.1d --with-zlib=/Users/yangzl/mysoft/zlib-1.2.11


nginx.conf配置
    upstream test {
            server 192.168.80.11:8080  weight=2;
            server 192.168.80.12:8080  weight=1;
           check interval=3000 rise=2 fall=5 timeout=1000 type=http;
           check_http_send "HEAD / HTTP/1.0\r\n\r\n";
           check_http_expect_alive http_2xx http_3xx;
        }

在nginx.conf配置文件http—server下面添加如下属性
        location /status {
         check_status;
        }

location / {
           proxy_pass http://test;
           root   html;
            index  index.html;
重启nginx

打开浏览器，输入网址进行查看
http://192.168.80.11/status



interval表示每隔3000毫秒向后端发送健康检查包 
rise表示如果连续成功次数达到2 服务器就被认为是up 
fail表示如果连续失败次数达到5 服务器就被认为是down
timeout表示后端健康请求的超时时间是1000毫秒 
type表示发送的健康检查包是http请求

check_http_send 表示http健康检查包发送的请求内容。为了减少传输数据量，推荐采用“head”方法

check_http_expect_alive 指定HTTP回复的成功状态，默认认为2XX和3XX的状态是健康的。


参考
https://blog.csdn.net/wsxxdwwzjdy/article/details/79203829

---------------------------------------------------------------------------------------------------------------------


