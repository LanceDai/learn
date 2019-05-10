#!/usr/bin/env bash
# 防止没取到,加一个x
if [[ "$1"x != "bash"x ]]; then
    echo "hdfs namenode -format"
    echo "sh -c '$1'"
fi

