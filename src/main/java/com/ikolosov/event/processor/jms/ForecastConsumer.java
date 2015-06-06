package com.ikolosov.event.processor.jms;

import com.ikolosov.event.processor.persistence.WeatherStorage;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * @author ikolosov
 */
@MessageDriven(
		name = "ForecastConsumer",
		activationConfig = {
				@ActivationConfigProperty(
						propertyName = "destinationLookup",
						propertyValue = WeatherQueue.JNDI_NAME),
				@ActivationConfigProperty(
						propertyName = "destinationType",
						propertyValue = "javax.jms.Queue"),
		})
public class ForecastConsumer implements MessageListener {

	@Inject
	private WeatherStorage weatherStorage;

	@Override
	public void onMessage(Message forecast) {
		String msgText;
		try {
			if (forecast instanceof TextMessage) {
				msgText = ((TextMessage) forecast).getText();
				System.out.println("Weather forecast received: " + msgText);
				weatherStorage.addForecast(msgText);
			} else {
				System.out.println("Non-text weather forecast was received.");
			}
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
