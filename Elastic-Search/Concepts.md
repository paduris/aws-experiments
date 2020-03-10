

**Fundamentals about Elastic Search**

* Not only for full text search but also can handle structured data and can aggregate data quickly.
* Often faster solution than Hadoop/Spark/Flink/etc
* Can scale horizontally
* Lucence under the hood
* REST based querying 
* Each "shard" is an inverted index of documents

**Kibana:**
* Very powerful Web UI for searching and visualizing 
* Provides Complex aggregations
* Often used for log analysis from different systems

**Logstash/Beats**
* Ways to feed data into Elastic search
* File Beat can monitor log files. parse them and import into elastic search in near real time
* Not just log file, it can collect data from different systems

**X-Pack**
* Security 
* Alerting
* Monitoring
* Google and find more information

**CURL Command**
`
curl -H "Context-Type:application/json" <URL> -d '<BODY>'
`

**Concepts**

* Documents ( like a row of data ) : are the things we are searching for. A structured JSON, every document has a unique ID, and a type.
* Type (Table) : A type defines the schema and mapping shared by documents that represent the same sort of things.
* Index ( like a Database )  : A collection of types which in turn contains documents
* Inverted Index: Simply maps search query terms to documents so that they can be easily searchable

**Research below**

* **TF-IDF** means Term Frequency*Inverse Document Frequency
    * Term Frequency -> is how often a term appears in a given document 
    * Document Frequency -> is how often a term appears in all documents
    * Term Frequency/Document Frequency measures the **relevance** of a term in a document
  

**Elastic Search Architecture**

* An Index is split into shards for spreading the load - a common pattern in distributed systems architecture
* Documents are hashed to particular shard
* Each shard may be on a different node in a cluster
* Every shard is a self contained Lucene index of its own
* Uses primary and replica shards in different nodes to handle fail over - fault tolerant system

* Write requests are routed to the primary shard, then replicated
* Read requests are routed to the primary or any replica


**Facts**

* The number of primary shards cannot be changed later.
* You can add more replica shards for more read throughput
* Worst case you can re-index your data
* The number of shards can be set up front via a out command via REST/HTTP


    
**Mapping & Indexing**

https://www.elastic.co/guide/en/elasticsearch/reference/current/mapping.html


**Create Mapping**
  * Mapping is the process of defining how a document, and the fields it contains, are stored and indexed.
  * Tells elastic search how to store the documents
  * A mapping is simply a **schema** definition
  * Elastic Search has reasonable defaults, but sometimes you need to customize them.
  * **Field Types** - text, keyword, byte, short, integer, long, float, double, boolean, date
        
        "properties":{
            "user_id":{
            "type": "long" 
              }
             }
  * **Field Index** -> do you want this field to be queryable? true/false
  
        "properties":{
            "genre":{
            "index": "false" 
                    }
                     }
  * **Field Analyzer** -> define your tokenizer and token filter, standard/whitespace/simple/english etc.
  
         "properties":{
              "description":{
              "analyzer": "english" 
                      }
       * Character filters -> remove HTML encoding, convert * & to and 
       * Tokenizer -> split string on whitespace/punctuation/non-letters
       * Token filter-> lowercasing,stemming, synonyms, stopwords etc    
         
                       }

* For example: For each primary we will have 1 replica - total 6 
`
PUT /testindex
{
"settings": {
                "number_of_shards": 3 ,
                "number_of_replicas" : 1
            }
}
`

**Analyzers**


**Create Mapping - mapping is some sort of schema definition**

```
curl -H "Content-Type:application/json" -XPUT 127.0.0.1:9200/movie -d '{ "mappings": { "movie": {"properties" : {"year": {"type": "date" }}}}}'

curl -H "Content-Type:application/json" -XGET 127.0.0.1:9200/movies/_mapping/movie?pretty 
 
curl -H "Content-Type:application/json" -XPUT 127.0.0.1:9200/movies/movie/109487 -d '{ "genre": ["IMAX","Sci-Fi"] , "title": "Interstellar", "year":2014 }'

curl -XGET 127.0.0.1:9200/movies/movie/_search?pretty
```

**Inserting multiple documents**

```
wget http://media.sundog-soft.com/es/movies.json

curl -H "Content-Type:application/json" -XPUT 127.0.0.1:9200/_bulk?pretty --data-binary @movies.json
```

**UPDATE Documents**

* Every document has a _version field
* Elastic search documents are immutable
* When you update an existing document, a new document is created with an incremented _version, the old document is marked for deletion

````
curl -XGET 127.0.0.1:9200/movies/movie/109487?pretty

curl -H "Content-Type:application/json" -XPOST 127.0.0.1:9200/movies/movie/109487/_update -d ' { "doc": { "title" : "Interstellar Su" } }'

curl -XGET 127.0.0.1:9200/movies/movie/109487?pretty -> check now the document is marked version2

curl -H "Content-Type:application/json" -XPUT 127.0.0.1:9200/movies/movie/109487?pretty -d ' { "doc": { "title" : "Interstellar Sure" } }'

