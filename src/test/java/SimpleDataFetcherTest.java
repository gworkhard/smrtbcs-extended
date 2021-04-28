import Utils.SimpleLineParser;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SimpleDataFetcherTest {



    @Test
    void testFetchData() throws IOException {
        Path testTempDir = null;
        Path file1 = null;
        Path file2 = null;

        try {
            testTempDir = Files.createTempDirectory("testTempDir");
            file1 = Files.createTempFile(testTempDir, "testfile1", ".log");
            file2 = Files.createTempFile(testTempDir,"testfile2", ".log");
            List<String> file1Lines = Arrays.asList(
                    "2019-01-01T00:12:01.001;ERROR; Ошибка 1",
                    "2019-01-01T00:13:01.004;ERROR; Ошибка 2"
            );
            List<String> file2Lines = Arrays.asList(
                    "2019-01-02T02:13:02.000;WARN; Предупреждение 1",
                    "2019-01-02T02:13:02.002;ERROR; Ошибка 5"
            );
            Files.write(file1, file1Lines, StandardCharsets.UTF_8);
            Files.write(file2, file2Lines, StandardCharsets.UTF_8);
        } catch (IOException ioe) {
            System.err.println(
                    "error creating temporary test file in " +
                            this.getClass().getSimpleName());
        }
        SimpleDataFetcher sdf = new SimpleDataFetcher(new SimpleLineParser());
        Map<LocalDateTime, AtomicInteger> mapToTestHours = sdf.fetchData(testTempDir.toString(), ChronoUnit.HOURS);

        assertEquals(mapToTestHours.get(LocalDateTime.parse("2019-01-01T00:00")).get(), 2, "HOURS data fetched wrong");
        assertEquals(mapToTestHours.get(LocalDateTime.parse("2019-01-02T02:00")).get(), 1, "HOURS data fetched wrong");

        Map<LocalDateTime, AtomicInteger> mapToTestMinutes = sdf.fetchData(testTempDir.toString(), ChronoUnit.MINUTES);

        assertEquals(mapToTestMinutes.get(LocalDateTime.parse("2019-01-01T00:12")).get(), 1, "MINUTES data fetched wrong");
        assertEquals(mapToTestMinutes.get(LocalDateTime.parse("2019-01-01T00:13")).get(), 1, "MINUTES data fetched wrong");
        assertEquals(mapToTestMinutes.get(LocalDateTime.parse("2019-01-02T02:13")).get(), 1, "MINUTES data fetched wrong");
    }
}