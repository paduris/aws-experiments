**Databases**
* We have a lot of managed databases on AWS to choose from 
* Choosing the database is tough as we have lots of choices that AWS offers, it all depends on your need and architecture
* **Needs**
    * Read heavy, Write heavy or Balanced , Throughput needs?  Will it change, does it need to scale or fluctuate during the day
    * How much data to store and for how long? Will it grow? Average object size? How are they accessed?
    * Data durability? Source of truth for the data?
    * Latency requirements? Concurrent users?
    * Data Model? How will you query the data? Joins? Structured? Semi Structured?
    * String schema? More flexibility ? Reporting? Search? RDBMS/NoSQL?
    * Licence cost? Switch to Cloud Native DB such as Aurora?
    
* **Types**
    * **RDBMS** = RDS, Aurora - great for joins, ElastiCache (Key/Value pairs)
    * **NoSQL** = DynamoDB, Neptune (graphs)
    * **Object Store** = S3 (for big objects) / Glacier (for backups / archives)
    * **Data Warehouse** = (SQL Analytics/BI): Redshift (OLAP), Athena
    * **Search**: ElasticSearch (JSON)- free text, unstructured searches
    * **Graphs**: Neptune - displays relationships between data.
    
* **RDS**
    * Managed PostgreSQL / MYSQL / Oracle / SQL Server
    * Must provision an EC2 instance & EBS Volume type and size
    * Support for Read Replicas and Multi AZ
    * Security through IAM, Security Groups, KMS, SSL in transit
    * Backup/ Snapshot / Point in time restore feature
    * Managed and Scheduled maintenance
    * Monitoring through CloudWatch
    * **Use case**: Store relational data sets (RDBMS/ OLTP). perform SQL queries transactional inserts/  updates/ delete is available
    * From Solution Architecture point of view: 
      * **Operations** : Small downtime when failover happens, when maintenance happens, scaling in read replicas / ec2 instance/ restore EBS implies manual intervention, application changes
      * **Security** : AWS responsible for OS Security, we are responsible for setting up KMS, security groups, IAM policies, authorizing users in DB, using SSL
      * **Reliability**: Multi AZ feature, failover in case of failures
      * **Performance**: Depends on EC2 instance type, EBS volume type, ability to add Read Replicas, doesnt' auto-scale
      * **Cost** : Pay per hour based on provisioned EC2 and EBS volume
* **Aurora**
    * Compatible API for PostgreSQL / MySQL
    * Data is held in 6 replicas across 3 AZ
    * Auto healing capability
    * Multi AZ,Auto Scaling Read Replicas
    * Read Replicas can be Global
    * Aurora database can be Global for DR or latency purposes
    * Auto scaling of storage from 10GB to 64TB
    * Define EC2 instance type for aurora instances
    * Same security/ monitoring/ maintenance features as RDS
    * "Aurora Serverless" option
    * **Use case:** Same as RDS , but with less maintenance/ more flexibility / more performance
    * From Solution Architecture point of view:
      * **Operations**: less operation, auto scaling storage
      * **Security** : AWS responsible for OS Security, we are responsible for setting up KMS, security groups, IAM policies, authorizing users in DB, using SSL
      * **Reliability**: Multi AZ feature, highly available, possibly more than RDS, Aurora Serverless Option
      * **Performance**:  5x performance (according to AWS) due to architectural optimizations. Up to 15 Read Replicas (only 5 for RDS)
      * **Cost** : Pay per hour based on EC2 and storage usage. Possibly lower costs compared to Enterprise grade databases such as Oracle.
      
* **ElastiCache**
    * Managed Redis/Memcached (similar offering as RDS, but for caches)
    * In-memory data store,sub-millisecond latency 
    * Must provision an EC2 instance type
    * Support for Clustering (Redis) and Multi AZ, Read Replicas (sharding)
    * Security through IAM, Security Groups, KMS, Redis Auth
    * Backup/ Snapshot/ point in time restore feature
    * Managed and Scheduled maintenance
    * Monitoring through CloudWatch
    * **Use case** : Key/Value store, Frequent reads, less writes, cache results for DB queries, store session data for websites, cannot use SQL
    * From Solution Architecture point of view:
      * **Operations**: Small downtime when failover happens, when maintenance happens, scaling in read replicas / ec2 instance/ restore EBS implies manual intervention, application changes
      * **Security** : AWS responsible for OS Security, we are responsible for setting up KMS, security groups, IAM policies, authorizing users (Redis Auth), using SSL
      * **Reliability**: Multi AZ feature, Clustering
      * **Performance**:  Sub-millisecond performance, in memory, read replicas for sharding, very popular cache option
      * **Cost** : Pay per hour based on EC2 and storage usage.
      
* **DynamoDB**
    * AWS propriety technology, managed No SQL database
    * Serverless, provisioned capacity, auto scaling, on demand capacity (Nov 2018)
    * Can replace ElastiCache as a key/value store (storing session data for example)
    * Highly Available, Multiple AZ by default, Read and Writes are decoupled, DAX for read cache
    * Reads can be eventually consistent or strongly consistent
    * Security, authentication and authorization is done through IAM
    * DynamoDB Streams to integrate with AWS Lambda
    * Back up/ Restore feature, Global Table feature
    * Monitoring through CloudWatch
    * Can only query on primary key, sort key or indexes
    * **Use case** : Serverless applications development (small documents 100s KB), distributed serverless cache, doesn't have the SQL query language available, has transactions capability from Nov 2018
    * From Solution Architecture point of view:
      * **Operations**: No operations needed, auto scaling capability, serverless
      * **Security** : Full Security through IAM policies, KMS encryption, SSL in flight 
      * **Reliability**: Multi AZ feature, Backups
      * **Performance**:  Single digit milli second performance, DAX for caching reads, performance doesn't degrade if your application scales
      * **Cost** : Pay per provisioned capacity and storage usage ( no need to guess in advance any capacity- can use auto scaling) 

