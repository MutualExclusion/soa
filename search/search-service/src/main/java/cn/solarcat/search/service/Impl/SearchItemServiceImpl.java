package cn.solarcat.search.service.Impl;

import java.util.List;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.solarcat.common.pojo.SearchItem;
import cn.solarcat.common.util.SolarCatResult;
import cn.solarcat.search.mapper.ItemMapper;
import cn.solarcat.search.service.SearchItemService;
@Service
public class SearchItemServiceImpl implements SearchItemService{
	@Autowired
	private ItemMapper itemMapper;
	@Autowired
	private SolrServer solrServer;
	@Override
	public SolarCatResult importAllItems() {
		try {
			List<SearchItem> itemList = itemMapper.getItemList();
			for (SearchItem searchItem : itemList) {
				SolrInputDocument document = new SolrInputDocument();
				document.addField("id", searchItem.getId());
				document.addField("item_title", searchItem.getTitle());
				document.addField("item_sell_point", searchItem.getSell_point());
				document.addField("item_price", searchItem.getPrice());
				document.addField("item_image", searchItem.getImage());
				document.addField("item_category_name", searchItem.getCategory_name());
				solrServer.add(document);
			}
			solrServer.commit();
			return SolarCatResult.ok();
		} catch (Exception e) {
			return SolarCatResult.build(500, "数据导入时发生异常");
		}
	}

}
