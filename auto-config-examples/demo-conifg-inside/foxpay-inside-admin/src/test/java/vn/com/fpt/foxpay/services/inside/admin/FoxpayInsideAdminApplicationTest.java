package vn.com.fpt.foxpay.services.inside.admin;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.ConfigurableApplicationContext;
import vn.com.fpt.foxpay.services.common.SomeConfig;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by khangld5 on May 10, 2021
 */
@WebMvcTest
class FoxpayInsideAdminApplicationTest {

    @Autowired
    ConfigurableApplicationContext context;

    @Test
    void loadConfiguration(){
        assertThat(context.getBeansOfType(SomeConfig.class)).hasSize(1);
    }
}