import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class SimpleDataPrinter implements DataPrinter {
    @Override
    public void printData(Map<LocalDateTime, AtomicInteger> dataMap, ChronoUnit unit, String outputFilePath) {
        List<String> list = dataMap.keySet().stream()
                .sorted()
                .map(date -> date.toLocalDate() +
                        "," +
                        " " +
                        date.toLocalTime().truncatedTo(unit) +
                        " - " +
                        date.plus(1, unit).toLocalTime() +
                        " Количество ошибок: " +
                        dataMap.get(date))
                .collect(Collectors.toList());
        try {
            Files.write(Paths.get(outputFilePath), list, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
