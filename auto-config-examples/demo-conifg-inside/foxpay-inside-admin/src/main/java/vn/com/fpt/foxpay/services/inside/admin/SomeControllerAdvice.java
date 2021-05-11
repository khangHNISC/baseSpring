package vn.com.fpt.foxpay.services.inside.admin;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
@ConditionalOnProperty(prefix = "application", name = "micro-service-api.debugging",
        havingValue = "false", matchIfMissing = true)
class SomeControllerAdvice {
}
