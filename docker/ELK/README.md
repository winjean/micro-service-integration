## docker 安装ES,kibana,logstash
** 拉取镜像
* docker pull docker.elastic.co/elasticsearch/elasticsearch:6.3.0
* docker pull docker.elastic.co/kibana/kibana:6.3.0
* docker pull docker.elastic.co/logstash/logstash:6.3.0

** 使用tag命令重新打包镜像
* docker tag docker.elastic.co/elasticsearch/elasticsearch:6.3.0 elasticsearch:latest
* docker tag docker.elastic.co/kibana/kibana:6.3.0 kibana:latest
* docker tag docker.elastic.co/logstash/logstash:6.3.0 logstash:latest

## 文件结构
```
dockerfile
    |- elasticsearch
    |    |- data
    |- logstash
    |    |- config-dir
    |    |    |- logstash.conf
    |- docker-compose.yml
```

## 检查是否安装成功  
* elasticsearch http://192.168.71.128:9200/?pretty  
* kibana http://192.168.71.128:5601  
* logstash  