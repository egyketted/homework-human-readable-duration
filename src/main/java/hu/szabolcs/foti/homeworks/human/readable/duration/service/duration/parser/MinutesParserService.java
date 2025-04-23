package hu.szabolcs.foti.homeworks.human.readable.duration.service.duration.parser;

import hu.szabolcs.foti.homeworks.human.readable.duration.service.duration.model.DurationComponent;
import org.springframework.stereotype.Service;

import static hu.szabolcs.foti.homeworks.human.readable.duration.service.duration.model.DurationComponent.MINUTE;
import static hu.szabolcs.foti.homeworks.human.readable.duration.constants.DurationParsingConstants.MINUTES_IN_AN_HOUR;
import static hu.szabolcs.foti.homeworks.human.readable.duration.constants.DurationParsingConstants.SECONDS_IN_A_MINUTE;

@Service
public class MinutesParserService extends DurationComponentParser {
    @Override
    public DurationComponent getParsedType() {
        return MINUTE;
    }

    @Override
    public int calculateValue(int seconds) {
        return (seconds / SECONDS_IN_A_MINUTE) % MINUTES_IN_AN_HOUR;
    }
}
