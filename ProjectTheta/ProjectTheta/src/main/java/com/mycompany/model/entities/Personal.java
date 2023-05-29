package com.mycompany.model.entities;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Personal {
    private int idPersonal;
    private String nombre;
    private String apellido;
    private String usuario;
    private String password;
    private String telefono;
    private String horaInicio;
    private String horaFin;
    private String diaDescanso;
    private CARGOS nombreCargo;

    public static enum CARGOS{
        ADMIN,EMPLEADO
    }

}
