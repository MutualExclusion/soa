package cn.solarcat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;

import cn.solarcat.common.util.SolarCatResult;
import cn.solarcat.content.service.ContentService;
import cn.solarcat.pojo.TbContent;

@Controller
public class ContentController {
	@Reference
	private ContentService contentSevice;

	@RequestMapping(value = "/content/save", method = RequestMethod.POST)
	@ResponseBody
	public SolarCatResult addContent(TbContent tbContent) {
		SolarCatResult result = contentSevice.addContent(tbContent);
		return result;
	}
}