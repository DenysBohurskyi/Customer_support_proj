package test.com.entity.linetype.extend;

import test.com.entity.data.QuestionType;
import test.com.entity.data.ResponseType;
import test.com.entity.data.Service;
import test.com.entity.linetype.LineType;

import java.util.Date;

public class C extends LineType {

    private Date date;
    private int timeToWait;

    public C(Service service, QuestionType questionType, ResponseType responseType) {
        super(service, questionType, responseType);
    }

    public C(Service service, QuestionType questionType, ResponseType responseType, Date date, int timeToWait) {
        super(service, questionType, responseType);
        this.date = date;
        this.timeToWait = timeToWait;
    }

    public C() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getTimeToWait() {
        return timeToWait;
    }

    public void setTimeToWait(int timeToWait) {
        this.timeToWait = timeToWait;
    }

    @Override
    public String toString() {
        return super.toString() + "C{" + "date=" + date + ", timeToWait=" + timeToWait + '}';
    }
}

