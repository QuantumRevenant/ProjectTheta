package com.mycompany.services;

import com.mycompany.database.Configuration;
import com.mycompany.model.entities.TipoPago;


import java.sql.*;
public class TipoPagoService {
    public TipoPago findById(int id){
        try {
            PreparedStatement statement = Configuration.getConnectionDatabase().prepareStatement("SELECT * FROM reservation.tipopago WHERE idTipoPago = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                TipoPago tPago = new TipoPago();
                tPago.setIdTipoPago(resultSet.getInt("idTipoPago"));
                tPago.setTipo(TipoPago.TIPOPAGO.valueOf(resultSet.getString("descripcion")));
                return tPago;
            }
            resultSet.close();
            statement.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
