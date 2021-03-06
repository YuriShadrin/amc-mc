# Metrics Collector
## Purpose
This document is dedicated to description of offer to design and implementation of information system for retrieving and collecting information from social networks about different media content - **Metrics Collector** (or MC, name can be discussed). Such information will contain some metrics (number of visitors, number of likes, posts, etc). MC does not have any analytics blocks or modules, MC just provides information for other Analytics Systems.

## Basic Definitions
**Source** - social network or any other data source which can be used to solve business goals. As a rule, source provides an own **API** to communicate with him. As a rule *Source* has limits such as: timeframe, number of requests in one timeframe, intensity of requests (number of requests in 1 second) and so on.

**Connector** - module which communicates with *Source* using corresponding API. *Connector* retrieves *Metrics Data* from *Source* and returns it back.

**Metrics Data** - data which contains metrics. *Metrics Data* will be defined in design in accordance with business requirements and possibilities of each particular *Source*.

**Data Saver** - module which saves *Metrics Data* received from *Connector* into *Storage*.

**Storage** - SQL DB (or any other storage).

**Task** - module which combines *Connector* and *Data Saver* to process tasks. *Task* contains one *Connector* and one *Data Saver*.

**Scheduler** - module which performs *Task*s in accordance with calculated schedule. One *Scheduler* can perform only one type of *Task*. Type of *Task* usually related to *Source* (for example: YouTube Scheduler, FaceBook Scheduler, etc.). Schedule is calculated in accordance with limits of each particular *Source* and business goals. An alternative name of this module - **Processor** (TBD). 

**Engine** - container of *Schedulers*, one *Engine* contains many *Schedulers*. *Engine* also has functions of *Engine Configuration* management, logging. Each *Engine* has an own identifier, *Engine* identifier is used to separate configuration of different *Engines* deployed into one *Cluster*.

**Engine Configuration** - set of configuration data for *Engine* functioning.

**Cluster** - container of *Engines*, *Cluster* also can has *Cluster Dashboard* module.

**Cluster Dashboard** - set of REST services which provides methods to configure *Cluster* (i.e. deployed *Engines*) and any other functionality (monitoring, eventing, etc). *Cluster Dashboard* communicates with deployed *Engines* through JMX. The main purpose of *Cluster Dashboard* is to provide possibility to create **UI**.

**Cluster Administrator**  - a responsible person who manages *Cluster*, different *Clusters* can have different *Cluster Administrators*.

The system can contain other additional modules, for example *Processing Monitor*, *Data Replication*, etc.

**Schematically:**

![](https://github.com/YuriShadrin/amc-mc/blob/master/docs/scheme1.png)

The common steps of processing is the following:

1. Engine reads configuration and starts all configured Schedulers.
2. Scheduler in accordance with configuration starts Task (*TIMESTAMP*)
3. Task performs an internal logic (calls Connector and Saver which are contained inside Task).
  * Connector calls Sources and returns Metrics Data to Task.
  * Task passes Metrics Data to Data Saver.
  * Data Saver saves data into Storage.
4. Scheduler calculates next start of Task taking into consideration existing limitations and *TIMESTAMP*.
5. Scheduler performs step #1. 

##Deployment
**Apache Tomcat** as deployment environment is proposed to use, there are reasons:
* Each Engine is deployed as separated WAR, name of WAR file is used for Engine identification (Engine identifier).
* Separately deployed Engines work isolated from each other.
* Tomcat provides access through HTTP/HTTPS, this access can be used for UI.
 
![](https://github.com/YuriShadrin/amc-mc/blob/master/docs/scheme2.png)

