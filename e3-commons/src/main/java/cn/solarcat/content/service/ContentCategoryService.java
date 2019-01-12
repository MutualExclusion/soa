package cn.solarcat.content.service;

import java.util.List;

import cn.solarcat.common.pojo.EasyUITreeNode;
import cn.solarcat.common.util.SolarCatResult;


public interface ContentCategoryService {

	List<EasyUITreeNode> getContentCatList(long parentId);
	SolarCatResult addContentCategory(long parentId, String name);
}