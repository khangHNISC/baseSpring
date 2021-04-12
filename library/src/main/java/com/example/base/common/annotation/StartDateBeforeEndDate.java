package com.example.base.common.annotation;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.messaging.handler.annotation.Payload;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
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

    String message() default "Start date must before End date";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class Validator implements ConstraintValidator<StartDateBeforeEndDate, Object> {

        @Override
        public boolean isValid(Object value, ConstraintValidatorContext context) {
            final Class<?> clazz = value.getClass();
            final List<Field> startDates = FieldUtils.getFieldsListWithAnnotation(clazz, StartDate.class);
            final List<Field> endDates = FieldUtils.getFieldsListWithAnnotation(clazz, EndDate.class);
            String startDateField = startDates.get(0).getName();
            String endDateField = endDates.get(0).getName();
            BeanWrapper wrapper = PropertyAccessorFactory.forBeanPropertyAccess(value);
            Date start = (Date) wrapper.getPropertyValue(startDateField);
            Date end = (Date) wrapper.getPropertyValue(endDateField);
            if (Objects.nonNull(start) && Objects.nonNull(end)) {
                return start.before(end);
            }
            return Objects.isNull(start) && Objects.isNull(end);
        }
    }
}
