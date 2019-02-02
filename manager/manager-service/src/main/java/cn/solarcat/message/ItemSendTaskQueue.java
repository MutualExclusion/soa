package cn.solarcat.message;

import javax.jms.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class ItemSendTaskQueue {
	@Autowired
	private JmsTemplate jmsTemplate;

	@Autowired
	private Queue queue;

	public ItemSendTaskQueue() {

	}

	@Async("MessageExecutor")
	public void SendSearch(String content) throws Exception {
		jmsTemplate.convertAndSend(queue, content);
	}

}