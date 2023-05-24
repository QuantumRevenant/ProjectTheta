package edu.pe.usil.db;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.*;

public class Configuration {
    public Configuration(){}
    public static Connection getConnectionDatabase(){
        Connection cn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/reservation","root","root"); //colocar root o dejar vacio
            System.out.println("Conexion exitosa!!!");
        } catch(Exception ex) {
            System.out.println("Error al conectar con la BD" + ex.getMessage());
        }
        return cn;//si hay una conexion
    }
}
