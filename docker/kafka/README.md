* query kafka version
find . -name \*kafka_\* | head -1 | grep -o '\kafka[^\n]*'

* create topic  
kafka-topics.sh --create --zookeeper zoo1:2181,zoo2:2181,zoo3:2181 --replication-factor 3 --partitions 2 --topic topic_name

* delete topic  
kafka-topics.sh --delete --zookeeper zoo1:2181,zoo2:2181,zoo3:2181 --topic topic_name

* list topic 
kafka-topics.sh --zookeeper zoo1:2181,zoo2:2181,zoo3:2181 --list  
 
* query topic info
kafka-topics.sh --zookeeper zoo1:2181,zoo2:2181,zoo3:2181 --topic topic_name --describe   

* send message  
kafka-console-producer.sh --broker-list 172.119.0.14:9092,172.119.0.15:9092,172.119.0.15:9092 --topic topic_name  

* consumer message
kafka-console-consumer.sh --bootstrap-server 172.119.0.14:9092,172.119.0.15:9092,172.119.0.15:9092 --topic topic_name

* producer message  
kafka-producer-perf-test.sh --topic topic-1 --throughput -1 --num-records 5000000 --record-size 100 --producer-props bootstrap.servers=172.119.0.15:9092 ack=-1  

* create consumer  
kafka-console-consumer.sh --bootstrap-server=172.119.0.15:9092 --topic topic-1 --from-beginning --consumer-property group.id=test-group1

* consumer info 
kafka-consumer-groups.sh --bootstrap-server 172.119.0.15:9092 --describe --group test-group1


````
kafka 加入JMX_PORT 后报 Port already in use
编辑 /opt/kafka/bin/kafka-run-class.sh 将
# JMX port to use 
if [ $JMX_PORT ]; then 
  KAFKA_JMX_OPTS="$KAFKA_JMX_OPTS -Dcom.sun.management.jmxremote.port=$JMX_PORT" 
fi 
To the following: 
 
修改为：
 
# JMX port to use 
if [ "$ISKAFKASERVER" = "true" ]; then 
  JMX_REMOTE_PORT=$JMX_PORT 
else 
  JMX_REMOTE_PORT=$CLIENT_JMX_PORT 
fi 
if [ $JMX_REMOTE_PORT ]; then 
  KAFKA_JMX_OPTS="$KAFKA_JMX_OPTS -Dcom.sun.management.jmxremote.port=$JMX_REMOTE_PORT" 
fi
````