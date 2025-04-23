package hu.szabolcs.foti.homeworks.human.readable.duration.service.duration.parser;

import hu.szabolcs.foti.homeworks.human.readable.duration.service.duration.model.DurationComponent;
import hu.szabolcs.foti.homeworks.human.readable.duration.constants.DurationParsingConstants;
import org.springframework.stereotype.Service;

import static hu.szabolcs.foti.homeworks.human.readable.duration.service.duration.model.DurationComponent.YEAR;

@Service
public class YearsParserService extends DurationComponentParser {
    @Override
    public DurationComponent getParsedType() {
        return YEAR;
    }

    @Override
    public int calculateValue(int seconds) {
        return seconds / DurationParsingConstants.SECONDS_IN_A_YEAR;
    }
}
