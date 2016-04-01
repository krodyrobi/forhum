FROM java:7

ENV CATALINA_HOME /usr/local/tomcat
ENV PATH $CATALINA_HOME/bin:$PATH
RUN mkdir -p "$CATALINA_HOME"
WORKDIR $CATALINA_HOME

COPY docker/tomcat.tar.gz tomcat.tar.gz
COPY docker/entry.sh entry.sh

RUN set -x \
  && tar -xvf tomcat.tar.gz --strip-components=1 \
	&& rm bin/*.bat \
	&& rm tomcat.tar.gz*

EXPOSE 8080

# foruhm should be mounted as a host volume
VOLUME /app
VOLUME /db_data

CMD ./entry.sh

