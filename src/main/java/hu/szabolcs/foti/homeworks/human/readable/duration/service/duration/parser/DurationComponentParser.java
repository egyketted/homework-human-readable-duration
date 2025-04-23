package hu.szabolcs.foti.homeworks.human.readable.duration.service.duration.parser;

import hu.szabolcs.foti.homeworks.human.readable.duration.service.duration.model.DurationComponent;
import hu.szabolcs.foti.homeworks.human.readable.duration.service.duration.model.DurationComponentValue;

public abstract class DurationComponentParser {

    public abstract DurationComponent getParsedType();
    public abstract int calculateValue(int seconds);

    public DurationComponentValue getParsedValue(int seconds) {
        return new DurationComponentValue(calculateValue(seconds));
    }
}
