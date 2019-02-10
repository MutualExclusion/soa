package cn.solarcat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;

import cn.solarcat.aop.Log;
import cn.solarcat.common.pojo.ACTION;
import cn.solarcat.common.pojo.EasyUIDataGridResult;
import cn.solarcat.common.pojo.LEVEL;
import cn.solarcat.common.util.SolarCatResult;
import cn.solarcat.content.service.ContentService;
import cn.solarcat.pojo.TbContent;

@Controller
public class ContentController {
	@Reference(timeout = 600000)
	private ContentService contentSevice;

	@Log(action = ACTION.MUTUAL, level = LEVEL.CONTROLLER)
	@RequestMapping(value = "/content/save", method = RequestMethod.POST)
	@ResponseBody
	public SolarCatResult addContent(TbContent tbContent) {
		SolarCatResult result = contentSevice.addContent(tbContent);
		return result;
	}

	@Log(action = ACTION.MUTUAL, level = LEVEL.CONTROLLER)
	@RequestMapping(value = "content/query/list")
	@ResponseBody
	public EasyUIDataGridResult getContentByCatId(int categoryId, int page, int rows) {
		EasyUIDataGridResult result = contentSevice.getContentByCatId(categoryId, page, rows);
		return result;

	}
}