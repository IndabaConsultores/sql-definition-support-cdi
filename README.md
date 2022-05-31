[![Travis-CI](https://travis-ci.org/IndabaConsultores/sql-definition-support-cdi.svg?branch=master)](https://travis-ci.org/IndabaConsultores/sql-definition-support-cdi) [![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=es.indaba%3Asql-definition-support-cdi&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=es.indaba%3Asql-definition-support-cdi) [![SonarCloud Technical Debt](https://sonarcloud.io/api/badges/measure?key=es.indaba:sql-definition-support-cdi&metric=sqale_debt_ratio)](https://sonarcloud.io/dashboard?id=es.indaba:sql-definition-support-cdi) [![SonarCloud Coverage](https://sonarcloud.io/api/badges/measure?key=es.indaba:sql-definition-support-cdi&metric=coverage)](https://sonarcloud.io/dashboard?id=es.indaba:sql-definition-support-cdi)

# SQL Definition Support for CDI

## IDEA

 Use sql-definition-support for externalize queries into *.sqld files placed in the applications Classpath
 
 Apply a solution similar to DeltaSpike Configuration module (http://deltaspike.apache.org/documentation/configuration.html) where propertie values are injected into component members. 
 
```
 @ApplicationScoped
public class SomeRandomService
{
    @Inject
    @ConfigProperty(name = "endpoint.poll.interval")
    private Integer pollInterval;

    @Inject
    @ConfigProperty(name = "endpoint.poll.servername")
    private String pollUrl;

    ...
 }
 ```
 
 This will require:
 
  - A CDI Extension that: 
   - Look for SQLD files in the classpath
   - Resolve injection point providing the defined querie value
   
# First proposal

@QueryRepository annotation allows to initialize the sql definition store by searching the query definition files in the classpath. The classpath search is performed using the provided lookup prefix.

```java
@ApplicationScoped
@QueryRepository("es.indaba.sqld.test.sqld")
@QueryRepository("es.indaba.sqld.test.yaml")
public class QueryDefinitionTestBean {
...
```

@QueryResolver qualifier allows to get the query string injected into the beans. 

```java
@Inject
@QueryResolver("query_key")
private String query;

@Inject
@QueryResolver("query_key")
private QueryDefinition query;
```

## Contribute
Pull requests are welcomed!!

This is an open debate if you find this support unnecessary or you think there is a better way to manage the SQL queries in code. Please open an issue and we will be please to discuss about it. 

## Licenses
This work is distributed under LGPL v3.
