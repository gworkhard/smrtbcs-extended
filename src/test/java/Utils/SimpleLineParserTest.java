package Utils;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class SimpleLineParserTest {

    @Test
    void testParse() {
        SimpleLineParser slp = new SimpleLineParser();
        String line = "2019-01-01T00:12:01.001;ERROR; Ошибка 1";
        LocalDateTime expectedLocalDateTime = LocalDateTime.parse("2019-01-01T00:12:01.001");
        assertEquals(expectedLocalDateTime, slp.parse(line), "Line parsing failed" );
    }
}