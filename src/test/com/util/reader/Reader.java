package test.com.util.reader;

import test.com.util.constant.Constants;
import test.com.util.writer.Writer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;

public class Reader {

    private Scanner scanner;
    private Writer writer;

    public Reader(Scanner scanner, Writer writer) {
        this.scanner = scanner;
        this.writer = writer;
    }

    public String readPathToFile() {
        String pathToFile = null;
        Writer.write(Constants.SPECIFY_PATH_TO_FILE);
        if (scanner.hasNext()) {
            pathToFile = scanner.next();
        }
        return pathToFile;
    }

    public int readLinesCount(String pathToFile) {
        int linesCount = -1;
        try (Stream<String> lines = Files.lines(Paths.get(pathToFile))) {
            linesCount = Integer.parseInt(lines.findFirst().get());
        } catch (IOException ex) {
            ex.printStackTrace();

        }
        return linesCount;
    }
}
