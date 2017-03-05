package com.neu.edu.wtp.utility;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionCounter implements HttpSessionListener{
	private List<String> sessions = new ArrayList<String>();
	public static final String COUNTER = "session-counter";

	@Override
	public void sessionCreated(HttpSessionEvent event) {
		// TODO Auto-generated method stub
//		System.out.println("SessionCounter.sessionCreated");
//		HttpSession session = event.getSession();
//		sessions.add(session.getId());
//		session.setAttribute(SessionCounter.COUNTER, this);

	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		// TODO Auto-generated method stub
//		System.out.println("SessionCounter.sessionDestroyed");
//		HttpSession session = event.getSession();
//		sessions.remove(session.getId());
//		session.setAttribute(SessionCounter.COUNTER, this);
	}

	public int getActiveSessionNumber() {
		return sessions.size();
	}
}
