
**CIDR - Classless Inter-Domain Routing**

**IPV4**
    * A CIDR has two components
        * The base IP (XX.XX.XX.XX)
        * The Subnet Mask - For 

            * (/32) -> 2 ^ 0
            * (/31) -> 2 ^ 1
            * (/30) -> 2 ^ 2
            * (/29) -> 2 ^ 3
            * (/28) -> 2 ^ 4
            
            * Example  192.168.0.0/24 
                192.168.0.0 - 192.168.0.255 ( 256 IPs)


**Private Vs Public**
                
* The Internet Assigned Numbers Authority (IANA) established certain blocks of IPV4 addresses for the use of 
  private (LAN) and public (Internet) addresses.
* Private IP can only allow certain values
    
    * 10.0.0.0 - 10.255.255.255.255 (10.0.0.0/8) = in big networks 
    * 172.16.0.0 - 172.31.255.255 (172.16.0.0/12) = default AWS one
    * 192.168.0.0 - 192.168.255.255 ( 192.168.0.0/16) = example : home networks
    * All the rest of the IP on the internet are public IP
        
* AWS Reserves 5 IP Address in a Subnet ( First 4 and last 1 )        
* For example if CIDR Block 10.0.0.0/24 
    * 10.0.0.0 is reserved for Network Address
    * 10.0.0.1 is reserved for VPC Router
    * 10.0.0.2 is reserved for mapping to Amazon provided DNS
    * 10.0.0.3 is reserved for AWS future
    * 10.0.0.255 Network broadcast address. AWS doesn't support broadcast in a VPC, therefore the address is reserved       
             
            
**Internet Gateway**
    * Internet gateways helps VPCs to connect to Internet
    * It scales horizontally and HA and redundant 
    * Must be created separated from VPC
    * One VPC can only be connected to one Internet Gateway and vice versa

**NAT Instances**
    * NAT instances 
    * Must disable source/destination check 
    * Private Subnet  connected to NAT Instance to connect to Internet
**NAT Gatweway**
    * AWS Managed, NAT higher bandwidth, better availability , no admin 
    * Pay by hour for usage and bandwidth
    * NAT is created in specific AZ, used EIP
    * Private Subnet --> NAT --> IGW      
            
**NACLs & Security Groups**


**VPC Peering**

**VPC Endpoints**
    * Allows you to connect to AWS Services using a private network instead of public network.
    * Scales horizontally and are redundant
    * They remove the need of Internet Gateway etc... to access the AWS Services
    * Two kinds of Endpoints 
        * **Interface** : provisions an ENI ( private IP Address) as an entry point (must attach to the security group) - most AWS services 
        * **Gateway**: provisions a target and must be used in a route table - S3 and Dynamo Table.
**VPC Flowlogs + Athena**

**Bastion Hosts**
  * We can use Bastion hosts to SSH into our private instances
  * The bastion is in the public subnet which is then connected to all other private instances
  * Bastion security group is tightened 
             
           
            
            
          
        
  
     
