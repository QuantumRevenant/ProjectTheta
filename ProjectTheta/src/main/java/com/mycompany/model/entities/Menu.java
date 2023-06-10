package com.mycompany.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Menu {
    private int idMenu;
    private int idCategoria;
    private String tipo;
    private String descripcion;
    private double precio;
}
