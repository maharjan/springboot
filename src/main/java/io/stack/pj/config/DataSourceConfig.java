package io.stack.pj.config;

import io.stack.pj.MainPackage;
import io.stack.pj.Setting.MainDbSetting;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Configuration
@EnableSpringDataWebSupport
@EnableTransactionManagement
@EnableJpaRepositories(basePackageClasses = {MainPackage.class},
        entityManagerFactoryRef = "emf", transactionManagerRef = "txnManager")
public class DataSourceConfig extends AbstractDataConfig {

    private static final String PU_NAME = "springboot";
    private static final String DATASOURCE_NAME = "springbootDS";

    private static final String[] PACKAGES = {MainPackage.class.getPackage().getName()};

    @Primary
    @Bean(name = DATASOURCE_NAME)
    public DataSource coreDataSource(final MainDbSetting dbSetting) {
        return buildDataSource(dbSetting);
    }

    @Bean(name = "emf")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(@Qualifier(value = DATASOURCE_NAME) DataSource dataSource) {
        return buildEntityManagerFactory(dataSource, PU_NAME, PACKAGES);
    }

    @Bean(name = "txnManager")
    public PlatformTransactionManager transactionManager(@Qualifier(value = "emf") EntityManagerFactory emf) {
        return buildTransactionManager(emf);
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

}
