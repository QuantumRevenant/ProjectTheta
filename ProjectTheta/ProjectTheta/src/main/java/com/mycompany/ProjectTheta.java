/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany;

import com.mycompany.model.Sha256;
import com.mycompany.view.StartMenu;

/**
 *
 * @author sebap
 */
public class ProjectTheta {

    public static void main(String[] args) {
        System.out.println(Sha256.sha256("Hello World!"));
        StartMenu.main(args);
    }
}
