package cn.solarcat.item.listener;

import java.io.PrintWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.jms.Message;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.alibaba.dubbo.config.annotation.Reference;

import cn.solarcat.common.configuration.ActiveMQConfiguration;
import cn.solarcat.common.configuration.ItemConfiguration;
import cn.solarcat.item.pojo.Item;
import cn.solarcat.pojo.TbItem;
import cn.solarcat.pojo.TbItemDesc;
import cn.solarcat.service.ItemService;
import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 监听商品添加消息，生成对应的静态页面
 * <p>
 * Title: HtmlGenListener
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Company: www.itcast.cn
 * </p>
 * 
 * @version 1.0
 */
@Component
public class HtmlGenListener {

	private static final Logger logger = LoggerFactory.getLogger(HtmlGenListener.class);
	@Reference(retries = 3)
	private ItemService itemService;
	@Autowired
	private FreeMarkerConfigurer freeMarkerConfigurer;
	private String HTML_GEN_PATH = ItemConfiguration.HTML_GEN_PATH;

	@JmsListener(destination = ActiveMQConfiguration.ITEM_ADD_TOPIC)
	public void onMessage(Message message) {
		try {
			// 创建一个模板，参考jsp
			// 从消息中取商品id
			TextMessage textMessage = (TextMessage) message;
			String text = textMessage.getText();
			Long itemId = new Long(text);
			logger.info("{}", itemId);
			// 等待事务提交
			Thread.sleep(60000);
			// 根据商品id查询商品信息，商品基本信息和商品描述。
			TbItem tbItem = itemService.getTbItemById(itemId);
			Item item = new Item(tbItem);
			// 取商品描述
			TbItemDesc itemDesc = itemService.getTbItemDescById(itemId);
			// 创建一个数据集，把商品数据封装
			Map<Object, Object> data = new HashMap<>();
			data.put("item", item);
			data.put("itemDesc", itemDesc);
			// 加载模板对象
			Configuration configuration = freeMarkerConfigurer.getConfiguration();
			configuration.setDefaultEncoding("GBK");
			Template template = configuration.getTemplate("item.ftl");
			// 创建一个输出流，指定输出的目录及文件名。
			Writer out = new PrintWriter(HTML_GEN_PATH + itemId + ".html", "UTF-8");
			// 生成静态页面。
			template.process(data, out);
			// 关闭流
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
