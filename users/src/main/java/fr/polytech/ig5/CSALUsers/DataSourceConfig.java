package fr.polytech.ig5.CSALUsers;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class DataSourceConfig {
    
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

    @Bean(name="transactionManager")
	public PlatformTransactionManager txManager(){
	    DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(getDataSource());
	    return transactionManager;
	}	
}
