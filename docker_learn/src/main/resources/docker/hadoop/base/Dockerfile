FROM ubuntu:latest
LABEL maintiner=LanceDai
WORKDIR /root

# 替换阿里云的源
RUN sed -i 's/archive.ubuntu.com/mirrors.ustc.edu.cn/g' /etc/apt/sources.list

# 更新源、安装openssh, jdk, bash 并修改配置文件和生成key 并且同步时间
RUN apt-get update -y && \
    apt-get install -y \
    openssh-server \
    openjdk-8-jdk && \
    echo "root:root" | chpasswd

# 安装Hadoop
# hadoop版本
# export HADOOP_VERSION=hadoop-2.6.0-cdh5.7.0
#ENV HADOOP_VERSION=2.6.0-cdh5.16.1
ENV HADOOP_VERSION=2.6.0-cdh5.7.0

# 下载速度过慢
#RUN wget http://archive.cloudera.com/cdh5/cdh/5/${HADOOP_VERSION}.tar.gz

#从本地导入已下载好的文件
COPY ./resources/hadoop-${HADOOP_VERSION}.tar.gz /root/

RUN cd /root && tar -xvf hadoop-${HADOOP_VERSION}.tar.gz -C /opt/ && \
    rm -rf /root/hadoop-${HADOOP_VERSION}.tar.gz


# set environment variabl
# echo export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64 >> ~/.bash_profile
ENV JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64/
# echo export HADOOP_HOME=/opt/hadoop-$HADOOP_VERSION/ >> ~/.bash_profile
ENV HADOOP_HOME=/opt/hadoop-$HADOOP_VERSION
# echo 'export PATH=$PATH:$HADOOP_HOME/bin:$HADOOP_HOME/sbin' >> ~/.bash_profile
ENV PATH=$PATH:$HADOOP_HOME/bin:$HADOOP_HOME/sbin
# source ~/.bash_profile

# format namenode
# 设定JAVA_HOME
RUN sed -i 's#${JAVA_HOME}#/usr/lib/jvm/java-8-openjdk-amd64/#g' $HADOOP_HOME/etc/hadoop/hadoop-env.sh

# 清理cmd文件
RUN rm -rf $HADOOP_HOME/sbin/*.cmd && \
    rm -rf $HADOOP_HOME/bin/*.cmd && \
    rm -rf $HADOOP_HOME/etc/hadoop/*.cmd

# ssh without key
# -f 生成的文件名 -t 指定要创建的密钥类型 -P 提供(旧)密语 指定为空
# 保证 可以 本机SSH自己
RUN ssh-keygen -t rsa -f ~/.ssh/id_rsa -P '' && \
    cat ~/.ssh/id_rsa.pub >> ~/.ssh/authorized_keys

# copy ssh setting
COPY resources/config /root/.ssh/

# 新建hadoop日志文件夹
RUN mkdir $HADOOP_HOME/logs
COPY entrypoint.sh /root/
RUN chmod a+x /root/entrypoint.sh

# 修改hadoop-env.sh声明JAVA_HOME, 保证环境变量的正常获取,使用sh -c 或shell命令而不是数组
ENTRYPOINT ["./entrypoint.sh"]
CMD  bash