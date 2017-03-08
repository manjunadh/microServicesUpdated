package se.callista.microservices.core.product.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hazelcast.config.Config;
import com.hazelcast.config.EvictionPolicy;
import com.hazelcast.config.MapConfig;

import se.callista.microservices.core.product.service.ProductService;

@Configuration
public class HazelcastConfiguration {
	private static final int RECEICED_MESSAGES_TRACK_TTL_SECS = 60 * 60;

	@Bean
	public Config config() {
		return new Config().addMapConfig(
				// Set up TTL for the Map tracking received Messages IDs
				new MapConfig().setName(ProductService.productName)
						.setEvictionPolicy(EvictionPolicy.LRU).setTimeToLiveSeconds(RECEICED_MESSAGES_TRACK_TTL_SECS));

	}
}
