FROM ubuntu:latest
LABEL maintiner=LanceDai
WORKDIR /root
# 换国内源
RUN sed -i 's/archive.ubuntu.com/mirrors.ustc.edu.cn/g' /etc/apt/sources.list
# install openssh-server, openjdk, wget and which(hdfs need)
RUN apt-get update -y && apt-get install -y openssh-server openjdk-8-jdk
# 下载Hadoop
# hadoop版本
# export HADOOP_VERSION=hadoop-2.6.0-cdh5.7.0
ARG HADOOP_VERSION=hadoop-2.6.0-cdh5.7.0

# 下载速度过慢
#RUN wget http://archive.cloudera.com/cdh5/cdh/5/${HADOOP_VERSION}.tar.gz

#从本地导入已下载好的文件
COPY ./resources/${HADOOP_VERSION}.tar.gz /root/

RUN cd /root && tar -xvf ${HADOOP_VERSION}.tar.gz -C /usr/local/ && \
    mv /usr/local/${HADOOP_VERSION} /usr/local/hadoop && \
    rm -rf /root/${HADOOP_VERSION}.tar.gz

# 清理cmd文件
RUN rm -rf /usr/local/hadoop/sbin/*.cmd && \
    rm -rf /usr/local/hadoop/bin/*.cmd

# set environment variabl
# echo export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64 >> ~/.bash_profile
ENV JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
# echo export HADOOP_HOME=/usr/local/hadoop >> ~/.bash_profile
ENV HADOOP_HOME=/usr/local/hadoop
# echo 'export PATH=$PATH:$HADOOP_HOME/bin:$HADOOP_HOME/sbin' >> ~/.bash_profile
ENV PATH=$PATH:$HADOOP_HOME/bin:$HADOOP_HOME/sbin
# source ~/.bash_profile

# ssh without key
# -f 生成的文件名 -t 指定要创建的密钥类型 -P 提供(旧)密语 指定为空
# 保证 可以 本机SSH自己
RUN ssh-keygen -t rsa -f ~/.ssh/id_rsa -P '' && \
    cat ~/.ssh/id_rsa.pub >> ~/.ssh/authorized_keys

RUN mkdir -p ~/hdfs/namenode && \
    mkdir -p ~/hdfs/datanode && \
    mkdir $HADOOP_HOME/logs

# 暴露挂载点 [hadoop配置文件, ssh配置文件, 脚本文件]
VOLUME ["/usr/local/hadoop/etc/hadoop/", "/root/.ssh/config/", "/root/"]

# format namenode
RUN hdfs namenode -format

# 修改hadoop-env.sh声明JAVA_HOME
# RUN sed -i 's!${JAVA_HOME}!/usr/lib/jvm/java-8-openjdk-amd64!g' ${HADOOP_HOME}/etc/hadoop/hadoop-env.sh
CMD ["systemctl start ssh"]
