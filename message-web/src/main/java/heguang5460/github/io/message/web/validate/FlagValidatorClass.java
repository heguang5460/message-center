package heguang5460.github.io.message.web.validate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 状态标记校验器
 *
 * @author heguang
 */
public class FlagValidatorClass implements ConstraintValidator<FlagValidator, String> {

    private String[] values;

    @Override
    public void initialize(FlagValidator flagValidator) {
        this.values = flagValidator.value();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        boolean isValid = false;
        for (int i = 0; i < values.length; i++) {
            if (values[i].equals(value)) {
                isValid = true;
                break;
            }
        }
        return isValid;
    }
}
