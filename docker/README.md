* 查看网络列表  
 docker network ls
 
* 查看对应网络的配置  
 docker network inspect <container id>
 
* docker-compose command  
  docker-compose ps   
  docker-compose build -- 构建或者重新构建服务  
  docker-compose start service  
  docker-compose stop service  
  docker-compose run service env  
  docker-compose rm service -- 删除指定服务的容器   
  docker-compose kill service  
  docker-compose port service 8080  
  docker-compose logs
