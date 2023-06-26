/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.model.entities;

import java.time.Duration;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author sebap
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mesa {

    private int codigo;
    private String nombreMesa;
    private MESA_STATUS mesa_status;

    public static enum MESA_STATUS {
        LIBRE, OCUPADA
    }

    public Mesa(int codigo) {
        this.codigo = codigo;
        nombreMesa="Mesa #"+codigo;
        LiberarMesa();
    }

    public void LiberarMesa() {
        mesa_status = MESA_STATUS.LIBRE;
    }
    
    public void OcuparMesa()
    {
        mesa_status = MESA_STATUS.OCUPADA;        
    }    
}
