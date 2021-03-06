package cn.solarcat.search.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.dubbo.config.annotation.Reference;

import cn.solarcat.common.pojo.SearchItem;
import cn.solarcat.common.pojo.SearchResult;
import cn.solarcat.search.service.SearchService;

@Controller
public class SearchController {
	@Reference(timeout = 600000)
	private SearchService searchService;
	private Integer SEARCH_RESULT_ROWS = 32;

	@RequestMapping("/search")
	public String searchItemList(HttpServletRequest request, String keyword,
			@RequestParam(defaultValue = "1") Integer page, Model model) throws Exception {
		SearchResult searchResult = searchService.search(keyword, page, SEARCH_RESULT_ROWS);
		List<SearchItem> itemList = searchResult.getItemList();
		if (itemList.isEmpty()) {
			return "404";
		} else {
			model.addAttribute("query", keyword);
			model.addAttribute("totalPages", searchResult.getTotalPages());
			model.addAttribute("page", page);
			model.addAttribute("recordCount", searchResult.getRecordCount());
			model.addAttribute("itemList", searchResult.getItemList());
			return "search";
		}

	}
}
