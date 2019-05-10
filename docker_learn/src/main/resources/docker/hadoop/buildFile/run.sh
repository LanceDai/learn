#!/usr/bin/env bash

par_dir=$(dirname "$PWD")
docker stop my_hadoop
docker rm my_hadoop

docker run --name my_hadoop \
-v ${par_dir}/config/hadoop/:/etc/hadoop/ \
-d hadoop_base:latest