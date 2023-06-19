package com.mycompany.controller;

import com.mycompany.model.entities.DetallePedido;
import com.mycompany.services.DetallePedidoService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class DetallePedidoController {
    private final DetallePedidoService detallePedidoService;

    public void addDetails(DetallePedido obj){
        detallePedidoService.save(obj);
    }
    
    public void addLstDetails(List<DetallePedido> lst){
        for(DetallePedido x:lst)
        {
            addDetails(x);
        }
    }
    
    public List<DetallePedido> getDetails(){
        return detallePedidoService.findAll();
    }
    
    public DetallePedido findByID(int pedidoId,int menuId)
    {
        return detallePedidoService.findByID(pedidoId, menuId);
    }
    
    public List<DetallePedido> listByPedidoID(int id)
    {
        return detallePedidoService.findByPedidoID(id);
    }
    
    public List<DetallePedido> listByMenuID(int id)
    {
        return detallePedidoService.findByMenuID(id);
    }
}
