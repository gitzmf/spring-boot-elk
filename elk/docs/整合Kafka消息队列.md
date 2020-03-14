# 使用Kafka作为消息队列减轻压力

安装Zookeeper。因为Kafka需要依赖JDK和Zookeeper，JDK的安装这里略过了。所以先进行Zookeeper的安装。到zookeeper.apache.org下载二进制安装包，解压后即可使用。

```
tar zxf zookeeper-3.4.14.tar.gz
mv zookeeper-3.4.14 /usr/local
#修改ZK的配置文件并启动服务。
cd /usr/local/zookeeper-3.4.14
cp zoo_sample.cfg zoo.cfg
vi zoo.cfg
clientPortAddress=0.0.0.0
clientPort=2181
#启动zk服务
/usr/local/zookeeper-3.4.14/bin/zkServer.sh start
```

安装Kafka。在kafka.apache.org/downloads下载二进制安装包，解压后即可使用

```
tar zxf kafka_2.11-2.1.1.tgz
mv kafka_2.11-2.1.1 /usr/local/
# 修改kafka配置文件
vi config/server.properties
zookeeper.connetc=192.168.1.100:2181  #zk服务地址与端口
listeners=PLAINTEST://192.168.1.100:9092  #kafka监听端口
# 后台启动kafka
nohup bin/kafka-server-start.sh config/server.properties &
```

将Filebeat的输出给到kafka中，修改OUTPUT部分

```
output:
  kafka:
    hosts:["192.168.1.100:9092"]
    topic: log  #写到kafka中的队列标签，可自定
```

配置Logstash从Redis中读取数据，修改input部分：

```
input {
 kafka {
        bootstrap_servers => "192.168.1.100:9092" 
        topic => ["log"]
        group_id => "log"
        codec => "plain"  #如果日志是json格式的这里就需要改为json
    }
}
```

查看kafka队列信息

```
# 查看Group，应该能看到log这个队列存在
/usr/local/kafka/bin/kafka-consumer-groups.sh --bootstrap-server 192.168.1.100:9092 --list  
# 查看具体的队列和消费信息
/usr/local/kafka/bin/kafka-consumer-groups.sh --bootstrap-server 192.168.1.100:9092 --group log --describe   
```

