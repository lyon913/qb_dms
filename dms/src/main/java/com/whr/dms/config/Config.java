package com.whr.dms.config;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class Config extends PropertiesConfiguration implements Configuration {
	public Config() throws ConfigurationException{
		super("config.properties");
	}
}
