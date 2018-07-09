
http://nginx.org/
https://nginx.org/en/docs/

https://www.nginx.com/
https://www.nginx.com/resources/wiki/


https://github.com/nginx/nginx
https://github.com/dockerfile/nginx
http://dockerfile.github.io/#/nginx


http://www.nginx.cn/doc/
http://tool.oschina.net/apidocs/apidoc?api=nginx-zh


https://www.oschina.net/p/nginx

Nginx（发音同 engine x）是一款轻量级的 Web 服务器/反向代理服务器及电子邮件（IMAP/POP3）代理服务器，并在一个 BSD-like 协议下发行，可以在 UNIX、GNU/Linux、BSD、Mac OS X、Solaris，以及 Microsoft Windows 等操作系统中运行。

Nginx 由俄罗斯的程序设计师 Igor Sysoev 所开发，最初供俄国大型的入口网站及搜寻引擎 Rambler（俄文：Рамблер）使用。其特点是占有内存少，并发能力强，事实上 nginx 的并发能力确实在同类型的网页服务器器中表现较好。

特点
Nginx 是一款面向性能设计的 HTTP 服务器，相较于 Apache、lighttpd 具有占有内存少，稳定性高等优势。与旧版本（<=2.2）的 Apache 不同，nginx 不采用每客户机一线程的设计模型，而是充分使用异步逻辑，削减了上下文调度开销，所以并发服务能力更强。整体采用模块化设计，有丰富的模块库和第三方模块库，配置灵活。在 Linux 操作系统下，nginx 使用 epoll 事件模型，得益于此，nginx 在 Linux 操作系统下效率相当高。同时 Nginx 在 OpenBSD 或 FreeBSD 操作系统上采用类似于 epoll 的高效事件模型 kqueue。

可大量平行处理
Nginx 在官方测试的结果中，能够支持五万个平行连接，而在实际的运作中，可以支持二万至四万个平行链接。

统计
2017 年三月，中国注册域名总数的 9.65% 使用 Nginx。(中国网络统计)

目前中国大陆使用 nginx 网站用户有：新浪、网易、腾讯，另外知名的微网志 Plurk 也使用 nginx。
oschian本站采用 Nginx 作为 Web 服务器。






