####Service Discovery (Spring Cloud Eureka Client)
It's just a regular Spring Boot web application with dependencies and **@EnableDiscoveryClient** that will turn on Eureka functionality

To register Eureka Client we need:
- The same dependency as for Eureka Server and **@EnableDiscoveryClient** annotation <br>
_@EnableDiscoveryClient mor preferable than @EnableEurekaDiscovery because it left open what technology is used as Discovery server_

- Add property to application.properties/yml <br>
`eureka.client.serviceUrl.defaultZone: http://server:port/eureka/` (here might be list of eureka servers)

**@EnableDiscoveryClient provides:**
- Automatically registers client with Eureka Server
	- registers the application name, host and port using values from the Spring Environment but can be overridden
	- gives a spring.application.name
- Makes this app an "instance" and a "client"
