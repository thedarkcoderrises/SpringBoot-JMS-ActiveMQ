package com.javasampleapproach.jms.client;

public interface JmsClient {
	public void send(String msg);
	public String receive();
}
