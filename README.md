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
@QueryResolver(name="query_key")
private String query;

@Inject
@QueryResolver(name="query_key")
private QueryDefinition query;
```
