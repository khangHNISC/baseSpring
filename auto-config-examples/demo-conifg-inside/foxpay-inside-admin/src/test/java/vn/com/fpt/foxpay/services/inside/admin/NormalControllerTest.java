package vn.com.fpt.foxpay.services.inside.admin;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.ConfigurableApplicationContext;
import vn.com.fpt.foxpay.services.common.SecurityConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

@WebMvcTest(
        controllers = NormalController.class,
        properties = "application.micro-service-api.debugging=true")
class NormalControllerTest {

    @Autowired
    ConfigurableApplicationContext context;

    @Test
    void noLoadControllerAdvice() {
        assertThat(context.getBeansOfType(SomeControllerAdvice.class)).isEmpty();
    }

    @Test
    void noLoadSecurityConfig() {
        assertThat(context.getBeansOfType(SecurityConfiguration.class)).isEmpty();
    }
}