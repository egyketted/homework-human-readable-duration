package hu.szabolcs.foti.homeworks.human.readable.duration.service.duration;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

import static hu.szabolcs.foti.homeworks.human.readable.duration.service.duration.DurationComponent.*;
import static hu.szabolcs.foti.homeworks.human.readable.duration.service.duration.DurationParsingConstants.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class DurationComponentParserTest {

    @Autowired
    private List<DurationComponentParser> parsers;

    @ParameterizedTest(name = "{0}")
    @MethodSource("testDataAndExpectedValueProviderForParserTests")
    public void parserTest(String displayName, int input, DurationComponentValue expected, DurationComponent parserType) {
        List<DurationComponentParser> testedParsers = new LinkedList<>();

        parsers.stream()
                .filter(parser -> parserType == null || parser.getParsedType() == parserType)
                .forEach(parser -> {
                    // Given
                    testedParsers.add(parser);

                    // When
                    DurationComponentValue result = parser.getParsedValue(input);

                    // Then
                    assertThat(result).isNotNull();
                    assertThat(result.getValue()).isEqualTo(expected.getValue());
                    assertThat(result.isPlural()).isEqualTo(expected.isPlural());
                });

        assertThat(testedParsers).isNotEmpty();

        testedParsers.forEach(parser -> System.out.printf("Tested %s parser%n", parser.getParsedType()));
    }

    public static Stream<Arguments> testDataAndExpectedValueProviderForParserTests() {
        return Stream.of(
                Arguments.of(
                        "All parsers should parse 0 seconds properly",
                        0,
                        new TestDurationComponentValue(0, false),
                        null),
                Arguments.of(
                        "Seconds parser should parse 70 seconds properly",
                        70,
                        new TestDurationComponentValue(10, true),
                        SECOND),
                Arguments.of(
                        "Seconds parser should parse large values properly",
                        Integer.MAX_VALUE,
                        new TestDurationComponentValue(7, true), // Integer.MAX_VALUE % 60 = 0
                        SECOND),
                Arguments.of(
                        "Seconds parser should handle plurality properly",
                        1,
                        new TestDurationComponentValue(1, false),
                        SECOND),
                Arguments.of(
                        "Minutes parser should parse 70 minutes properly",
                        SECONDS_IN_A_MINUTE * 70,
                        new TestDurationComponentValue(10, true),
                        MINUTE),
                Arguments.of(
                        "Minutes parser should parse large values properly",
                        Integer.MAX_VALUE,
                        new TestDurationComponentValue(14, true),
                        MINUTE),
                Arguments.of(
                        "Minutes parser should handle plurality properly",
                        SECONDS_IN_A_MINUTE + 10,
                        new TestDurationComponentValue(1, false),
                        MINUTE),
                Arguments.of(
                        "Hours parser should parse 70 hours properly",
                        SECONDS_IN_AN_HOUR * 70,
                        new TestDurationComponentValue(22, true),
                        HOUR),
                Arguments.of(
                        "Hours parser should parse large values properly",
                        Integer.MAX_VALUE,
                        new TestDurationComponentValue(3, true),
                        HOUR),
                Arguments.of(
                        "Hours parser should handle plurality properly",
                        SECONDS_IN_AN_HOUR + 10,
                        new TestDurationComponentValue(1, false),
                        HOUR),
                Arguments.of(
                        "Days parser should parse 368 days properly",
                        SECONDS_IN_A_DAY * 368,
                        new TestDurationComponentValue(3, true),
                        DAY),
                Arguments.of(
                        "Days parser should parse large values properly",
                        Integer.MAX_VALUE,
                        new TestDurationComponentValue(35, true),
                        DAY),
                Arguments.of(
                        "Days parser should handle plurality properly",
                        SECONDS_IN_A_DAY + 10,
                        new TestDurationComponentValue(1, false),
                        DAY)
        );
    }

    public static class TestDurationComponentValue extends DurationComponentValue {

        private final boolean expectedPlural;

        public TestDurationComponentValue(int value, boolean plural) {
            super(value);
            this.expectedPlural = plural;
        }

        @Override
        public boolean isPlural() {
            return expectedPlural;
        }
    }
}
