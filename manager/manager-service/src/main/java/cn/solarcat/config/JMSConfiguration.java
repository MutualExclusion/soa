package cn.solarcat.config;

import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JMSConfiguration {
	/**
	 * topic
	 */
	public static final String TOPIC_MESSAGE = "itemAddTopic";

	@Bean
	public ActiveMQTopic activeMQTopic() {
		return new ActiveMQTopic(TOPIC_MESSAGE);
	}

}
