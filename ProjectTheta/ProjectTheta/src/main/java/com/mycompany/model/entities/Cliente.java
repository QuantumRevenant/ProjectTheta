package com.mycompany.model.entities;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {
    private int idCliente;
    private String nombre;
    private String apellido;
    private String dni;
    private String telefono;
    private String direccion;
}
