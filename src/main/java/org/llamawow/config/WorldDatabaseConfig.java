package org.llamawow.config;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

public class WorldDatabaseConfig {

    @Bean(name = "worldDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.world")
    public DataSource dataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    @Bean(name = "worldEntityManager")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("worldDataSource") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("org.llamawow.entity") // Ajustado para worldEntity
                .persistenceUnit("worldPU")
                .build();
    }

    @Bean(name = "worldTransactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("worldEntityManager") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
