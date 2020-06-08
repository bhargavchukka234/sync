# sync
Redis download: https://redis.io/download

Cluster setup: go to “Creating and using a Redis Cluster” in https://redis.io/topics/cluster-tutorial

# Simple steps to create one node redis cluster
Install redis(apt-get or brew). Go to directory to create cluster files

mkdir -p redis/30001;
cd redis/30001;
echo $'port 30001\n cluster-enabled yes\ncluster-config-file nodes.conf\ncluster-node-timeout 5000\nappendonly yes\n' > redis.conf;
/usr/local/bin/redis-server redis.conf;


Java jdk version check: /usr/libexec/java_home -V

Download Kafka: http://www-us.apache.org/dist/kafka/2.4.0/kafka_2.13-2.4.0.tgz
Java version 1.8: https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html
Terminal version update : export JAVA_HOME=$(/usr/libexec/java_home -v 1.8)


go to Kafka directory: cd kafka_2.13-2.4.0

Zookeeper start: bin/zookeeper-server-start.sh config/zookeeper.properties

Kafka brokers start (server properties files in path “kafka/“): 

bin/kafka-server-start.sh config/server.properties

bin/kafka-server-start.sh config/server-1.properties

bin/kafka-server-start.sh config/server-2.properties


Create topic:  ./bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 3 --partitions 10 --topic demo

Topic description: ./bin/kafka-topics.sh --describe --topic demo --zookeeper localhost:2181

Console consumer: bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --group console --topic demo

Consumer group information: bin/kafka-consumer-groups.sh --bootstrap-server localhost:9092 --group consumerGroup1 --describe
