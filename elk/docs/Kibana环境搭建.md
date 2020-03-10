

# Kibana安装

​		Kibana是一个开源的分析和可视化平台，设计用于和Elasticsearch一起工作。你用Kibana来搜索，查看，并和存储在Elasticsearch索引中的数据进行交互。你可以轻松地执行高级数据分析，并且以各种图标、表格和地图的形式可视化数据。Kibana使得理解大量数据变得很容易。它简单的、基于浏览器的界面使你能够快速创建和共享动态仪表板，实时显示Elasticsearch查询的变化。在6.6版本后支持简体中文。

```
[root@elk zmf]# tar -zxvf kibana-6.3.1-linux-x86_64.tar.gz
[root@elk zmf]# mv kibana-6.3.1-linux-x86_64 kibana6.3
[root@elk zmf]# cd kibana6.3/config
[root@elk config]# nano kibana.yml
```
修改以下配置
```
server.port: 5601
server.host: "192.168.199.133"
elasticsearch.url: "http://192.168.199.133:9200"
kibana.index: ".kibana"
```
启动Kibana
```
[root@elk  kibana6.3]# ./bin/kibana
```

访问Kibana UI ，地址为 http://192.168.199.133:5601

Discover:数据搜索查看
Visualize:图标制作
Dashboard:仪表盘制作
Timeline:时序数据的高级可视化分析
DevTools:开发者工具
Management:kibana 相关配置