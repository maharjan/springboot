package io.stack.pj.annotation;

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
@Documented
@Constraint(validatedBy = {})
@ReportAsSingleViolation
@Pattern.List({
        @Pattern(regexp = "[9][8|7][0-9]{8}")
})
public @interface MobileNumber {

    String message() default "Invalid Mobile Number.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}