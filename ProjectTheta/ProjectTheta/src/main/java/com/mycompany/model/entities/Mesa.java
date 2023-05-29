package com.mycompany.model.entities;
public class Mesa {
     private int codigo ;     
     private MESA_STATUS mesa_status;
     private Long codigoPedido;
     
     private static enum MESA_STATUS{
         LIBRE,OCUPADA
     }
     
}
