package com.slimechan.raceway_system;

import com.slimechan.raceway_system.console.ReaderThread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;

import javax.servlet.MultipartConfigElement;

@SpringBootApplication

public class RacewaySystemApplication {

	@Autowired
	private ReaderThread th;

	public static void main(String[] args) {
		SpringApplication.run(RacewaySystemApplication.class, args);
	}

	@Bean
	public void console(){
		th.start();
	}

	@Bean
	public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		factory.setMaxFileSize("128KB");
		factory.setMaxRequestSize("128KB");
		return factory.createMultipartConfig();
	}
}
