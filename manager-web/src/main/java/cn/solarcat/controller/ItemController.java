package cn.solarcat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;

import cn.solarcat.aop.Log;
import cn.solarcat.common.pojo.ACTION;
import cn.solarcat.common.pojo.EasyUIDataGridResult;
import cn.solarcat.common.pojo.LEVEL;
import cn.solarcat.common.util.SolarCatResult;
import cn.solarcat.pojo.TbItem;
import cn.solarcat.pojo.TbItemDesc;
import cn.solarcat.service.ItemDescService;
import cn.solarcat.service.ItemParamItemService;
import cn.solarcat.service.ItemParamService;
import cn.solarcat.service.ItemService;

@Controller
public class ItemController {
	@Reference(timeout = 600000)
	private ItemService itemService;
	@Reference(timeout = 600000, retries = 0)
	private ItemDescService itemDescService;
	@Reference(timeout = 600000)
	private ItemParamItemService itemParamItemService;
	@Reference(timeout = 600000)
	private ItemParamService itemParamService;

	@Log(action = ACTION.MUTUAL, level = LEVEL.CONTROLLER)
	@RequestMapping("/item/{itemId}")
	@ResponseBody
	public TbItem getItemById(@PathVariable Long itemId) {
		TbItem tbItem = itemService.getTbItemById(itemId);
		return tbItem;
	}

	@Log(action = ACTION.MUTUAL, level = LEVEL.CONTROLLER)
	@RequestMapping("/item/list")
	@ResponseBody
	public EasyUIDataGridResult getItemList(Integer page, Integer rows) {
		EasyUIDataGridResult result = itemService.getItemList(page, rows);
		return result;
	}

	@Log(action = ACTION.MUTUAL, level = LEVEL.CONTROLLER)
	@RequestMapping(value = "/item/save", method = RequestMethod.POST)
	@ResponseBody
	public SolarCatResult addItem(TbItem tbItem, String desc) {
		SolarCatResult result = itemService.addItem(tbItem, desc);
		return result;
	}

	@Log(action = ACTION.MUTUAL, level = LEVEL.CONTROLLER)
	@RequestMapping(value = "/rest/item/delete", method = RequestMethod.POST)
	@ResponseBody
	public SolarCatResult deleteItem(String ids) {
		String[] arr = ids.split(",");
		SolarCatResult solarCatResult = null;
		for (String Id : arr) {
			solarCatResult = itemService.deleteItem(Long.valueOf(Id));
		}
		return solarCatResult;
	}

	@Log(action = ACTION.MUTUAL, level = LEVEL.CONTROLLER)
	@RequestMapping(value = "/rest/item/query/item/desc/{itemId}")
	@ResponseBody
	public SolarCatResult loadItemDesc(@PathVariable Long itemId) {
		SolarCatResult result = itemDescService.getItemDesc(itemId);
		return result;

	}

	@Log(action = ACTION.MUTUAL, level = LEVEL.CONTROLLER)
	@RequestMapping(value = "/rest/page/item-edit")
	public String goToEdit(Long time) {
		return "item-edit";
	}

	@Log(action = ACTION.MUTUAL, level = LEVEL.CONTROLLER)
	@RequestMapping(value = "/rest/item/param/item/query/{itemId}")
	@ResponseBody
	public SolarCatResult loadItemQuery(@PathVariable Long itemId) {
		SolarCatResult solarCatResult = itemParamItemService.getItemParamItem(itemId);
		return SolarCatResult.ok(solarCatResult);

	}

	@Log(action = ACTION.MUTUAL, level = LEVEL.CONTROLLER)
	@RequestMapping(value = "/rest/item/update")
	@ResponseBody
	public SolarCatResult updateItem(TbItem item, TbItemDesc itemDesc) {
		SolarCatResult result = itemService.updateItemAndDesc(item, itemDesc);
		return result;

	}

	@Log(action = ACTION.MUTUAL, level = LEVEL.CONTROLLER)
	@RequestMapping(value = "/rest/item/instock")
	@ResponseBody
	public SolarCatResult updateInstock(String ids) {
		String[] arr = ids.split(",");
		SolarCatResult result = null;
		for (String Id : arr) {
			result = itemService.updateInstock(Long.valueOf(Id));
		}
		return result;
	}

	@Log(action = ACTION.MUTUAL, level = LEVEL.CONTROLLER)
	@RequestMapping(value = "/rest/item/reshelf")
	@ResponseBody
	public SolarCatResult updateReshelf(String ids) {
		String[] arr = ids.split(",");
		SolarCatResult result = null;
		for (String Id : arr) {
			result = itemService.updateReshelf(Long.valueOf(Id));
		}
		return result;
	}

}
