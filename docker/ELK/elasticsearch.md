* 集群是否健康  
curl -X GET http://192.168.71.128:9200/_cat/health?v

* 查看集群节点数
curl -X GET http://192.168.71.128:9200/_cat/nodes?v  

* 查看集群全部索引
curl -X GET http://192.168.71.128:9200/_cat/indices?v  

* 创建索引
curl -X PUT http://192.168.71.128:9200/customer?pretty

* 删除索引
curl -X DELETE http://localhost:9200/debug-role-2019.04.11

* 创建文档
curl -X PUT "localhost:9200/index/doc/1" -H 'Content-Type: application/json' -d'{"name": "winjean"}'

* 查询文档
curl -X GET "localhost:9200/index/doc/1"