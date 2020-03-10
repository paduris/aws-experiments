**Messaging**

* **SQS - Simple Queue Service** -> Queue model
    * Oldest offering (over 10 years old)
    * Fully Managed
    * Scales from 1 message per second to 10000 msgs per second
    * **Default retention of messages:** 4 days, maximum of 14 days
    * No limit to how many messages can be in the queue
    * Low latency (<10 ms on publish and receive)
    * Horizontal scaling in terms of number of consumers
    * Can have duplicate messages (at least once delivery, occasionally)
    * Can have out of order messages (best effort ordering)
    * Limitation of 256KB per message sent
    * **Delay Queue**
        * Delay a message up to 15 mins ( consumers don't see it immediately )
        * Default is 0 seconds (message is available right away)
        * Can set a default at queue level
        * Can override the default using the **DelaySeconds** parameter
    * **Producing Messages**
        * Define Body (256KB - String)
        * Add message attributes (key/value pair - metadata- optional) 
        * Provide Delay Delivery (optional)
        * Response to producer from SQS after it sent to SQS 
            - Message identifier
            - MD5 hash of the message body
    * **Consumer**
        * Poll SQS for messages ( consumer receive up to 10 messages at a time )
        * Process the message with in the visibility timeout
        * Delete the message using the message ID * receipt handle - deleted once the consumer consumes messages
    * **Visibility Time out** 
        * When consumer polls a message from a queue, the message is "invisible" to other consumers for a defined period.
        * Set between 0 seconds to 12 hours ( default is 30 secs)
        * If you set too high (15 mins ) and consumer fails to process the message, you must wait a long time before processing the message again.
        * If you set too low (30 secs) and consumer needs time to process the message ( 2 minutes), another consumer will receive the message and the message 
          will be processed more than once.
        * **ChangeMessageVisibility** - API to change the visibility while processing a message
        * **DeleteMessage** API to tell SQS that the message was successfully processed.
    * **Dead Letter Queue**
        * If a consumer fails to process a message with in the Visibility Timeout..the message goes back to the SQS queue
        * We can set a threshold of how many times a message can go back to the queue - its called a "redrive policy"
        * After the threshold is exceeded, the message goes into a dead letter queue (DLQ)
        * We have to create a DLQ first and then designate it "dead letter queue"
        * Make sure to process the messages in the DLQ before they expire
    * **Long Polling** 
        * When a consumer requests message from the queue, it can optionally "wait" for the messages to arrive  the if there are none in the queue, this is called "Long Polling"
        * LongPolling decreases the no of API calls made to SQS while increasing the efficiency and latency of your application.
        * The wait time can be between 1 sec to 20 sec (20 sec preferable)
        * Long polling is more preferable than short polling
        * Long polling can ve enabled at the queue or at the API level using "WaitTimeSeconds"
    * **FIFO Queue**
        * Newer offering ( First in - First out) - not available in all regions
        * Name of the queue must end with **.fifo**
        * Lower throughput (up to 3000 msgs per second with batching, 300/s without)
        * Messages are processed in order by the consumer
        * Messages are sent exactly once
        * No per message delay ( only per queue delay)
        * Ability to do content based **de-deduplication**
        * 5 min interval **de-duplication** using "Duplication ID"
        * Message Groups:
            * Possibility to group messages for FIFO ordering using "Message Group ID"
            * Only one worker can be assigned per message group so that messages are processed in order
            * Message group is just an extra tag on the message.
    * **SNS - System to System Messaging** -> Pub/Sub model
        * The "Event Producer" only sends messages to one SNS topic
        * As many "event receivers" (subscriptions) as we want to listen to the SNS topic notifications
        * Each subscriber to the topic will get all the messages (note: there is a new feature to filter messages)
        * Up to 1000000 subscriptions per topic
        * 100000 topic limit
        * **Subscribers**
           * SQS
           * HTTP/HTTPS
           * Lambda
           * Emails
           * SMS Messages
           * Mobile Notifications
           * It integrates with so many other AWS services
        * AWS SNS - How to publish 
            * Create Topic
            * Create a subscription ( or many)
            * Publish to the topic
        * Direct Publish (for more apps SDK)
            * Create a platform application
            * Create a platform endpoint 
            * Publish to the platform endpoint
            * Works with Google GCM,Apple APNS, Amazon ADM
    * **SNS + SQS : Fan Out**
        * Push once in SNS, receive in many SQS
        * Fully decoupled 
        * No data loss
        * Ability to add receivers of data later
        * SQS allows for delayed processing
        * SQS allows for retries of work
        * May have many workers on one queue and one worker on the other queue
        
        
* **Kinesis** -> Real time streaming model
        * Kinesis is a managed alternative to Apache Kafka
        * Great for application logs, metrics, IoT, click streams
        * Great for "real time" big data
        * Great for streaming processing frameworks (Spark, NiFi, etc..)
        * Data is automatically replicated to 3 AZ
        * **Kinesis Streams**: Low latency streaming ingest at scale
            * Streams are divided in ordered Shards/Partitions
            * Data retention is 1 day by default, can go up to 7 days
            * Ability to reprocess/replay data
            * Multiple applications can consume the same stream
            * Real time processing with scale of throughput
            * Once data is inserted in Kinesis, it can't be deleted (immutability )
        * **Kinesis Analytics**: Perform real-time analytics on streams using SQL
        * **Kinesis Firehose**: Load streams into S3, Redshift, ElasticSearch..
        
