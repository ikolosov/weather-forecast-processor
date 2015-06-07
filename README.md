### Weather Forecast Processor
This application has to demonstrate such Java EE 7 facilities as WebSockets, JMS and EJB being used together. 
JMS role is to participate in message generating, sending and receiving.
EJB handles message consumption (MDB) and makes the entire process periodic (task scheduling).
WebSockets take care of concurrent client-side live updates - all the clients obtain the updates simultaneously.        

### Technology Stack
* JDK 8
* Apache Maven v.3.2
* Java EE 7.0 (WebSockets, JMS, EJB)
* WildFly Application Server v.8.2

### Build Instructions
Invoke the following maven command from the app root dir:

`mvn clean package`

Examine build log, make sure build was successful:

`[INFO] BUILD SUCCESS`

### Launch Instructions
Once the app is assembled, deploy weather-forecast-processor.war to WildFly server.

The app is build around two schedulers:

`a. WeatherWebSocket class - websocket endpoint and its client ws pair which is initialized inside js script delivered by index.jsp;`

`b. ForecastProducer (sends JMS messages periodically) and ForecastConsumer (MDB that consumes them) classes.`

Access the app in several different browser windows and just watch them out - you should be receiving the latest weather forecast updates in all your clients at once.