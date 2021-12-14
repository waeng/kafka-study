# Start the ZooKeeper service
bin/zookeeper-server-start.sh config/zookeeper.properties
# Start the Kafka broker service
bin/kafka-server-start.sh config/server.properties
#创建一个主题来存储您的事件
bin/kafka-topics.sh --create --topic quickstart-events --bootstrap-server localhost:9092 --partitions 3 --replication-factor 1
#查看主题信息
bin/kafka-topics.sh --describe --topic quickstart-events --bootstrap-server localhost:9092
#写入事件
bin/kafka-console-producer.sh --topic quickstart-events --bootstrap-server localhost:9092
#阅读事件
bin/kafka-console-consumer.sh --topic quickstart-events --from-beginning --bootstrap-server localhost:9092