````
* Now it is updated to version - it didn't created but it updated


**DELETE Documents**
```
curl -XGET localhost:9200/movies/movie/_search?q=Dark

curl -XDELETE localhost:9200/movies/movie/58559?pretty
```

**Concurrency**
* Optimistic concurrency control
* retry on conflicts -> configuration  
* Use version numbers to handle concurrency 

`
curl -H "Content-Type:application/json" -XPOST 127.0.0.1:9200/movies/movie/109487/_update?retry_on_conflict=5 -d ' { "doc": { "title" : "Interstellar Su" } }'
`

**SEARCH**

* Use _keyword_ mapping type for exact match 
* Use _text_ type mapping to allow analyzing -> search on analyzing fields will return anything remotely relevant

`
curl -H "Content-Type:application/json" -XGET localhost:9200/movies/movie/_search?pretty -d ' { "query": { "match": { "title": "Star Trek"} } }'
`

* Analyzers belong to shard area - sometimes you need to understand how analyzers are working for correct search results


**Parent-Child Relationships**
* Strategies for relational data - modelling parent-child relationships - always loads inside a same shard

```
curl -H "Content-Type:application/json" -XPUT localhost:9200/series -d ' {"mappings": { "movie": {"properties" : { "film_to_franchise" : { "type" : "join","relations": {"franchise": "film" }}}}}}'

wget http://media.sundog-soft.com/es6/series.json

curl -H "Content-Type:application/json" -XPUT localhost:9200/_bulk?pretty --data-binary @series.json

curl -H "Content-Type:application/json" localhost:9200/series/movie/_search?pretty -d '{ "query": {"has_parent": {"parent_type": "franchise" , "query":{ "match": {"title":"Star Wars"} } } } }'

curl -H "Content-Type:application/json" localhost:9200/series/movie/_search?pretty -d '{ "query": {"has_child": {"type": "film" , "query":{ "match": {"title":"The Force Awakens"} } } } }

```


**Query Lite**


**Queries and Filters**

> term: filter by exact values -> {“term”: {“year”: 2014}}

> terms: match if any exact values in a list match -> {“terms”: {“genre”: [“Sci-Fi”, “Adventure”] } }

> range: Find numbers or dates in a given range (gt, gte, lt, lte) -> {“range”: {“year”: {“gte”: 2010}}}

> exists: Find documents where a field exists -> {“exists”: {“field”: “tags”}}

> missing: Find documents where a field is missing -> {“missing”: {“field”: “tags”}}

* bool: Combine filters with Boolean logic (must, must_not, should)

> match_all: returns all documents and is the default. Normally used with a filter. -> {“match_all”: {}}

> match: searches analyzed results, such as full text search. -> {“match”: {“title”: “star”}}

> multi_match: run the same query on multiple fields. -> {“multi_match”: {“query”: “star”, “fields”: [“title”, “synopsis” ] } }

> bool: Works like a bool filter, but results are scored by relevance.

* use slop
* use phrase search

**Pagination**

`
    curl -H "Content-Type:application/json" -XGET 127.0.0.1:9200/movies/movie/_search?pretty -d'
    {
    "from": 2,
    "size": 2,
    "query": {"match": {"genre": "Sci-Fi"}}
    }'
`

**NOTE**
* A text field that is analyzed for full-text search can’t be used to sort documents
* This is because it exists in the inverted index as individual terms, not as the entire string.

* If you need to sort on an analyzed field, map an unanalyzed copy using the keyword type.
`
    curl -H "Content-Type:application/json" -XPUT 127.0.0.1:9200/movies/ -d '
    {
    "mappings": {
    "movie": {
    "properties" : {
    "title": {
    "type" : “text",
    "fields": {
    "raw": {
    "type": “keyword",
    }}}}}}}
`

**Filters**

`
    curl -H "Content-Type:application/json" -XGET 127.0.0.1:9200/movies/_search?pretty -d'
    {
    "query":{
    "bool": {
    "must": {"match": {"genre": "Sci-Fi"}},
    "must_not": {"match": {"title": "trek"}},
    "filter": {"range": {"year": {"gte": 2010, "lt": 2015}}}
    }
    }
    }
`

**fuzzy matches**

* fuzziness: AUTO
* • 0 for 1-2 character strings
* • 1 for 3-5 character strings
* • 2 for anything else

`
    curl -H "Content-Type:application/json" -XGET 127.0.0.1:9200/movies/movie/_search?pretty -d '
    {
    "query": {
    "fuzzy": {
    "title": {"value": "intrsteller", "fuzziness": 2}
    }
    }
    }'`

**Prefix queries on strings**

`
    curl -H "Content-Type:application/json" -XGET '127.0.0.1:9200/movies/movie/_search?pretty' -d '
    {
    "query": {
    "prefix": {
    "year": "201"
    }
    }
    }'
`

**wildcard queries**

`
    curl -H "Content-Type:application/json" -XGET '127.0.0.1:9200/movies/movie/_search?pretty' -d '
    {
    "query": {
    "wildcard": {
    "year": "1*"
    }
    }
    }'
`