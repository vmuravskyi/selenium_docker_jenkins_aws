FROM bellsoft/liberica-openjdk-alpine:21-cds

# install curl and jq
RUN apk add curl jq

# create workspace
WORKDIR /home/selenium-docker

# Add the required files
COPY target/docker-resources         ./
COPY run.sh                          run.sh

# Run the tests
ENTRYPOINT ["sh", "run.sh"]

# docker build -t=muravskyi/automation-tests .
# docker run -e BROWSER=chrome -e HUB_HOST=192.168.0.21 -e TEST_SUITE=flight-reservation.xml -e THREAD_COUNT=4 muravskyi/automation-tests