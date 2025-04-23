package hu.szabolcs.foti.homeworks.human.readable.duration.service.duration;

public abstract class DurationComponentParser {

    public abstract DurationComponent getParsedType();
    public abstract int calculateValue(int seconds);

    public DurationComponentValue getParsedValue(int seconds) {
        return new DurationComponentValue(calculateValue(seconds));
    }
}
