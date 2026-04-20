FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY build/libs/toyvillage.jar toyvillage.jar

ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "toyvillage.jar"]