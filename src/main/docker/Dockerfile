FROM openjdk:8-jdk-alpine
RUN mkdir -p /usr/local/websocket
ADD  @project.build.finalName@.jar /usr/local/websocket/
ADD run.sh run.sh
RUN chmod +x run.sh
CMD ./run.sh