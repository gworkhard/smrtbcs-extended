import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public interface DataPrinter {
    void printData(Map<LocalDateTime, AtomicInteger> data, ChronoUnit chronoUnit, String outputFilePath);
}
