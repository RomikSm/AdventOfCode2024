package days;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Helper {
    public static List<String> readLines(String filename) {
        try {
            return Files.readAllLines(Paths.get("src/inputs/" + filename));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
