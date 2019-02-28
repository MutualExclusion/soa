package cn.solarcat.item.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.dubbo.config.annotation.Reference;

import cn.solarcat.common.util.JudgeFunction;
import cn.solarcat.item.pojo.Item;
import cn.solarcat.pojo.TbItem;
import cn.solarcat.pojo.TbItemDesc;
import cn.solarcat.service.ItemService;

@Controller
public class ItemController {
	@Reference
	private ItemService itemService;

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
}
