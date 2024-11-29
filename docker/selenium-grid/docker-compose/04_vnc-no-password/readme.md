# Running Tests

### TestNG parameters

- Running a test suite

`java -cp 'libs/*' org.testng.TestNG test-suites/flight-reservation.xml`

- Running a test suite with specific thread count

`java -cp 'libs/*' org.testng.TestNG -threadcount 2 test-suites/flight-reservation.xml`

- TestNG by default creates **test-output** directory. You can change it with **-d** option.

`java -cp 'libs/*' org.testng.TestNG -threadcount 2 -d result test-suites/flight-reservation.xml`

---

### System Properties

- To pass the browser option

`java -Dbrowser=chrome -cp 'libs/*' org.testng.TestNG test-suites/flight-reservation.xml`

- To run the tests using Selenium Grid

`java -Dselenium.grid.enabled=true -Dselenium.grid.hubHost=localhost -cp 'libs/*' org.testng.TestNG test-suites/flight-reservation.xml`

- To run the tests using Selenium Grid with specific thread count

`java -Dselenium.grid.enabled=true -Dselenium.grid.hubHost=localhost -cp 'libs/*' org.testng.TestNG test-suites/flight-reservation.xml -threadcount 2`

- To run docker container with volume mapping

`docker run -it -v ${PWD}/result:/home/selenium-docker/test-output vmuravskyi/automation`

- To run tests inside docker container sending selenium hub ip as option (hub running in another container with forwarded 4444 port to localhost)

`java -Dselenium.grid.enabled=true -Dselenium.grid.hubHost=192.168.0.21 -cp 'libs/*' org.testng.TestNG ./test-suits/vendor-portal.xml`