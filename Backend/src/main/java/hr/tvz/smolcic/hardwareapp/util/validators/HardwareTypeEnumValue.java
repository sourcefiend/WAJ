package hr.tvz.smolcic.hardwareapp.util.validators;

import hr.tvz.smolcic.hardwareapp.enums.HardwareType;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = HardwareTypeEnumValueValidator.class)
public @interface HardwareTypeEnumValue {
    Class<? extends Enum<?>> enumClass();
    String message() default "Hardware type must be any value of enum {enumClass}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
