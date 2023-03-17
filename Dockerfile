FROM jomifred/jacamo:1.1
COPY ./ .
RUN ./gradlew compileJava
ENTRYPOINT ./gradlew run