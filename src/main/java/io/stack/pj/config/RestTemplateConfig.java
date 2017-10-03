package io.stack.pj.config;

import io.stack.pj.setting.MainRestTemplateSetting;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author Prajin Maharjan
 * @version 1.0
 */
@Configuration
public class RestTemplateConfig extends AbstractRestTemplateConfig{

    @Bean
    public RestTemplate defaultRestTemplate(MainRestTemplateSetting setting) {
        return initRestTemplate(setting);
    }
}
