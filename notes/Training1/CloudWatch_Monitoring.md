**AWS Monitoring**
* **AWS CloudWatch Metrics**
    * CloudWatch provides metric for every service in AWS
    * Metric is a variable to monitor (CPU Utilization, Networking, ...etc )
    * **Metrics** belong to _namespaces_
    * **Dimension** is an attribute of a metric (instance Id, environment, etc )
    * Up to 10 Dimensions per metric
    * Metric have timestamps
    * Can create CloudWatch dashboards of metrics
* **Dashboards**
    * Great way to setup dashboards for quick access to key metrics
    * Dashboards are global
    * Dashboards can include graphs from different regions
    * You can change the time zone & time range of the dashboards
    * You can set up automatic refresh (ex: 10s , 1m , 5m etc.)
    * **Pricing**
        * 3 dashboards (up to 50 metrics ) for free
       
* **AWS CloudWatch EC2 Detailed Monitoring**  
    * EC2 instance metrics have metrics "Every 5 mins"
    * With detailed monitoring (for a cost), you get data for "every 1 minute"
    * Use detailed monitoring if you want more prompt scale your ASG
    * The AWS Free Tier allows us to have 10 detailed monitoring metrics
    * **Note** : EC2 Memory usage is by default not pushed (must be pushed from inside the instance as a custom metric )
    
* **AWS CloudWatch Custom Metrics**
    * Possibility to define and send your own custom metrics to CloudWatch
    * Ability to use dimensions (attributes) to segment metrics
        * Instance.id
        * Environment name
    * Metric Resolution 
        * Standard : 1 minute
        * High Resolution : up to 1 second (StorageResolution API Parameter) - higher cost     
        * Use API call **PutMetricData**
        * Use exponential back off in case of throttle errors ( try after configurable time)
        
       
***AWS CloudWatch Log**
   * Applications can send logs to CloudWatch using the SDK
   * CloudWatch can collect log from 
        * Elastic Beanstalk: collection of logs from application
        * ECS: Collection from containers
        * AWS Lambda: Collection from function logs
        * VPC Flow logs: VPC specific logs
        * API Gateway
        * CloudTrail based on filter
        * CloudWatch log agents: for example on EC2 machines
        * Route53 : Log DNS queries
   * CloudWatch logs can send to:
        * Batch exporter to S3 for archival
        * Stream to ElasticSearch cluster for further analysis
        
   * Logs storage architecture
    * Log Groups : Arbitrary name, usually representing an application
    * Log Streams : instances within application / log files containers
   * Can define log expiration policies ( never expire, 30 days , etc)
   * Using the AWS CLI we can tail CloudWatch logs
   * To send logs to CloudWatch, make sure **IAM permissions** are correct.
   * **Security** : encryption of logs using KMS at the Group Level
   
***CloudWatch Logs Metric Filter & Insights**
   * For example, find a specific IP inside a log
   * Metric filters can be used to trigger alarms ( for ex: trying to catch shady activity )
   * CloudWatch Logs Insights ( new - Nov 2018) can be used to query logs and add queries to CloudWatch Dashboards -For ex:  Queries like - No of exceptions in last 5 mins
   * Watch Quick Demo to check to install cloud log agent to send logs to CloudWatch 
   
   
   
**CloudWatch Alarms**
    * Alarms are used to trigger notifications for any metric
    * Alarms can go to Auto Scaling, EC2 Actions, SNS Notifications
    * Various options (sampling, %, max, min, etc..)
    * Alarm States
        * OK
        * INSUFFICIENT_DATA
        * ALARM
    * Period
        * Length of time in seconds to evaluate the metric 
        * High resolution custom metric: can only 
        
        
        
        ***REVISIT****
   
**CloudWatch and CloudTrail - Exam questions** 
    * Cloud Watch - performance
    * Cloud Trail - Auditing 
   
   
   
   
   
   
       