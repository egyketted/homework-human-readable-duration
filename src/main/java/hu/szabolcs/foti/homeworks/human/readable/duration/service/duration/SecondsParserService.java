package hu.szabolcs.foti.homeworks.human.readable.duration.service.duration;

import org.springframework.stereotype.Service;

@Service
public class SecondsParserService implements DurationComponentParser {

    @Override
    public DurationComponent getParsedType() {
        return DurationComponent.SECOND;
    }

    @Override
    public DurationComponentValue getParsedValue(int seconds) {
        int value = seconds % 60;
        return new DurationComponentValue(value);
    }
}
