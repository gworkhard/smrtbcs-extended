import Utils.SimpleLineParser;

import java.time.temporal.ChronoUnit;
import java.util.Locale;

public class SmartbicsTestApp {

    public static void main(String[] args) {
        StatisticCollector sc = new SimpleStatisticCollector(
                new SimpleDataFetcher(new SimpleLineParser()),
                new SimpleDataPrinter());
        sc.collect(ConfigData.LOG_DIR, ConfigData.OUTPUT_FILE_PATH, getChronoUnit(args));
    }

    public static ChronoUnit getChronoUnit(String[] args) {
        if (args.length != 0) {
            if (args[0].equalsIgnoreCase(ChronoUnit.HOURS.toString())) {
                return ChronoUnit.HOURS;
            }
            if (args[0].equalsIgnoreCase(ChronoUnit.MINUTES.toString())) {
                return ChronoUnit.MINUTES;
            }
        }
        return ConfigData.DEFAULT_CHRONOUNIT;
    }
}