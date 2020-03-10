**Serverless**
* Serverless is a new paradigm in which developers don't have to manage servers
* They just deploy code
* They just deploy..functions !
* Initially Serverless == FaaS (Function as a Service)
* Serverless was pioneered by AWS Lambda but now also includes anything that's managed: "databases,messaging,storage,etc."
* Serverless does not mean there are no servers..it means you don't manage/provision/ see them..

**AWS Lambda**
* Virtual **Functions** - no servers to manage
* Limited by time - **short executions**
* Run **on-demand**
* Scaling is automated
* Benefits
    * Easy Pricing
        * Pay per request and compute time
        * Free tier of 1,000,000 AWS Lambda requests and 400,000 GBs of compute time
        * Integrated with the whole AWS Stack
        * Integrated with many programming languages
        * Easy monitoring through AWs Cloud Watch 
        * Easy to get more resources per functions (up to 3GB of RAM)
        * Increasing RAM will also improve CPU and network 
* **AWS Lambda Configuration**
    * Timeout : default 3 secs, max 300s    
    
* **AWS Lambda Limits**
    * **Execution**
        * Memory allocation : 128 MB - 2008 MB (64 MD increments)
        * Maximum execution time : 300 seconds 
        * Disk capacity in the 'function container' (in/tmp): 512 MB
    * **Deployment**
        * Lambda function deployment size (compressed.zip) : 50 MB
        * Size of uncompressed deployment (code + dependencies): 250 MB
        * Can use the /tmp directory to load other files at start up
        * Size of environment variables : 4KB
* **DynamoDB**