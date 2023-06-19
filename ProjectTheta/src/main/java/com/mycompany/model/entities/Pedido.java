package com.mycompany.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {

    private int idPedido;
    private String descripcion;
    private double total;
    private String fechaPedido;
    private PEDIDO_STATUS Status;
    private Personal idPersonal;
    private TipoPedido idTipoPedido;
    private Cliente idCliente;
    private TipoPago idTipoPago;
    private double igv;

    public static enum PEDIDO_STATUS {
        PENDIENTE, EN_ENVIO, POR_RECOGER, COMPLETO, CANCELADO, OTRO;
    }

    public static double igv() {
        return .18;
    }
    
    public Pedido clone(){
        return new Pedido(idPedido, descripcion, total, fechaPedido, Status, idPersonal, idTipoPedido, idCliente, idTipoPago, igv);
    }
}
