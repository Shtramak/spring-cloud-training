### Spring Cloud Hystrix

**The Problem: Cascading Failure**
- Having a large numbers of services as dependencies can lead to a "cascading failures"
- Without mitigating this, microservices are a recipe for certain disaster

![hystrix-cascading-failures](https://raw.githubusercontent.com/Shtramak/spring-cloud-training/master/images/hystrix-cascading-failure.jpg)

**_Hystrix - The Software Circuit Breaker_**
- Hystrix - Part of Netflix OSS
- Light, easy-to-use wrapper provided by Spring Cloud
- Detects failure conditions and "opens" to disallow a further calls
    - Hystrix Default - 20 failures in 5 seconds
- Identify "fallback" - what to do n case of a service dependency failure
    - Think: catch block, but more sophisticated
    - Fallbacks can be chained
- Automatically "closes" itself after interval
    - Hystrix Default - 5 seconds

**Hystrix (Spring Cloud) Setup**
- Add the Dependency
```
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-hystrix</artifactId>
</dependency>
```
- Enable Hystrix within a configuration class
```java
@SpringBootApplication
@EnableHystrix
public class Application{
}
```

Use the `@HystrixCommand` to wrap methods in a circuit breaker

![hystrix-command-example](https://raw.githubusercontent.com/Shtramak/spring-cloud-training/master/images/hystrix-command-example.jpg)

Even more fallback method also can have fallback method for the case if it fails, there's no limit for number of levels.

Failure / Recovery behavior highly customizable 

![hystrix-command-customization](https://raw.githubusercontent.com/Shtramak/spring-cloud-training/master/images/hystrix-command-customization.jpg)

**_Hystrix Dashboard_** - provides a built-in dashboard to check the status of the circuit breakers

**Hystrix Dashboard Setup**
- Add the additional Dependency, include actuator:
```
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-hystrix-dashboard</artifactId>
</dependency>
<dependency>
     <groupId>org.springframework.boot</groupId>
     <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```
- Enable Hystrix Dashboard within a configuration class:
```java
@EnableHystrix
@EnableHystrixDashboard
@SpringBootApplication
public class Application{
}
```

Finally add this property to application.* to allow Actuator to expose the Hystrix stream:
```
management: 
      endpoints: 
        web: 
          exposure: 
            include: 'hystrix.stream'
```

![hystrix-command-customization](https://raw.githubusercontent.com/Shtramak/spring-cloud-training/master/images/hystrix-dashboard.jpg)

![hystrix-command-customization](https://raw.githubusercontent.com/Shtramak/spring-cloud-training/master/images/hystrix-dashboard-access.jpg)

**Summary**
- Software circuit breakers protect against cascade failure
- Spring Cloud Netflix Hystrix provides an easy way to add circuit breakers to your applications
- You can use Hystrix Dashboard and Turbine to monitor your circuit breakers
