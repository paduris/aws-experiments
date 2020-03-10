**Relational Database Service**

**NO SQL**
    * Collection - Table
    * Document - Row
    * Key-Value pairs - Fields 

* RDS Read Replicas
    * Up to 5 Read Replicas
    * Replication is ASYNC - eventual consistency
    * Master writes
    * Each read replica will have its own DNS endpoint
    * You can have read replicas that have Multi-AZ
    * Read replicas can be promoted to be their own databases. This breaks the replication.
    * You can have a a read replica in a second region
    
**RDS Multi AZ** (Disaster Recovery)



**RDS Encryption** 
     
    
    
**RDS (OLTP)**
 * SQL
 * MySQL
 * PostgreSQL
 * Oracle
 * Aurora
 * MariaDB
**DynamoDB (No SQL)**
**Red Shift (OLAP)**

**Exam Tips**
    **Backups**
        * Automated Backups
        * Database Snapshots
        * RDS runs on virtual machines
        * You cannot log in to these operation systems however
        * Patching of the RDS Operating system and DB is Amazon's responsibility
          

    