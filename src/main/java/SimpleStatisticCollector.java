import java.io.IOException;
import java.io.UncheckedIOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class SimpleStatisticCollector implements StatisticCollector {

    private final DataFetcher dataFetcher;
    private final DataPrinter dataPrinter;

    public SimpleStatisticCollector(DataFetcher dataFetcher, DataPrinter dataPrinter) {
        this.dataFetcher = dataFetcher;
        this.dataPrinter = dataPrinter;
    }

    public void collect(String sourceDir, String outputFilePath, ChronoUnit chronoUnit) {
        Map<LocalDateTime, AtomicInteger> data = null;
        try {
            data = dataFetcher.fetchData(sourceDir, chronoUnit);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        dataPrinter.printData(data, chronoUnit, outputFilePath);
    }
}
