package cn.solarcat.sso.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;

import cn.solarcat.common.util.SolarCatResult;
import cn.solarcat.sso.service.TokenService;

@Controller
public class TokenController {
	@Reference
	private TokenService tokenService;

	@RequestMapping(value = "/user/token/{token}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public String getUserByToken(@PathVariable String token, String callback) {
		SolarCatResult solarCatResult = tokenService.getUserByToken(token);
		if (StringUtils.isNotBlank(callback)) {
			return callback + "(" + /** JsonUtils.objectToJson(solarCatResult) */
					JSONObject.toJSONString(solarCatResult) + ");";
		}
		return /* JsonUtils.objectToJson(solarCatResult) */JSONObject.toJSONString(solarCatResult);
	}
}
