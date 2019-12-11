package com.tuniu.ord.common.validator;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

import org.apache.commons.validator.GenericValidator;

import com.tuniu.ord.common.converter.WhiteSpaceConverter;
import com.tuniu.ord.core.Logging.Log4jLogger;
import com.tuniu.ord.core.Logging.LogFactory;
import com.tuniu.ord.core.Logging.LogMessageIdentifier;
import com.tuniu.ord.core.exception.IllegalArgumentException;
import com.tuniu.ord.core.exception.ValidationException;
import com.tuniu.ord.core.exception.ValidationException.ReasonEnum;

/**
 * @author fangzhongwei
 * 
 */
public final class ArgumentValidator {

    private static final Log4jLogger logger = LogFactory.getLogger(ArgumentValidator.class);

    private ArgumentValidator() {
    }

    public static void isNotNull(String name, Object value) {
        if (null == value) {
            String message = String.format("Parameter %s must not be null.", name);
            throw new IllegalArgumentException(message);
        }
    }

    public static void isNotEmpty(String name, String value) {
        isNotNull(name, value);

        value = WhiteSpaceConverter.convert(value);
        if (0 == value.trim().length()) {
            String message = String.format("Parameter %s must not be empty.", name);
            throw new IllegalArgumentException(message);
        }
    }

    public static void isNotNullEmpty(String name, Collection<?> collection) {
        isNotNull(name, collection);

        if (0 == collection.size()) {
            String message = String.format("Parameter %s must not be empty.", name);
            throw new IllegalArgumentException(message);
        }
    }

    public static void isPositiveNumber(String member, long inputValue) throws ValidationException {
        if (!(Long.valueOf(inputValue).compareTo(Long.valueOf(0)) >= 0)) {
            ValidationException vf = new ValidationException(ReasonEnum.POSITIVE_NUMBER, member, new Object[] { member,
                    Long.valueOf(inputValue) });
            logValidationFailure(vf);
            throw vf;
        }
    }

    public static void isPositiveAndNonZeroNumber(String member, long inputValue) throws ValidationException {
        if (!(Long.valueOf(inputValue).compareTo(Long.valueOf(0)) > 0)) {
            ValidationException vf = new ValidationException(ReasonEnum.POSITIVE_NON_ZERO_NUMBER, member, new Object[] {
                    member, Long.valueOf(inputValue) });
            logValidationFailure(vf);
            throw vf;
        }
    }

    public static void minLength(String member, String inputValue, int minValue, boolean flag) throws ValidationException {
        if (flag) {
            isNotEmpty(member, inputValue);
        } else {
            return;
        }

        if (inputValue.length() < minValue) {
            ValidationException vf = new ValidationException(ReasonEnum.MIN_LENGTH, member, new Object[] { member,
                    Integer.valueOf(inputValue), Integer.valueOf(minValue) });
            logValidationFailure(vf);
            throw vf;
        }
    }

    public static void maxLength(String member, String inputValue, int maxValue, boolean flag) throws ValidationException {
        if (flag) {
            isNotEmpty(member, inputValue);
        } else {
            return;
        }

        if (inputValue.length() > maxValue) {
            ValidationException vf = new ValidationException(ReasonEnum.MAX_LENGTH, member, new Object[] { member,
                    Integer.valueOf(inputValue), Integer.valueOf(maxValue) });
            logValidationFailure(vf);
            throw vf;
        }
    }

    public static void isLength(String member, String inputValue, int length, boolean flag) throws ValidationException {
        if (flag) {
            isNotEmpty(member, inputValue);
        } else {
            return;
        }

        if (inputValue.length() != length) {
            ValidationException vf = new ValidationException(ReasonEnum.LENGTH, member, new Object[] { member,
                    Integer.valueOf(inputValue), Integer.valueOf(length) });
            logValidationFailure(vf);
            throw vf;
        }
    }

    public static void isInteger(String member, String inputValue) throws ValidationException {
        if (!GenericValidator.isInt(inputValue)) {
            ValidationException vf = new ValidationException(ReasonEnum.INTEGER, member, new Object[] { member,
                    Integer.valueOf(inputValue) });
            logValidationFailure(vf);
            throw vf;
        }
    }

