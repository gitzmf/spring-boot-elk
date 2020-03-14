# Filebeat环境搭建

​       由于Logstash依赖于JAVA环境，偏重量级，占用系统资源较高，而且每台客户端都要配置JAVA的话也略嫌麻烦。所以出现了beat，它不依赖JAVA环境，非常轻量。beat有很多种，比如偏向日志的 filebeat、偏向指标的metricbeat、偏向数据包的packetbeat等。

**对比下几种工作模式：**
1、Logstash -> Elasticsearch -> Kibana（适合分析，但偏重量级）
2、Filebeat -> Elasticsearch -> Kibana（只适合查看日志，不适合分析）
3、Filebeat -> Logstash（需要开启端口监听） -> Elasticsearch -> Kibana（适合分析）

```
[root@elk zmf]# wget https://artifacts.elastic.co/downloads/beats/filebeat/filebeat-6.3.0-linux-x86_64.tar.gz
[root@elk zmf]# tar zxf filebeat-6.3.0-linux-x86_64.tar.gz
[root@elk zmf]# mv filebeat-6.3.0-linux-x86_64 filebeat6.3
```

filebeat配置文件详解

```
filebeat.inputs:
- type: log
  enabled: true
  backoff: "1s"
  paths:
    - /var/log/message.log
  fields:  # 如果要采集多个日志需要增加该选项作为区分
    filetype: system  # 给每个日志打个标签，用于if判断
  fields_under_root: true
#- type: log
#  enabled: true
#  backoff: "1s"
#  paths:
#    - /var/log/nginx/access.log
#  fields:
#    filetype: nginx
#  fields_under_root: true
output:
  elasticsearch:
  hosts: ["192.168.199.133:9200"]
```

启动Filebeat，启动速度相比Logstash会快许多，内存占用也更少

```
[root@elk zmf]# ./filebeat6.3/filebeat -e -c ./filebeat6.3/filebeat.yml
```

**Filebeat+Logstash架构**
​       如果Filebeat直接把日志传给ES的话，由于其不支持正则、无法移除字段等，没办法实现数据分析，所以更优的配置是将Filebeat收集到的日志输出给一台Logstash服务器，由Logstash处理后再给ES。
修改Filebeat输出配置，输出给到Logstash，其他配置不动

```
output:
  logstash:
  hosts: ["192.168.199.133:5044"]
```

修改Logstash input相关配置，其他不动

```
input {
  beats {
    host => '192.168.99.133'  #如果配置了很多个beat，那这行可以不写，只保留port部分
    port => 5044
  }
}

filter {
    grok {
        match => {
            "message" => '(?<source_ip>\d{1,3}\.\d{1,3}\.\d{1,3}\.\d{1,3}) - [a-zA-Z0-9-]+ \[(?<nginx_time>[^ ]+) \+\d+\] "(?<method>[A-Z]+) (?<request_url>[^ ]+) HTTP/\d.\d" (?<status>\d+) \d+ "(?<referer>[^"]+)" "(?<agent>[^"]+)" ".*"'
        }   
        remove_field => ["message", "beat"]
    }   
     date {
        match => ["nginx_time", "dd/MMM/yyyy:HH:mm:ss"]
        target => "@timestamp"
    }
}

output {
  elasticsearch {
    hosts => ["http://127.0.0.1:9200"]
  }
}
```

