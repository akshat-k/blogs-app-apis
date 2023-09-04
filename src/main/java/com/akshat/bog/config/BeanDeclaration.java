package com.akshat.bog.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration

public class BeanDeclaration {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
