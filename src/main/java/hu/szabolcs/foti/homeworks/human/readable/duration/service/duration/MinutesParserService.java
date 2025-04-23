package hu.szabolcs.foti.homeworks.human.readable.duration.service.duration;

import org.springframework.stereotype.Service;

import static hu.szabolcs.foti.homeworks.human.readable.duration.service.duration.DurationComponent.MINUTE;
import static hu.szabolcs.foti.homeworks.human.readable.duration.service.duration.DurationParsingConstants.MINUTES_IN_AN_HOUR;
import static hu.szabolcs.foti.homeworks.human.readable.duration.service.duration.DurationParsingConstants.SECONDS_IN_A_MINUTE;

@Service
public class MinutesParserService implements DurationComponentParser {
    @Override
    public DurationComponent getParsedType() {
        return MINUTE;
    }

    @Override
    public DurationComponentValue getParsedValue(int seconds) {
        int value = (seconds / SECONDS_IN_A_MINUTE) % MINUTES_IN_AN_HOUR;
        return new DurationComponentValue(value);
    }
}
