package vn.com.fpt.foxpay.services.inside.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import vn.com.fpt.foxpay.services.common.ApplicationProperties;

@SpringBootApplication(scanBasePackages =
        {"vn.com.fpt.foxpay.services.common", "vn.com.fpt.foxpay.services.inside.admin"})
@EnableConfigurationProperties(ApplicationProperties.class)
public class FoxpayInsideAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(FoxpayInsideAdminApplication.class, args);
    }

}
