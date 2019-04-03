package cn.solarcat.item.service.Impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.alibaba.dubbo.config.annotation.Reference;

import cn.solarcat.common.util.SolarCatResult;
import cn.solarcat.item.function.HTMLCreateRunnable;
import cn.solarcat.item.function.HTMLCreateSington;
import cn.solarcat.item.pojo.Item;
import cn.solarcat.item.service.HtmlService;
import cn.solarcat.item.util.FileHandle;
import cn.solarcat.item.util.ItemContact;
import cn.solarcat.pojo.TbItem;
import cn.solarcat.pojo.TbItemDesc;
import cn.solarcat.service.ItemService;

@Service
public class HtmlServiceImpl implements HtmlService {
	@Reference(timeout = 600000)
	private ItemService itemService;

	@Autowired
	private HTMLCreateRunnable htmlCreate;

	@Override
	public SolarCatResult CreatNewHtmlPage(String path) {
		File f = new File(path);
		// 删除该路径下的所有HTML文件
		FileHandle.delete(f);
		// 重新生成
		// 构造上下文(Model)
		Context context = new Context();
		// 根据商品id查询商品信息，商品基本信息和商品描述。

		List<TbItem> list = itemService.selectTbItem();
		for (TbItem temp : list) {
			Item item = new Item(temp);
			// 取商品描述
			TbItemDesc itemDesc = itemService.getTbItemDescById(temp.getId());
			// 创建一个数据集，把商品数据封装
			context.setVariable("item", item);
			context.setVariable("itemDesc", itemDesc);
			FileWriter write = null;
			try {
				write = new FileWriter(path + item.getId() + ItemContact.HTML_SUFFIX_S);
			} catch (IOException e) {
				e.printStackTrace();
			}
			TemplateEngine template = HTMLCreateSington.getInstance();
			htmlCreate.run(template, context, write, ItemContact.HTML_TEMPLATES_NAME);
		}
		return SolarCatResult.ok();
		// TODO 此处应该做日志处理

	}
}
