package com.oracle.Angbit.configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import com.oracle.Angbit.service.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SpringConfig implements WebMvcConfigurer {

	private final DataSource	dataSource;
	private final EntityManager	em;
	
	public SpringConfig(DataSource dataSource, EntityManager em) {
		this.dataSource = dataSource;
		this.em = em;
	}

	//InterCeptor
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/myInfo");
	}
	
}
