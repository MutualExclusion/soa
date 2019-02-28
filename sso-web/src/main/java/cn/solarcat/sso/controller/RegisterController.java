package cn.solarcat.sso.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;

import cn.solarcat.common.util.JudgeFunction;
import cn.solarcat.common.util.SolarCatResult;
import cn.solarcat.pojo.TbUser;
import cn.solarcat.sso.service.UserService;

@Controller
public class RegisterController {
	@Reference(timeout = 600000)
	private UserService userService;

	@RequestMapping("/register")
	public String showRegister(HttpServletRequest request) {
		if (JudgeFunction.JudgeIsMoblie(request)) {
			return "register-phone";
		} else {
			return "register";
		}
	}

	@RequestMapping("/user/check/{param}/{type}")
	@ResponseBody
	public SolarCatResult checkData(@PathVariable String param, @PathVariable int type) {
		SolarCatResult solarCatResult = userService.checkData(param, type);
		return solarCatResult;
	}

	@RequestMapping(value = "/user/register", method = RequestMethod.POST)
	@ResponseBody
	public SolarCatResult register(TbUser user) {
		SolarCatResult solarCatResult = userService.register(user);
		return solarCatResult;
	}
}
