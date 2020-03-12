# LogStash详解

输入、过滤器和输出  
Logstash 能够动态地采集、转换和传输数据，不受格式或复杂度的影响。利用 Grok 从非结构化数据中派生出结构，从 IP 地址解码出地理坐标，匿名化或排除敏感字段，并简化整体处理过程。

官方文档： https://www.elastic.co/guide/en/logstash/6.3/index.html

![img](https://main.qcloudimg.com/raw/7367005a46890c48d27e8518a14a1772.png)

#### 输入（采集各种样式、大小和来源的数据）
数据往往以各种各样的形式，或分散或集中地存在于很多系统中。 Logstash 支持 [各种输入选择](https://www.elastic.co/guide/en/logstash/current/input-plugins.html) ，可以在同一时间从众多常用来源捕捉事件。能够以连续的流式传输方式，轻松地从您的日志、指标、Web 应用、数据存储以及各种 AWS 服务采集数据。
### 过滤器（实时解析和转换数据）
数据从源传输到存储库的过程中，Logstash 过滤器能够解析各个事件，识别已命名的字段以构建结构，并将它们转换成通用格式，以便更轻松、更快速地分析和实现商业价值。
- 利用 Grok 从非结构化数据中派生出结构
- 从 IP 地址破译出地理坐标
- 将 PII 数据匿名化，完全排除敏感字段
- 简化整体处理，不受数据源、格式或架构的影响
我们的[过滤器库](https://www.elastic.co/guide/en/logstash/current/filter-plugins.html)丰富多样，拥有无限可能。
### 输出（选择您的存储库，导出您的数据）
​      尽管 Elasticsearch 是我们的首选输出方向，能够为我们的搜索和分析带来无限可能，但它并非唯一选择。
Logstash 提供[众多输出选择](https://www.elastic.co/guide/en/logstash/current/output-plugins.html)，您可以将数据发送到您要指定的地方，并且能够灵活地解锁众多下游用例。

### Codecs（编码解码器）

​		Codecs不是一个单独的流程，而是编码解码器，而是在输入和输出等插件中用于数据转换的模块，用于对数据进行编码处理，常见的插件如json，[multiline](https://www.elastic.co/guide/en/logstash/5.6/codec-plugins.html)。

### 插件
​      Logstash 采用可插拔框架，拥有 200 多个插件。您可以将不同的输入选择、过滤器和输出选择混合搭配、精心安排，让它们在管道中和谐地运行。