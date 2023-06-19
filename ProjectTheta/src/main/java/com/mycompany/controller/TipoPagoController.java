/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controller;

import com.mycompany.model.entities.TipoPago;
import com.mycompany.services.TipoPagoService;
import java.util.List;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author sebap
 */
@RequiredArgsConstructor
public class TipoPagoController {
        private final TipoPagoService tipoPagoService;

    public List<TipoPago> getEmployees(){
        return tipoPagoService.findAll();
    }

    public TipoPago findPersonalById(int id){
        return tipoPagoService.findById(id);
    }
}