    public static void isLong(String member, String inputValue) throws ValidationException {
        if (!GenericValidator.isLong(inputValue)) {
            ValidationException vf = new ValidationException(ReasonEnum.LONG, member, new Object[] { member,
                    Integer.valueOf(inputValue) });
            logValidationFailure(vf);
            throw vf;
        }
    }

    public static void isBoolean(String member, String inputValue) throws ValidationException {
        if (!("false".equals(inputValue.toLowerCase()) || "true".equals(inputValue.toLowerCase()))) {
            ValidationException vf = new ValidationException(ReasonEnum.BOOLEAN, member, new Object[] { member,
                    Integer.valueOf(inputValue) });
            logValidationFailure(vf);
            throw vf;
        }
    }

    public static void isDouble(String member, String inputValue) throws ValidationException {
        if (!GenericValidator.isDouble(inputValue)) {
            ValidationException vf = new ValidationException(ReasonEnum.DOUBLE, member, new Object[] { member,
                    Integer.valueOf(inputValue) });
            logValidationFailure(vf);
            throw vf;
        }
    }

    public static void isNotInRange(String member, BigDecimal inputValue, BigDecimal minValue, BigDecimal maxValue)
            throws ValidationException {
        if ((null != inputValue && null != minValue && inputValue.compareTo(minValue) == -1)
                || (null != inputValue && null != maxValue && inputValue.compareTo(maxValue) == 1)) {
            ValidationException vf = new ValidationException(ReasonEnum.VALUE_NOT_IN_RANGE, member, new Object[] { member,
                    inputValue, minValue, maxValue });
            logValidationFailure(vf);
            throw vf;
        }
    }

    public static void isNotInRange(String member, int inputValue, int minValue, int maxValue) throws ValidationException {
        if ((inputValue < minValue) || (inputValue > maxValue)) {
            ValidationException vf = new ValidationException(ReasonEnum.VALUE_NOT_IN_RANGE, member, new Object[] { member,
                    Integer.valueOf(inputValue), Integer.valueOf(minValue), Integer.valueOf(maxValue) });
            logValidationFailure(vf);
            throw vf;
        }
    }

    public static void isEqual(String member, BigDecimal inputValue, BigDecimal value) throws ValidationException {
        if (null != inputValue && null != value && inputValue.compareTo(value) != 0) {
            ValidationException vf = new ValidationException(ReasonEnum.VALUE_NOT_EQ, member, new Object[] { member,
                    inputValue, value });
            logValidationFailure(vf);
            throw vf;
        }
    }

    public static void isEqual(String member, int inputValue, int value) throws ValidationException {
        if (inputValue != value) {
            ValidationException vf = new ValidationException(ReasonEnum.VALUE_NOT_EQ, member, new Object[] { member,
                    Integer.valueOf(inputValue), Integer.valueOf(value) });
            logValidationFailure(vf);
            throw vf;
        }
    }

    public static void isValidDateRange(Date fromDate, Date toDate) throws ValidationException {
        isNotNull(null, fromDate);
        isNotNull(null, toDate);
        if (fromDate.after(toDate)) {
            ValidationException vf = new ValidationException(ReasonEnum.INVALID_DATE_RANGE, null, new Object[] { fromDate,
                    toDate });
            logValidationFailure(vf);
            throw vf;
        }
    }

    private static void logValidationFailure(ValidationException vf) {
        logger.warn(Log4jLogger.SYSTEM_LOG, vf, LogMessageIdentifier.WARN_VALIDATION_FAILED);
    }

    public static void isEquals(String firstStr, String secondStr) {
        isNotNull(null, firstStr);
        isNotNull(null, secondStr);
        if (!firstStr.equals(secondStr)) {
            throw new IllegalArgumentException("String is not equals");
        }
    }

    public static void isFitNumWithParams(int size, int length) {
        if (size != length) {
            String message = String.format("Parameters size is not fit");
            throw new IllegalArgumentException(message);
        }
    }

}
