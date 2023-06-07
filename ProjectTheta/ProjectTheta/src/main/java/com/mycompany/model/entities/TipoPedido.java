package com.mycompany.model.entities;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TipoPedido {
    private int idTipoPedido;
    private PEDIDOS tipoPedido;

    public static enum PEDIDOS{
        PICKUP,RESERVA,DELIVERY,SALON
    }
}
