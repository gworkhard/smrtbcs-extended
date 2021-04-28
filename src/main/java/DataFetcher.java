import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public interface DataFetcher {
    Map<LocalDateTime, AtomicInteger> fetchData(String logDirectoryPath, ChronoUnit chronoUnit) throws IOException;
}
