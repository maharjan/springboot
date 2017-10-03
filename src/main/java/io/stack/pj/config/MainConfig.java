package io.stack.pj.config;

import io.stack.pj.MainPackage;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Imports all bean classes annotated with @Configuration just as in Spring XML.
 *
 * @author Prajin Maharjan
 * @since 1.0
 */
@Configuration
@Import(value = {
        DataSourceConfig.class,
        SwaggerConfig.class,
        AsyncExecutorConfig.class,
        SecurityConfig.class,
        RestTemplateConfig.class,
        JavaMelodyConfig.class
})
@ComponentScan(basePackageClasses = {MainPackage.class})
public class MainConfig {
}
