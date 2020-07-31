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
 docker save 6961102ed619 > image.tar 导出镜像   
 docker load -i web.tar   导入镜像  
 docker load < web.tar   
 docker tag e98c36988666 docker.lsmsp.cn:12125/image:1.0-SNAPSHOT 为docker打tag
 
 
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


* docker swarm command  
  docker swarm init --advertise-addr 192.168.31.43   创建集群  
  docker node ls  查看集群节点  
  docker node update --availability drain node139  节点下线  
  docker node update --availability active node139  节点重新上线  
  docker swarm join-token -q manager  查看manager token  
  docker swarm join-token -q worker  查看worker token  
  docker swarm join --token SWMTKN-1-2lefzq18zohy9yr1vskutf1sfb2a590xz9d0mjj2m15zu9eprw-2938j5f50t35ycut0vbj2sx0s 192.168.31.43:2377  加入集群  
  docker swarm leave --force   节点离开集群
  docker info  查看集群信息  
  
  docker service ps mysql 查看集群服务状态列表   
  docker service scale nginx=4  动态调整副本数  
  
  docker stack deploy -c docker-compose.yml  isnp --resolve-image=never --with-registry-auth  部署  
  


* docker 加速
编辑 /etc/docker/daemon.json  
{
  "registry-mirrors": ["https://l10nt4hq.mirror.aliyuncs.com"]
}  
sudo systemctl daemon-reload  
sudo systemctl restart docker  