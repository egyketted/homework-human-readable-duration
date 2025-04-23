package hu.szabolcs.foti.homeworks.human.readable.duration.service.duration;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

import static hu.szabolcs.foti.homeworks.human.readable.duration.constants.DurationParsingConstants.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class DurationFormatterServiceTest {

    @Autowired
    private DurationFormatterService service;

    @ParameterizedTest(name = "{0}")
    @MethodSource("testDataAndExpectedValueProviderForServiceTests")
    public void parserTest(String displayName, int input, String expected) {
        // When
        String result = service.formatDuration(input);

        // Then
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(expected);
    }

    public static Stream<Arguments> testDataAndExpectedValueProviderForServiceTests() {
        return Stream.of(
                Arguments.of(
                        "Should parse 0 and return now",
                        0,
                        "now"),
                Arguments.of(
                        "Should parse integer max and return valid value",
                        Integer.MAX_VALUE,
                        "68 years, 35 days, 3 hours, 14 minutes and 7 seconds"),
                Arguments.of(
                        "Should handle only second component",
                        7,
                        "7 seconds"),
                Arguments.of(
                        "Should handle only second and minute",
                        61,
                        "1 minute and 1 second"),
                Arguments.of(
                        "Should handle only second, minute and hour",
                        SECONDS_IN_AN_HOUR + SECONDS_IN_A_MINUTE + 2,
                        "1 hour, 1 minute and 2 seconds"),
                Arguments.of(
                        "Should handle only second, minute, hour and day",
                        SECONDS_IN_A_DAY + (2 * SECONDS_IN_AN_HOUR) + SECONDS_IN_A_MINUTE + 2,
                        "1 day, 2 hours, 1 minute and 2 seconds"),
                Arguments.of(
                        "Should handle only second and hour",
                        SECONDS_IN_AN_HOUR + 2,
                        "1 hour and 2 seconds"),
                Arguments.of(
                        "Should handle only second, hour and year",
                        SECONDS_IN_A_YEAR + SECONDS_IN_AN_HOUR + 1,
                        "1 year, 1 hour and 1 second"),
                Arguments.of(
                        "Should handle only second and year",
                        SECONDS_IN_A_YEAR + 1,
                        "1 year and 1 second"),
                Arguments.of(
                        "Should handle only hour and year",
                        SECONDS_IN_A_YEAR + SECONDS_IN_AN_HOUR,
                        "1 year and 1 hour"),
                Arguments.of(
                        "Should handle negative values",
                        -2,
                        "Invalid input -2 < 0")
        );
    }
}
