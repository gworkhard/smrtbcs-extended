import Utils.LineParser;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class SimpleDataFetcher implements DataFetcher {

    public static final String ERROR_INDICATOR = "ERROR";
    public static final String LOG_FILE_ENDING = ".log";
    public final LineParser<LocalDateTime> parser;

    public SimpleDataFetcher(LineParser<LocalDateTime> parser) {
        this.parser = parser;
    }

    private static Stream<String> lines(Path path) {
        try {
            return Files.lines(path);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    @Override
    public Map<LocalDateTime, AtomicInteger> fetchData(String sourceDirPath, ChronoUnit chronoUnit) throws IOException {
        Path dir = Paths.get(sourceDirPath);
        return Files.walk(dir)
                .filter(filePath -> filePath.toString().endsWith(LOG_FILE_ENDING))
                .parallel()
                .flatMap(SimpleDataFetcher::lines)
                .filter(line -> line.contains(ERROR_INDICATOR))
                .map(parser::parse)
                .collect(
                        ConcurrentHashMap::new,
                        (map, localDateTime) -> {
                            LocalDateTime truncatedDateTime = localDateTime.truncatedTo(chronoUnit);
                            AtomicInteger errorCounter = map.get(truncatedDateTime);
                            if (errorCounter == null) {
                                map.put(truncatedDateTime, new AtomicInteger(1));
                            } else {
                                errorCounter.incrementAndGet();
                            }
                        }, ConcurrentHashMap::putAll);
    }

}
