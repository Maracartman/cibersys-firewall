FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD target/cfauth.jar cfauth.jar
ENV JAVA_OPTS=""
ENV SPRING_PROFILE="development"
EXPOSE 5655
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /cfauth.jar  --spring.profiles.active=$SPRING_PROFILE" ]
