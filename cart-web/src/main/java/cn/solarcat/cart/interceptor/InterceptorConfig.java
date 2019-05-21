package cn.solarcat.cart.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.alibaba.dubbo.config.annotation.Reference;

import cn.solarcat.aop.Log;
import cn.solarcat.common.pojo.ACTION;
import cn.solarcat.common.pojo.LEVEL;
import cn.solarcat.sso.service.TokenService;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

	@Reference(timeout = 600000)
	private TokenService tokenService;

	@Log(action = ACTION.SELECT, level = LEVEL.SERVICE)
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		LoginInterceptor loginInterceptor = new LoginInterceptor();
		loginInterceptor.setTokenService(tokenService);
		InterceptorRegistration interceptor = registry.addInterceptor(loginInterceptor);

		interceptor.addPathPatterns("/**");
	}
}
