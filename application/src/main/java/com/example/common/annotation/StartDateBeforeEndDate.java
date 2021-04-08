package com.example.common.annotation;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.messaging.handler.annotation.Payload;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.Date;
import java.util.Objects;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by khangld5 on Mar 12, 2021
 */
@Target({TYPE, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = StartDateBeforeEndDate.Validator.class)
@Documented
public @interface StartDateBeforeEndDate {

    String startDate();

    String endDate();

    String message() default "{start date must before end date}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class Validator implements ConstraintValidator<StartDateBeforeEndDate, Object> {

        private String startDate;
        private String endDate;

        @Override
        public void initialize(StartDateBeforeEndDate constraintAnnotation) {
            startDate = constraintAnnotation.startDate();
            endDate = constraintAnnotation.endDate();
        }

        @Override
        public boolean isValid(Object value, ConstraintValidatorContext context) {
            BeanWrapper wrapper = PropertyAccessorFactory.forBeanPropertyAccess(value);
            Date start = (Date) wrapper.getPropertyValue(this.startDate);
            Date end = (Date) wrapper.getPropertyValue(this.endDate);
            if (Objects.nonNull(start) && Objects.nonNull(end)) {
                return start.before(end);
            }
            return Objects.isNull(start) && Objects.isNull(end);
        }
    }
}
