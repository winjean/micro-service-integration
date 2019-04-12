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
kafka-console-producer.sh --broker-list kafka1:9092,kafka2:9093,kafka3:9094 --topic topic_name  

kafka-console-consumer.sh --bootstrap-server kafka1:9092,kafka2:9093,kafka3:9094 --topic topic_name