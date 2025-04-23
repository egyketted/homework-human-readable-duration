package hu.szabolcs.foti.homeworks.human.readable.duration.service.duration.parser;

import hu.szabolcs.foti.homeworks.human.readable.duration.service.duration.model.DurationComponent;
import org.springframework.stereotype.Service;

import static hu.szabolcs.foti.homeworks.human.readable.duration.constants.DurationParsingConstants.SECONDS_IN_A_MINUTE;

@Service
public class SecondsParserService extends DurationComponentParser {

    @Override
    public DurationComponent getParsedType() {
        return DurationComponent.SECOND;
    }

    @Override
    public int calculateValue(int seconds) {
        return seconds % SECONDS_IN_A_MINUTE;
    }
}
