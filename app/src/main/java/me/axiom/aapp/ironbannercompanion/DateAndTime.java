package me.axiom.aapp.ironbannercompanion;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

public class DateAndTime {

    DateAndTime instance;

    DateTime utcTime;
    int dayOfWeek, hourOfDay;

    public void init() {

        utcTime = DateTime.now(DateTimeZone.UTC);
        dayOfWeek = utcTime.getDayOfWeek();
        hourOfDay = utcTime.getHourOfDay();

    }

    public DateAndTime getInstance() {

        return instance;

    }

    public int getDayOfWeek() {

        int day;

        if (getHourOfDay() >= 9) {

            day = dayOfWeek;

        } else {

            if (dayOfWeek == 0) {

                day = 7;

            } else {

                day = dayOfWeek - 1;

            }

        }

        return day;

    }

    public int getHourOfDay() {

        return hourOfDay;

    }

}
