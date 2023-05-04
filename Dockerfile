FROM openjdk:11
ADD target/carsale-backend.jar carsale-backend.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "carsale-backend.jar"]
