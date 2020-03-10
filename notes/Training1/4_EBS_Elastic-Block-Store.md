**What is an EBS Volume?**

* An EC2 machine looses its root volume (main drive) when it manually terminated by default.
* Unexpected terminations might happen from time to time 
* Sometimes, you need a way to store your instance data somewhere 
* An EBS volume is a **network drive** you can attach to your instances while they run, for example : database data
* It is a network drive not physical drive
* It uses network to communicate the instance, which means there might be a bit of latency
* It can be detached from a EC2 instance and attached to another one quickly as long as it is in same AZ
* It is **locked to an AZ** - An EBS volume in us-east-1a cannot be attached to us-east-1b , to move across we need to snapshot it.
* Have a provisioned capacity ( size in GBs and IOPS - input/output operations per second)
* EBS Volumes are only **scoped to AZ**

* How to attach a EBS Volume and format it, it is available on AWS documentation

 > lsblk
 > sudo file -s /dev/xvdb
 > sudo mkfs -t ext4 /dev/xvdb
 > sudo mkdir /data
 > sudo mount /dev/xdvb /data
 > lsblk
 > sudo ls
 > sudo nano Hello.txt   -> type some text in the file and save and exit
 > lsblk
 > sudo cp /etc/fstab /etc/fstab.orig
 > sudo nano /etc/fstab
 * Add the below line
 > /dev/xvdb /data ext4 defaults,nofail 0 2 
 > cat /etc/fstab 
 
 > sudo file -s /dev/xvdb
 > sudo umount /data
 > lsblk
 > sudo mount -a
 > lsblk
 
 
 

**EBS Volume Type**

* **GP2 (SSD)** : General Purpose SSD volume that balances price and performance for a wide variety of workloads
    * Recommended for most workloads
    * System boot volumes
    * Virtual desktops
    * Low latency interactive apps
    * Development and test environments
    * 1 GB - 16TB
    * Small GP2 volumes can burst IOPS to 3000
    * As of today - the max IOPS is 16000
    
    
* **IO1(SSD) Provisioned IOPS SSD**   : Highest performance SSD volume for mission critical low latency or high throughput workloads
    * Large database workloads - MongoDB, Cassandra, MySQL, Oracle etc.
    * 4GB - 16TB
    * IOPS is provisioned - Min 100 - Max 64000 (Nitro instances) else MAX 32000 other instances 
    
    
* **ST1 (HDD) Throughput optimized** : Low cost HDD volume designed for frequently accessed, throughput intensive workloads
    * Big data, Data warehouses, Log processing
    * Apache Kafka
    * Cannot be a boot volume
    * 500 GB - 16TB
    * Max IOPS is 500
    * Max throughput of 500 MB/s - can burst
    
* **SCI (HDD) Cold HDD** : Lowest cost HDD volume designed for less frequently accessed workloads
      * Scenarios where the lowest storage cost is important
      * Cannot be boot volume
      * 500 GB - 16TB
      * Max IOPS is 250
      * Max throughput of 250 MB/s - can burst      
      
* Only GP2 and IO1 can be used as boot volumes

**EBS Snapshots**
    * Incremental - only backup changed blocks
    * Can copy snapshots across AZ or Region
    * Max 100000 snapshots
    * Snapshots will be stored in S3
    * Not necessarily to detach volume to do snapshot, but recommended
    * EBS backup use I/O and you shouldn't run them while your application is handling a lot of traffic
    * EBS volumes restored by snapshots need to be pre-warmed
    * Snapshots can be automated using Amazon Data Lifecycle Manager
    
    
**EBS Encryption**
    * Data at rest is encrypted inside the volume 
    * All the data in flight moving between the instance and the volume is encrypted
    * All snapshots are encrypted
    * All volumes created from the snapshot
    * Encryption has a minimal impact on latency
    * EBS Encryption leverages keys from KMS (AES -256)
    
     
**EBS vs Instance Store**
    * Some instance do not come with Root EBS volumes
    * Instead, they come with "Instance Store" (= ephemeral storage)
    * Instance store is physically attached to the machine ( EBS is a network drive)
    * **Pros**
       * Better I/O performance
       * Good for buffer /cache/ scratch data /temporary content
       * Data services reboots
    * **Cons**
        * On stop or termination, the instance store is lost
        * You can't resize the instance store
        * Backups must be operated by the user
        
        
**EBS RAID Options**
    * RAID 0 (increases performance)
        * Fast but no fault tolerance 
    * RAID 1 ( increase fault tolerance)
        * Mirroring a volume to another
        * If one disk fails, our logical volume still works
        * We have to send the data to two EBS volume at the same time (2X network)
        * Useful for applications that needs increase volume fault tolerance
         
         
         
          
 


 