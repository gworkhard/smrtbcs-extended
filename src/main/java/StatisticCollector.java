import java.time.temporal.ChronoUnit;

public interface StatisticCollector {
    void collect(String sourceDir, String outputFilePath, ChronoUnit chronoUnit);
}
