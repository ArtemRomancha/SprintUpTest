FROM openjdk:9
ADD target/spring-boot-site-project.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]