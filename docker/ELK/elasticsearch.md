* 集群是否健康  
curl -X GET http://192.168.71.128:9200/_cat/health?v
* 查看集群节点数
curl -X GET http://192.168.71.128:9200/_cat/nodes?v  
* 查看集群全部索引
curl -X GET http://192.168.71.128:9200/_cat/indices?v  
* 创建索引
curl -X PUT http://192.168.71.128:9200/customer?pretty