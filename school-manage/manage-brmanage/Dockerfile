FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER wangiegie@gmail.com

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN mkdir -p /pigx-brmanage

WORKDIR /pigx-brmanage

EXPOSE 4000

ADD ./pig-brmanage/pig-brmanage-biz/target/pig-brmanage-biz.jar ./

CMD java -Djava.security.egd=file:/dev/./urandom -jar pig-brmanage-biz.jar
