# Spring Boot Project Showcasing the CQRS Pattern
## Introduction  
This is a project that showcases the Command Query Responsibility Segregation (CQRS) Architectural Pattern made with Spring Boot.  

This project was made as part of a university course.

## What’s CQRS?  
The Command Query Responsibility Segregation (CQRS) pattern separates a service’s write tasks from its read tasks. While reading and writing to the same database is acceptable for small applications, distributed applications operating at web-scale require a segmented approach. Typically there’s more read activity than write activity. Also, read activity is immutable. Thus, replicas dedicated to reading data can be spread out over a variety of geolocations. This approach allows users to get the data that is closest to them. The result is a more efficient enterprise application.

<p align="center">
  <img src="/assets/cqrs.png" alt="CQRS Schema"/>
</p>

Separating write from read activity also allows Enterprise Architects to
create a variety of read databases, with each database optimized for
a specific purpose.
### Pros:
+ Separating write activity from ready activities allows you to use
the best database technology for the task at hand, for example,
a SQL database for writing and a noSQL database for reading.
+ Read activity tends to be more frequent than writing, thus you
can reduce response latency by placing read data sources in
strategic geolocations for better performance.
+ Separating write from read activity leads to more efficient scaling
of storage capacity based on real-world usage.
### Cons:
- Supporting the CQRS pattern requires expertise in a variety of
database technologies.
- Using the CQRS patterns means that more database technologies
are required hence there is more inherent cost either in terms of
hardware or if a cloud provider is used, utilization expense.
- Ensuring data consistency requires special consideration in terms
of Service Level Agreements.
- Using a large number of databases means more points of failure,
thus companies need to have comprehensive monitoring and
fail-safety mechanisms in place to provide adequate operation.

## Project Architecture:
For this project, we aimed for the simplest possible architecture yet
being very efficient. So the idea is: we separate the data into two
categories: one for writing activities and the other for the reading
activities. In our case we used the NoSQL MongoDB for the write
activity, specifically MongoDB Atlas which is a fully-managed cloud
database that handles all the complexity of deploying, managing, and
healing your deployments on the cloud service provider of your choice
(AWS , Azure, and GCP).

<p align="center">
  <img src="/assets/mongodb.png" alt="MongoDB Logo"/>
</p>

As for the read activity we used Elasticsearch which is an open-source
search engine that is highly scalable.
Elasticsearch allows you to store, search, and analyze huge volumes of
data quickly and in near real-time and give back answers in
milliseconds. It’s able to achieve fast search responses because instead
of searching the text directly, it searches an index.

<p align="center">
  <img src="/assets/es2.png" alt="Elasticsearch Logo"/>
</p>

As for the communication between the two databases, we opted for
the simplest solution which is storing the events for every modification in
the write database, be it creation, update or delete in queues for the
read database to consume. For that we used the message broker
RabbitMQ, specifically a cloud server known as CloudAMQP which fully
manages, with high availability, RabbitMQ servers and clusters. So it
acts like “RabbitMQ as a Service”.

<p align="center">
  <img src="/assets/cloudamqp2.png" alt="CloudAMQP Logo"/>
</p>

Last but not least, we implemented an API Gateway and a Service
Registry & Discovery service to register all the instances that will be
used, so in our case: the command service which takes care of all the
write activity, the query service that will be responsible to return the
requested data and the gateway that will redirect all incoming
requests to the corresponding service.
For the Service Registry we used the Netflix Eureka as it is very efficient
with Spring Boot and Spring Cloud for the API Gateway.

<p align="center">
  <img src="/assets/service-discovery.png" alt="Service Discovery with Netflix Eureka"/>
</p>

The full architecture of the project can be resumed in the following schema:

<p align="center">
  <img src="/assets/project-architecture-cropped.png" alt="Full Project Architecture"/>
</p>

# How to run  

As mentioned previously, the project uses the services provided by MongoDB Atlas and CloudAMQP. If you want to change the environment, changing the connection string and the database or server URL is needed.  
Therefore, the following servers must be running locally in your machine:  
+ MongoDB server
+ RabbitMQ server
+ Elasticsearch server
In addition, you have to run the Spring Boot servers in order of their necessary availability
The Spring Boot can be run with the following command: 
```sh
$ mvn spring-boot:run
```

# Future plans

So far, the only plans is to implement a configuration server for the projects to make it more secure and elegant. It should also be noted that the simple RabbitMQ implementation is not always the best case and that it should be replaced with something like topics or exchange.

# Contributing

Anyone is allowed to contibute in this project. Pull requests are welcome.
For major changes, please open an issue first to discuss what you would like to change.
