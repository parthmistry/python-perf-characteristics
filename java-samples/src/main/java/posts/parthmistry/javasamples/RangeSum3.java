package posts.parthmistry.javasamples;

import posts.parthmistry.javasamples.utils.ElapsedTimeMonitor;

public class RangeSum3 {

    public static void main(String[] args) throws Exception {
        var iterations = 20;
        var batchSize = 10_000_000;

        var globalTimeMonitor = new ElapsedTimeMonitor();
        var iterationDurationSum = 0L;

        for (int n = 0; n < iterations; n++) {
            var elapsedTimeMonitor = new ElapsedTimeMonitor();
            var iterationSum = 0L;

            for (int i = 0; i < batchSize; i++) {
                iterationSum += (i + 5 + 10);
            }

            System.out.println("iteration sum: " + iterationSum);
            iterationDurationSum += elapsedTimeMonitor.getElapsedTimeMillis();
        }

        System.out.println("average iteration duration: " + (iterationDurationSum / Integer.valueOf(iterations).doubleValue()) + " ms");
        System.out.println("total duration: " + globalTimeMonitor.getElapsedTimeMillis() + " ms");
    }

}
