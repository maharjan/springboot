For spring boot application console text banner generation:
https://devops.datenkollektiv.de/banner.txt/index.html

For Thymeleaf setup
https://github.com/eugenp/tutorials/tree/master/spring-thymeleaf

For Spring+Thymeleaf Details
http://www.thymeleaf.org/doc/tutorials/3.0/thymeleafspring.html

IMPORTANT:
- Project setup for IntelliJ
- Project on first run will create default admin & normal user login id (see Bootstrap)
- Application basic property config sample is in springboot_prop.yml. This file must be copied to apache tomcat conf folder.
- Build Commands:
  For Linux & IntelliJ base
  1) gradle wrapper
  2) ./gradlew clean cleanIdea idea
  3) ./gradlew build

- Log file is generated inside tomcat log folder and runtime log can be viewed on IDE console.