package posts.parthmistry.javasamples;

import java.nio.file.Files;
import java.nio.file.Paths;

public class LogLineCountWithLineSizeSum {

    public static void main(String[] args) throws Exception {
        var logFilePath = args[0];

        var logLineCount = 0L;
        var lineSizeSum = 0L;

        try (var reader = Files.newBufferedReader(Paths.get(logFilePath))) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                logLineCount += 1;
                lineSizeSum += line.length();
            }
        }

        System.out.println("log line count: " + logLineCount);
        System.out.println("log line size sum: " + lineSizeSum);
    }

}
