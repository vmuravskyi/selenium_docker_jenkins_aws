FROM bellsoft/liberica-openjdk-alpine:21-cds

# create workspace
WORKDIR /home/selenium-docker

# Add the required files
COPY target/docker-resources ./

# Environment variables
# BROWSER
# HUB_HOST
# TEST_SUITE
# THREAD_COUNT

# Run the tests
ENTRYPOINT java -cp 'libs/*' \
    -Dselenium.grid.enabled=true \
    -Dselenium.grid.hubHost=${HUB_HOST} \
    -Dbrowser=${BROWSER} \
    org.testng.TestNG \
    -threadcount ${THREAD_COUNT} \
    ./test-suits/${TEST_SUITE}

# docker build -t=muravskyi/test-automation .
# docker run -e BROWSER=chrome -e HUB_HOST=192.168.0.21 -e TEST_SUITE=flight-reservation.xml -e THREAD_COUNT=4 muravskyi/test-automation