package posts.parthmistry.javasamples;

import posts.parthmistry.javasamples.utils.ElapsedTimeMonitor;

import java.util.ArrayList;

public class IntegerStringConversion {

    public static void main(String[] args) {
        var iterations = 20;
        var batchSize = 10_000_000;

        var globalTimeMonitor = new ElapsedTimeMonitor();
        var iterationDurationSum = 0L;

        for (int n = 0; n < iterations; ++n) {
            var iterationTimeMonitor = new ElapsedTimeMonitor();
            long iterationSum = 0;

            for (int i = 0; i < batchSize; ++i) {
                var s = Integer.toString(i);
                iterationSum += s.length();
            }

            var iterationDuration = iterationTimeMonitor.getElapsedTimeMillis();
            iterationDurationSum += iterationDuration;

            System.out.println("iteration sum: " + iterationSum);
            System.out.println("iteration duration: " + iterationDuration + " ms");
        }

        System.out.println("average iteration duration: " + (iterationDurationSum / ((double) iterations)) + " ms");
        System.out.println("global duration: " + globalTimeMonitor.getElapsedTimeMillis() + " ms");
        System.out.println();

        var strList = new ArrayList<String>();

        for (int i = 0; i < batchSize; i++) {
            strList.add(Integer.toString(i));
        }

        globalTimeMonitor = new ElapsedTimeMonitor();
        iterationDurationSum = 0L;

        for (int n = 0; n < iterations; ++n) {
            var iterationTimeMonitor = new ElapsedTimeMonitor();
            long iterationSum = 0;

            for (var s : strList) {
                var num = Integer.parseInt(s);
                iterationSum += num;
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
