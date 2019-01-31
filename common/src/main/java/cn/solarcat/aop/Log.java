package cn.solarcat.aop;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;

import cn.solarcat.common.pojo.ACTION;
import cn.solarcat.common.pojo.LEVEL;

@Component
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {
	ACTION action() default ACTION.NODO;

	LEVEL level() default LEVEL.SERVICE;
}
