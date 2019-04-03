package cn.solarcat.service.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.solarcat.common.util.SolarCatResult;
import cn.solarcat.service.Impl.ItemServiceImpl;

@Controller
public class ItemController {
	@Autowired
	private ItemServiceImpl itemServiceImpl;

	@RequestMapping("/item/select/tbitem")
	@ResponseBody
	public SolarCatResult selectTbItem() {
		return new SolarCatResult(itemServiceImpl.selectTbItem());
	}
}
