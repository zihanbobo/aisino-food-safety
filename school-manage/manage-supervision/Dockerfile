FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER wangiegie@gmail.com

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN mkdir -p /pigx-school

WORKDIR /pigx-school

EXPOSE 4000

ADD ./pig-school/pig-safety-biz/target/pig-safety-biz.jar ./

CMD java -Djava.security.egd=file:/dev/./urandom -jar pig-safety-biz.jar
