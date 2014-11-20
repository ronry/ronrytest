#性能记录
##web性能
#### 简单用例
###### 场景描述
###### 压测数据
###### 用五毛插件
| sleep时间  | 100并发 | 300并发    | 500并发  |1000并发|
|-------|:---:|-----------|-------:|-------|
| 1s  | ? | Requests per second:241.18 [#/sec] (mean)<br> Time per request: 1243.908 [ms] (mean) | $3,000 |Requests per second:243.44 [#/sec] (mean)<br> Time per request:4107.770 [ms] (mean)
| 500ms |?  | bird      |Requests per second:486.41 [#/sec] (mean) <br/>Time per request:1233.522 [ms] (mean)|?
| jedi  | ?   | undefined | $0     |?
####### sleep 1s:
300个并发：


1000个并发


用官方tomcat
300个并发：
Requests per second:    191.56 [#/sec] (mean) Time per request:       1566.124 [ms] (mean)
