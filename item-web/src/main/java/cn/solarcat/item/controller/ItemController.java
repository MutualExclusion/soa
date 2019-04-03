package cn.solarcat.item.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;

import cn.solarcat.common.configuration.ItemConfiguration;
import cn.solarcat.common.util.JudgeFunction;
import cn.solarcat.common.util.SolarCatResult;
import cn.solarcat.item.pojo.Item;
import cn.solarcat.item.service.HtmlService;
import cn.solarcat.pojo.TbItem;
import cn.solarcat.pojo.TbItemDesc;
import cn.solarcat.service.ItemService;

@Controller
public class ItemController {
	@Reference(timeout = 600000)
	private ItemService itemService;

	@Autowired
	private HtmlService htmlService;

	@RequestMapping("/item/{itemId}")
	public String showItemInfo(HttpServletRequest request, @PathVariable Long itemId, Model model) {
		TbItem tbItem = itemService.getTbItemById(itemId);
		Item item = new Item(tbItem);
		TbItemDesc tbItemDesc = itemService.getTbItemDescById(itemId);
		model.addAttribute("item", item);
		model.addAttribute("itemDesc", tbItemDesc);
		if (JudgeFunction.JudgeIsMoblie(request)) {
			return "item-phone";
		} else {
			return "item";
		}

	}

	/**
	 * 创建全新的页面
	 * 
	 * @return
	 */
	@RequestMapping("/item/create/dev")
	@ResponseBody
	public SolarCatResult createNewHtmlPageDev() {
		SolarCatResult solarCatResult = htmlService.CreatNewHtmlPage(ItemConfiguration.HTML_TEST_GEN_PATH);
		return solarCatResult;
	}

	/**
	 * 创建全新的页面
	 * 
	 * @return
	 */
	@RequestMapping("/item/create")
	@ResponseBody
	public SolarCatResult createNewHtmlPage() {
		SolarCatResult solarCatResult = htmlService.CreatNewHtmlPage(ItemConfiguration.HTML_GEN_PATH);
		return solarCatResult;
	}
}
