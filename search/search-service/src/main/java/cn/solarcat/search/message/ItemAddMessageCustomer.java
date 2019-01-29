package cn.solarcat.search.message;

import javax.jms.Message;
import javax.jms.TextMessage;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;

import cn.solarcat.common.pojo.ACTION;
import cn.solarcat.common.pojo.RETURN;
import cn.solarcat.common.pojo.SearchItem;
import cn.solarcat.search.mapper.ItemMapper;

public class ItemAddMessageCustomer {
	private static final Logger logger = LoggerFactory.getLogger(ItemAddMessageCustomer.class);
	@Autowired
	private ItemMapper ItemMapper;
	@Autowired
	private SolrClient solrClient;

	@JmsListener(destination = "ITEM_ADD_QUEUE")
	public void onMessage(Message message) {
		String text;
		try {
			TextMessage textMessage = (TextMessage) message;
			text = textMessage.getText();
			Long itemId = new Long(text);
			Thread.sleep(1000);
			SearchItem searchItem = ItemMapper.getItemById(itemId);
			SolrInputDocument document = new SolrInputDocument();
			document.addField("id", searchItem.getId());
			document.addField("item_title", searchItem.getTitle());
			document.addField("item_sell_point", searchItem.getSell_point());
			document.addField("item_price", searchItem.getPrice());
			document.addField("item_image", searchItem.getImage());
			document.addField("item_category_name", searchItem.getCategory_name());
			solrClient.add(document);
			solrClient.commit();

		} catch (Exception e) {
			logger.error("[" + RETURN.FAIL + "] :" + ACTION.ActiontoString(ACTION.ADD) + ": {}" + e.toString());
		}
		logger.info("[" + RETURN.SUCCESS + "] :" + ACTION.ActiontoString(ACTION.ADD) + ": {}" + message);
	}

}
