package posts.parthmistry.javasamples;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Pattern;

public class LogLineRegexWithCharCount {

    public static void main(String[] args) throws Exception {
        var logFilePath = args[0];

        var charCount = 0L;

        var pattern = Pattern.compile("[\\d]+-[\\d]+-[\\d]+ [\\d]+:[\\d]+:[\\d]+\\.[\\d]+ .* REQUEST on (.*)");

        try (var reader = Files.newBufferedReader(Paths.get(logFilePath))) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                var matcher = pattern.matcher(line);
                if (matcher.matches()) {
                    var matchedGroup = matcher.group(1).toUpperCase();
                    for (int i = 0; i < matchedGroup.length(); i++) {
                        var c = matchedGroup.charAt(i);
                        if (c == 'A' || c == 'E') {
                            charCount += 1;
                        }
                    }
                }
            }
        }

        System.out.println("char count: " + charCount);
    }

}
