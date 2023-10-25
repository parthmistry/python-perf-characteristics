package posts.parthmistry.javasamples.utils;

import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.UUID;

public class DummyUUIDFileGenerator {

    public static void main(String[] args) throws Exception {
        var recordCount = Long.parseLong(args[0]);
        var uuidFilePath1 = args[1];
        var uuidFilePath2 = args[2];

        var uuidSet = new HashSet<String>();

        try (var writer = new PrintWriter(Files.newBufferedWriter(Paths.get(uuidFilePath1)))) {
            for (long i = 0; i < recordCount; i++) {
                var uuid = UUID.randomUUID();
                writer.println(uuid);
                uuidSet.add(uuid.toString());
            }
        }

        try (var writer = new PrintWriter(Files.newBufferedWriter(Paths.get(uuidFilePath2)))) {
            for (var uuidString : uuidSet) {
                writer.println(uuidString);
            }
        }
    }

}
