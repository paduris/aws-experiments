

* Install Virtual Box - for running a virtual machine
* Download Ubuntu server from Ubuntu page - ubuntu iso image
* Install JRE and JDK
* Create Port Forwarding Rules
    

```
> sudo apt-get install open-8-jdk-headless -y
> sudo apt-get install open-8-jre-headless -y
> wget -qO - https://artifacts.elastic.co/GPG-KEY-elasticsearch | sudo apt-key add -
> sudo apt-get install apt-transport-https
> echo "deb https://artifacts.elastic.co/packages/6.x/apt stable main" | sudo tee -a /etc/apt/sources.list.d/elastic-6.x.list
> sudo apt-get update && sudo apt-get install elasticsearch
> sudo vi /etc/elasticsearch/elasticsearch.yml -> change the network property to 0.0.0.0 and uncomment it.
> sudo /bin/systemctl deamon-reload
> sudo /bin/systemctl start elasticsearch.service
```
**Test:**

`> curl 127.0.0.1:9200 `

**Get and load some data**
````
> wget http://media.sundog-soft.com/es6/shakes-mapping.json
> curl -H "Context-Type: application/json" -XPUT 127.0.0.1:9200/shakes --date-binary @shakes-mapping.json 
> wget http://media.sundog-soft.com/es6/shakespeare_6.0.json`
> curl -H "Context-Type: application/json" -XPOST 127.0.0.1:9200/shakespeare/doc/_bulk?pretty --date-binary @shakes-mapping.json
 ````
**Test by Querying some data**

`` > curl -H "Content-Type: application/json" -XGET '127.0.0.1:9200/shakespeare/_search?pretty' -d '{"query":{"match_phrase":{"text-entry" : "to be or not to be"}}}'``

 



 





