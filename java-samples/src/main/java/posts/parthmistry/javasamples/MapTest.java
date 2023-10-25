package posts.parthmistry.javasamples;

import posts.parthmistry.javasamples.utils.ElapsedTimeMonitor;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

public class MapTest {

    public static void main(String[] args) throws Exception {
        var uuidFilePath1 = args[0];
        var uuidFilePath2 = args[1];

        var uuidList = new ArrayList<String>();
        var uuidMap = new HashMap<String, Integer>();

        try (var uuidLines = Files.lines(Paths.get(uuidFilePath1))) {
            uuidLines.forEach(uuidList::add);
        }

        try (var uuidLines = Files.lines(Paths.get(uuidFilePath2))) {
            uuidLines.forEach(uuid -> uuidMap.put(uuid, 1));
        }

        var iterations = 20;

        var globalTimeMonitor = new ElapsedTimeMonitor();
        var iterationDurationSum = 0L;

        for (int i = 0; i < iterations; ++i) {
            var iterationTimeMonitor = new ElapsedTimeMonitor();
            var iterationSum = 0L;

            for (var uuid : uuidList) {
                var mapValue = uuidMap.get(uuid);
                if (mapValue != null) {
                    iterationSum += mapValue;
                }
            }

            var iterationDuration = iterationTimeMonitor.getElapsedTimeMillis();
            iterationDurationSum += iterationDuration;

            System.out.println("iteration sum: " + iterationSum);
            System.out.println("iteration duration: " + iterationDuration + " ms");
        }

        System.out.println("average iteration duration: " + (iterationDurationSum / ((double) iterations)) + " ms");
        System.out.println("global duration: " + globalTimeMonitor.getElapsedTimeMillis() + " ms");
    }

}
