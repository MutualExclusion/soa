package cn.solarcat.portal.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.dubbo.config.annotation.Reference;

import cn.solarcat.content.service.ContentService;
import cn.solarcat.pojo.TbContent;

@Controller
public class IndexController {
	@Reference
	private ContentService contentService;
	private Long CONTENT_LUNBO_ID = 89L;

	@RequestMapping("/index")
	public String showIndex(Model model) {
		List<TbContent> ad1List = contentService.getContentListByCid(CONTENT_LUNBO_ID);
		for (TbContent tbContent : ad1List) {
			System.out.println(tbContent);
		}
		model.addAttribute("ad1List", ad1List);
		return "index";
	}
}
