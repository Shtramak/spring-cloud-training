**Spring Cloud Config Client** is simply an application. Clients connect to Config server over HTTP and retrieve their configuration settings.

To create Config Server Client there's few steps:
- Include Spring Cloud Strarter for config:
```
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-config</artifactId>
</dependency>
```
- Configure application name and server location in **_bootstrap.properties_** or **_bootstrap.yml_**
```
spring:
  application:
    name: config-server-client
  cloud:
    config:
      uri: http://localhost:8002
```
Now Client connects at startup for additional configuration settings

**Configuration file naming convention:** <br>
`<spring.applicaltion.name>-<profile>.yml` <br>
`<spring.applicaltion.name>` is set by client application's bootstrap.yml <br>
`<profile>` - client's spring.profiles.active
	
**Obtain settings from server:** <br>
`http://<server>:<port>/<spring.application.name>/<profile>` <br>
Spring Config Server clients do this automagically on startup
