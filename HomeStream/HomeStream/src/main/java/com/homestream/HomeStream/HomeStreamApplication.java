package com.homestream.HomeStream;

import com.homestream.HomeStream.main.console.ConsoleHandler;
import com.homestream.HomeStream.main.assets.property.Properties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class HomeStreamApplication extends WebMvcConfigurerAdapter {

	public static void main(String[] args)
	{
		ConsoleHandler consoleHandler = new ConsoleHandler();
		for(String s : Properties.LANGUAGES) System.out.println(s);
		for(String s : Properties.LANGUAGE_TAGS) System.out.println(s);

		System.out.println(Properties.VERSION);
		SpringApplication homeStream = new SpringApplication();

		Map<String, Object> properties = new HashMap<>();
		properties.put("spring.resources.static-locations", ".\\res\\css");

		homeStream.setDefaultProperties(properties);
		homeStream.run(HomeStreamApplication.class, args);

		consoleHandler.run();
		
	}
}
