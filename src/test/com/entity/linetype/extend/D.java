package test.com.entity.linetype.extend;

import test.com.entity.data.DatePeriod;
import test.com.entity.data.QuestionType;
import test.com.entity.data.ResponseType;
import test.com.entity.data.Service;
import test.com.entity.linetype.LineType;

public class D extends LineType {

    private DatePeriod datePeriod;

    public D(Service service, QuestionType questionType, ResponseType responseType, DatePeriod datePeriod) {
        super(service, questionType, responseType);
        this.datePeriod = datePeriod;
    }

    public D() {
    }

    public DatePeriod getDatePeriod() {
        return datePeriod;
    }

    public void setDatePeriod(DatePeriod datePeriod) {
        this.datePeriod = datePeriod;
    }

}