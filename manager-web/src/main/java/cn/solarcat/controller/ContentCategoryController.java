package cn.solarcat.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;

import cn.solarcat.aop.Log;
import cn.solarcat.common.pojo.ACTION;
import cn.solarcat.common.pojo.EasyUITreeNode;
import cn.solarcat.common.pojo.LEVEL;
import cn.solarcat.common.util.SolarCatResult;
import cn.solarcat.content.service.ContentCategoryService;

@Controller
public class ContentCategoryController {
	@Reference(timeout = 600000)
	private ContentCategoryService contentCategoryService;

	@Log(action = ACTION.MUTUAL, level = LEVEL.CONTROLLER)
	@RequestMapping("/content/category/list")
	@ResponseBody
	public List<EasyUITreeNode> getContCatList(@RequestParam(name = "id", defaultValue = "0") Long parentId) {
		List<EasyUITreeNode> list = contentCategoryService.getContentCatList(parentId);
		return list;

	}

	@Log(action = ACTION.MUTUAL, level = LEVEL.CONTROLLER)
	@RequestMapping("/content/category/create")
	@ResponseBody
	public SolarCatResult addContCat(Long parentId, String name) {
		SolarCatResult result = contentCategoryService.addContentCategory(parentId, name);
		return result;
	}

	@Log(action = ACTION.MUTUAL, level = LEVEL.CONTROLLER)
	@RequestMapping("/content/category/update")
	@ResponseBody
	public SolarCatResult updateContCat(Long id, String name) {
		SolarCatResult result = contentCategoryService.updateContentCategory(id, name);
		return result;
	}

	@Log(action = ACTION.MUTUAL, level = LEVEL.CONTROLLER)
	@RequestMapping("/content/category/delete")
	@ResponseBody
	public SolarCatResult daleteContCat(Long id) {
		SolarCatResult result = contentCategoryService.deleteContentCategory(id);
		return result;
	}
}
