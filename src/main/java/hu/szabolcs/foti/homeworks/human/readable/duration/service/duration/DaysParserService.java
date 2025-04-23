package hu.szabolcs.foti.homeworks.human.readable.duration.service.duration;

import org.springframework.stereotype.Service;

import static hu.szabolcs.foti.homeworks.human.readable.duration.service.duration.DurationComponent.DAY;
import static hu.szabolcs.foti.homeworks.human.readable.duration.service.duration.DurationParsingConstants.DAYS_IN_A_YEAR;
import static hu.szabolcs.foti.homeworks.human.readable.duration.service.duration.DurationParsingConstants.SECONDS_IN_A_DAY;

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
