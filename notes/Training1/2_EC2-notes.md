**EC2 - Elastic Compute Cloud**  

* Popular AWS offering
* Renting virtual machines (EC2)
* Storing data on virtual drives (EBS)
* Distributing load across machines (ELB)
* Scaling the services using auto-scaling group (ASG)

**EC2 Launch Types**

* **On Demand instances**: 
    * Short workloads 
    * Predictable pricing 
    * Billing per second after first minute
    
* **Reserved Instances**: 
    * Long workloads ( >= 1 year)
    * 75% discount- Pay upfront for what you use with long term commitment 
    * Reserve period can be 1 or 3 years
    * Recommended for steady usage applications (think database)
    * Reserve a specific instance type
    
* **Convertible Reserved Instances**: 
    * Long workloads with flexible instances
    * Convert a EC2 instance type
    * upt 54% discount
    
* **Scheduled Reserved Instances**: 
    * Launch with in time window you reserve - for ex: every saturday night
    
* **Spot Instances**: 
    * Short workloads, for cheap, can lose instances ( for example : like price-line bidding)
    * 90% discount compared to on-demand
    * Spot instance are reclaimed with a 2 minute notification warning when the spot price goes above your bid
    * Used for batch jobs, Big Data analysis or workloads that are resilient to failures
    * Launch instances into a Spot block for 1 to 6 hours.
     
* **Dedicated Hosts**: 
    * No other customers will share your hardware - complete control - 3 year period reservation
    * Full control of EC2 instance placement
    * Visibility into the underlying sockets/physical cores of the hardware
    * More expensive
    * Useful for software that have complicated licensing mode (BYOL- Bring Your Own License) Or for companies that have strong regulatory or compliance needs
    
* **Dedicated Instances**:
    * Instances running on hardware that's dedicated to you
    * May share hardware with other instances in same account
    * No control over instance placement( can move hardware after stop/start)
    
* **Dedicated Hosts**: 
    * Book an entire physical server, control instance placement

**EC2 Instance Type - Main & Important**

* R: applications that needs a lot of RAM - in-memory caches
* C: applications that needs good CPU - compute/database
* M: applications that are balanced(think "medium" ) - general / webapp etc.
* I: applications that need a good local I/O (instance storage)- databases
* G: applications that need a GPU - video rendering/machine learning

* T2/T3: burst instances (up to a capacity) - credit for good CPU usage 
* T2/T3: unlimited: unlimited burst


**For more information on EC2 Instance Types**
https://aws.amazon.com/ec2/instance-types/

**AMI - Amazon Machine Image**

* An image to use to create our instances
* We can create custom AMI 
    * Pre-installed packages 
    * AMI are built for specific region not globally
    * AMI can be found and published on the Amazon Market Place
    * They live on S3
    * Overall it is a quite inexpensive to store AMIs on S3
    * Cross Account copying AMI 
        * You can share an AMI with another Amazon account
        * Sharing the AMI doesn't affect the ownership of AMI
    
**Placement Groups**
    * Sometime you want to control over the EC2 placement strategy , that strategy is defined using placement groups
    **Cluster** ( same AZ , same hardware)
     * **Use case**: 
        * BigData job that need to be complete fast
        * Application that needs extremely low latency and high network throughput 
     * **Pros**: Great Network , low latency in a single AZ
     * **Cons**: if the entire Rack fails, we will be in trouble
    **Spread**
      * Can span across AZ, max 7 instances per AZ
      * Reduces the risk of failures , for critical applications
      * EC2 instances on different physical hardware
    **Partition**
        * Spreads instances across many different partitions ( which rely on different set of racks) with in a AZ. Scales to 100 EC2 instances per group (Hadoop , Cassandra , Kafka)
        * Upto 7 partitions per AZ

**EC2 Hands on**

**Key Pair**

A key pair consists of a public key that AWS stores and a private key file that you store. 
Together they allow you to connect to your instance securely.

We use SSH (port 22) - it allows you to control a remote machine using the command line.

**Commands**
```
For ex:
> ssh ec2-user@<public_ip> -i <downloaded-keyfile.pem>

> chmod 0400 <downloaded-keyfile.pem>
> ssh ec2-user@35.180.100.144 -i keyvalue-pair.pem
```

**Security Groups**
* Security Groups are the fundamental of network security in AWS
* They control how traffic is allowed into or out of our EC2 machines
* They regulate 
    * Access to ports
    * Authorized IP ranges
    * Control of inbound network
    * Control of outbound network 
    * Can be attached to multiple instances
    * Locked down to a region / VPC combination
    * If your application not accessible (time out) , then it could be because of security group inbound configuration.
    * All inbound traffic is blocked by default 
    * All outbound traffic is authorized by default

**Private IP vs Public IP**
* We use IPV4 
* IPV6 is newer and solves problems for the IOT( Internet of Things )
* Private IP - Company Network - private network
* Private IP means the machine can only be identified on a private network
* Public IP - machines identified uniquely on the whole internet

**Elastic IP**
* When you stop and then start an EC2 instance, it can change its public IP.
* If you need to have a fixed IP address, you need an Elastic IP
* Use dedicated IP for EC2 machine
* Use Elastic IPs configuration management
* Try not to use Elastic IP
* Instead, use random IP and register a DNS name to it (Route 53) or use load balancer

**Install & Launch Apache Web Server**

```
>sudo su
>yum update -y [this command forces the machines to update]
>yum install -y httpd.x86_64
>systemctl start httpd.service [systemctl works for only Amazon Linux 2]
>systemctl enable httpd.service [start the service]
>curl localhost:80 [You should see a index page html - with in machine you can access]

```

In the browser(chrome or IE)

`
http://<public-ip>:80 [Make sure security group is configured to allow http requests]
`
* Replace the content in index.html
 
* Refresh the above to show the updated message

```
> echo "Hello, World" > /var/www/html/index.html
> echo "Hello, World from $(hostname -f)" > /var/www/html/index.html

```


**EC2 User Data**

* Bootstrap our instances using an EC2 user data script - means launching commands when a machine starts - only run once at the instance of first start
* EC2 user date is used to automate boot tasks
   * Installing updates
   * Installing software
   * Downloading common files from the internet
  
* We can install Apache using EC2 User Data script    

Under Configure Instance Details
User Data Text Area:

````
    #!/bin/bash
    # install httpd (Linux2 version)  
    yum update -y 
    yum install -y httpd.x86_64
    systemctl start httpd.service
    systemctl enable httpd.service
    echo "Hello, World from $(hostname -f)" > /var/www/html/index.html
````



 
 

