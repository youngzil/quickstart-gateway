1、下载、编译安装和运行


---------------------------------------------------------------------------------------------------------------------

参考nginx部署，基本是一样的
/Users/yangzl/git/quickstart-gateway/docs/nginx/Nginx部署.md


下载
http://tengine.taobao.org/download_cn.html

---------------------------------------------------------------------------------------------------------------------
编译安装和运行：./configure  &&  make  &&  make  install


1、进入根目录，使用以下命令配置并生成Makefile 文件和 objs文件夹：

./configure --prefix=/Users/yangzl/mysoft/tengine2.3.2 --with-pcre=/Users/yangzl/mysoft/pcre-8.43 --with-openssl=/Users/yangzl/mysoft/openssl-1.1.1d


./configure --prefix=/Users/yangzl/mysoft/tengine2.3.2 --with-http_upstream_check_module --with-pcre=/Users/yangzl/mysoft/pcre-8.43 --with-openssl=/Users/yangzl/mysoft/openssl-1.1.1d


./configure --prefix=$HOME/tengine-gateway --with-pcre=$HOME/pcre-8.43 --with-openssl=$HOME/openssl-1.1.1d --with-zlib=$HOME/zlib-1.2.11



2、编译安装：
make && make install


3、进入sbin目录下
./sbin/nginx -h   查看使用帮助
./sbin/nginx   启动
/usr/local/nginx/sbin/nginx -c /usr/local/nginx/conf/nginx.conf


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


