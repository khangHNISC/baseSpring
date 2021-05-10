package vn.com.fpt.foxpay.services.inside.admin;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import vn.com.fpt.foxpay.services.common.PackageConfig;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by khangld5 on May 10, 2021
 */
@SpringBootTest
class FoxpayInsideAdminApplicationTest {

    @Autowired
    ConfigurableApplicationContext context;

    @Test
    public void loadConfiguration(){
        assertThat(context.getBeansOfType(PackageConfig.class)).hasSize(1);
    }
}