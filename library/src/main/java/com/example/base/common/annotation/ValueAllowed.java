package com.example.base.common.annotation;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.Arrays;
import java.util.List;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by khangld5 on Apr 08, 2021
 */
@Constraint(validatedBy = ValueAllowed.Validator.class)
@Target({PARAMETER})
@Retention(RUNTIME)
@Documented
public @interface ValueAllowed {

    String property();

    String[] values();

    String message() default "the property does not match allowable list";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class Validator implements ConstraintValidator<ValueAllowed, Object> {

        private String code;

        private String message;

        private List<String> allowable;

        @Override
        public void initialize(ValueAllowed constraintAnnotation) {
            code = constraintAnnotation.property();
            allowable = Arrays.asList(constraintAnnotation.values());
            message = constraintAnnotation.message();
        }

        @Override
        public boolean isValid(Object o, ConstraintValidatorContext context) {
            BeanWrapper wrapper = PropertyAccessorFactory.forBeanPropertyAccess(o);
            String partnerCode = (String) wrapper.getPropertyValue(code);
            boolean valid = StringUtils.isNotBlank(partnerCode) && allowable.contains(partnerCode);
            if (!valid) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(message.concat(this.allowable.toString()))
                        .addPropertyNode(this.code).addConstraintViolation();
            }
            return valid;
        }
    }
}
