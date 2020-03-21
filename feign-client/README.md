### FEIGN CLIENT

**What is it?**
- Declarative REST client, from Netflix
- Allows you to write calls to REST services with no implementation code
- Alternative to RestTemplate
- Spring Cloud provides easy wrapper for using Feign

_**Spring REST Template**_
- Spring's Rest Template provides very easy way to call REST services

![rest-template](https://raw.githubusercontent.com/Shtramak/spring-cloud-training/master/images/rest-template.jpg)

- Still, this code must be
    - Written
    - Unit-tested with mocks/stubs

The second parameter of the getForObject() method is the type that we expect to receive with that GET request.
The Template will instantiate an object of this class and populate it based on the returned result. The result
could be a JSON or XML or any other format that can be interpreted by an external or internal library to convert
it to object. Spring Template will do this conversion automatically.

_**Spring Cloud Feign**_

Feign Alternative - Declarative Web Service Clients.

**How does it work?**
- Define interfaces for your REST client code
- Annotate interface with Feign annotation
- Annotate methods with Spring MVC annotations
    - Other implementations like JAX/RS pluggable<br>
    
**Spring Cloud will implement it at run-time**
- Scan for interfaces
- Automatically implements code to call REST service and process response

Example of how Feign Client should look like

![feign-client](https://raw.githubusercontent.com/Shtramak/spring-cloud-training/master/images/feign-client.jpg)

To enable Feign functionality **@EnableFeignClients** annotations should be added. And when the application starts up
Feign libraries will see the annotations and provide runtime implementation of exactly what was described in 
FeignClient interface.

**_Ribbon and Eureka_**
- The previous example - hard-coded URL:
    - `@FeignClient(url="localhost:8080/warehouse")`
- ... use an Eureka "Client ID" instead:
    - `@FeignClient("WAREHOUSE") //PAY ATTENTION ON UPPERCASE`
- Ribbon is automatically enabled
    - Eureka gives our application all "Clients" that match the given Client ID
    - Ribbon automatically applies load balancing
    - Feign handles the code

Feign starter require a runtime (not compile time) dependency:
```
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-feign</artifactId>
</dependency>
```

**_Summary_**
- Feign provides a very easy way to call RESTful services
- Feign integrates with Ribbon and Eureka automatically

**P.S. Name of a Feign Client must be uppercase, otherwise Ribbon wont work properly and additionally** `appName.ribbon.listOfServers` **should be set**
