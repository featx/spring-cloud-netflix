FROM java:8-jre-alpine

ENV LANG=en_US.utf8 \
    SERVICE_HOME=/home/featx \
    SPRING_PROFILES_ACTIVE=default \
    TZ=Asia/Shanghai

#   SW_AGENT_NAME=service-provider \
#   SW_AGENT_COLLECTOR_BACKEND_SERVICES=172.17.0.6:11800
#   -javaagent:/mnt/local/apache.com/skywalking/agent/skywalking-agent.jar
COPY build/libs/service-provider-1.0.0-SNAPSHOT.jar ${SERVICE_HOME}/

WORKDIR $SERVICE_HOME

RUN set -ex && \
  mkdir log && \
  apk add -U tzdata && \
  cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && \
  date && \
  apk del tzdata

EXPOSE 8001
# Remote Debug Usage: Expose the debug port to host, And add the agent JOPTS to java CMD
EXPOSE 5005
# "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005",
CMD ["java", "-Xms512m", "-Xmx512m", "-XX:MetaspaceSize=128m", "-XX:MaxMetaspaceSize=256m","-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005", "-jar", "service-provider-1.0.0-SNAPSHOT.jar", ">", "log/spring-cloud-netflix.log"]