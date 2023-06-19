package com.mycompany.services;

import com.mycompany.database.Configuration;
import com.mycompany.model.entities.TipoPago;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TipoPagoService {

    public List<TipoPago> findAll() {
        try {
            List<TipoPago> tipoPago = new ArrayList<>();
            CallableStatement caller = Configuration.getConnectionDatabase().prepareCall("{CALL tipoPagoList()}");
            ResultSet listGet = caller.executeQuery();
            while (listGet.next()) {
                TipoPago p = new TipoPago();
                p.setIdTipoPago(listGet.getInt("idTipoPago"));
                p.setTipoPago(listGet.getString("descripcion"));
                tipoPago.add(p);
            }
            Configuration.getConnectionDatabase().close();
            listGet.close();
            return tipoPago;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public TipoPago findById(int id) {
        try {
            PreparedStatement statement = Configuration.getConnectionDatabase().prepareStatement("SELECT * FROM reservation.tipopago WHERE idTipoPago = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                TipoPago tPago = new TipoPago();
                tPago.setIdTipoPago(resultSet.getInt("idTipoPago"));
                tPago.setTipoPago(resultSet.getString("descripcion"));
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
