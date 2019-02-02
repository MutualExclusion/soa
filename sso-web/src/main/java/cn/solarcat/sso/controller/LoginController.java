package cn.solarcat.sso.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.solarcat.common.util.CookieUtils;
import cn.solarcat.common.util.SolarCatResult;
import cn.solarcat.sso.service.UserService;

@Controller
public class LoginController {
	@Autowired
	private UserService userService;
	private String TOKEN_KEY = "token";

	@RequestMapping("/page/login")
	public String showLogin() {
		return "login";
	}

	@RequestMapping(value = "/user/login", method = RequestMethod.POST)
	@ResponseBody
	public SolarCatResult login(String username, String password, HttpServletRequest request,
			HttpServletResponse response) {
		SolarCatResult solarCatResult = userService.login(username, password);
		if (solarCatResult.getStatus() == 200) {
			String token = solarCatResult.getData().toString();
			CookieUtils.setCookie(request, response, TOKEN_KEY, token);
		}
		return solarCatResult;
	}

}
