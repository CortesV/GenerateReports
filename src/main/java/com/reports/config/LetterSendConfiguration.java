package com.reports.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
public class LetterSendConfiguration {
	@Autowired
	private Environment environment;
	
	@Value("${report.mail.username}")
	private String username;
	
	@Value("${report.mail.password}")
	private String password;
	
	@Bean
	public Properties propertiesReport() {
		Properties props = new Properties();
		props.put("mail.smtp.host", environment.getRequiredProperty("report.mail.smtp.host"));
		props.put("mail.smtp.starttls.enable", environment.getRequiredProperty("report.mail.smtp.starttls.enable"));
		props.put("mail.smtp.socketFactory.port",
				environment.getRequiredProperty("report.mail.smtp.socketFactory.port"));
		props.put("mail.smtp.socketFactory.class",
				environment.getRequiredProperty("report.mail.smtp.socketFactory.class"));
		props.put("mail.smtp.auth", environment.getRequiredProperty("report.mail.smtp.auth"));
		props.put("mail.smtp.port", environment.getRequiredProperty("report.mail.smtp.port"));
		return props;
	}
	
	public String getUsername(){
		return username;
	}
	
	public String getPassword(){
		return password;
	}
	

}
