## color-events 
### How to run ?  
To properly run __color-events__ microservices as well as to test it, __RabbitMQ__ is necessary. It should be run via docker image.
To do so, execute the following command: 

```shell script
docker run --rm -it -p 5672:5672 -p 15672:15672 rabbitmq:3.7.11-management
``` 

With __RabbitMQ__ running, we can run: 
1) unit/functional tests: 
```shell script
./gradlew clean test
```
2) application: 
```shell script
./gradlew build 
./gradlew run
```
### How to run performance tests?  

Performance of this microservice could be tested via separate application [color-events-performance-test](https://github.com/snaffy/color-events-performance-test)

To perform such a test, run the __color-events__ microservice and then run tests from the project: __color-events-performance-test__ via command:
``
./gradlew clean test
``
Results from executed tests will be placed in the __/target__ directory

Assuming that the performance testing framework does not have to be strictly Java-based, a simpler tool will be [Siege] (https://github.com/JoeDog/siege), it can also be used as a docker container https://hub.docker.com/r/yokogawa/siege/:

Sample performance tests:
```
docker run yokogawa/siege -c 5 -r 1 "http://host.docker.internal:8080/colors/publish POST [{\"publish\": true,\"color\": \"255,0,0\"}]" --content-type "application/json"

docker run yokogawa/siege -c 20 -r 10 "http://host.docker.internal:8080/colors/publish POST [{\"publish\": true,\"color\": \"255,0,0\"}]" --content-type "application/json"

docker run yokogawa/siege -c 20 -r 5 "http://host.docker.internal:8080/colors/publish POST [{\"publish\": true,\"color\": \"255,0,0\"},{\"publish\": true,\"color\": \"0,255,0\"},{\"publish\": true,\"color\": \"0,0,255\"},{\"publish\": true,\"color\": null}]" --content-type "application/json"
```

Sample result:
```
docker run yokogawa/siege -c 20 -r 5 "http://host.docker.internal:8080/colors/publish POST [{\"publish\": true,\"color\": \"255,0,0\"},{\"publish\": true,\"color\": \"0,255,0\"},{\"publish\": true,\"color\": \"0,0,255\"},{\"publish\": true,\"color\": null}]" --content-type "application/json"
New configuration template added to /root/.siege
Run siege -C to view the current settings in that file
** SIEGE 4.0.4
** Preparing 20 concurrent users for battle.
The server is now under siege...
Transactions:                    100 hits
Availability:                 100.00 %
Elapsed time:                   0.30 secs
Data transferred:               0.00 MB
Response time:                  0.05 secs
Transaction rate:             333.33 trans/sec
Throughput:                     0.01 MB/sec
Concurrency:                   17.73
Successful transactions:         100
Failed transactions:               0
Longest transaction:            0.08
Shortest transaction:           0.03

docker run yokogawa/siege -c 20 -r 5  "http://host.docker.internal:8080/colors/publish POST [{\"publish\": true,\"color\": null}]" --content-type "application/json"
New configuration template added to /root/.siege
Run siege -C to view the current settings in that file
** SIEGE 4.0.4
** Preparing 20 concurrent users for battle.
The server is now under siege...
Transactions:                    100 hits
Availability:                 100.00 %
Elapsed time:                   0.24 secs
Data transferred:               0.00 MB
Response time:                  0.04 secs
Transaction rate:             416.67 trans/sec
Throughput:                     0.01 MB/sec
Concurrency:                   18.08
Successful transactions:         100
Failed transactions:               0
Longest transaction:            0.06
Shortest transaction:           0.03
```
