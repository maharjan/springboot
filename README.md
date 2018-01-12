For spring boot application console text banner generation:
[Banner Generation](https://devops.datenkollektiv.de/banner.txt/index.html)

For Thymeleaf setup
[Thymeleaf](https://github.com/eugenp/tutorials/tree/master/spring-thymeleaf)

For Spring+Thymeleaf Details
[Spring + Thymeleaf](http://www.thymeleaf.org/doc/tutorials/3.0/thymeleafspring.html)

For in-depth Spring Boot Property configuration
[Spring Boot](https://docs.spring.io/spring-boot/docs/1.4.x/reference/html/common-application-properties.html)

**IMPORTANT**:
- Project setup for IntelliJ
- Project on first run will create default admin & normal user login id (see Bootstrap)
- Application basic property config sample is in springboot_prop.yml. This file must be copied to apache tomcat conf folder.
- Build Commands:
  For Linux & IntelliJ base
  1) gradle wrapper
  2) ./gradlew clean cleanIdea idea
  3) ./gradlew build

- Profile based configuration, such that under 'dev' profile the H2 database will be used and swagger is available.
  While under 'prod' MySQL is used and swagger is obstructed.
  Similarly, log level, pool, session timeout are configured differently for different profiles
- Log file is generated inside tomcat log folder and runtime log can be viewed on IDE console.
- For API documentation : http://localhost:8080/swagger-ui.html
- For HTTP request, SQL queries, Threads status etc : http://localhost:8080/monitoring

Project Setup:-
git clone https://github.com/maharjan/springboot.git
cd springboot/
gradle wrapper
./gradlew clean cleanIdea idea
./gradlew build
