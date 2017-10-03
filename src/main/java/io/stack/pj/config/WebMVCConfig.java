package io.stack.pj.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

/**
 * It will override the thymeleaf default configuration provided by spring boot.
 *
 * @author Prajin Maharjan
 * @version 1.0
 */
@Configuration
//@EnableWebMvc
public class WebMVCConfig extends WebMvcConfigurerAdapter implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    //
//    @Bean
//    public ViewResolver htmlViewResolver() {
//        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
//        SpringResourceTemplateResolver springResource = new SpringResourceTemplateResolver();
//        springResource.setApplicationContext(applicationContext);
//        springResource.setPrefix("templates/");
//        springResource.setCacheable(false);
//        springResource.setTemplateMode(TemplateMode.HTML);
//
//        resolver.setTemplateEngine(templateEngine(springResource));
//        resolver.setContentType("text/html");
//        resolver.setCharacterEncoding("UTF-8");
//        resolver.setViewNames(ArrayUtil.array("*.html", "*.jsp"));
//        return resolver;
//    }
//
//    @Bean
//    public ViewResolver cssViewResolver() {
//        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
//        SpringResourceTemplateResolver springResource = new SpringResourceTemplateResolver();
//        springResource.setApplicationContext(applicationContext);
//        springResource.setPrefix("static/");
//        springResource.setCacheable(false);
//        springResource.setTemplateMode(TemplateMode.CSS);
//
//        resolver.setTemplateEngine(templateEngine(springResource));
//        resolver.setContentType("text/css");
//        resolver.setCharacterEncoding("UTF-8");
//        resolver.setViewNames(ArrayUtil.array("*.css"));
//        return resolver;
//    }
//
//    @Bean
//    public ViewResolver javascriptViewResolver() {
//        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
//        SpringResourceTemplateResolver springResource = new SpringResourceTemplateResolver();
//        springResource.setApplicationContext(applicationContext);
//        springResource.setPrefix("static/");
//        springResource.setCacheable(false);
//        springResource.setTemplateMode(TemplateMode.JAVASCRIPT);
//
//        resolver.setTemplateEngine(templateEngine(springResource));
//        resolver.setContentType("application/javascript");
//        resolver.setCharacterEncoding("UTF-8");
//        resolver.setViewNames(ArrayUtil.array("*.js"));
//        return resolver;
//    }
//
    @Bean
    @Description("Spring Message Resolver")
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        return messageSource;
    }

    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
        localeResolver.setDefaultLocale(new Locale("en"));
        return localeResolver;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        return localeChangeInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**", "/static/**").addResourceLocations("/resources/", "/static/");
        registry.addResourceHandler("/favicon.ico").addResourceLocations("/static/");
    }

//    @Override
//    @Description("Custom Conversion Service")
//    public void addFormatters(FormatterRegistry registry) {
//        registry.addFormatter(new NameFormatter());
//    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }
//
//    private TemplateEngine templateEngine(ITemplateResolver templateResolver) {
//        SpringTemplateEngine engine = new SpringTemplateEngine();
//        engine.addDialect(new LayoutDialect(new GroupingStrategy()));
//        engine.addDialect(new Java8TimeDialect());
//        engine.setTemplateResolver(templateResolver);
//        engine.setTemplateEngineMessageSource(messageSource());
//        return engine;
//    }
}