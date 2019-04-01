package cn.solarcat.item.listener;

import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

import javax.jms.Message;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import com.alibaba.dubbo.config.annotation.Reference;

import cn.solarcat.common.configuration.ActiveMQConfiguration;
import cn.solarcat.common.configuration.ItemConfiguration;
import cn.solarcat.item.pojo.Item;
import cn.solarcat.pojo.TbItem;
import cn.solarcat.pojo.TbItemDesc;
import cn.solarcat.service.ItemService;

@Component
public class HtmlGenListener {

	private static final Logger logger = LoggerFactory.getLogger(HtmlGenListener.class);
	@Reference(retries = 3)
	private ItemService itemService;

	@JmsListener(destination = ActiveMQConfiguration.ITEM_ADD_TOPIC)
	public void onMessage(Message message) {
		try {
			ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
			resolver.setPrefix("templates/");// 模板所在目录，相对于当前classloader的classpath。
			resolver.setSuffix(".html");// 模板文件后缀
			TemplateEngine templateEngine = new TemplateEngine();
			templateEngine.setTemplateResolver(resolver);

			// 构造上下文(Model)
			Context context = new Context();
			TextMessage textMessage = (TextMessage) message;
			String text = textMessage.getText();
			Long itemId = new Long(text);

			// 等待事务提交
			Thread.sleep(1000);
			// 根据商品id查询商品信息，商品基本信息和商品描述。
			TbItem tbItem = itemService.getTbItemById(itemId);
			Item item = new Item(tbItem);
			// 取商品描述
			TbItemDesc itemDesc = itemService.getTbItemDescById(itemId);
			// 创建一个数据集，把商品数据封装
			Map<Object, Object> data = new HashMap<>();
			data.put("item", item);
			data.put("itemDesc", itemDesc);
			context.setVariable("item", item);
			context.setVariable("itemDesc", itemDesc);

			// 渲染模板
			FileWriter write = new FileWriter(ItemConfiguration.HTML_GEN_PATH + itemId + ".html");
			templateEngine.process("item", context, write);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
