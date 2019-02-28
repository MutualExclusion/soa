package cn.solarcat.portal.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.dubbo.config.annotation.Reference;

import cn.solarcat.common.util.JudgeFunction;
import cn.solarcat.content.service.ContentService;
import cn.solarcat.pojo.TbContent;

@Controller
public class IndexController {
	@Reference(timeout = 600000)
	private ContentService contentService;
	private Long CONTENT_LUNBO_ID = 89L;

	@RequestMapping("/index")
	public String showIndex(HttpServletRequest request, Model model) {
		List<TbContent> ad1List = contentService.getContentListByCid(CONTENT_LUNBO_ID);
		model.addAttribute("items", ad1List);
		if (JudgeFunction.JudgeIsMoblie(request)) {
			return "index";
		} else {
			return "team";
		}

	}
}
