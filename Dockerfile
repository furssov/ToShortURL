FROM bellsoft/liberica-openjdk-alpine
COPY ShortUrl-0.0.1-SNAPSHOT.jar /shortUrl.jar
CMD ["java", "-jar", "/shortUrl.jar"]