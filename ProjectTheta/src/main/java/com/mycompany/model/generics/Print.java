/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.model.generics;

import com.mycompany.controller.ProgramController;
import java.time.LocalDateTime;

/**
 *
 * @author sebap
 */
public class Print {
    private static ProgramController pc=ProgramController.getProgramController();
    
    public static void msg(String x)
    {
        System.out.println(x);
    }
    
    public static void log(String x)
    {
        LocalDateTime dateTime=LocalDateTime.now();
        String date=dateTime.format(pc.getFormatDayTime());
        System.out.println("<"+date+"> "+x);
    }
    
    public static void error(String x)
    {
        LocalDateTime dateTime=LocalDateTime.now();
        String date=dateTime.format(pc.getFormatDayTime());
        System.out.println("<"+date+">- Error -"+x);
    }
}
