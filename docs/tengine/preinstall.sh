#!/usr/bin/env bash

NGINX_GATEWAY_HOME=/home/aifgw

if [[ ! -d "$NGINX_GATEWAY_HOME" ]]; then
  mkdir -p $NGINX_GATEWAY_HOME
fi

scp $HOME/deploy/pkg/tengine/tengine-2.3.2.tar.gz ~

scp $HOME/deploy/pkg/nginx-depend/openssl-1.1.1d.tar.gz ~
scp $HOME/deploy/pkg/nginx-depend/pcre-8.43.tar.gz ~
scp $HOME/deploy/pkg/nginx-depend/zlib-1.2.11.tar.gz ~

cd $HOME
tar -xzvf tengine-2.3.2.tar.gz
tar -xzvf pcre-8.43.tar.gz
tar -xzvf openssl-1.1.1d.tar.gz
tar -xzvf zlib-1.2.11.tar.gz

rm -f tengine-2.3.2.tar.gz
rm -f pcre-8.43.tar.gz
rm -f openssl-1.1.1d.tar.gz
rm -f zlib-1.2.11.tar.gz


cd $HOME/tengine-2.3.2
./configure --prefix=$NGINX_GATEWAY_HOME/tengine-gateway --add-module=./modules/ngx_http_upstream_check_module --with-pcre=$HOME/pcre-8.43 --with-openssl=$HOME/openssl-1.1.1d --with-zlib=$HOME/zlib-1.2.11
make && make install

cd $HOME
rm -rf tengine-2.3.2
rm -rf pcre-8.43
rm -rf openssl-1.1.1d
rm -rf zlib-1.2.11

cd $NGINX_GATEWAY_HOME
tar -czvf tengine-gateway.tar.gz tengine-gateway
rm -rf  tengine-gateway

mv tengine-gateway.tar.gz $HOME/deploy/pkg/tengine
