**Security & Encryption**

* **Server side encryption at rest**
    * Data is encrypted after being received by the server
    * Data is decrypted before being sent 
    * It is stored in an encrypted form thanks to key (usually a data key)
    * The encryption/decryption keys must be managed somewhere and the server must have access to it.
    
* **Client side encryption**
    * Data is encrypted by the client and never decrypted by the server
    * Data will be decrypted by a receiving client
    * The server should not be able to decrypt the data
    * Could leverage Envelope Encryption - * Research 
    
    
* **AWS KMS (Key Management Service)**
    * Anytime you hear "encryption" for an AWS service, it is most likely KMS
    * Easy way to control access to your data., AWS manages keys for us
    * Fully integrated with IAM for authorization
    * Seamlessly integrated into :
        * Amazon EBS: encrypt volumes
        * Amazon S3: Server side encryption of objects
        * Amazon Redshift : encryption of data
        * Amazon RDS: encryption of data
        * Amazon SSM : Parameter store
        * etc...
     * But you can also use the CLI/SDK 
     
     
* **AWS KMS 101**
    * Anytime you need to share sensitive information ..use KMS
        * Database passwords
        * Credentials to external service
        * Private Key of SSL certificates
    * The value in KMS is that the CMK (Customer Master Key) used to encrypt data can never be retrieved by the user, and the CMK can be rotated for extra security 
    * Never ever store your secrets in plaintext , especially in your code.
    * Encrypted secrets can be stored in the code/environment variable 
    * **KMS can only help in encrypting up to 4KB of data per call**
    * You can encrypt up to 4 kilobytes (4096 bytes) of arbitrary data such as an RSA key, a database password, or other sensitive information.
    * If the data > 4KB, use Envelope encryption 
    * To give access to KMS to someone :
        * Make sure the Key Policy allows the user
        * Make sure the IAM Policy allows the API calls
    * Able to fully manage the keys & policies 
        * Create 
        * Rotation policies
        * Disable 
        * Enable
    * Able to audit key usage (using CloudTrail)
    * Three types of Customer Master Keys (CMK)
        * AWS Managed Service Default CMK : **free**
        * User Keys created in KMS: $1/month
        * User Keys imported ( must be 256-bit symmetric key): $1/month
        * + pay for API call to KMS ($0.03 /10000 calls)

    * The below services requires migration to encrypt ( through Snapshot / Backup )
        * EBS Volumes
        * RDS databases
        * ElastiCache
        * EFS network file system
        
    * In-place Encryption:
        * S3 
        
        
***AWS Parameter Store**
    * Secure storage for configuration and secrets
    * Optional Seamless Encryption using KMS
    * Serverless, scalable, durable, easy SDK, free
    * Version tracking of configurations / secrets
    * Configuration management using path & IAM
    * Notifications with CloudWatch Events
    * Integration with CloudFormation
    

***AWS STS - Security Token Service**
    * Allows to grant limited and temporary access to AWS Resources
    * Token is valid upto one hour (must be refreshed)
    * Cross Account Access
        * Allows users from one AWS Account access resources in another
        * **Federation (Active Directory)** 
            *Provides non AWS user with temporary AWS access by linking Active Directory Credentials
            *Uses SAML (Security Assertion markup language)
            * Allows Single Sign On (SSO) which enables users to log in to AWS console without assigning IAM credentials
        * **Federation with third party providers / Cognito**
          * Used mainly by web and mobile applications
          * Makes use of Facebook/Google/Amazon etc to federate them 
***Cross Account Access**
    * Define a IAM role for another account to access
    * Define which accounts can access to this role
    * Use AWS STS(Security Token Service ) to retrieve credentials and impersonate the IAM role you have access to (**AssumeRoleAPI**)
    * Temporary credentials can be valid between 15 mins to 1 hour 
    
    
**Identity federation with SAML & Cognito**
    * Federation lets users outside of AWS to assume temporary role for accessing AWS resources
    * These users assume identity provided access role
    * Federtion assumes a form of 3rd party authentication
        * LDAP
        * Microsoft Active Directory (SAML)
        * Single Sign on
        * Open ID
        * Cognito
    * Using federation, you don't need to create IAM users ( user management is outside of AWS )
    * **SAML Federation** ( for large enterprises )
        * To integrate Active Directory /ADFS with AWS ( or any SAML 2.0 )
        * Provide access to AWS Console or CLI ( through temporary needs )
        * No need to create an IAM user for each of your employees
    * **Custom Identity Broker Application**
        * Use only if identity provide is not compatible with SAML 2.0
        * The identity broker must determine ( we have to program it - lot more work ) the appropriate IAM policy
    * **AWS Cognito** - Federated Identity Pools Fo Public Applications 
        * **Goal**
            * Provide direct access to AWS Resources from the Client Side
        * **How**
            * Log in to federated identity provider - or remain anonymous 
            * Get temporary AWS credentials back from the Federated Identity Pool
            * These credentials come with a pre-defined IAM policy stating their permissions
        **Example**
            * Provide ( temporary) access to write to S3 bucket using Facebook Login 
            
        
***AWS Shared Responsibility Model**        
    * AWS responsibility - Security in Cloud
        * Protecting infrastructure (hardware, software, facilities and networking) that runs all of the AWS services
        * Managed services like S3, DynamoDB, RDS etc
    * Customer responsibility - Security in the cloud
        * For Ec2 instance, customer is responsible for management of the quest IS ( including security patches and updates ), firewall & network configuration, IAM etc
