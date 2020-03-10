# LogStash安装

​		Logstash是一个用ruby开发的开源工具，他可以对你的日志进行收集、分析，并将其存储供以后使用（如，搜索）。Logstash在其过程中担任搬运工的角色，它为数据存储，报表查询和日志解析创建了一个功能强大的管道链。Logstash提供了多种多样的input，filters，codecs和output组件，让使用者轻松实现强大的功能。

```
[root@elk zmf]# tar -zxvf logstash-6.3.1.tar.gz 
[root@elk zmf]# mv logstash-6.3.1 logstash6.3
```
修改ruby仓库地址为中国
```
[root@elk logstash6.3]# nano Gemfile
```
修改为`source "https://gems.ruby-china.com/"`
安装`logstash-codec-json_lines`插件

```
[root@elk logstash6.3]# ./bin/logstash-plugin install logstash-codec-json_lines
Validating logstash-codec-json_lines
Installing logstash-codec-json_lines
Installation successful
```

新建整合spring-boot-elk项目的配置文件

```
[root@elk logstash6.3]# nano config/log4j_to_es.conf 
input { 
  tcp {
    port => 4560  # logstash接收数据的端口
    # json_lines是一个json解析器，接收json的数据。这个要装 logstash-codec-json_lines 插件
    codec => json_lines 
  }
}
output {
  elasticsearch {
    hosts => "192.168.199.133:9200" # 指向我们安装的elasticsearch地址
    index => "springboot-logstash-%{+YYYY.MM.dd}" # #用一个项目名称来做索引,类似数据库库或表
  }
}
```

启动logstash

```
[root@elk logstash6.3]# ./bin/logstash -f config/log4j_to_es.conf 
```

