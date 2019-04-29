package cn.solarcat.item.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.solarcat.aop.Log;
import cn.solarcat.common.pojo.ACTION;
import cn.solarcat.common.pojo.LEVEL;

@Controller
public class PageController {
	@Log(action = ACTION.MUTUAL, level = LEVEL.CONTROLLER)
	@RequestMapping("/")
	public String showIndex() {
		return "index";
	}

	@Log(action = ACTION.MUTUAL, level = LEVEL.CONTROLLER)
	@RequestMapping("/{page}")
	public String showPage(@PathVariable String page) {
		return page;
	}
}