package cn.solarcat.message;

import javax.jms.Queue;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

import cn.solarcat.common.configuration.ActiveMQConfiguration;

@Configuration
public class JmsConfig {

	public String QUEUE = ActiveMQConfiguration.ITEM_ADD_TOPIC;

	public String TOPIC = ActiveMQConfiguration.ITEM_ADD_TOPIC;

	@Bean("queue")
	public Queue queue() {
		return new ActiveMQQueue(QUEUE);
	}

	@Bean("topic")
	public Topic topic() {
		return new ActiveMQTopic(TOPIC);
	}

	/**
	 * topic模式的ListenerContainer
	 * 
	 * @return
	 */
	@Bean
	public JmsListenerContainerFactory<?> topicListenerFactory() {
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		ActiveMQConnectionFactory connectionFactor = new ActiveMQConnectionFactory();
		factory.setPubSubDomain(true);
		factory.setConnectionFactory(connectionFactor);
		return factory;
	}

	/**
	 * queue模式的ListenerContainer
	 * 
	 * @return
	 */
	@Bean
	public JmsListenerContainerFactory<?> queueListenerFactory() {
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		ActiveMQConnectionFactory connectionFactor = new ActiveMQConnectionFactory();
		factory.setPubSubDomain(false);
		factory.setConnectionFactory(connectionFactor);
		return factory;
	}
}
