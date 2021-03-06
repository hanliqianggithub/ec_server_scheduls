FROM daocloud.io/brave8/maven-jdk8

ENV PINPOINT_VERSION=1.6.2

ADD configure.sh /usr/local/bin/

ADD schedels-0.0.1-SNAPSHOT.jar /app.jar

RUN chmod a+x /usr/local/bin/configure.sh \
        && mkdir -p /assets/pinpoint-agent \
        && curl -SL https://raw.githubusercontent.com/naver/pinpoint/$PINPOINT_VERSION/agent/src/main/resources-release/pinpoint.config -o /assets/pinpoint.config \
        && curl -SL https://github.com/naver/pinpoint/releases/download/$PINPOINT_VERSION/pinpoint-agent-$PINPOINT_VERSION.tar.gz -o pinpoint-agent-$PINPOINT_VERSION.tar.gz \
        && gunzip pinpoint-agent-$PINPOINT_VERSION.tar.gz \
        && tar -xf pinpoint-agent-$PINPOINT_VERSION.tar -C /assets/pinpoint-agent \
        && curl -SL https://raw.githubusercontent.com/naver/pinpoint/$PINPOINT_VERSION/agent/src/main/resources-release/lib/log4j.xml -o /assets/pinpoint-agent/lib/log4j.xml \
        && sed -i 's/DEBUG/INFO/' /assets/pinpoint-agent/lib/log4j.xml \
        && rm pinpoint-agent-$PINPOINT_VERSION.tar 

EXPOSE 8080
ENTRYPOINT ["/usr/local/bin/configure.sh"]