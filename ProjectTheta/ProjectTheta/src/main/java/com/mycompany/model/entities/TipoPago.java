package com.mycompany.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TipoPago {
    private int idTipoPago;
    private TIPOPAGO tipo;

    public static enum TIPOPAGO{
        TARJETA,TRANSFERENCIA,EFECTIVO
    }
}
