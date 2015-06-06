package com.ikolosov.event.processor.jms;

import javax.jms.JMSDestinationDefinition;

/**
 * @author ikolosov
 */
@JMSDestinationDefinition(
		name = WeatherQueue.JNDI_NAME,
		interfaceName = "javax.jms.Queue"
)
public class WeatherQueue {

	public static final String JNDI_NAME = "java:global/jms/weatherQueue";
}
