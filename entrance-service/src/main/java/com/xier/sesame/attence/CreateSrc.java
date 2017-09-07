package com.xier.sesame.attence;

import java.io.IOException;
import java.sql.SQLException;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.xier.sesame.common.sourcegenerator.SourceGenerator; 

@ComponentScan(basePackages="com.xier.sesame.common.sourcegenerator")
public class CreateSrc {
	public static void main(String[] args) throws SQLException, IOException {
		
		final ApplicationContext ctx = SpringApplication.run(CreateSrc.class, args);
		SourceGenerator SourceGenerator = ctx.getBean(SourceGenerator.class);
		SourceGenerator.createApiImplSource();
	}
}
