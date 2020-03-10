Time - Questions are lengthy - about 2 minutes for each question

**Core Services for Exam:**
* Security, Identity & Compliance ( IAM )
* Network & Content Delivery ( Route53, VPC, CDN)
* Compute (EC2 & Lambda)
* Storage (S3)
* Databases ( DynamoDB & Red shift)
* AWS Global Infrastructure




**S3**
    **Exam Tips:**
         * Stores all versions of an object
         * Great backup tool
         * Once enabled versioning can't be disabled but only suspended.
         * Integrate with life cycle rules
         * Versioning has MFA - Multi factor authentication.
         * Life cycle Rule - Automates moving of objects between storage classes
         
         
1. Difference between S3-Pre-Signed URLs, OAI and CloudFront Signed URL/Cookies - (3 questions)
2. Need to move legacy Proprietary file system AWS, what is the best solution - EBS, EFS, S3?
3. S3 - different storage classes - glacier use case.
4. Need to know EBS Volumes short names e.g. gp2, st1
5. Cheapest EC2 Reserved Instances - Convertible, Scheduled, Standard
6. VPC - 
	a. how to block a IP Address - NACL vs. Security Group
	b. Webserver and Database serve Security group configuration
	
7. Redis Auth Command - use case.
8. AWS Config - how can you create inventory of resources and track over time what has changed.
9. Redshift Cross Region Snapshot - use case.
10. ECS & SQS.
11. How to architect fault-tolerant architecture? E.g. you need to have minimum 6 instances in 3 AZs, create a MOST cos-efficient configuration.
12. DynamoDB - use case question like you need a storage solution for unstructured data which is stored in key-value pair.
13. AWS Data pipeline - scenario was you need to move data constantly between On-prem data center to AWS, select 2 answers
14. Athena - need to run query on S3 but don't want to manage the infrastructure.