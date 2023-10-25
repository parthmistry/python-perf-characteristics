package posts.parthmistry.javasamples;

import posts.parthmistry.javasamples.utils.CustomMath;
import posts.parthmistry.javasamples.utils.ElapsedTimeMonitor;

import java.util.ArrayList;

public class IntegerListSumWithObjectAllocation2 {

    public static void main(String[] args) {
        var iterations = 20;
        var batchSize = 10_000_000;

        var list1 = new ArrayList<Integer>();
        var list2 = new ArrayList<Integer>();

        for (int i = 0; i < batchSize; ++i) {
            list1.add(i);
            list2.add(i + 5);
        }

        var globalTimeMonitor = new ElapsedTimeMonitor();
        var iterationDurationSum = 0L;

        for (int n = 0; n < iterations; ++n) {
            var iterationTimeMonitor = new ElapsedTimeMonitor();
            long iterationSum = 0;
            var customMath = new CustomMath();

            for (int i = 0; i < batchSize; ++i) {
                var value1 = list1.get(i);
                var value2 = list2.get(i);
                iterationSum += customMath.doSum(value1, value2);
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
