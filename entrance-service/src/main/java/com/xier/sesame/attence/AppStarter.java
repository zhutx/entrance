package com.xier.sesame.attence;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.xier.sesame.rabbit.autoconf.DubboApplication;

/**
 * 启动器
 * 
 * @author erxiao 2016年1月9日
 */
@Configuration
@ComponentScan("com.xier.sesame")
@EnableAutoConfiguration
public class AppStarter extends DubboApplication {
	
	private final static Object ONLY_OBJECT_RESOURCE = AppStarter.class;

	public static void main(String[] args) {
		
		start(ONLY_OBJECT_RESOURCE, args);
		
	}

}
