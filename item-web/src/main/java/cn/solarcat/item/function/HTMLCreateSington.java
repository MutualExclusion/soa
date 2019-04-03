package cn.solarcat.item.function;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import cn.solarcat.item.util.ItemContact;

public class HTMLCreateSington {
	private static TemplateEngine templateEngine;

	public static TemplateEngine getInstance() {
		if (templateEngine == null) {
			synchronized (HTMLCreateSington.class) {
				ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
				resolver.setPrefix("templates/");// 模板所在目录，相对于当前classloader的classpath。
				resolver.setSuffix(ItemContact.HTML_SUFFIX_S);// 模板文件后缀
				templateEngine = new TemplateEngine();
				templateEngine.setTemplateResolver(resolver);
			}
		}
		return templateEngine;
	}
}
