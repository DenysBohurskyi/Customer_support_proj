package test.com.util.validation;

import test.com.exception.InvalidDataException;
import test.com.util.constant.Constants;

public class Validation {
    private static String SERVICE_REGEX = "(([1-9]|10)(\\.[1-3])?)|\\*";
    private static String QUESTION_TYPE_REGEX = "(([1-9]|10)(\\.([1-9]|1[0-9]|20)(\\.[1-5])?)?)|\\*";
    private static String RESPONSE_TYPE_REGEX = "P|N|\\*";

    public static String DATE_SPLITTER = "\\-";
    public static String LINE_SPLITTER = " ";
    public static String TIME_TO_WAIT_REGEX = "\\d{1,6}";
    public static String DATE_REGEX = "dd.MM.yyyy";
    public static String NUMBER_SPLITTER = "\\.";

    public static boolean validationService(String query) throws InvalidDataException {
        if (query.matches(SERVICE_REGEX)) {
            return true;
        }
        throw new InvalidDataException(Constants.INVALID_DATA);
    }

    public static boolean validationQuestionType(String query) throws InvalidDataException {
        if (query.matches(QUESTION_TYPE_REGEX)) {
            return true;
        }
        throw new InvalidDataException(Constants.INVALID_DATA);
    }

    public static boolean validationResponseType(String query) throws InvalidDataException {
        if (query.matches(RESPONSE_TYPE_REGEX)) {
            return true;
        }
        throw new InvalidDataException(Constants.INVALID_DATA);
    }
}
