package test.com.util.parser;

import test.com.entity.data.*;
import test.com.entity.data.wrapper.Data;
import test.com.entity.linetype.extend.C;
import test.com.entity.linetype.extend.D;
import test.com.exception.InvalidDataException;
import test.com.util.constant.Constants;
import test.com.util.reader.Reader;
import test.com.util.validation.Validation;
import test.com.util.writer.Writer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Parser {

    private Reader reader;

    public Parser(Reader reader) {
        this.reader = reader;
    }

    public Data parseFileData(String pathToFile) throws IOException, ParseException {
        Data data = new Data();
        int linesCount = reader.readLinesCount(pathToFile);
        data.setLinesCount(linesCount);
        Map<D, List<C>> waitingTimeWithQueries = new LinkedHashMap<>();
        List<C> timeToWaitList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(pathToFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.matches(Validation.TIME_TO_WAIT_REGEX)) {
                    continue;
                }
                String[] lineContains = line.split(Validation.LINE_SPLITTER);
                if (LineTypeEnum.valueOf(lineContains[0]) == LineTypeEnum.C) {
                    C c = new C();
                    c.setService(checkValidationService(lineContains[1]));
                    c.setQuestionType(checkValidationQuestionType(lineContains[2]));
                    c.setResponseType(checkValidationResponseType(lineContains[3]));
                    c.setDate(parseDate(lineContains[4]));
                    c.setTimeToWait(parseWaitingTime(lineContains[5]));
                    timeToWaitList.add(c);
                } else if (LineTypeEnum.valueOf(lineContains[0]) == LineTypeEnum.D) {
                    D d = new D();
                    d.setService(checkValidationService(lineContains[1]));
                    d.setQuestionType(checkValidationQuestionType(lineContains[2]));
                    d.setResponseType(checkValidationResponseType(lineContains[3]));
                    d.setDatePeriod(parseDatePeriod(lineContains[4]));
                    waitingTimeWithQueries.put(d, new ArrayList(timeToWaitList));
                }
            }
        }
        data.setQueriesAndItsWaitingTimeLines(waitingTimeWithQueries);
        return data;
    }

    private Service checkValidationService(String serviceQuery){
        Service service = null;
        try {
            if(Validation.validationService(serviceQuery)) {
                service = parseServiceAndVariation(serviceQuery);
            }
        } catch (InvalidDataException ex) {
            Writer.writeExeption(ex.getMessage());
        }
        return service;
    }

    private QuestionType checkValidationQuestionType(String questionTypeQuery){
        QuestionType questionType = null;
        try {
            if(Validation.validationQuestionType(questionTypeQuery)) {
                questionType = parseQuestionTypeAndCategoryAndSubcategory(questionTypeQuery);
            }
        } catch (InvalidDataException ex) {
            Writer.writeExeption(ex.getMessage());
        }
        return questionType;
    }

    private ResponseType checkValidationResponseType(String responseTypeQuery){
        ResponseType responseType = null;
        try {
            if(Validation.validationResponseType(responseTypeQuery)) {
                responseType = parseResponseType(responseTypeQuery);
            }
        } catch (InvalidDataException ex) {
            Writer.writeExeption(ex.getMessage());
        }
        return responseType;
    }

    private DatePeriod parseDatePeriod(String datePeriodString) throws ParseException {
        String[] datePeriods = datePeriodString.split(Validation.DATE_SPLITTER);
        String dateFrom;
        String dateTo;
        DatePeriod datePeriod;
        if (datePeriods.length == 1) {
            dateFrom = datePeriods[0];
            datePeriod = new DatePeriod(parseDate(dateFrom), null);
            return datePeriod;
        } else {
            dateFrom = datePeriods[0];
            dateTo = datePeriods[1];
        }
        return new DatePeriod(parseDate(dateFrom), parseDate(dateTo));
    }

    private int parseWaitingTime(String waitingTime) {
        return Integer.parseInt(waitingTime);
    }

    private Date parseDate(String date) throws ParseException {
        return new SimpleDateFormat(Validation.DATE_REGEX).parse(date);
    }

    private ResponseType parseResponseType(String responseType) {
        return ResponseType.valueOf(responseType);
    }

    private Service parseServiceAndVariation(String lineContain) {
        String[] serviceContains = lineContain.split(Validation.NUMBER_SPLITTER);
        Service service;
        String serviceId = serviceContains[0];
        if (serviceHasVariation(serviceContains)) {
            service = new Service(serviceId, createVariation(serviceContains[1]));
        } else {
            service = new Service(serviceId, null);
        }
        return service;
    }

    private QuestionType parseQuestionTypeAndCategoryAndSubcategory(String lineContain) {
        String[] questionTypeContains = lineContain.split(Validation.NUMBER_SPLITTER);
        QuestionType questionType;
        String questionTypeId = parseQuestionTypeId(questionTypeContains[0]);
        if (categoryHasSubCategory(questionTypeContains)) {
            questionType = new QuestionType(questionTypeId, createCategory(questionTypeContains[1], questionTypeContains[2]));
        } else if (questionTypeHasCategory(questionTypeContains)) {
            questionType = new QuestionType(questionTypeId, createCategory(questionTypeContains[1]));
        } else {
            questionType = new QuestionType(questionTypeId);
        }
        return questionType;
    }

    private QuestionType.Category createCategory(String categoryId) {
        return new QuestionType.Category(categoryId, null);
    }

    private boolean questionTypeHasCategory(String[] questionTypeContains) {
        return questionTypeContains.length == Constants.ELEMENTS_COUNT;
    }

    private QuestionType.Category.SubCategory createSubCategory(String subCategoryId) {
        return new QuestionType.Category.SubCategory(subCategoryId);
    }

    private QuestionType.Category createCategory(String categoryId, String subCategoryId) {
        return new QuestionType.Category(categoryId, createSubCategory(subCategoryId));
    }

    private String parseQuestionTypeId(String questionTypeContain) {
        return questionTypeContain;
    }

    private boolean categoryHasSubCategory(String[] questionTypeContains) {
        return questionTypeContains.length > Constants.ELEMENTS_COUNT;
    }

    private boolean serviceHasVariation(String[] serviceContains) {
        return serviceContains.length == Constants.ELEMENTS_COUNT;
    }

    private Service.Variant createVariation(String variationId) {
        return new Service.Variant(variationId);
    }


}
