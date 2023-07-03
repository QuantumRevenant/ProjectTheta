package com.mycompany.model.generics;

import com.mycompany.controller.ProgramController;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class General {
    public static String clock() {
        ProgramController pc=ProgramController.getProgramController();
        LocalDateTime datetime= LocalDateTime.now();
       DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("HH:mm:ss");
       DateTimeFormatter formatDay = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        return ("Date: "+datetime.format(formatDay)+" - Time: "+datetime.format(formatTime));

    }
    
    public static LocalDateTime parseLDT(String str)
    {
        return LocalDateTime.parse(str,ProgramController.getProgramController().getFormatDayTime());
    }
    
    public static int getSelectedId(JComboBox<String> comboBox){
        String palabra = comboBox.getSelectedItem().toString();
        int indice = palabra.indexOf(']');
        return Integer.parseInt(palabra.substring(1, indice));
    }
    
    public static void filtrarCbo(JComboBox<String> comboBox, JTextField textField) {
        DefaultComboBoxModel<String> dcbm = new DefaultComboBoxModel<>();
        String busqueda = textField.getText();
        for (int i = 0; i < comboBox.getItemCount(); i++) {
            String item = comboBox.getItemAt(i);
            if (item.toLowerCase().contains(busqueda.toLowerCase())) {
                dcbm.addElement(item);
            }
        }
        if(dcbm.getSize() == 0){ dcbm.addElement("[0] - All"); }
        comboBox.setModel(dcbm);
    }
}
