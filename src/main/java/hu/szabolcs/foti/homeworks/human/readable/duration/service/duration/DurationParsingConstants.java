package hu.szabolcs.foti.homeworks.human.readable.duration.service.duration;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DurationParsingConstants {

    public static final int SECONDS_IN_A_MINUTE = 60;
    public static final int MINUTES_IN_AN_HOUR = 60;
    public static final int HOURS_IN_A_DAY = 24;
    public static final int DAYS_IN_A_YEAR = 365;
    public static final int SECONDS_IN_AN_HOUR = SECONDS_IN_A_MINUTE * MINUTES_IN_AN_HOUR;
    public static final int SECONDS_IN_A_DAY = SECONDS_IN_AN_HOUR * HOURS_IN_A_DAY;
    public static final int SECONDS_IN_A_YEAR = SECONDS_IN_A_DAY * DAYS_IN_A_YEAR;

}
