Goal of **Spring Cloud** - provide libraries to apply common patterns needed in distributed applications
- Distributed / Versioned / Centralized Configuration Managment
- Service Registration and Discovery
- Load Balancing
- Service-to-Service Calls
- Circuit Breakers
- Routing

Spring Cloud enables easy use of Netflix libraries

The best way to add Spring Cloud dependency to a project (to not use `<parent>`)

```
<dependencyManagement>
     <dependencies>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-boot-dependencies</artifactId>
            <version>...</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>
 ```

**Issues we can faced during configuration:**
- Package configuration files with application (requires rebuild and restart of the application)
- Configuration files in common file system (unavailable in cloud)
- User environment variables (Done differently on different platforms, large number of individual variables to manage / duplicate)
- Use a cloud-vendor specific solution (Coupling application to specific environment)

**Spring Cloud solution:**

Spring Cloud Config 
- Provides centralized, externalized, secured, easy-to-reach source of application configuration

Spring Cloud Bus
- Provides simple way to notify clients to config changes

Spring Cloud Netflix Eureka
- Service Discovery - allows applications to register themselves as clients
