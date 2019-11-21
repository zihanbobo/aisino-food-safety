FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER wangiegie@gmail.com

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN mkdir -p /pig-foodsafety

WORKDIR /pig-foodsafety

EXPOSE 4050

ADD ./pig-foodsafety/pig-foodsafety-biz/target/pig-foodsafety-biz.jar ./

CMD java -Djava.security.egd=file:/dev/./urandom -jar pig-foodsafety-biz.jar
