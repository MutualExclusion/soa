package cn.solarcat.cart.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;

import cn.solarcat.cart.service.CartService;
import cn.solarcat.common.util.ReturnCode;
import cn.solarcat.common.util.SolarCatResult;
import cn.solarcat.pojo.TbUser;
import cn.solarcat.service.ItemService;

@Controller
public class AccountController {
	@Reference(timeout = 600000)
	private ItemService itemService;
	@Reference(timeout = 600000)
	private CartService cartService;

	@RequestMapping("/bashboad")
	public String showPage(HttpServletRequest request, HttpServletResponse response, Model model) {
		TbUser user = (TbUser) request.getAttribute("user");
		if (user != null) {
			// 如果是登录状态，把购物车写入redis
			return "index";
		} else {
			return "411";
		}
	}

	@RequestMapping("/user/myinfo")
	@ResponseBody
	public SolarCatResult showMyINfo(HttpServletRequest request, HttpServletResponse response, Model model) {
		TbUser user = (TbUser) request.getAttribute("user");

		if (user != null) {
			return SolarCatResult.ok(user);
		} else {
			return SolarCatResult.build(ReturnCode.C411);
		}
	}

	@RequestMapping("/myinfo")
	public String showMyINfoPage(HttpServletRequest request, HttpServletResponse response, Model model) {
		return "myinfo";
	}

	@RequestMapping("/address")
	public String showAddressPage(HttpServletRequest request, HttpServletResponse response, Model model) {
		return "address";
	}

	@RequestMapping("/user/address")
	public String showAddress(HttpServletRequest request, HttpServletResponse response, Model model) {
		return "address";
	}

	@RequestMapping("/user/order")
	@ResponseBody
	public SolarCatResult showOrder(HttpServletRequest request, HttpServletResponse response, Model model) {
		TbUser user = (TbUser) request.getAttribute("user");

		if (user != null) {
			return SolarCatResult.ok(user);
		} else {
			return SolarCatResult.build(ReturnCode.C411);
		}
	}

	@RequestMapping("/order")
	public String showOrderPage(HttpServletRequest request, HttpServletResponse response, Model model) {
		return "order";
	}
}
