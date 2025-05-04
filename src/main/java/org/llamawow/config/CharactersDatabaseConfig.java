package org.llamawow.config;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//*********************************//
//  Web Hecha por EduardoCruzDev   //
//*********************************//

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "org.llamawow.repository.characters",
        entityManagerFactoryRef = "charactersEntityManager",
        transactionManagerRef = "charactersTransactionManager"
)
public class CharactersDatabaseConfig {

    @Bean(name = "charactersDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.characters")
    public DataSource dataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    @Bean(name = "charactersEntityManager")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("charactersDataSource") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("org.llamawow.entity") // Ajustado para CharactersEntity
                .persistenceUnit("charactersPU")
                .build();
    }

    @Bean(name = "charactersTransactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("charactersEntityManager") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}