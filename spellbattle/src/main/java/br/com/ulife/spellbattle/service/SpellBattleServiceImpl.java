package br.com.ulife.spellbattle.service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import javax.inject.Inject;

import br.com.ulife.spellbattle.conf.BH;
import br.com.ulife.spellbattle.conf.Rio;
import br.com.ulife.spellbattle.producer.ClientContainer;
import br.com.ulife.spellbattle.resource.BattleDuration;

import com.twitter.hbc.core.Client;

public class SpellBattleServiceImpl implements SpellBattleService {

	@Inject @Rio private ClientContainer rio;
	@Inject @BH private ClientContainer bh;	
	
	@Override
	public Map<String, Object> battle(BattleDuration duration) {

		Client rioClient = rio.getClient();
		Client bhClient = bh.getClient();
		
		//Start reading the streamings
		rioClient.connect();
		bhClient.connect();

		final Long stopTime = System.currentTimeMillis() + duration.getTime() * 1000;
		
		final AtomicInteger totalRio = new AtomicInteger();
		final AtomicInteger totalBH = new AtomicInteger();
		
		Thread rioConsumer = new Thread(new QueueConsumer(totalRio, rio.getClient(), rio.getQueue(), stopTime), "rio=consumer");
		Thread bhConsumer = new Thread(new QueueConsumer(totalBH, bh.getClient(), bh.getQueue(), stopTime), "bh-consumer");
		
		//Start consuming the streaming messages
		rioConsumer.start();
		bhConsumer.start();
		
		try {
			//Wait until both consumers are done...
			rioConsumer.join();
			bhConsumer.join();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		
		Map<String, Object> result = new HashMap<>();
		
		result.put("rio", totalRio.get());
		result.put("bh", totalBH.get());
		result.put("winner", totalRio.get() < totalBH.get() ? "Rio de Janeiro" :
			totalBH.get() < totalRio.get() ? "Belo Horizonte" : "EMPATE");
		
		return result;
	}

}
