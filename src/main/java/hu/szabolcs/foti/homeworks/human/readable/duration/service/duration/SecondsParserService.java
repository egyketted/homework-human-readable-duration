package hu.szabolcs.foti.homeworks.human.readable.duration.service.duration;

import org.springframework.stereotype.Service;

import static hu.szabolcs.foti.homeworks.human.readable.duration.service.duration.DurationParsingConstants.SECONDS_IN_A_MINUTE;

@Service
public class SecondsParserService implements DurationComponentParser {

    @Override
    public DurationComponent getParsedType() {
        return DurationComponent.SECOND;
    }

    @Override
    public DurationComponentValue getParsedValue(int seconds) {
        int value = seconds % SECONDS_IN_A_MINUTE;
        return new DurationComponentValue(value);
    }
}
