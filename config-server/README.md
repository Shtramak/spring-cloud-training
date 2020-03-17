**Spring Cloud Config** is simply an application that is designated as a centralized server that serves up configuration information.
Clients connect over HTTP and retrieve their configuration settings.

To create Config Server there's 3 steps:
- Create simple Spring Boot Application with dependencies:
```
<parent>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-parent</artifactId>
    <version>...</version>
</parent>
	
<dependencies>
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-config-server</artifactId>
    </dependency>
</dependencies>
```
or use
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
instead of `<parent>`
- Specify the place where configuration stored in **_application.yml_**
```
spring:
  cloud:
    config:
      server:
        git:
          uri: https://github.com/bla/bla/bla [https://github.com/Shtramak/spring-cloud-config-repo]
          searchPaths: ConfigData //subfolder (Optional)
```
- Add **@EnableConfigServer** on SpringBootApplication class

Spring Cloud Config Server uses an interface EnvironmentRepository to use other sources. 
In Spring Cloud there's 2 implementations for this interface: Git and Native(local files)
