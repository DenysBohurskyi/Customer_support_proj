package test.com.initializer;

import test.com.Main;
import test.com.util.parser.Parser;
import test.com.util.reader.Reader;
import test.com.util.writer.Writer;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

public class Initializer {

    public void init() throws IOException, ParseException {
        Scanner scanner = new Scanner(System.in);
        Writer writer = new Writer();
        Reader reader = new Reader(scanner, writer);
        Parser parser = new Parser(reader);

        Main main = new Main(parser, reader);
        main.process();

    }

}
