<%@page contentType="text/html" pageEncoding="UTF-8" %>
<html>

<head>
    <meta charset="UTF-8">
    <title>weather forecast</title>
</head>

<body>
<h4>Weather Forecast Processor (JMS & WebSockets)</h4>
The most recent weather forecast:<br/>

<span id="weatherForecast"></span>
<script type="text/javascript">
    var uri = "ws://localhost:8080/weather-forecast-processor/weather";
    var ws = new WebSocket(uri);

    ws.onmessage = function (event) {
        var mySpan = document.getElementById("weatherForecast");
        mySpan.innerHTML = event.data;
    };

    ws.onerror = function (event) {
        console.log("Smth. went wrong: ", event)
    };

    function closeConnection() {
        ws.close();
    }
</script>

</body>
</html>