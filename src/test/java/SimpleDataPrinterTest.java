import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

class SimpleDataPrinterTest {

    @Test
    void testPrintData() {
        SimpleDataPrinter sdp = new SimpleDataPrinter();
        File file = null;
        try {
            file = File.createTempFile("Statistics", ".txt");
        } catch (IOException e) {
            System.err.println(
                    "error creating temporary test file in " +
                            this.getClass().getSimpleName());
        }
        Map<LocalDateTime, AtomicInteger> data = new ConcurrentHashMap<>();
        data.put(LocalDateTime.parse("2019-01-01T00:00"), new AtomicInteger(2));
        data.put(LocalDateTime.parse("2019-01-02T02:00"), new AtomicInteger(100));
        sdp.printData(data, ChronoUnit.HOURS, file.getPath());
        List<String> lines = null;
        try {
            lines = Files.readAllLines(file.toPath());
        } catch (IOException e) {
            System.err.println(
                    "error creating temporary test file in " +
                            this.getClass().getSimpleName());
        }
        assertTrue(lines.contains("2019-01-01, 00:00 - 01:00 Количество ошибок: 2"), "Wrong HOURS data printed");
        assertTrue(lines.contains("2019-01-02, 02:00 - 03:00 Количество ошибок: 100"), "Wrong HOURS data printed");

        sdp.printData(data, ChronoUnit.MINUTES, file.getPath());
        try {
            lines = Files.readAllLines(file.toPath());
        } catch (IOException e) {
            System.err.println(
                    "error creating temporary test file in " +
                            this.getClass().getSimpleName());
        }

        assertTrue(lines.contains("2019-01-01, 00:00 - 00:01 Количество ошибок: 2"), "Wrong MINUTES data printed");
        assertTrue(lines.contains("2019-01-02, 02:00 - 02:01 Количество ошибок: 100"), "Wrong MINUTES data printed");
    }
}