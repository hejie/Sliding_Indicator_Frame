package com.sohu.pp;

public class SlidingMenuEvent {
	
	private String eventName;

	public SlidingMenuEvent(String eventName) {
		super();
		this.eventName = eventName;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

}
