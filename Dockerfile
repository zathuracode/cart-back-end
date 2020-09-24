FROM openjdk:14
VOLUME /tmp
EXPOSE 9090
COPY cart-back-end-0.0.1-SNAPSHOT.jar /cart-back-end-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","cart-back-end-0.0.1-SNAPSHOT.jar"]