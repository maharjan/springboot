package io.stack.pj.Setting;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Prajin Maharjan
 * @version 1.0
 */
@Component
@ConfigurationProperties("resttemplate")
public class MainRestTemplateSetting extends AbstractRestTemplateSetting{

}
