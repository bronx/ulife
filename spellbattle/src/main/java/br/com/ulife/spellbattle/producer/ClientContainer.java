package br.com.ulife.spellbattle.producer;

import java.util.concurrent.BlockingQueue;

import com.twitter.hbc.core.Client;

public class ClientContainer {

	private final Client client;
	private final BlockingQueue<String> queue;
	
	/**
	 * CDI eyes
	 */
	public ClientContainer() {
		client = null;
		queue = null;
	}
	
	public ClientContainer(Client client, BlockingQueue<String> queue) {
		this.client = client;
		this.queue = queue;
	}
	
	public Client getClient() {
		return client;
	}
	
	public BlockingQueue<String> getQueue() {
		return queue;
	}
	
}
