package com.mycompany.model.generics;
import java.util.*;

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
            int month = (fecha / 100) - year * 100;
            int day = (fecha - year * 10000 - month * 100);

            return (day + "/" + month + "/" + year);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int getFechaInt(int day, int month, int year) {
        return year * 10000 + month * 100 + day;
    }

    public static String getFechaString(int day, int month, int year) {
        return (day + "/" + month + "/" + year);
    }

    public static int getFechaActual() {
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
            int minute = (tiempo / 100) - hour * 100;
            int second = (tiempo - hour * 10000 - minute * 100);

            String sHour = "" + hour;
            String sMinute = "" + minute;
            String sSecond = "" + second;

            if (hour < 10) {
                sHour = ("0" + hour);
            }
            if (minute < 10) {
                sMinute = ("0" + minute);
            }
            if (second < 10) {
                sSecond = ("0" + second);
            }

            return (sHour + ":" + sMinute + ":" + sSecond);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int getHoraInt(int hour, int minute, int second) {
        return hour * 10000 + minute * 100 + second;
    }

    public static String getHoraString(int hour, int minute, int second) {
        String sHour = "" + hour;
        String sMinute = "" + minute;
        String sSecond = "" + second;

        if (hour < 10) {
            sHour = ("0" + hour);
        }
        if (minute < 10) {
            sMinute = ("0" + minute);
        }
        if (second < 10) {
            sSecond = ("0" + second);
        }

        return (sHour + ":" + sMinute + ":" + sSecond);
    }
    public static int getHoraActual() {
        Calendar cal = new GregorianCalendar();
        int second = cal.get(Calendar.SECOND);
        int minute = cal.get(Calendar.MINUTE);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        return hour * 10000 + minute * 100 + second;
    }

    public static int getMinutosEntreTiempos(int tiempo1, int tiempo2) {
        int diferencia = Math.abs(tiempo1 - tiempo2);
        int horas = diferencia / 10000;
        int minutos = (diferencia / 100) - (horas * 100);
        return horas * 60 + minutos;
    }

    public static int sumMinutos(int tiempo, int minutos) {
        int hour = tiempo / 10000;
        int minute = (tiempo / 100) - hour * 100;
        int second = (tiempo - hour * 10000 - minute * 100);

        minute += minutos;

        int i = 0;

        while (minute >= 60) {
            minute -= 60;
            i++;
        }

        hour += i;

        while (hour >= 24) {

            hour -= 24;
        }
        return hour * 10000 + minute * 100 + second;
    }

    public static int sumTiempos(int tiempo1, int tiempo2) {
        int horas[] = new int[2];
        int minutos[] = new int[2];
        int segundos[] = new int[2];

        horas[0] = tiempo1 / 10000;
        minutos[0] = (tiempo1 / 100) - horas[0] * 100;
        segundos[0] = (tiempo1 - horas[0] * 10000 - minutos[0] * 100);

        horas[0] = tiempo1 / 10000;
        minutos[0] = (tiempo1 / 100) - horas[0] * 100;
        segundos[0] = (tiempo1 - horas[0] * 10000 - minutos[0] * 100);

        horas[1] = tiempo2 / 10000;
        minutos[1] = (tiempo2 / 100) - horas[1] * 100;
        segundos[1] = (tiempo2 - horas[1] * 10000 - minutos[1] * 100);

        int sumHoras = horas[0] + horas[1];
        int sumMinutos = minutos[0] + minutos[1];
        int sumSegundos = segundos[0] + segundos[1];

        int i = 0;

        while (sumSegundos >= 60) {
            sumSegundos -= 60;
            i++;
        }

        sumMinutos += i;
        i = 0;

        while (sumMinutos >= 60) {
            sumMinutos -= 60;
            i++;
        }

        sumHoras += i;
        i = 0;

        return sumHoras * 10000 + sumMinutos * 100 + sumSegundos;
    }
}
