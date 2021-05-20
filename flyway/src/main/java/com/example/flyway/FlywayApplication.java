package com.example.flyway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
class FlywayApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlywayApplication.class, args);
    }

    /**
     * fail attempt for run fly way script toward specific DB
     * There is @FlywayDataSource but not working.
     * set spring.flyway.url=jdbc:h2:mem:testdb for single data source only
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.url")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public EntityManagerFactoryBuilder entityManagerFactoryBuilder() {

        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setShowSql(false);
        adapter.setDatabase(Database.POSTGRESQL);

        Map<String, Object> vendorProperties = new HashMap<>();
        vendorProperties.put("hibernate.format_sql", true);
        vendorProperties.put("hibernate.temp.use_jdbc_metadata_defaults", false);

        return new EntityManagerFactoryBuilder(adapter, vendorProperties, null);
    }
}
