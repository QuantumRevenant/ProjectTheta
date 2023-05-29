package com.mycompany.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetallePedido {
    private Servicios idServicio;
    private Pedido idPedido;
    private int cantidadPlatos;
    private double subTotal;
}



