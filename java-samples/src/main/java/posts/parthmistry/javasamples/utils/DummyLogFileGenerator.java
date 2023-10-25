package posts.parthmistry.javasamples.utils;

import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;

public class DummyLogFileGenerator {

    private static final Random threadRandom = new Random(10);

    private static final Random logMessagesRandom = new Random(10);

    private static final Random logTimeRandom = new Random(10);

    private static final List<String> logMessages = List.of(
            "scheduled task 1",
            "scheduled task 2",
            "REQUEST on /api/products",
            "REQUEST on /api/orders",
            "REQUEST on /api/users",
            "REQUEST on /api/register",
            "REQUEST on /api/login",
            "some cleanup"
    );

    private static LocalDateTime logTime = LocalDateTime.of(2023, Month.JANUARY, 1, 0, 0, 0);

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    public static void main(String[] args) throws Exception {
        var logLineCount = Long.parseLong(args[0]);
        var logFilePath = args[1];

        try (var writer = new PrintWriter(Files.newBufferedWriter(Paths.get(logFilePath)))) {
            for (long i = 0; i < logLineCount; i++) {
                writer.println(nextLogTime() + " [" + nextThreadId() + "] : " + nextLogMessage());
            }
        }
    }

    private static String nextThreadId() {
        return "thread-" + threadRandom.nextInt(10);
    }

    private static String nextLogMessage() {
        var logMessageIndex = logMessagesRandom.nextInt(logMessages.size());
        return logMessages.get(logMessageIndex);
    }

    private static String nextLogTime() {
        var additionalMillis = logTimeRandom.nextInt(100) + 1;
        logTime = logTime.plus(additionalMillis, ChronoUnit.MILLIS);
        return dateTimeFormatter.format(logTime);
    }

}
