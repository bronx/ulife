package br.com.ulife.spellbattle.producer;

import java.util.Arrays;
import java.util.concurrent.LinkedBlockingQueue;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import br.com.caelum.vraptor.environment.Environment;
import br.com.ulife.spellbattle.conf.BH;
import br.com.ulife.spellbattle.conf.Rio;

import com.twitter.hbc.ClientBuilder;
import com.twitter.hbc.core.Constants;
import com.twitter.hbc.core.HttpHosts;
import com.twitter.hbc.core.endpoint.Location;
import com.twitter.hbc.core.endpoint.Location.Coordinate;
import com.twitter.hbc.core.endpoint.StatusesFilterEndpoint;
import com.twitter.hbc.core.endpoint.StreamingEndpoint;
import com.twitter.hbc.core.processor.StringDelimitedProcessor;
import com.twitter.hbc.httpclient.auth.Authentication;
import com.twitter.hbc.httpclient.auth.OAuth1;

public class ContainerProducer {

	@Inject private Environment env;
	
	@Produces @Rio @RequestScoped
	public ClientContainer getRioClient() {
		return getClientContainer("rio",
				new Location(
						new Coordinate(
								Double.valueOf(env.get("rio.sw.long")), 
								Double.valueOf(env.get("rio.sw.lat"))
							), 
						new Coordinate(
								Double.valueOf(env.get("rio.ne.long")), 
								Double.valueOf(env.get("rio.ne.lat"))
							)
					)
				);
	}
	
	@Produces @BH @RequestScoped
	public ClientContainer getBHClient() {
		return getClientContainer("bh",
				new Location(
						new Coordinate(
								Double.valueOf(env.get("bh.sw.long")), 
								Double.valueOf(env.get("bh.sw.lat"))
							), 
						new Coordinate(
								Double.valueOf(env.get("bh.ne.long")), 
								Double.valueOf(env.get("bh.ne.lat"))
							)
					)
				);
	}	
	
	private ClientContainer getClientContainer(String name, Location location) {
		
		LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<String>();		
		
		ClientBuilder builder = new ClientBuilder()
		  .name("ulife-twitter-client-" + name)
		  .hosts(new HttpHosts(Constants.STREAM_HOST))
		  .authentication(getAuth())
		  .endpoint(getEndpoint(location))
		  .processor(new StringDelimitedProcessor(queue));
		
		return new ClientContainer(builder.build(), queue);
		

	}

	private StreamingEndpoint getEndpoint(Location location) {
		return new StatusesFilterEndpoint().locations(Arrays.asList(location));
	}

	private Authentication getAuth() {
		return new OAuth1(
				System.getProperty("consumer.key"), 
				System.getProperty("consumer.secret"), 
				System.getProperty("oauth.token"), 
				System.getProperty("oauth.secret")
			);
	}

	
}
