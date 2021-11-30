package com.oracle.Angbit.configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import com.oracle.Angbit.service.LoginInterceptor;
import com.oracle.Angbit.service.myInfo.WidrawInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
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

	public void addInterceptors(InterceptorRegistry registry) {
		// 세션 아이디 체크 인터셉터(로그인 여부)
		registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/myInfo");
		// 회원 상태 확인 인터셉터(로그인 시)
		registry.addInterceptor(new WidrawInterceptor()).addPathPatterns("/gologin");
		registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/statusList");
		registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/status_y_history");
		registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/status_n_history");
		registry.addInterceptor(new WidrawInterceptor()).addPathPatterns("/lg/loginSuccess");
	}
	
}
