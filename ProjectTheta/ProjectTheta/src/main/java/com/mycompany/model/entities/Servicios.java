package com.mycompany.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Servicios {
    private int idServicio;
    private String tipo;
    private String descripcion;
    private double precio;
}
