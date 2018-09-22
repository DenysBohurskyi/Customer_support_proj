package test.com;

import test.com.initializer.Initializer;
import test.com.entity.data.wrapper.Data;
import test.com.controller.Controller;
import test.com.util.parser.Parser;
import test.com.util.reader.Reader;
import test.com.util.writer.Writer;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class Main {

    private Parser parser;
    private Reader reader;

    public Main(Parser parser, Reader reader) {
        this.parser = parser;
        this.reader = reader;
    }

    public static void main(String[] args) throws IOException, ParseException {

        Initializer initializer = new Initializer();
        initializer.init();
    }

    public void process() throws IOException, ParseException {
        String pathToFile = reader.readPathToFile();
        Data data = parser.parseFileData(pathToFile);
        Controller controller = new Controller();
        List<String> averageWaitings = controller.averageWaitingTimes(data.getQueriesAndItsWaitingTimeLines());
        for (String averageWaiting : averageWaitings) {
            Writer.write(averageWaiting);
        }

    }
}
