package fr.polytech.ig5.CSALoffers;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaTemplate;

import javax.sql.DataSource;

@SpringBootApplication
public class CsalOffersApplication {


	@Bean
	@ConfigurationProperties("spring.datasource")
	public DataSource getDataSource() {
        /*DataSourceBuilder dsb = DataSourceBuilder.create();
        return dsb.url("jdbc:postgresql://localhost:5432/postgres")
                .driverClassName("org.postgresql.Driver")
                .password("postgres")
                .username("postgres")
                .build();*/
		return DataSourceBuilder.create().build();
	}

	@Bean(name="myJDBCTemplate")
	@Primary
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(getDataSource());
	}




	public static void main(String[] args) {
		SpringApplication.run(CsalOffersApplication.class, args);
	}

}
