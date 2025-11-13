package com.javatechie.multiple.ds.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(
		/*exclude = {
				DataSourceAutoConfiguration.class//to exclude a class programmatically
		}*/
)
@EnableScheduling
public class MultipleDatabasesApplication {

	public static void main(String[] args) {

		SpringApplication.run(MultipleDatabasesApplication.class, args);

	}

}
