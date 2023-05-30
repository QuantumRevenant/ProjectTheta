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
    private String Status;
    private Personal idPersonal;
    private TipoPedido idTipoPedido;
    private Cliente idCliente;
    private TipoPago idTipoPago;
    private double igv;

    public double getIgv() {
        return .18;
    }
}
