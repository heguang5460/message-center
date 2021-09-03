FROM centos:7
MAINTAINER heguang<heguang5460@foxmail.com>

ENV TZ "Asia/Shanghai"
ENV LANG=C.UTF-8
ENV RUNTIME_PROFILE dev
ENV DOCKER_IP 127.0.0.1
ENV JVM_XMS 1024m
ENV JVM_XMX 1024m
ENV JVM_XMN 512m

RUN yum -y install wget

RUN wget "https://repo.huaweicloud.com/java/jdk/8u171-b11/jdk-8u171-linux-x64.rpm" && \
    yum -y localinstall jdk-8u171-linux-x64.rpm && \
    rm jdk-8u171-linux-x64.rpm

# 创建JVM垃圾回收日志目录
RUN mkdir -p /root/logs/java/ \
    mkdir -p /opt/app/

ADD startTomcat.sh /opt/app
ADD setenv.sh /opt/app/
ADD message-web.jar /opt/app/
ENV ENV_APP_FILE_PATH=/opt/app/message-web.jar

EXPOSE 8082

WORKDIR /opt/app/

ENTRYPOINT ["/opt/app/startTomcat.sh"]