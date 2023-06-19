package com.mycompany.model.entities;

import com.mycompany.controller.TipoPedidoController;
import com.mycompany.services.TipoPedidoService;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TipoPedido {
    private static TipoPedidoController tpc = new TipoPedidoController(new TipoPedidoService());
    private int idTipoPedido;
    private String tipoPedido;
    
    public static int SALON=tpc.findPersonalByName("salon")!=null?tpc.findPersonalByName("salon").getIdTipoPedido():-1; 
    public static int RESERVA=tpc.findPersonalByName("reserva")!=null?tpc.findPersonalByName("reserva").getIdTipoPedido():-1;
}
