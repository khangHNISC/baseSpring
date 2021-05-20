package com.example.flyway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
class FlywayApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlywayApplication.class, args);
    }

    @Bean
    public EntityManagerFactoryBuilder entityManagerFactoryBuilder() {

        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setShowSql(false);
        adapter.setDatabase(Database.POSTGRESQL);

        Map<String, Object> vendorProperties = new HashMap<>();
        vendorProperties.put("hibernate.format_sql", true);
        vendorProperties.put("hibernate.temp.use_jdbc_metadata_defaults", false);

        return new EntityManagerFactoryBuilder(adapter, vendorProperties,null);
    }
}
