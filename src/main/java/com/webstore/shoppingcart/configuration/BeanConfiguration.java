package com.webstore.shoppingcart.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	@Bean
	public ConfigurableServletWebServerFactory jettyServletWebServerFactory() {
	    JettyServletWebServerFactory factory = new JettyServletWebServerFactory();
	    factory.setPort(8080);
	    factory.setContextPath("/shoppingcart");

	    return factory;
	}
}
