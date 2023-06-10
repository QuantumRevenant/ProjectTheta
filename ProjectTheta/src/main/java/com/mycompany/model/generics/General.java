package com.mycompany.model.generics;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class General {
    public static String clock() {
        Calendar cal = new GregorianCalendar();
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);

        int second = cal.get(Calendar.SECOND);
        int minute = cal.get(Calendar.MINUTE);
        int hour = cal.get(Calendar.HOUR_OF_DAY);

        String output = ("Time: " + hour + ":" + minute + ":" + second + "    Date: " + day + "/" + month + "/" + year);
        return output;

    }
}
