/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.model;

/**
 *
 * @author sebap
 */
public class Mesa {
     private int codigo ;     
     private MESA_STATUS mesa_status;
     private Long codigoPedido;
     
     private static enum MESA_STATUS{
         LIBRE,OCUPADA
     }
     
}
