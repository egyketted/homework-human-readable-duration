package hu.szabolcs.foti.homeworks.human.readable.duration.service.duration;

public interface DurationComponentParser {

    DurationComponent getParsedType();
    DurationComponentValue getParsedValue(int seconds);
}
