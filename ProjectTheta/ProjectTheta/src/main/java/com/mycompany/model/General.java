/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.model;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author sebap
 */
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
