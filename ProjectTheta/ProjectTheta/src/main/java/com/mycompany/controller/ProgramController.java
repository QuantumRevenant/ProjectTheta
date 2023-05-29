/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author sebap
 */
public class ProgramController {

    private int tiempoEstandarEnMesa;
    private int tiempoPrevioReserva;
    private int tiempoEsperaEnMesa;
    private int cantidadMesas;

    private final String directoryRoute = "./src/main/java/com/mycompany/data/";
    private final String fileName = "preferences.csv";
    private final String standarFileName="standarPreferences.csv";

    public ProgramController() {
    }
    
    public ProgramController(int tiempoEstandarEnMesa, int tiempoPrevioReserva, int tiempoEsperaEnMesa, int cantidadMesas) {
        this.tiempoEstandarEnMesa = tiempoEstandarEnMesa;
        this.tiempoPrevioReserva = tiempoPrevioReserva;
        this.tiempoEsperaEnMesa = tiempoEsperaEnMesa;
        this.cantidadMesas = cantidadMesas;
    }
    
    public int getTiempoEstandarEnMesa() {
        return tiempoEstandarEnMesa;
    }

    public void setTiempoEstandarEnMesa(int tiempoEstandarEnMesa) {
        this.tiempoEstandarEnMesa = tiempoEstandarEnMesa;
    }

    public int getTiempoPrevioReserva() {
        return tiempoPrevioReserva;
    }

    public void setTiempoPrevioReserva(int tiempoPrevioReserva) {
        this.tiempoPrevioReserva = tiempoPrevioReserva;
    }

    public int getTiempoEsperaEnMesa() {
        return tiempoEsperaEnMesa;
    }

    public void setTiempoEsperaEnMesa(int tiempoEsperaEnMesa) {
        this.tiempoEsperaEnMesa = tiempoEsperaEnMesa;
    }

    public int getCantidadMesas() {
        return cantidadMesas;
    }

    public void setCantidadMesas(int cantidadMesas) {
        this.cantidadMesas = cantidadMesas;
    }

    public void grabar() {
        try {
            PrintWriter pw;
            String linea;
            pw = new PrintWriter(new FileWriter(directoryRoute + fileName));
            for (int i = 0; i < 1; i++) {
                linea = (tiempoEstandarEnMesa + ";"
                        + tiempoPrevioReserva + ";"
                        + tiempoEsperaEnMesa + ";"
                        + cantidadMesas);
                pw.println(linea);
            }
            pw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void cargar() {
        try {
            BufferedReader br;
            String linea = null;
            String[] array = null;
            br = new BufferedReader(new FileReader(directoryRoute + fileName));
            while ((linea = br.readLine()) != null) {
                array = linea.split(";");
                tiempoEstandarEnMesa = (Integer.parseInt(array[0].trim()));
                tiempoPrevioReserva = (Integer.parseInt(array[1].trim()));
                tiempoEsperaEnMesa = (Integer.parseInt(array[2].trim()));
                cantidadMesas = (Integer.parseInt(array[3].trim()));
            }
            br.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void cargarEstandar() {
        try {
            BufferedReader br;
            String linea = null;
            String[] array = null;
            br = new BufferedReader(new FileReader(directoryRoute + standarFileName));
            while ((linea = br.readLine()) != null) {
                array = linea.split(";");
                tiempoEstandarEnMesa = (Integer.parseInt(array[0].trim()));
                tiempoPrevioReserva = (Integer.parseInt(array[1].trim()));
                tiempoEsperaEnMesa = (Integer.parseInt(array[2].trim()));
                cantidadMesas = (Integer.parseInt(array[3].trim()));
            }
            br.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
