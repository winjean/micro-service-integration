## docker 安装 zipkin
* docker pull openzipkin/zipkin
* docker pull docker.elastic.co/elasticsearch/elasticsearch:6.3.0

## 文件结构
```
dockerfile
    |- elasticsearch
    |    |- data
    |- docker-compose.yml
```    

命令模式进入dockerfile目录，执行启动命令
docker-compose up -d