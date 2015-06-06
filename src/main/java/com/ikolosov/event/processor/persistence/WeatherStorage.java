package com.ikolosov.event.processor.persistence;

import javax.ejb.Singleton;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author ikolosov
 */
@Singleton
public class WeatherStorage {

	private final BlockingQueue<String> forecasts = new LinkedBlockingQueue<>();

	public void addForecast(String forecast) {
		forecasts.offer(forecast);
	}

	public String getLatestForecast() {
		return forecasts.poll();
	}
}
