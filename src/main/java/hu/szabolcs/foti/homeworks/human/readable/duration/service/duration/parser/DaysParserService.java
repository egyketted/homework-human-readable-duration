package hu.szabolcs.foti.homeworks.human.readable.duration.service.duration.parser;

import hu.szabolcs.foti.homeworks.human.readable.duration.service.duration.model.DurationComponent;
import org.springframework.stereotype.Service;

import static hu.szabolcs.foti.homeworks.human.readable.duration.service.duration.model.DurationComponent.DAY;
import static hu.szabolcs.foti.homeworks.human.readable.duration.constants.DurationParsingConstants.DAYS_IN_A_YEAR;
import static hu.szabolcs.foti.homeworks.human.readable.duration.constants.DurationParsingConstants.SECONDS_IN_A_DAY;

@Service
public class DaysParserService extends DurationComponentParser {
    @Override
    public DurationComponent getParsedType() {
        return DAY;
    }

    @Override
    public int calculateValue(int seconds) {
        return (seconds / SECONDS_IN_A_DAY) % DAYS_IN_A_YEAR;
    }
}
