FROM jomifred/jacamo:1.2
COPY ./ .
ENTRYPOINT ./gradlew run