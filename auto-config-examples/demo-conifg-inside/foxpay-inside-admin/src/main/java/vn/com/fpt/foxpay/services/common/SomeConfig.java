package vn.com.fpt.foxpay.services.common;

import io.github.jhipster.config.JHipsterProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by khangld5 on May 10, 2021
 */
@Configuration
public class SomeConfig {
    final JHipsterProperties properties;

    public SomeConfig(JHipsterProperties properties) {
        this.properties = properties;
    }
}
