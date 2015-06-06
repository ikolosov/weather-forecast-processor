package com.ikolosov.event.processor.websocket;

import com.ikolosov.event.processor.persistence.WeatherStorage;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author ikolosov
 */
@ServerEndpoint("/weather")
@Singleton
public class WeatherWebSocket {

	private static final AtomicBoolean activated = new AtomicBoolean();
	private static final Set<Session> sessions = Collections.newSetFromMap(new ConcurrentHashMap<>());

	@Inject
	private WeatherStorage weatherStorage;

	@OnOpen
	private void onSessionOpen(Session session) {
		if (!activated.get())
			activated.set(true);
		sessions.clear();
		sessions.addAll(session.getOpenSessions());
	}

	/**
	 * This method will be invoked every second.
	 */
	@Schedule(second = "*/1", minute = "*", hour = "*")
	private void pushUpdates() throws IOException {
		if (activated.get() && !sessions.isEmpty()) {
			String forecast = weatherStorage.getLatestForecast();
			if (forecast != null)
				for (Session aSession : sessions)
					if (aSession.isOpen())
						aSession.getBasicRemote().sendText(forecast);
		}
	}
}
