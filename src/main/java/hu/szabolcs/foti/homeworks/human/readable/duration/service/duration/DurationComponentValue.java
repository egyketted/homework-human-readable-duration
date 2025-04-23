package hu.szabolcs.foti.homeworks.human.readable.duration.service.duration;

import lombok.Getter;

@Getter
public class DurationComponentValue {

    private final boolean plural;
    private final int value;

    public DurationComponentValue(int value) {
        this.value = value;
        this.plural = value > 1;
    }
}
