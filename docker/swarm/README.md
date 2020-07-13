#Docker Swarm常用命令
##查看集群节点
docker node ls
##创建nginx服务 
[comment]: <>(docker pull hub.test.com:5000/almi/nginx:0.1 #下载私有仓库镜像)
docker service create --name nginx  --replicas 2 --publish 80:80 hub.test.com:5000/almi/nginx:0.1
<!--
--network=swarm_test)
replicas 2 创建2个容器,hub.test.com为私有仓库
-->
##查看swarm集群中的服务
docker service ls
docker service ps nginx
##kill其中一个容器
docker kill 96e6bccc2209
##等会自动启动一个新的容器
[comment]: <>(修改服务实例数量为3)
docker service scale nginx=3
##删除nginx服务
docker service rm nginx
##删除swarm节点
docker swarm leave --force  #node
docker node rm -f <node>    #manager
##docker swarm 常用命令
docker swarm init               #初始化集群
docker swarm join-token worker  #查看工作节点的 token
docker swarm join-token manager #查看管理节点的 token
docker swarm join               #加入集群中
##docker node 常用命令
docker node ls      #查看所有集群节点
docker node rm      #删除某个节点（-f强制删除）
docker node inspect ##查看节点详情
docker node demote  #节点降级，由管理节点降级为工作节点
docker node promote #节点升级，由工作节点升级为管理节点
docker node update  #更新节点
docker node ps      #查看节点中的 Task 任务
##docker service 常用命令
docker service create   #部署服务
docker service inspect  #查看服务详情
docker service logs     #产看某个服务日志
docker service ls       #查看所有服务详情
docker service rm       #删除某个服务（-f强制删除）
docker service scale    #设置某个服务个数
docker service update   #更新某个服务