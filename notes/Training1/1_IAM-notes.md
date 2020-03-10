**AWS Regions & AZs**
AWS has regions all around the world
For Example:
````Region Name: US East (N. Virginia)	
Region : us-east-1
EndPoint : rds.us-east-1.amazonaws.com
Region -> Availability Zone - AZ
````
Each Region has 2 or more availability zones
For example:
`(us-east-1a, us-east-1b..)    `

Each AZ is a physical data center (sometimes may be several data centers because they are close together, they are counted as 1 AZ) in the region.

**IAM (Identity and Access Management)**
* IAM is at the center of AWS and has a global view
* Centralized control of your AWS account
* Granular permissions
* Integrates with many different AWS services
* Supports PCI DSS Compliance
* IAM is a global service ( encompasses all regions )
```
Users  - Physical Person
Groups - Collections of users -> Users Groups such as admins, developers, engineering etc...
Policies - Policies are made up of JSON documents, they give permissions as to what a User/Group/Role is able to do.
Roles  - Internal usage with in AWS resources - given to machines 
````
* Permissions are governed by Policies (JSON)
* MFA (Multi Factor Authentication) can be set up
* IAM has predefined policies provided by Amazon
**Recommendation:**  
    * It's best to give users the minimal amount of permissions they need to perform their job (least privilege principles)
    * One IAM User per Physical Person
    * One IAM Role per Application
    * IAM credentials should never be **shared**
    * Never use IAM credentials in code
    * Never use the ROOT account expect for initial setup
    * Never use ROOT IAM credentials
**IAM Federation**
    * Big Enterprises usually integrate their own repository of users with IAM so that once can login into AWS using their company credentials
    * Identify Federation uses the SAML standard (for ex: Active Directory)



