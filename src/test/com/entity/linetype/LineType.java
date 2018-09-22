package test.com.entity.linetype;

import test.com.entity.data.QuestionType;
import test.com.entity.data.ResponseType;
import test.com.entity.data.Service;

public abstract class LineType {

    private Service service;
    private QuestionType questionType;
    private ResponseType responseType;

    public LineType(Service service, QuestionType questionType, ResponseType responseType) {
        this.service = service;
        this.questionType = questionType;
        this.responseType = responseType;
    }

    public LineType() {
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }

    public ResponseType getResponseType() {
        return responseType;
    }

    public void setResponseType(ResponseType responseType) {
        this.responseType = responseType;
    }

    @Override
    public String toString() {
        return "LineType{" +
                "controller=" + service +
                ", questionType=" + questionType +
                ", responseType=" + responseType +
                '}';
    }
}

