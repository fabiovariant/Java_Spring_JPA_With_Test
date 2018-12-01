package br.com.tokyo.TokyoCustomer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("br.com.tokyo")
public class TokyoCustomerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TokyoCustomerApplication.class, args);
	}
}
