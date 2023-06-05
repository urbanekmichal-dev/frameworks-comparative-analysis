# Tests

Example
mvn gatling:test -DbaseUrl=http://[address]:[port]/ -DdurationMin=[time] -DrequestPersecond=[quantity] -Dgatling.simulationClass=[simulation class]


### Notification
1. Single notification simulation
```
mvn gatling:test -DbaseUrl=http://localhost:8080/ -Dduration=5 -Dusers=500 -Dgatling.simulationClass=gatling.test.notifications.NotificationSimulation
```
2. Notification with delay simulation
```
mvn gatling:test -DbaseUrl=http://localhost:8080/ -Dduration=1 -Dusers=100 -Dgatling.simulationClass=gatling.test.notifications.NotificationWithDelaySimulation
```
3. Notification SSE simulation
```
mvn gatling:test -DbaseUrl=http://localhost:8080/ -Dduration=1 -Dusers=100 -Dgatling.simulationClass=gatling.test.notifications.NotificationSseSimulation
```
### CRUD
1. Create -> Update -> Delete
```
mvn gatling:test -DbaseUrl=http://localhost:8080/ -DdurationMin=1 -Dusers=200 -Dgatling.simulationClass=gatling.test.crud.CreateUpdateDeleteTaskSimulation 
```
2. Create -> Get -> Delete
```
mvn gatling:test -DbaseUrl=http://localhost:8080/ -DdurationMin=1 -Dusers=10 -Dgatling.simulationClass=gatling.test.example.notifications.NotificationSseSimulation
```
### SORTING
1. Quick-Sort
```
mvn gatling:test -DbaseUrl=http://localhost:8080/ -Dduration=5 -Dusers=50000 -Dgatling.simulationClass=gatling.test.sort.QuickSortSimulation
```
2. Merge-Sort
```
mvn gatling:test -DbaseUrl=http://localhost:8080/ -Dduration=1 -Dusers=500 -Dgatling.simulationClass=gatling.test.sort.MergeSortSimulation
```
3. Insertion-Sort
```
mvn gatling:test -DbaseUrl=http://localhost:8080/ -Dduration=1 -Dusers=1000 -Dgatling.simulationClass=gatling.test.sort.InsertionSortSimulation
```
4. Bubble-Sort
```
mvn gatling:test -DbaseUrl=http://localhost:8080/ -Dduration=1 -Dusers=1000 -Dgatling.simulationClass=gatling.test.sort.BubbleSortSimulation
```
  
### CRYPTO
1. Encrypt->Decrypt
```
mvn gatling:test -DbaseUrl=http://localhost:8080/ -Dduration=5 -Dusers=10 -Dgatling.simulationClass=gatling.test.crypto.EncryptDecryptSimulation
```

## Building

```
mvn clean package
```

## Running

Using the executable jar file (`run-simulation-using-jar.sh`):

```bash
JAVA_OPTS="-DbaseUrl=http://localhost:8080  -DdurationMin=1 -DrequestPerSecond=10"
SIMULATION_NAME=gatling.test.example.simulation.ExampleSimulation
java ${JAVA_OPTS} -cp target/gatling-java-example.jar io.gatling.app.Gatling -s "${SIMULATION_NAME}"
```

Using the Gatling Maven plugin (`run-simulation-using-plugin.sh`):

```bash
mvn test -Pperf-test
```

Using the Docker container (`run-simulation-using-docker.sh`):

```bash
docker build -t gatling-java-example:latest .
docker run -e "JAVA_OPTS=-DbaseUrl=http://some-target-host:8080 -DdurationMin=1 -DrequestPerSecond=10" \
-e SIMULATION_NAME=gatling.test.example.simulation.ExampleSimulation gatling-java-example:latest
```

## Working With Makefile

Build executable jar and Docker image

```
make dist image
```

Run Docker image:

```
make run
```