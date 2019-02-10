package cn.solarcat.item.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.dubbo.config.annotation.Reference;

import cn.solarcat.aop.Log;
import cn.solarcat.common.pojo.ACTION;
import cn.solarcat.common.pojo.LEVEL;
import cn.solarcat.item.pojo.Item;
import cn.solarcat.pojo.TbItem;
import cn.solarcat.pojo.TbItemDesc;
import cn.solarcat.service.ItemService;

@Controller
public class ItemController {
	@Reference
	private ItemService itemService;

	@RequestMapping("/item/{itemId}")
	@Log(action = ACTION.MUTUAL, level = LEVEL.CONTROLLER)
	public String showItemInfo(@PathVariable Long itemId, Model model) {
		TbItem tbItem = itemService.getTbItemById(itemId);
		Item item = new Item(tbItem);
		TbItemDesc tbItemDesc = itemService.getTbItemDescById(itemId);
		model.addAttribute("item", item);
		model.addAttribute("itemDesc", tbItemDesc);
		return "item";
	}
}
