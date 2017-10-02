package io.stack.pj;

import io.stack.pj.config.MainConfig;
import io.stack.pj.shared.PropertyNames;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.SecurityFilterAutoConfiguration;
import org.springframework.boot.autoconfigure.session.SessionAutoConfiguration;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

import java.util.Properties;

/**
 * @author Prajin Maharjan
 * @version 1.0
 */
@Import(value = MainConfig.class)
@EnableAspectJAutoProxy(proxyTargetClass = true)
@SpringBootApplication(scanBasePackageClasses = {MainPackage.class},
        exclude = {DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class,
                HibernateJpaAutoConfiguration.class, SessionAutoConfiguration.class,
                SecurityAutoConfiguration.class, SecurityFilterAutoConfiguration.class})
public class SpringbootApplication extends SpringBootServletInitializer {

    static Properties getProperties() {
        Properties props = new Properties();
        props.put("spring.config.location", PropertyNames.APP_PROP_FILE);
        return props;
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringbootApplication.class)
                .properties(getProperties());
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(SpringbootApplication.class)
                .sources(SpringbootApplication.class)
                .properties(getProperties())
                .run(args);
    }
}
