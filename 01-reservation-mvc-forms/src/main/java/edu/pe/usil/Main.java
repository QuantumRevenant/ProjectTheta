package edu.pe.usil;

import edu.pe.usil.db.Configuration;
import java.sql.*;


public class Main {
    public static void main(String[] args) throws SQLException {
        Connection c = Configuration.getConnectionDatabase();
        System.out.println("Exito" + c);

    }
}
