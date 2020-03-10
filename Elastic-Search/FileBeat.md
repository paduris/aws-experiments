sudo apt-get update && sudo apt-get install filebeat
cd /usr/share/elasticsearch/
sudo bin/elasticsearch-plugin install ingest-geoip
sudo bin/elasticsearch-plugin install ingest-user-agent
sudo /bin/systemctl stop elasticsearch.service
sudo /bin/systemctl start elasticsearch.service
cd /usr/share/filebeat/bin
sudo filebeat setup --dashboards
sudo mv apache2.yml.disabled apache2.yml
sudo vi /etc/filebeat/modules.d/apache2.yml
Change access and error log paths to
["/home/<username>/logs/access*"]
["/home/<username>/logs/error*"]
Make /home/<username>/logs - > mkdir logs
cd into it
wget http://media.sundog-soft.com/es/access_log
sudo /bin/systemctl start filebeat.service

chmod go-w /etc/filebeat/modules.d/apache2.yml