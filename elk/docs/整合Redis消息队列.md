# 使用Redis作为消息队列减轻压力

​       当多台Filebeat向Logstash传输数据再交给ES入库时，压力会比较大，可以在Filebeat和Logstash之间加上一层消息队列减轻压力，比如Redis、Kafka（日志量大的时候推荐用Kafka，比较重量级，依赖JDK和Zookeeper）。整个架构就是：Filebeat > Redis\Kafka > Logstash > Elasticsearch > Kibana。

## ELK架构引入Redis配置步骤

首先将Filebeat的输出给到redis中，修改OUTPUT部分：

```
output:
  redis:
    hosts:["192.168.1.100"]
    port:6379
    password: 'redis_password'
    key: 'log'  #这个key就是将写入redis的key名，list类型
```

登录Redis后通过keys *命令可以看到有了一个key叫做log，这个就是配置文件里设置的key名，使用LLEN demo可以看到该key的长度，input如果输入了100行，那么这个key就应该有100行

配置Logstash从Redis中读取数据，修改input部分：

```
input {
    redis {
        host => "192.168.1.100"  
        port => "6379"  
        db => "0"  
        data_type => "list"
        key => "log" 
        password => 'redis_password'
    }
}
```

所有组件都运行成功后再去redis看看，队列会因为被output到了ES中而消失掉或者是迅速减少长度