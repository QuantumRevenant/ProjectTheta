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
     
    public Mesa(int codigo) {
        this.codigo = codigo;
        mesa_status=MESA_STATUS.LIBRE;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public MESA_STATUS getMesa_status() {
        return mesa_status;
    }

    public void setMesa_status(MESA_STATUS mesa_status) {
        this.mesa_status = mesa_status;
    }

    public Long getCodigoPedido() {
        return codigoPedido;
    }

    public void setCodigoPedido(Long codigoPedido) {
        this.codigoPedido = codigoPedido;
    }
     
     
     
}
