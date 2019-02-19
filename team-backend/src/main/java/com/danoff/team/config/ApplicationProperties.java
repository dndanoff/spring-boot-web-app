package com.danoff.team.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class ApplicationProperties {
	
	private final Environment env;
	
	@Autowired
	public ApplicationProperties(Environment env) {
		this.env = env;
	}
	
}
