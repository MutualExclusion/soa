package cn.solarcat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;

@SpringBootApplication
@EnableDubbo
public class CartWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(CartWebApplication.class, args);
	}

}