* docker command  
 docker search image
 docker pull image
 docker network ls 查看网络列表  
 docker network inspect <container_id>  查看对应网络的配置
 docker run -i -t --name container_name -v 宿主机目录:容器目录 centos /bin/bash  
 docker exec -i -t container_id /bin/bash  
 docker ps      默认显示运行的容器  
 docker ps –a   显示所有容器
 docker rm container_id 删除容器
 docker rmi image_id  删除镜像
 docker tag image:tag image:tag 使用tag命令重新打包镜像
 
 
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


* docker 加速
编辑 /etc/docker/daemon.json  
{
  "registry-mirrors": ["https://l10nt4hq.mirror.aliyuncs.com"]
}  
sudo systemctl daemon-reload  
sudo systemctl restart docker  