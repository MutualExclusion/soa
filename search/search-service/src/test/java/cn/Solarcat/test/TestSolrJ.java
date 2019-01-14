package cn.Solarcat.test;

import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.junit.Test;

public class TestSolrJ {
//	@Test
//	public void addDocument() throws SolrServerException, IOException {
//		SolrServer solrServer = new HttpSolrServer("http://139.199.201.162:8100/solr/collection1");
//		SolrInputDocument document = new SolrInputDocument();
//		document.addField("id", "doc01");
//		document.addField("item_title", "测试商品01");
//		document.addField("item_price", 1000);
//		solrServer.add(document);
//		solrServer.commit();
//	}
//	@Test
//	public void deleteDocument() throws SolrServerException, IOException {
//		SolrServer solrServer = new HttpSolrServer("http://123.207.24.140:8082/solr/collection1");
//		solrServer.deleteById("doc01");
//		solrServer.commit();
//	}
	@Test
	public void quertIndex() throws SolrServerException {
		final SolrServer solrServer = new HttpSolrServer("http://139.199.201.162:8100/solr/collection1");
		final SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("*:*");
		solrQuery.set("q", "*:*");
		final QueryResponse queryResponse = solrServer.query(solrQuery);
		final SolrDocumentList solrDocumentList = queryResponse.getResults();
		System.out.println("查询结果总记录数：" + solrDocumentList.getNumFound());
		for (final SolrDocument solrDocument : solrDocumentList) {
			System.out.println(solrDocument.get("id"));
			System.out.println(solrDocument.get("item_title"));
			System.out.println(solrDocument.get("item_sell_point"));
			System.out.println(solrDocument.get("item_price"));
			System.out.println(solrDocument.get("item_image"));
			System.out.println(solrDocument.get("item_category_name"));
		}
	}

	@Test
	public void queryIndexFuza() throws SolrServerException {
		final SolrServer solrServer = new HttpSolrServer("http://139.199.201.162:8100/solr/collection1");
		final SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("手机");
		solrQuery.setStart(0);
		solrQuery.setRows(20);
		solrQuery.set("df", "item_title");
		solrQuery.setHighlight(true);
		solrQuery.addHighlightField("item_title");
		solrQuery.setHighlightSimplePre("<em>");
		solrQuery.setHighlightSimplePost("</em>");
		final QueryResponse queryResponse = solrServer.query(solrQuery);
		final SolrDocumentList solrDocumentList = queryResponse.getResults();
		System.out.println("查询结果总记录数：" + solrDocumentList.getNumFound());
		for (final SolrDocument solrDocument : solrDocumentList) {
			System.out.println(solrDocument.get("id"));
			final Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();
			final List<String> list = highlighting.get(solrDocument.get("id")).get("item_title");
			String title = "";
			if (list != null && list.size() > 0) {
				title = list.get(0);
			} else {
				title = (String) solrDocument.get("item_title");
			}
			System.out.println(title);
			System.out.println(solrDocument.get("item_sell_point"));
			System.out.println(solrDocument.get("item_price"));
			System.out.println(solrDocument.get("item_image"));
			System.out.println(solrDocument.get("item_category_name"));
		}

	}

}
