FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD target/hospital-drug-0.0.1.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar","--spring.profiles.active=pro"]