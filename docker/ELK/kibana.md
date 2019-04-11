## 检查Kibana状态
* http://localhost:5601/status
* http://192.168.101.5:5601/api/status(json 格式)


```
GET _search
{
  "query": {
    "match_all": {}
  }
}
```