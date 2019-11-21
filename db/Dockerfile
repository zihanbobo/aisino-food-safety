FROM mysql:5.7

MAINTAINER sn

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

COPY ./db/pig.sql /docker-entrypoint-initdb.d
