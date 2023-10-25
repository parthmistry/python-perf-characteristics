package posts.parthmistry.javasamples.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class ConvertTextLogFileToJsonLogFile {

    public static void main(String[] args) throws Exception {
        var logFilePath = args[0];
        var jsonLogFilePath = args[1];

        var mapper = new ObjectMapper();

        var pattern = Pattern.compile("(.*) \\[(.*?)] : (.*)");

        try (var reader = Files.newBufferedReader(Paths.get(logFilePath));
             var writer = new PrintWriter(Files.newBufferedWriter(Paths.get(jsonLogFilePath)))) {

            String line = null;
            while ((line = reader.readLine()) != null) {
                var matcher = pattern.matcher(line);
                if (matcher.matches()) {
                    var recordMap = new LinkedHashMap<String, String>();
                    recordMap.put("time", matcher.group(1));
                    recordMap.put("thread", matcher.group(2));
                    recordMap.put("message", matcher.group(3));
                    writer.println(mapper.writeValueAsString(recordMap));
                }
            }
        }
    }

}
