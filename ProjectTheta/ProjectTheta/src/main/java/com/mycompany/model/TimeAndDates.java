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
public class TimeAndDates {
    public static int convertFechaSTOI(String fecha) {
        if (fecha.length() != 10) {
            return -1;
        }
        if (fecha.charAt(2) != '/' || fecha.charAt(5) != '/') {
            return -1;
        }

        try {
            String parts[] = fecha.split("/");
            int day = Integer.parseInt(parts[0].trim());
            int month = Integer.parseInt(parts[1].trim());
            int year = Integer.parseInt(parts[2].trim());
            return year * 10000 + month * 100 + day;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static String convertFechaITOS(int fecha) {
        if (Math.floor(Math.log10(Math.abs(fecha)) + 1) != 8) {
            return null;
        }

        try {
            int year = fecha / 10000;
            int month=(fecha-year)/100;
            int day=(fecha-year-month);

            return (day+"/"+month+"/"+year);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static int getFechaInt(int day,int month, int year)
    {
        return year * 10000 + month * 100 + day;
    }
    
    public static String getFechaString(int day,int month, int year)
    {
        return (day+"/"+month+"/"+year);
    }
    
    public static int getFechaActual()
    {
        Calendar cal = new GregorianCalendar();
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);
        return year * 10000 + month * 100 + day;
    }
    
       public static int convertHoraSTOI(String tiempo) {
        if (tiempo.length() != 8) {
            return -1;
        }
        if (tiempo.charAt(2) != ':' || tiempo.charAt(5) != ':') {
            return -1;
        }

        try {
            String parts[] = tiempo.split(":");
            int hour = Integer.parseInt(parts[0].trim());
            int minute = Integer.parseInt(parts[1].trim());
            int second = Integer.parseInt(parts[2].trim());
            return hour * 10000 + minute * 100 + second;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static String convertHoraITOS(int tiempo) {
        if (Math.floor(Math.log10(Math.abs(tiempo)) + 1) != 6) {
            return null;
        }

        try {
            int hour = tiempo / 10000;
            int minute=(tiempo-hour)/100;
            int second=(tiempo-hour-minute);

            return (hour+":"+minute+":"+second);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static int getHoraInt(int hour,int minute, int second)
    {
        return hour * 10000 + minute * 100 + second;
    }
    
    public static String getHoraString(int hour,int minute, int second)
    {
        return (hour+"/"+minute+"/"+second);
    }
    
    public static int getHoraActual()
    {
        Calendar cal = new GregorianCalendar();
        int second = cal.get(Calendar.SECOND);
        int minute = cal.get(Calendar.MINUTE);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        return hour * 10000 + minute * 100 + second;
    }
    
    public static int getMinutosEntreTiempos(int tiempo1,int tiempo2)
    {
       int diferencia=Math.abs(tiempo1-tiempo2);
       int horas=diferencia/1000;
       int minutos=diferencia/100;
       return horas*60+minutos;
    }
}
