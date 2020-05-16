#### API GATEWAY

Fallacies of Distributed Computing - you can't assume a fast secure network.

![microservices-via-web](https://raw.githubusercontent.com/Shtramak/spring-cloud-training/master/images/accessing-microservices-via-web.jpg)

API Gateway provides simplified access for client:
- Custom API (customized specifically for what the client needs)
- Security (single place in the architecture where security can be applied at least fo authentication)
- No Cross-Origin Resource Sharing (CORS) since we have only one domain on the backend 
- Fewer Trips (reduce number of individual backend calls into one call)
- Have ability to implement caching in the API Gateway if we want to
- etc.

![api-gateway](https://raw.githubusercontent.com/Shtramak/spring-cloud-training/master/images/api-gateway.jpg)

Number of API Gateway depends in large part on how similar or dissimilar are client needs. In case when needs are identical then one API Gateway is enough,
otherwise, multiple API Gateways might be used.

So, the API Gateway is built to address the remote client needs. It's a one stop shop for the client to access.

- It serves as a facade to the real API
- It reduces the number of calls and i can reduce the data carried back in the calls to the client. It can remove data that's not needed
- In simple cases it simply routes to the real backend services
- It can handle caching, so it can reduce number of service calls
- It can handle protocol translation. That means that the backend Microservices might be set up to work with messaging protocols or TCP or some other
protocol other then something like HTTP. So, the API Gateway would take care of taking the clients HTTP request putting it into a message potentially
waiting for a reply message, and returning a response. Thus, the client application has no need to deal with any of the messaging API
- The API Gateway can also optimize calls to the backend API by expanding hyperlinks found within RESTful resources. If we're following RESTful principles
correctly, we're doing something that we call HATEOS (Hypertext as the engine of application state). That means that our microservices send link to
related resources. And the idea is it's up to the client to make the determination on whether it wants to follow the links or no. API Gateway can take care
of resolving these calls and in aligning the response. So the client gets just the data it needs with no wast or extra round trips.

Thus, API Gateway is a pretty key piece to the microservices architecture at least from the client's perspective.

Zuul from Netflix Zuul is another part of the Netflix family of open source software and Spring Cloud provides an easy way to use it.
One of its ability is to serve as a router and a load balancer.

Zuul - JVM-based router and Load Balancer:
- Can be used for many API Gateway needs
- Routing - send request to real server. 

Routing simply means that the client can make a request that goes to the API Gateway and the Gateway will immediately send that request to the real server
 that's responsible for it. This is also known as a reverse proxy as we are proxy end calls to the various backend services

![zuul-basic-usage](https://raw.githubusercontent.com/Shtramak/spring-cloud-training/master/images/zuul-basic-usage.jpg)

All we need to enable Zuul in Spring Cloud is to add @EnableZuulProxy annotation in configuration class.

And the default Zuul behavior is this:
- It registers itself with Eureka along with all of the other clients which of course implies that it is configured with the URL to Eureka and it collects
 all the client IDs. Client IDs are used to build URI to the actual backend services. And since Ribbon and Hystrix are automatically enabled we have all of
  their benefits (load balancing and circuit breaker)
- After it registers with Eureka and learns all of the client names it defines some good old fashioned Spring MVC handler mappings. All URL patterns are mapped 
to a special controller called the ZuulController. This controller is provided by the framework and the logic within it simply turns around and calls the
 actual Eureka identified service. 

![zuul-features](https://raw.githubusercontent.com/Shtramak/spring-cloud-training/master/images/zuul-features.jpg)
