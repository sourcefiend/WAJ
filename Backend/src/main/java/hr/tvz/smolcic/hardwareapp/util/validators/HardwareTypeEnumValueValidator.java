package hr.tvz.smolcic.hardwareapp.util.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HardwareTypeEnumValueValidator implements ConstraintValidator<HardwareTypeEnumValue, CharSequence> {
    private List<String> acceptedValues;

    @Override
    public void initialize(HardwareTypeEnumValue annotation) {
        acceptedValues = Stream.of(annotation.enumClass().getEnumConstants()).map(Enum::name).collect(Collectors.toList());
    }

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        return acceptedValues.contains(value.toString());
    }
}
