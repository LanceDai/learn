# 构建Hadoop镜像
FROM ubuntu:latest
LABEL maintiner=LanceDai
# 换国内源
RUN sed -i 's/archive.ubuntu.com/mirrors.ustc.edu.cn/g' /etc/apt/sources.list
# 更新缓存
RUN apt-get update
RUN apt-get install -y wget
# 创建Hadoop文件夹
RUN cd /opt && mkdir hadoop && cd ./hadoop
# 下载Hadoop，并解压至特定文件夹
RUN wget -d http://archive.cloudera.com/cdh5/cdh/5/hadoop-2.6.0-cdh5.9.3.tar.gz -o hadoop.tar.gz
RUN tar -xvf hadoop.tar.gz && rm -rf hadoop.tar.gz && cd hadoop
# 配置Hadoop环境变量
ENV HADOOP_HOME /opt/hadoop/


