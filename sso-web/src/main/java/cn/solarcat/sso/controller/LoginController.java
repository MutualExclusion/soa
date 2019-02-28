package cn.solarcat.sso.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;

import cn.solarcat.common.util.CookieUtils;
import cn.solarcat.common.util.JudgeFunction;
import cn.solarcat.common.util.SolarCatResult;
import cn.solarcat.sso.service.UserService;

@Controller
public class LoginController {
	@Reference(timeout = 600000)
	private UserService userService;
	private String TOKEN_KEY = "token";

	@RequestMapping("/login")
	public String showLogin(HttpServletRequest request) {
		if (JudgeFunction.JudgeIsMoblie(request)) {
			return "login-phone";
		} else {
			return "login";
		}
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