***S3**
    * S3 is a key/value store for objects
    * Great for big objects, not so great for small objects
    * Serverless, scales infinitely, max object size is 5TB
    * Eventually consistency for overwrites and deletes
    * Tiers: S3 Standard, S3 IA, S3 One Zone IA, Glacier for backups
    * Features: Versioning, Encryption, Cross Region Replication, etc...
    * Security: IAM, Bucket Policies, ACL
    * Encryption : SSE-S3, SSE-KMS, SSE-C, client side encryption, SSL in transit
    * **Use case** : Static files, key value store for big files, website hosting
    * From Solution Architecture point of view:
          * **Operations**: No operations needed
          * **Security** : Full Security through IAM policies, Bucket policies , ACL, Encryption (Server/Client),SSL
          * **Reliability**: 99.99999999% durability/99.99% availability, Multi AZ, CRR 
          * **Performance**:  Scales to thousands of read/writes per second, transfer acceleration / multi part for big files
          * **Cost** : Pay per storage usage, network cost, request number
          
**Athena**
    * Fully Serverless database with SQL capabilities 
    * Used to query data in S3 
    * Pay per query
    * Output results back to S3
    * Secured through IAM
    ***Use Case** : One time SQL queries, serverless queries on S3, log analytics
    * From Solution Architecture point of view:
              * **Operations**: No operations needed
              * **Security** : IAM + S3 security 
              * **Reliability**: Managed service, uses Presto engine, highly available 
              * **Performance**: Queries scale based on data size
              * **Cost** : Pay per query/ per TB of data scanned, serverless 
***Redshift** : Analytics/BI/Data Warehouse
    * Redshift is based on PostgreSQL, but its not used for OLTP
    * It's OLAP - online analytical processing (analytical and data warehousing)      
    * 10x better performance than other data warehouses,scale to Peta Bytes of data
    * Columnar storage of data (instead of row based)
    * Massively parallel query Execution (MPP), highly available 
    * Pay as you go based on the instances provisioned  
    * Has a SQL interface for performing the queries
    * BI tools such as AWS Quick sight or Tableau integrate with it      
    * Data is loaded from S3, DynamoDB,DMS, other DBs....
    * From 1 node to 128 nodes, up tp 160 GB of space per node
    * Currently only **available in 1AZ**
    * Can restore snapshots to new AZs in the event of an outage
    * Leader node: for query planning, results aggregation
    * Compute node: for performing the queries, send results to leader - charges only for compute nodes not for leader node  
    * **Redshift spectrum**: Performs queries directly against S3 ( no need to load)
    * Back up & Restore, Security VPC/IAM,KMS, Monitoring
    * Backups enabled by default within 1 day retention period
    * Redshift always attempts to maintain at least three copies of your data(the original and replica on the compute nodes and a backup in S3)
    * Redshift can also asynchronously replicate your snapshots to S3 in another region for disaster recovery  
    * Maximum retention period is 35 days
    * Redshift Enhanced VPC Routing: COPY/ UNLOAD goes through VPC
    * From Solution Architecture point of view:
      * **Operations**: Small downtime when failover happens, when maintenance happens, scaling in read replicas / ec2 instance/ restore EBS implies manual intervention, application changes
      * **Security** : IAM , VPC, KMS, SSL (similar ro RDS)
      * **Reliability**: Highly available, auto healing features
      * **Performance**: 10X performance vs other data warehousing, compression
      * **Cost** : Pay per node provisioned. 1/10th of the cost vs other warehouses
   
***Neptune** - Graph Database
    * Fully managed graph database
    * When do we use Graphs
        * High relationship data
        * Social Networking: Users friends with Users, replied to comment on post of user and likes other comments
        * Knowledge graphs (Wikipedia for example)
        * Highly available across 3 AZ, with up to 15 read replicas
        * Point in time recovery, continuous back up to Amazon S3
        * Support for KMS encryption at rest + HTTPS
        * From Solution Architecture point of view:
              * **Operations**: Similar to RDS
              * **Security** : IAM , VPC, KMS, SSL (similar ro RDS) + IAM Authentication
              * **Reliability**: Multi AZ, clustering
              * **Performance**: best suited for graphs, clustering to improve performance
              * **Cost** : Pay per node provisioned. (Similar ro RDS)
              
***Elastic Search** (Searching & Indexing)
    * Example : In DynamoDB, you can only find by primary key or indexes
    * With Elastic Search, you can search any field, even partially matches
    * It's common to use Elastic Search as a complement to another database
    * Elastic search also has some usage for Big Data applications
    * You can provision a cluster of instances
    * Built - in integrations: Amazon Kinesis Data Firehose, AWS Iot and Amazon cloud watch logs for data ingestion
    * Security through Cognito & IAM, KMS encryption, SSL & VPC
    * Comes with Kibana (visualization) & Logstash (log ingestion) - ELK stack
    * From Solution Architecture point of view:
                  * **Operations**: Similar to RDS
                  * **Security** : Cognito, IAM , VPC, KMS, SSL 
                  * **Reliability**: Multi AZ, clustering
                  * **Performance**: based on Elastic Search project (open source), petabyte scale 
                  * **Cost**: Pay per node provisioned(similar to RDS)
    
    
  
    