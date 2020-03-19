###Service Discovery (Spring Cloud Eureka Server)
It's just a regular Spring Boot web application with dependencies and **@EnableEurekaServer** that will turn on Eureka functionality

**Important points:**<br>
- Eureka Server designed for multi-instance use (Standalone mode will actually warn you when it runs without any peers)
- Eureka Server does not persist service registrations (relies on client registrations; always up to date, always in memory)
- Typical productio usage - many Eureka server instances running in different availability zones/regions

**Eureka Server Dependency:**
```
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-eureka-server</artifactId>
</dependency>
```
If there's only one Eureka Server (usually not production use) then following properties should be set:
- eureka.client.registerWithEureka: false
- eureka.client.fetchRegistry: false

Otherwise list of other EurekaServers should be set:
- eureka.client.serviceUrl.defaultZone: http://server:port/eureka, ...

**2 APPROACHES TO CONFIGURE EUREKA AND CONFIG SERVER:**
- Config First Bootstrap (default) - Use Config Server to configure location of Eureka server<br>
    - implies spring.cloud config.uri configured in each app
- Eureka First Bootstrap - User Eureka to expose location to config server <br>
	- config server is just another client
	- implies spring.cloud.config.discovery.enabled=true, eureka.client.serviceUrl.defaultZone=... configured in each app
	- client makes two network trips to obtain configuration

A vanilla Netflix Eureka instance is registered with an ID that is equal to its host name (that is, there is only one service per host). Spring Cloud Eureka
 provides a sensible default, which is defined as follows:<br>
 `${spring.cloud.client.hostname}:${spring.application.name}:${spring.application.instance_id:${server.port}}}`<br>
 
 **example:** `myhost:myappname:8080`
 
 By using Spring Cloud, you can override this value by providing a unique identifier in eureka.instance.instanceId.<br>
 Add following configuration to **_application.yml_**:
```
eureka:
  instance:
    instanceId: ${spring.application.name}:${vcap.application.instance_id:${spring.application.instance_id:${random.value}}}
```
With the metadata shown in the preceding example and multiple service instances deployed on localhost, the random value is inserted there to make the instance unique.
