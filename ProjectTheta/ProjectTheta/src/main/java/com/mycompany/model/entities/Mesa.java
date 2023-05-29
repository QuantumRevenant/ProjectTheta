/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.model.entities;

/**
 *
 * @author sebap
 */
public class Mesa {
     private int codigo ;     
     private MESA_STATUS mesa_status;
     private long codigoPedido;
     private int fechaDeReserva;
     private int horaDeReserva; 
     private String nombreCliente;
     private int horaDeLiberacion;
     
     public static enum MESA_STATUS{
         LIBRE,OCUPADA
     }
     
    public Mesa(int codigo) {
        this.codigo = codigo;
        LiberarMesa();        
    }
    
    public void LiberarMesa()
    {
        mesa_status=MESA_STATUS.LIBRE;
        codigoPedido=-1;
        fechaDeReserva=-1;
        horaDeReserva=-1;
        nombreCliente=null; 
        horaDeLiberacion=-1;
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

    public long getCodigoPedido() {
        return codigoPedido;
    }

    public void setCodigoPedido(long codigoPedido) {
        this.codigoPedido = codigoPedido;
    }

    public int getFechaDeReserva() {
        return fechaDeReserva;
    }

    public void setFechaDeReserva(int fechaDeReserva) {
        this.fechaDeReserva = fechaDeReserva;
    }

    public int getHoraDeReserva() {
        return horaDeReserva;
    }

    public void setHoraDeReserva(int horaDeReserva) {
        this.horaDeReserva = horaDeReserva;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public int getHoraDeLiberacion() {
        return horaDeLiberacion;
    }

    public void setHoraDeLiberacion(int horaDeLiberacion) {
        this.horaDeLiberacion = horaDeLiberacion;
    }
      
}
