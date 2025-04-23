package hu.szabolcs.foti.homeworks.human.readable.duration.service.duration.parser;

import hu.szabolcs.foti.homeworks.human.readable.duration.service.duration.model.DurationComponent;
import org.springframework.stereotype.Service;

import static hu.szabolcs.foti.homeworks.human.readable.duration.service.duration.model.DurationComponent.HOUR;
import static hu.szabolcs.foti.homeworks.human.readable.duration.constants.DurationParsingConstants.*;

@Service
public class HoursParserService extends DurationComponentParser {
    @Override
    public DurationComponent getParsedType() {
        return HOUR;
    }

    @Override
    public int calculateValue(int seconds) {
        return (seconds / SECONDS_IN_AN_HOUR) % HOURS_IN_A_DAY;
    }
}
