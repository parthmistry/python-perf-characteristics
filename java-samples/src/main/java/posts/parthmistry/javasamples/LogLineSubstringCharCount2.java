package posts.parthmistry.javasamples;

import java.nio.file.Files;
import java.nio.file.Paths;

public class LogLineSubstringCharCount2 {

    public static void main(String[] args) throws Exception {
        var logFilePath = args[0];

        var charCount = 0L;

        try (var reader = Files.newBufferedReader(Paths.get(logFilePath))) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                var requestOnIndex = line.indexOf("REQUEST on");
                if (requestOnIndex != -1) {
                    var substring = line.substring(requestOnIndex).toUpperCase();
                    for (int i = 0; i < substring.length(); i++) {
                        var c = substring.charAt(i);
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
