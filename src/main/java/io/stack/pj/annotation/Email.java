package io.stack.pj.annotation;

import io.stack.pj.util.RegexUtil;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.Pattern;
import java.lang.annotation.*;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Target({ElementType.ANNOTATION_TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@ReportAsSingleViolation
@Documented
@Pattern(regexp = RegexUtil.EMAIL_PATTERN)
public @interface Email {

    String message() default "Invalid email address.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
