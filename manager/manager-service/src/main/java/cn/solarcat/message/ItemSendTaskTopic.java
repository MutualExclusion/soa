package cn.solarcat.message;

import javax.jms.Topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class ItemSendTaskTopic {
	@Autowired
	private JmsTemplate jmsTemplate;

	@Autowired
	private Topic topic;

	public ItemSendTaskTopic() {

	}

	@Async("MessageExecutor")
	public void SendTopic(String content) throws Exception {
		jmsTemplate.convertAndSend(topic, content);
	}

}