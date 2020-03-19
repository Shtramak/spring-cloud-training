####LOAD BALANCING (SPRING CLOUD RIBBON)
Traditional load balancers are **Server-Side Load Balancers**:
- Distribute incoming traffic among several servers
- Software (Apache, Nginx, HA Proxy) or Hardware (F5, NSX, BigIP)

![server-load-balancer](https://raw.githubusercontent.com/Shtramak/spring-cloud-training/master/images/server-load-balancer.jpg)

**Client-Side Load Balancer:**
- Based on some criteria (round robin)
- Part of client software
- Server can still employ its own load balancer

![client-load-balancer](https://raw.githubusercontent.com/Shtramak/spring-cloud-training/master/images/client-load-balancer.jpg)

**Why we need Client-Side Load Balancer?**

Not all servers are the same:
- Some may be unavailable (faults)
- Some may be slower than others (performance)
- Some may be further away than others (regions)

**Netflix Ribbon** - another part of the Netflix OSS family:
- client side load balancer
- automatically integrates with service discovery (Eureka)
- built in failure resiliency (Hystrix)
- caching / batching
- multiple protocols (HTTP, TCP, UDP)

**Spring Cloud** provides an easy API Wrapper for using Ribbon

Key Ribbon Concepts:
- List of Servers (determines what the list of possible servers are for a given service/client)
    - Static - populated via configuration
    - Dynamic - populated via Service Discovery
- Filtered List of Servers
    - Criteria by which you wish to limit the total list
    - Sping Cloud default - Filter servers in the same _zone_
- Load Balancer
    - The Load Balancer is the actual component that routes the calls to the servers in the filtered list
    - Spring Clouds Default - ZoneAwareLoadBalancer
- Ping
    - Used to test if the server is up or down
    - Spring Cloud default - delegate to Eureka to determine if server is up or down (heartbeats)
```
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-ribbon</artifactId>
</dependency>
```

**Summary:**
- Client-Side Load Balancing augments regular load balancing by allowing the client to select a server based on some criteria
- Spring Cloud Ribbon is an easy-to-use implementation of client side balancing.
