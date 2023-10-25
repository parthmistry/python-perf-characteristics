package posts.parthmistry.javasamples;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Pattern;

public class LogLineRegexCount {

    public static void main(String[] args) throws Exception {
        var logFilePath = args[0];

        var logLineCount = 0L;

        var pattern = Pattern.compile("[\\d]+-[\\d]+-[\\d]+ [\\d]+:[\\d]+:[\\d]+\\.[\\d]+ .* REQUEST on (.*)");

        try (var reader = Files.newBufferedReader(Paths.get(logFilePath))) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                var matcher = pattern.matcher(line);
                if (matcher.matches()) {
                    logLineCount += 1;
                }
            }
        }

        System.out.println("matching log line count: " + logLineCount);
    }

}
