package com.xier.sesame.attence;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Random;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.xier.sesame.common.sourcegenerator.SourceGenerator;

@ComponentScan(basePackages="com.xier.sesame.common.sourcegenerator")
public class CreateApiSrc {

	public static void main(String[] args) throws SQLException, IOException {
		
		System.out.println(new Random().nextInt(2));
		
		final ApplicationContext ctx = SpringApplication.run(CreateApiSrc.class, args);
		SourceGenerator SourceGenerator = ctx.getBean(SourceGenerator.class);
		SourceGenerator.createApiInterfaceSource();
	}

}
