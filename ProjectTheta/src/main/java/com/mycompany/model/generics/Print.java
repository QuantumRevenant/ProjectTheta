/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.model.generics;

import com.mycompany.controller.ProgramController;
import java.time.LocalDateTime;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author sebap
 */
public class Print {

    private static ProgramController pc = ProgramController.getProgramController();

    public static void msg(Object x) {
        System.out.println(x);
    }

    public static void log(Object x) {
        LocalDateTime dateTime = LocalDateTime.now();
        String date = dateTime.format(pc.getFormatDayTime());
        System.out.println("<" + date + "> " + x);
    }

    public static void error(Object x) {
        LocalDateTime dateTime = LocalDateTime.now();
        String date = dateTime.format(pc.getFormatDayTime());
        System.out.println("<" + date + ">- Error -" + x);
    }

    public static void warning(Object x, JFrame parent) {
        JOptionPane.showMessageDialog(parent, x, "Warning", JOptionPane.WARNING_MESSAGE);
    }
    
    public static boolean warnConf(Object x, JFrame parent){
        int opt = JOptionPane.showConfirmDialog(parent, x, "Warning", JOptionPane.OK_CANCEL_OPTION);
        return opt == JOptionPane.OK_OPTION;
    }
}
