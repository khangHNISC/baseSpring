package vn.com.fpt.foxpay.services.inside.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"vn.com.fpt.foxpay.services.common", "vn.com.fpt.foxpay.services.inside.admin"})
@SpringBootApplication
public class FoxpayInsideAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(FoxpayInsideAdminApplication.class, args);
    }

}
