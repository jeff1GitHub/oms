package com.og.oms.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 启动配置
 * @author 研发
 *
 */
@Configuration
public class MyWebAppConfigurer extends WebMvcConfigurerAdapter {

	@Value("${nas.home}")
	private String nasHome;
	
	/**
	 * 增加通过Ueditor上传的图片的本地绝对路径映射
	 * 如：registry.addResourceHandler("/image/**").addResourceLocations("file:d:/output/image/");
	 *    /image/**  ===>  d:/output/image/
	 *    访问:
	 *    		ContextPath/image/123.jpg  ===> d:/output/image/123.jpg
	 *    		ContextPath/image/20171112/123.jpg  ===> d:/output/image/20171112/123.jpg
	 * 
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/image/**").addResourceLocations("file:" + nasHome + "/image/");
		registry.addResourceHandler("/file/**").addResourceLocations("file:" + nasHome + "/file/");
		super.addResourceHandlers(registry);
	}
}
