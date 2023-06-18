package com.mycompany.controller;

import com.mycompany.model.entities.DetallePedido;
import com.mycompany.services.DetallePedidoService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class DetallePedidoController {
    private final DetallePedidoService detallePedidoService;
    public List<DetallePedido> getDetails(){
        return detallePedidoService.findAll();
    }
}
