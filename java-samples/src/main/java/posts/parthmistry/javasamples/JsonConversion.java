package posts.parthmistry.javasamples;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import posts.parthmistry.javasamples.utils.ElapsedTimeMonitor;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class JsonConversion {

    public static void main(String[] args) throws Exception {
        var logFilePath = args[0];
        var totalRecordsToProcess = Integer.parseInt(args[1]);

        var mapper = new ObjectMapper();
        var pattern = Pattern.compile("(.*) \\[(.*?)] : (.*)");

        var rawJsonStringList = new ArrayList<String>(totalRecordsToProcess);
        var parsedMaps = new ArrayList<HashMap<String, String>>(totalRecordsToProcess);

        try (var reader = Files.newBufferedReader(Paths.get(logFilePath))) {
            for (int i = 0; i < totalRecordsToProcess; i++) {
                String line = reader.readLine();

                var matcher = pattern.matcher(line);
                if (matcher.matches()) {
                    var recordMap = new LinkedHashMap<String, String>();
                    recordMap.put("time", matcher.group(1));
                    recordMap.put("thread", matcher.group(2));
                    recordMap.put("message", matcher.group(3));
                    rawJsonStringList.add(mapper.writeValueAsString(recordMap));
                    parsedMaps.add(recordMap);
                }
            }
        }

        var iterations = 20;

        var globalTimeMonitor = new ElapsedTimeMonitor();
        var iterationDurationSum = 0L;

        for (int n = 0; n < iterations; n++) {
            var iterationTimeMonitor = new ElapsedTimeMonitor();
            var jsonStringList = new ArrayList<String>(totalRecordsToProcess);

            for (var parsedMap : parsedMaps) {
                jsonStringList.add(mapper.writeValueAsString(parsedMap));
            }

            var iterationDuration = iterationTimeMonitor.getElapsedTimeMillis();
            iterationDurationSum += iterationDuration;

            System.out.println("list size: " + jsonStringList.size());
            System.out.println("serialize iteration duration: " + iterationDuration + " ms");
        }

        System.out.println("average iteration duration: " + (iterationDurationSum / ((double) iterations)) + " ms");
        System.out.println("global duration: " + globalTimeMonitor.getElapsedTimeMillis() + " ms");

        globalTimeMonitor = new ElapsedTimeMonitor();
        iterationDurationSum = 0L;

        for (int n = 0; n < iterations; n++) {
            var iterationTimeMonitor = new ElapsedTimeMonitor();
            var deserializedList = new ArrayList<Map<String, String>>(totalRecordsToProcess);
            var typeReference = new TypeReference<Map<String, String>>() {};

            for (var rawJsonString : rawJsonStringList) {
                deserializedList.add(mapper.readValue(rawJsonString, typeReference));
            }

            var iterationDuration = iterationTimeMonitor.getElapsedTimeMillis();
            iterationDurationSum += iterationDuration;

            System.out.println("list size: " + deserializedList.size());
            System.out.println("deserialize iteration duration: " + iterationDuration + " ms");
        }

        System.out.println("average iteration duration: " + (iterationDurationSum / ((double) iterations)) + " ms");
        System.out.println("global duration: " + globalTimeMonitor.getElapsedTimeMillis() + " ms");
    }

}
