# Spring Boot Project Showcasing the CQRS Pattern

## What’s CQRS?  
The Command Query Responsibility Segregation (CQRS) pattern separates a service’s write tasks from its read tasks. While reading and writing to the same database is acceptable for small applications, distributed applications operating at web-scale require a segmented approach. Typically there’s more read activity than write activity. Also, read activity is immutable. Thus, replicas dedicated to reading data can be spread out over a variety of geolocations. This approach allows users to get the data that is closest to them. The result is a more efficient enterprise application.

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
