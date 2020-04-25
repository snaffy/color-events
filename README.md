## color-events 
###How to run ?  
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
###How to run performance tests?  

Performance of this microservice could be tested via separate application [color-events-performance-test](https://github.com/snaffy/color-events-performance-test)


