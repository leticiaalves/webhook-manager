package br.com.moip.webhook.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan("br.com.moip.webhook.manager")
public class WebhookManagerApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(WebhookManagerApplication.class, args);
	}
}
