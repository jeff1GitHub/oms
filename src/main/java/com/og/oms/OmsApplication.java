package com.og.oms;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import com.og.oms.ueditor.UEditorController;

@MapperScan("com.og.oms.dao")
@SpringBootApplication
public class OmsApplication extends SpringBootServletInitializer {
    private static Logger logger = LoggerFactory.getLogger(OmsApplication.class);

	@Value("${nas.home}")
	private String nasHome;

	@Bean
	public ServletRegistrationBean servletRegistrationBean() {
		return new ServletRegistrationBean(new UEditorController(nasHome), "/lib/ueditor/UEditor/*");
	}
    
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(OmsApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(OmsApplication.class, args);
        logger.info("服务器启动成功!");
    }
}
