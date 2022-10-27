package fr.polytech.ig5.CSAL;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class CsalApplication {

	public static void main(String[] args) {
		SpringApplication.run(CsalApplication.class, args);
	}

}
