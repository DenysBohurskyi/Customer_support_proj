package test.com.controller;

import test.com.entity.data.DatePeriod;
import test.com.entity.data.QuestionType;
import test.com.entity.linetype.extend.C;
import test.com.entity.linetype.extend.D;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Controller {

    public List<String> averageWaitingTimes(Map<D, List<C>> data) {
        List<String> averageTimes = new ArrayList<>();
        Set<D> queries = data.keySet();
        for (D key : queries) {
            double average = 0;
            int i = 0;
            List<C> values = data.get(key);
            for (C value : values) {
                boolean valid = checkCmatchesD(key, value);
                if (valid) {
                    DatePeriod datePeriod = key.getDatePeriod();
                    if (datePeriod.consistsInPeriod(value.getDate())) {
                        average += value.getTimeToWait();
                        i++;
                    }
                }
            }
            if (average != 0) {
                average /= i;
                averageTimes.add(String.valueOf(roundingAverageWaitingTime(average)));
            } else {
                averageTimes.add("-");
            }

        }
        return averageTimes;
    }

    private int roundingAverageWaitingTime(double waitingTime){
        return (int)waitingTime;
    }

    private boolean checkCmatchesD(D query, C timeLine) {
        return checkService(query.getService(), timeLine.getService())
                && checkQuestionType(query.getQuestionType(), timeLine.getQuestionType())
                && checkResponceType(query.getResponseType().toString(), timeLine.getResponseType().toString());
    }

    private boolean checkQuestionType(QuestionType queryQuestionType, QuestionType timeLineQuestionType) {
        boolean result = false;
        if ("*".equals(queryQuestionType.getId())) {
            result = true;
        } else {
            result = queryQuestionType.equals(timeLineQuestionType);
        }
        return result;
    }

    private boolean checkService(test.com.entity.data.Service queryService, test.com.entity.data.Service timeLineService) {
        boolean result = false;
        if ("*".equals(queryService.getId())) {
            result = true;
        } else {
            result = queryService.equals(timeLineService);
        }
        return result;
    }

    private boolean checkResponceType(String queryResponceType, String timeLineResponceType) {
        boolean result = false;
        if ("*".equals(queryResponceType)) {
            result = true;
        } else {
            result = queryResponceType.equals(timeLineResponceType);
        }
        return result;
    }
}
