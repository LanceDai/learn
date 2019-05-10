#!/bin/bash

set -e

function addProperty() {
  local path=$1
  local name=$2
  local value=$3

  local entry="\t<property>\n\t\t<name>$name</name>\n\t\t<value>${value}</value>\n\t</property>"
  local escapedEntry=$(echo $entry | sed 's/\//\\\//g')
  if [[ -f "$path.template" ]];then
    mv "$path.template" "$path"
  fi
  sed -i "/<\/configuration>/ s/.*/${escapedEntry}\n&/" $path
}

echo "start hadoop-setting"
ln -s /opt/hadoop-${HADOOP_VERSION}/etc/hadoop ${HADOOP_CONFIG_DIR}
if [[ -h ${HADOOP_CONFIG_DIR} ]]; then
    echo "link hadoop-config-dir ok"
fi
mkdir -p ${HDFS_NAMENODE_DIR} ${HDFS_DATANODE_DIR}

if [[ -d ${HDFS_NAMENODE_DIR} ]]; then
    echo "create hdfs-namenode-dir ok"
fi

if [[ -d ${HDFS_DATANODE_DIR} ]]; then
    echo "create hdfs-datanode-dir ok"
fi
configFilePrefix="${HADOOP_CONFIG_DIR}"
#configFilePrefix="."

for line in `printenv | perl -sne 'print "$1\n" if m/^(.+?____.+?=.*)/'` ; do
    #upper->lower _->-(for value should not be valid) __->. ___->- ____->separetion(@)
    line=`echo ${line} | perl -pe 's/____/@/g; s/___/-/g;s/__/./g;'`
    configFileName=`echo ${line} | sed 's!\(.*\)@.*!\1!g' | sed 's/_/-/g'| tr '[A-Z]' '[a-z]'`
    propertyName=`echo ${line} | sed 's!.*@\(.*\)=.*!\1!g' | sed 's/_/-/g'`
    propertyValue=`echo ${line} | sed 's!.*=\(.*\)!\1!g'`
    configFileName="${configFilePrefix}/${configFileName}.xml"
    addProperty ${configFileName} ${propertyName} ${propertyValue}
    echo "add property:"
    echo "---configFileName=${configFileName}"
    echo "---propertyName=${propertyName}"
    echo "---propertyValue=${propertyValue}"
done

# 清空文件
echo "" > "${configFilePrefix}/slaves"
for (( i = 1; i <= ${SLAVE_NUM}; ++i )); do
    echo "hadoop-slave${i}" >> "${configFilePrefix}/slaves"
    echo "add slave --> hadoop-slave${i}"
done

# 启动ssh
service ssh start

# 防止没取到,加一个x
echo "args[1] = $1"
if [[ "$1"x == "start-all.sh"x ]]; then
    hdfs namenode -format
    sh -c "$1"
fi
echo "hadoop start ok"
sh -c "bash"