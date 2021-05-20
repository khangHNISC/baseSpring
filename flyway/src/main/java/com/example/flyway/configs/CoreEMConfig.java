package com.example.flyway.configs;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(basePackages = "com.example.flyway.core",
        entityManagerFactoryRef = "coreEMF",
        transactionManagerRef = "coreTM"
)
public class CoreEMConfig {

    @Bean(name = "coreEMF")
    LocalContainerEntityManagerFactoryBean entityManagerFactory(
            DataSource dataSource,
            EntityManagerFactoryBuilder builder) {

        return builder
                .dataSource(dataSource)
                .packages("com.example.flyway.core")
                .build();
    }

    @Bean(name = "coreTM")
    public PlatformTransactionManager transactionManager(
            @Qualifier("coreEMF") EntityManagerFactory emf) {

        return new JpaTransactionManager(emf);
    }
}
