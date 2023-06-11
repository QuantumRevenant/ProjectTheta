package com.mycompany;

import com.mycompany.model.generics.Sha256;
import com.mycompany.view.StartMenu;

public class ProjectTheta {
    public static void main(String[] args) {
        System.out.println(Sha256.sha256("Hello World!"));
        StartMenu.main(args);
    }
}
