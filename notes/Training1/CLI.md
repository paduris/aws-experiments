* So far we have interacted with services manually and they exposed standard information for clients
    * EC2 -> exposes a standard linux machine 
    * RDS -> exposes a standard database url
    * Elastic Cache -> exposes a standard cache url 
    * ASG/ALB are automated and we don't have to program against them 
    * Route53 setup was manual
    
* **AWS CLI** - provides a way to interact with aws services without using online console

* Developing and performing AWS tasks against AWS can be done in several ways 
    * Using the AWS CLI on our local computer
    * Using the AWS CLI on our EC2 machines
    * Using the AWS SDK on our local computer
    * Using the AWS SDK on our EC2 machines
    * Using the AWS Instance Metadata Service for EC2
               
* Install AWS CLI - check AWS documentation             

* AWS CLI on EC2 
    * IAM roles can be attached to EC2 instances
    * IAM roles can come with a policy authorizing exactly what the EC2 instance should be able to do 
    
    
    * aws configure
    * us-west-2
    * use IAM policies to get access 
    * > aws s3 ls
    * > aws s3 mb s3://<bucket-name> (creates a bucket in s3)
    
    * https://docs.aws.amazon.com/cli/latest/reference/s3/index.html
    
    
    
* AWS EC2 Instance Metadata
  * AWS EC2 Instance Metadata is powerful but one of the least known features to developers 
  * It allows AWS Ec2 instances to "learn about themselves" without using an IAM Role for that purpose 
  * The url is http://169.254.169.254/latest/meta-data
  * You can retrieve the IAM Role name from the metadata, but you **cannot** retrieve the IAM policy 
  * Metadata = Info about the EC2 instance 
  * Userdata - launch script of the EC2 instance
  
  
  * To get EC2 metadata `curl http://169.254.169.254/latest/meta-data/ ` 
  


* AWS - SDK 
 * We have to use the AWS SDK when coding against AWS Services such as DynamoDB
 * The AWS CLI uses the python SDK (boto3)
 * If you don't specify or configure a default region, then us-east-1 will be chosen default 
 
 * AWS SDK Credentials Security 
    * It's recommended to use the default credential provider chain 
        * AWS credentials at ~/.aws/credentials (only on our computer or on premise)
        * Instance Profile Credentials using IAM Roles ( for EC2 machines, etc...)
        * Environment variables (AWS_ACCESS_KEY_ID, AWS_SECRET_ACCESS_KEY)
        
 * Exponential Back off
   * They apply to rate limited apis
   * Retry mechanism is included in SDK calls 
   