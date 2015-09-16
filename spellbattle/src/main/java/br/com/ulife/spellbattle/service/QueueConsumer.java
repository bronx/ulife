package br.com.ulife.spellbattle.service;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

import br.com.ulife.spellbattle.resource.Tweet;
import br.com.ulife.spellbattle.spellcheck.SpellChecker;

import com.google.gson.Gson;
import com.twitter.hbc.core.Client;

public class QueueConsumer implements Runnable {

	private final SpellChecker checker;
	private final AtomicInteger count;
	private final Client client;
	private final BlockingQueue<String> queue;
	private final Long stopTime;

	public QueueConsumer(final AtomicInteger count, 
			final Client client, final BlockingQueue<String> queue, final Long stopTime) {
		this.checker = new SpellChecker();
		this.count = count;
		this.client = client;
		this.queue = queue;
		this.stopTime = stopTime;
	}

	@Override
	public void run() {
		while(System.currentTimeMillis() < stopTime) {
			try {
				Tweet tweet = new Gson().fromJson(queue.take(), Tweet.class);
				count.addAndGet(checker.countMispelledWordsIn(tweet.getText()));
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
		//Stop streaming
		client.stop();
	}
	
}
