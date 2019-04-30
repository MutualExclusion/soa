package cn.solarcat.cart.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.dubbo.config.annotation.Reference;

import cn.solarcat.cart.service.CartService;
import cn.solarcat.service.ItemService;

@Controller
public class AccountController {
	@Reference(timeout = 600000)
	private ItemService itemService;
	@Reference(timeout = 600000)
	private CartService cartService;

	@RequestMapping("/dashboard")
	public String showInfo(HttpServletRequest request, HttpServletResponse response) {
		// 判断用户是否登录
		return "dashboard";
	}
}
