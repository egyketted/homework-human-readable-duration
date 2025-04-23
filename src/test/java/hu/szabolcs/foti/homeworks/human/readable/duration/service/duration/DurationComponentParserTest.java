package hu.szabolcs.foti.homeworks.human.readable.duration.service.duration;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

import static hu.szabolcs.foti.homeworks.human.readable.duration.service.duration.DurationComponent.MINUTE;
import static hu.szabolcs.foti.homeworks.human.readable.duration.service.duration.DurationComponent.SECOND;
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

        testedParsers.forEach(parser -> {
            System.out.println(String.format("Tested %s parser", parser.getParsedType()));
        });
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
                        4200,
                        new TestDurationComponentValue(10, true),
                        MINUTE),
                Arguments.of(
                        "Minutes parser should parse large values properly",
                        Integer.MAX_VALUE,
                        new TestDurationComponentValue(14, true),
                        MINUTE),
                Arguments.of(
                        "Minutes parser should handle plurality properly",
                        70,
                        new TestDurationComponentValue(1, false),
                        MINUTE)
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
