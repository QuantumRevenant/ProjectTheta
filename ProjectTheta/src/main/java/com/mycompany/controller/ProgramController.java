package com.mycompany.controller;

import com.mycompany.model.entities.Personal;
import com.mycompany.model.generics.Sha256;
import com.mycompany.services.PersonalService;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
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
    private final DateTimeFormatter formatDayTimeMinutes = DateTimeFormatter.ofPattern("dd-MM-yyyy, HH:mm");
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

    public static Personal logInAdmin(JFrame parent) {
        ProgramController pc = getProgramController();
        PersonalController prC = new PersonalController(new PersonalService());
        JPanel p = new JPanel(new BorderLayout(5, 5));

        JPanel title = new JPanel(new GridLayout(0, 1, 2, 2));
        JLabel lblTitle = new JLabel("Login as Admin", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 14));
        title.add(lblTitle);
        p.add(title, BorderLayout.NORTH);

        JPanel labels = new JPanel(new GridLayout(0, 1, 2, 2));
        labels.add(new JLabel("User Name", SwingConstants.TRAILING));
        labels.add(new JLabel("Password", SwingConstants.TRAILING));
        p.add(labels, BorderLayout.LINE_START);

        JPanel controls = new JPanel(new GridLayout(0, 1, 2, 2));
        JTextField uf = new JTextField();
        controls.add(uf);
        JPasswordField pf = new JPasswordField();
        controls.add(pf);
        p.add(controls, BorderLayout.CENTER);

        Personal empleado = null;
        int okCxl = JOptionPane.showConfirmDialog(parent, p, "Login Admin", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (okCxl == JOptionPane.OK_OPTION) {
            String password = Sha256.sha256(new String(pf.getPassword()));
            empleado = prC.Admin(uf.getText(), password);
        }
        if (empleado != null) {
            pc.cleanSesion();
            pc.setIdColaboradorActivo(empleado);
            pc.openSesion(15);
        } else {
            System.out.println("Sesión No Iniciada - Admin");
        }
        if (empleado == null) {
            JOptionPane.showMessageDialog(parent, "Credencial Invalida", "Error", JOptionPane.WARNING_MESSAGE);
        }
        return empleado;
    }

    public static Personal logInUser(JFrame parent) {
        ProgramController pc = getProgramController();
        PersonalController prC = new PersonalController(new PersonalService());
        JPanel p = new JPanel(new BorderLayout(5, 5));

        JPanel title = new JPanel(new GridLayout(0, 1, 2, 2));
        JLabel lblTitle = new JLabel("Login as User", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 14));
        title.add(lblTitle);
        p.add(title, BorderLayout.NORTH);
        JPanel labels = new JPanel(new GridLayout(0, 1, 2, 2));
        labels.add(new JLabel("Password", SwingConstants.TRAILING));
        p.add(labels, BorderLayout.LINE_START);

        JPanel controls = new JPanel(new GridLayout(0, 1, 2, 2));
        JPasswordField pf = new JPasswordField();
        controls.add(pf);
        p.add(controls, BorderLayout.CENTER);

        Personal empleado = null;
        int okCxl = JOptionPane.showConfirmDialog(parent, p, "Login User", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (okCxl == JOptionPane.OK_OPTION) {
            String password = Sha256.sha256(new String(pf.getPassword()));
            empleado = prC.findPersonalByPassword(password);
        }
        if (empleado != null) {
            pc.cleanSesion();
            pc.setIdColaboradorActivo(empleado);
            pc.openSesion(15);
        } else {
            System.out.println("Sesión No Iniciada - User");
        }
        if (empleado == null) {
            JOptionPane.showMessageDialog(parent, "Credencial Invalida", "Error", JOptionPane.WARNING_MESSAGE);
        }
        return empleado;
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
