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
    private MESA_STATUS mesa_status;
    private Pedido idPedido;
    private LocalDateTime fechaHoraMesa;
    private Cliente idCliente;
    private int horaDeLiberacion;

    public static enum MESA_STATUS {
        LIBRE, OCUPADA
    }

    public Mesa(int codigo) {
        this.codigo = codigo;
        LiberarMesa();
    }

    public void LiberarMesa() {
        mesa_status = MESA_STATUS.LIBRE;
        idPedido = null;
        fechaHoraMesa = LocalDateTime.now();
        idCliente = null;
        horaDeLiberacion = -1;
    }

    public long getMinutosFaltantes() {
        Duration d = Duration.between(fechaHoraMesa, LocalDateTime.now());
        return d.toMinutes();
    }
}
