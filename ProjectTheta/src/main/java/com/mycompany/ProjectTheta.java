package com.mycompany;

import com.mycompany.controller.MesaController;
import com.mycompany.database.Configuration;
import com.mycompany.model.generics.Print;
import com.mycompany.model.generics.Sha256;
import com.mycompany.view.MainMenu;
import com.mycompany.view.StartMenu;

public class ProjectTheta {
    
    public static void main(String[] args) {
        System.out.println(Sha256.sha256("Hello World!"));
        System.out.println(Sha256.sha256("https://github.com/QuantumRevenant/ProjectTheta"));
//        Configuration config=Configuration.getConf();
//        StartMenu.main(args);
        MainMenu.main(args);
    }
}
