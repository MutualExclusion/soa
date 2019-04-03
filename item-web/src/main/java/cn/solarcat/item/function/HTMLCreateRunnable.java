package cn.solarcat.item.function;

import java.io.FileWriter;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Component
public class HTMLCreateRunnable {

	@Async
	public void run(TemplateEngine templateEngine, Context context, FileWriter write, String TemplateName) {
		templateEngine.process(TemplateName, context, write);
	}
}
