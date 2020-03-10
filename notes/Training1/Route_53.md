
**Route 53**

You can use Amazon Route 53 to register new domains, transfer existing domains, route traffic for your domains to your AWS and external resources
and monitor the health of your resources.

* Route53 is a Managed DNS (Domain Name System)
* Route53 is globally hosted
* DNS is a collection of rules and records which helps clients understand how to reach a server through URLs
* In AWS, the most common records are
    * **A**: URL to IPV4
    * **AAAA**: URL to IPV6
    * **CNAME**: URL to URL
    * **Alias**: URL to AWS resource
     
* Route53 can use:
    * public domain names you own or buy ex: application1.mypublicdomain.com
    * private domain names that can be resolved by your instances in your VPCs ex: application1.company.internal

* Route53 has advanced features such as:
    * Load balancing ( through DNS - also called client load balancing)
    * Health checks
    * Routing policy: simple, failover, geolocation,latency, weighted, multi value
    * You pay $0.50 per month hosted zone

* Created a new domain _earthfacez.com_ 


``` 
> dig myfirstrecord.earthfacez.com
> nslookup dig myfirstrecord.earthfacez.com
 ```
 
 
 **CNAME vs ALIAS**
 
 * CNAME : Points a URL to any other URL ( only for non root domain)
 * Alias: Points a URL to AWS Resource ( works for root domain and non root domain) 
    * Free of charge
    * Native health check
    
 
 * Simple Routing Policy 
 * Weighted Routing Policy 
    * Control the % of the requests that go to specific endpoint
    * Helpful to test 1% of traffic on new app version for example
 * Latency Routing Policy
    * Redirect to the server that has the least latency close to us
    * Super helpful when latency of users is a priority
    * Latency is evaluated in terms of user to designated AWS Region
        