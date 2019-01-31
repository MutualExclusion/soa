package cn.solarcat.aop;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;

import cn.solarcat.common.pojo.LogEntity;

@Aspect
@Component("LogRecord")
public class LogRecord {

	private static final Logger logger = LoggerFactory.getLogger(LogRecord.class);
	private static final String POINT = "@annotation(cn.solarcat.aop.Log)";

	@Around(value = POINT)
	public Object doAround(ProceedingJoinPoint joinPoint) throws Exception {
		// 获取签名类
		Signature signature = joinPoint.getSignature();
		MethodSignature methodSignature = (MethodSignature) signature;
		Method method = methodSignature.getMethod();
		Log log = method.getAnnotation(Log.class);
//		if (controllerLog == null) {
//			return controllerLog;
//		}
		long startTime = System.currentTimeMillis();
		// 定义返回对象、得到方法需要的参数
		Object resultData = null;
		Object[] obj = joinPoint.getArgs();
		LogEntity logInfo = new LogEntity();
		logInfo.setAction(log.action());
		try {
			resultData = joinPoint.proceed(obj);
			long endTime = System.currentTimeMillis();

			// 组装日志信息

			logInfo.setClassName(method.getDeclaringClass().getName());
			logInfo.setStartTime(startTime);
			logInfo.setEndTime(endTime);
			logInfo.setExpendTime(endTime - startTime);
			logInfo.setLevel(log.level());
			logInfo.setMethod(method.getName());
			logInfo.setObj((Object[]) resultData);
			logInfo.setParams(obj);

			logger.info("target:{}", JSONObject.toJSONString(logInfo));
		} catch (Throwable e) {
			long endTime = System.currentTimeMillis();
			// 组装日志信息
			logInfo.setClassName(method.getDeclaringClass().getName());
			logInfo.setStartTime(startTime);
			logInfo.setEndTime(endTime);
			logInfo.setExpendTime(endTime - startTime);
			logInfo.setLevel(log.level());
			logInfo.setMethod(method.getName());
			logInfo.setException(e.getMessage());
			logInfo.setParams(obj);
			logger.error("target:{}", JSONObject.toJSONString(logInfo));
		}
		return resultData;
	}

//	private static Log getAnnotationLog(JoinPoint joinPoint) throws Exception {
//		
//		if (method != null) {
//			return method.getAnnotation(Log.class);
//		}
//		return null;
//	}

}