package Utils;

import java.time.LocalDateTime;

public class SimpleLineParser implements LineParser<LocalDateTime> {
    public static final String SEPARATOR = ";";

    @Override
    public LocalDateTime parse(String line) {
        return LocalDateTime.parse(line.split(SEPARATOR)[0]);
    }
}
