package com.uday.learningNavigator.Configs;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.client.RestTemplate;

/**
 * AppConfigs
 */
@Configuration
public class AppConfigs {

    @Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	@Scope("prototype")
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
// //solve entity manager's  issue
// 	@Bean(name="entityManagerFactory")
// 	public LocalSessionFactoryBean sessionFactory() {
// 		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
// 		return sessionFactory;
// 	}
}