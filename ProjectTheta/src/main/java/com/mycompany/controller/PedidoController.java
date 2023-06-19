package com.mycompany.controller;

import com.mycompany.model.entities.Pedido;
import com.mycompany.services.PedidoService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class PedidoController {
    private final PedidoService pedidoService;


    public List<Pedido> getOrders(){
        return pedidoService.findAll();
    }

    public void addOrder(Pedido pedido){pedidoService.save(pedido);}

    public void deleteOrder(Pedido pedido){
        pedidoService.delete(pedido);
    }

    public void updateOrder(Pedido pedido){
        pedidoService.update(pedido);
    }
    
     public Pedido findCustomerById(int id){
        return pedidoService.findById(id);
    }
     
     public Pedido findMaxItem(){
         return pedidoService.findMaxItem();
     }
    /*VALIDAR EN EL FRONT*/

}
