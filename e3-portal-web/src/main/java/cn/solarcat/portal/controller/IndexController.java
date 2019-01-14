package cn.solarcat.portal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.solarcat.content.service.ContentService;
import cn.solarcat.pojo.TbContent;

@Controller
public class IndexController {
	@Autowired
	private ContentService contentService;
	@Value("${CONTENT_LUNBO_ID}")
	private Long CONTENT_LUNBO_ID;
	@RequestMapping("/index")
public String showIndex(Model model) {
	List<TbContent> ad1List= contentService.getContentListByCid(CONTENT_LUNBO_ID);
	model.addAttribute("ad1List", ad1List);
	return "index";
}
}
