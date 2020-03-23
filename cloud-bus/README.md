### Spring Cloud Bus

**Recall Spring Cloud Config:**
- Centralized server that serves-up configuration information
    - Configuration itself can be backed by source control
- Clients connect over HTTP and retrieve their configuration settings
    - Clients connect at startup time

![client-load-balancer](https://raw.githubusercontent.com/Shtramak/spring-cloud-training/master/images/config-server.jpg)

**Dynamic Configuration Changes**
- But what if we have configuration changes after the client applications are running
- Traditional approach: "Bounce" all applications
    - Repeating the startup process

**Potential Solution: Polling**
- Applications cloud periodically poll the Config Server for changes
    - After all, they send Eureka heartbeats
    
![client-load-balancer](https://raw.githubusercontent.com/Shtramak/spring-cloud-training/master/images/config-poll-solution.jpg)

- Probably best to push the changes from server to client instead
    - Config changes probably rare, no need to waste resources

- Push configuration changes to client applications via messaging technology, like AMQP

![client-load-balancer](https://raw.githubusercontent.com/Shtramak/spring-cloud-training/master/images/bus-solution.jpg)

**Spring Cloud Bus (Part 1)**
- Broadcasts configuration changes to clients
    - Eliminates need for client polling
- Based on Messaging technology - Currently AMQP Only
    - Clients become subscribers to configuration changes

Basically Spring Cloud Config Server publishes messages to the broker and the clients become subscribers
to the changes.

**Spring Cloud Bus Setup**
- Add dependency to the **_Spring Cloud Config Server_**
    - ```
      <dependency>
          <groupId>org.springframework.cloud</groupId>
          <artifactId>spring-cloud-starter-bus-amqp</artifactId>
      </dependency>
      <dependency>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-actuator</artifactId>
      </dependency>
      ```
    - within application.yml add _`management.endpoints.web.exposure.include: bus-refresh`_ to permit external POST requests to trigger the refresh (_see Part 2_)
        - in this case POST request to refresh endpoint will looks like _`http://localhost:8002/actuator/bus-refresh`_ 
- Add the same dependency to each of your clients
    - Code works automatically
    - Assumption: client code has spring cloud parent/dependency management section

**Spring Cloud Bus Setup (Part 2)**
- Run an AMQP server, such as Rabbit MQ
- Rabbit MQ:
    - Open Source
    - Easy to Install and Run
    - Pretty popular
- Spring Cloud Bus works automatically with Rabbit MQ on localhost

**Broadcasting Changes**
1. Make changes to your config file(s)
    - Config Server does not poll for changes
2. POST /bus/refresh to your config server
3. Broker ensures message delivery to clients
4. Clients receive message and refresh themselves 

![client-load-balancer](https://raw.githubusercontent.com/Shtramak/spring-cloud-training/master/images/broadcasting-changes.jpg)

**How Refresh Works**

 Spring Boot Application can be Refreshed at Runtime

- Actuator provides /refresh endpoint (POST)
    - org.spingframework.boot / spring-boot-actuator dependency
- ONLY affects the following:
    - Beans marked with @ConfigurationProperties
    - Beans marked with @RefreshScope
    - Logging level
    
**_@ConfigurationProperties_**
- Introduced in Spring Boot
- Easy alternative to multiple @Value annotation
- Properties rebound on POST /refresh

![client-load-balancer](https://raw.githubusercontent.com/Shtramak/spring-cloud-training/master/images/configuration_properties.jpg)

**_@RefreshScope_**
- Introduced in Spring Cloud
- Greater control, safe reloading of bean (not just property binding)
    - Side-effect: makes bean lazy
- Reloaded (not just rebound) on POST /refresh

![client-load-balancer](https://raw.githubusercontent.com/Shtramak/spring-cloud-training/master/images/refresh-scope.jpg)

**How @RefreshScope Works**
- Spring creates a proxy for the actual bean
- Proxy is dependency injected into other beans
- Proxy contains logic to call methods on the target bean
- On refresh, the "target" bean is changes to the newly created bean
    - older bean is dereferenced
- Result: users of original bean can safely finish their work

![client-load-balancer](https://raw.githubusercontent.com/Shtramak/spring-cloud-training/master/images/refresh-scope-principle.jpg)
