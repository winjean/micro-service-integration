* 执行 -e  
bin/logstash -e ''
* 文件 --config 或 -f  
bin/logstash -f agent.conf
* 测试 --configtest 或 -t  
用来测试 Logstash 读取到的配置文件语法是否能正常解析。
* 日志 --log 或 -l  
Logstash 默认输出日志到标准错误。  
生产环境下你可以通过 bin/logstash -l logs/logstash.log 命令来统一存储日志。