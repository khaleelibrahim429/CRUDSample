FROM openjdk:17-alpine
EXPOSE 8080
ADD target/doctor-patient-crud.jar doctor-patient-crud.jar
ENTRYPOINT ["java", "-jar","doctor-patient-crud.jar"]