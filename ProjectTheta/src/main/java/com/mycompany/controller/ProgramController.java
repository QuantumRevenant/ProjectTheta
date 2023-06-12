/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controller;

import com.mycompany.model.entities.Personal;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ProgramController {

    private int tiempoEstandarEnMesa;
    private int tiempoPrevioReserva;
    private int tiempoEsperaEnMesa;
    private int cantidadMesas;

    private Personal IdColaboradorActivo = null;
    private LocalDateTime cierreSesion = null;

    private final DateTimeFormatter formatDayTime = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    private final DateTimeFormatter formatDay = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private final DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("HH:mm:ss");

    private final String directoryRoute = "./src/main/java/com/mycompany/data/";
    private final String fileName = "preferences.csv";
    private final String standarFileName = "standarPreferences.csv";

    public static ProgramController theProgramController;

    public static ProgramController getProgramController() {
        if (theProgramController == null) {
            theProgramController = new ProgramController();
        }
        return theProgramController;
    }

    public ProgramController() {
        cargar();
    }

    public ProgramController(int tiempoEstandarEnMesa, int tiempoPrevioReserva, int tiempoEsperaEnMesa, int cantidadMesas) {
        this.tiempoEstandarEnMesa = tiempoEstandarEnMesa;
        this.tiempoPrevioReserva = tiempoPrevioReserva;
        this.tiempoEsperaEnMesa = tiempoEsperaEnMesa;
        this.cantidadMesas = cantidadMesas;
    }

    public void openSesion(int minutos) {
        cierreSesion = LocalDateTime.now().plusMinutes(minutos);
    }

    public long minutosRestanteSesion() {
        Duration d = Duration.between(LocalDateTime.now(), cierreSesion);
        return d.toMinutes();
    }

    public void cleanSesion() {
        IdColaboradorActivo = null;
        cierreSesion = null;
    }

    public boolean isActiveSesion() {
        return IdColaboradorActivo != null;
    }

    public void setSesion(Personal Id) {
        IdColaboradorActivo = Id;
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
