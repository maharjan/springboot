package io.stack.pj.Setting;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * @author Prajin Maharjan
 * @version 1.0
 */
@Getter
@Setter
@ToString
@Component
@ConfigurationProperties("datasource")
@Validated
public class MainDbSetting extends AbstractDBSetting {

}