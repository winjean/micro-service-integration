## docker 安装ES,kibana,logstash
** 拉取镜像
* docker pull elasticsearch
* docker pull kibana
* docker pull logstash
* docker pull prima/filebeat

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