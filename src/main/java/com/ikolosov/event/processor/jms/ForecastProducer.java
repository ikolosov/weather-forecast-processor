package com.ikolosov.event.processor.jms;

import javax.annotation.Resource;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Queue;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author ikolosov
 */
@Stateless
public class ForecastProducer {

	private static final List<String> weatherForecasts = new ArrayList<String>() {{
		add("A deadly rainfall may hit the region.");
		add("Reported tornado will strike the coastline.");
		add("Severe storms are possible in the northern plains.");
		add("The risk of flash flooding will also persist.");
		add("Rain showers will be developing during the evening hours.");
	}};

	@Inject
	private JMSContext jmsContext;

	@Resource(mappedName = WeatherQueue.JNDI_NAME)
	private Queue jmsQueue;

	/**
	 * This method will be invoked every three seconds.
	 */
	@Schedule(second = "*/3", minute = "*", hour = "*")
	private void produce() {
		String message = weatherForecasts.get(new Random().nextInt(5));
		jmsContext.createProducer().send(jmsQueue, message);
		System.out.println("Weather forecast produced: " + message);
	}
}
