/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controller;

import com.mycompany.model.entities.TipoPedido;
import com.mycompany.services.TipoPedidoService;
import java.util.List;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author sebap
 */
@RequiredArgsConstructor
public class TipoPedidoController {
        private final TipoPedidoService tipoPedidoController;

    public List<TipoPedido> getEmployees(){
        return tipoPedidoController.findAll();
    }

    public TipoPedido findPersonalById(int id){
        return tipoPedidoController.findById(id);
    }
    
    public TipoPedido findPersonalByName(String name){
        return tipoPedidoController.findByName(name);
    }
}
