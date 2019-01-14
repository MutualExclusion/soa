package cn.solarcat.search.message;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;

import cn.solarcat.common.pojo.SearchItem;
import cn.solarcat.search.mapper.ItemMapper;

public class ItemAddMessageListener implements MessageListener{
	@Autowired
	private ItemMapper ItemMapper;
	@Autowired
	private SolrServer solrServer;
	@Override
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
			solrServer.add(document);
			solrServer.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
