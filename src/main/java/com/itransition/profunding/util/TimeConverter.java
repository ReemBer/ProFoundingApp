package com.itransition.profunding.util;

import java.util.Date;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 10.09.2017 21:16
 */
public class TimeConverter {

    public static final long MILLS_IN_SECOND = 1000L;
    public static final long SECONDS_IN_MINUTE = 60L;
    public static final long MINUTES_IN_HOUR = 60L;

    public static long HoursToMills(long hours) {
        return hours
                *MINUTES_IN_HOUR
                *SECONDS_IN_MINUTE
                *MILLS_IN_SECOND;
    }

}
