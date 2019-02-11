package cn.solarcat.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

@Component
public class GlobalExceptionResolver implements HandlerExceptionResolver {
	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionResolver.class);

	@Override
	public ModelAndView resolveException(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2,
			Exception arg3) {
		// 打印控制台
		arg3.printStackTrace();
		// 写日志
		logger.error("{}", arg3);
		// 发邮件，发短信
		// 错误页面
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("error/exception");
		return modelAndView;
	}

}
