package cn.solarcat.cart.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.solarcat.common.util.CookieUtils;
import cn.solarcat.common.util.SolarCatResult;
import cn.solarcat.pojo.TbUser;
import cn.solarcat.sso.service.TokenService;

public class LoginInterceptor implements HandlerInterceptor {

	private TokenService tokenService;

	public TokenService getTokenService() {
		return tokenService;
	}

	public void setTokenService(TokenService tokenService) {
		this.tokenService = tokenService;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 前处理，执行handler之前执行此方法。
		// 返回true，放行 false：拦截
		// 1.从cookie中取token
		String token = CookieUtils.getCookieValue(request, "token");
		// 2.如果没有token，未登录状态，直接放行
		if (StringUtils.isBlank(token)) {
			return true;
		}
		// 3.取到token，需要调用sso系统的服务，根据token取用户信息
		SolarCatResult solarResult = tokenService.getUserByToken(token);
		// 4.没有取到用户信息。登录过期，直接放行。
		if (solarResult.getStatus() != 200) {
			return true;
		}
		// 5.取到用户信息。登录状态。
		TbUser user = (TbUser) solarResult.getData();
		// 6.把用户信息放到request中。只需要在Controller中判断request中是否包含user信息。放行
		request.setAttribute("user", user);
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// handler执行之后，返回ModeAndView之前

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// 完成处理，返回ModelAndView之后。
		// 可以再此处理异常

	}

}
