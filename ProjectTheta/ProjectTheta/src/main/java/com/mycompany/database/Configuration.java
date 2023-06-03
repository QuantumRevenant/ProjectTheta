package com.mycompany.database;

import lombok.NoArgsConstructor;

import java.sql.Connection;
import java.sql.DriverManager;

@NoArgsConstructor
public class Configuration {

    public static Connection getConnectionDatabase(){
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/reservation","root","");
            //modificar database connection segun tus parametros
            System.out.println("Conexion exitosa!!!");
        } catch(Exception ex) {
            System.out.println("Error al conectar con la BD" + ex.getMessage());
        }
        return con;
    }
}
