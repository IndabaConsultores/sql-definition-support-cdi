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

@QueryResolver qualifier

used in this way
```java
@Inject
@QueryResolver(name="query_key")
private String query;

@Inject
@QueryResolver(name="query_key")
private QueryDefinition query;
```

Pending work: 
 - Startup customization by restricting package name for classpath *.sqld search

