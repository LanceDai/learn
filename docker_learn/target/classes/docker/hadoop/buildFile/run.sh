#!/usr/bin/env bash

docker run --name my_hadoop hadoop_cluster:latest \
-v ../config/hadoop:/usr/local/hadoop/etc/hadoop/ \
-v ../config/ssh/:/root/.ssh/config/ \
-v ../config/script/:/root/