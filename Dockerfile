FROM jomifred/jacamo:1.1
COPY ./ .
ENTRYPOINT ./gradlew run