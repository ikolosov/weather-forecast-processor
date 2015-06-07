### Weather Forecast Processor
This application has to demonstrate Java EE 7 capabilities - WebSockets, JMS and EJB being used together. 
Weather Forecast Processor is a sample app the 

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

`a. WeatherWebSocket class - websocket endpoint and its client ws pair which is initialized inside index.jsp;`

`b. ForecastProducer (sends JMS messages) and ForecastConsumer (MDB that consumes them) classes.`

Access the app in several different browser windows and just watch them out - you should get the latest weather forecast displayed in all your clients at once.
