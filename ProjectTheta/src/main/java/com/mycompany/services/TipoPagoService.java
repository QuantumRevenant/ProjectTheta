package com.mycompany.services;

import com.mycompany.database.Configuration;
import com.mycompany.model.entities.TipoPago;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TipoPagoService {

    private Configuration configuration = Configuration.getConf();

    public List<TipoPago> findAll() {
        try {
            List<TipoPago> tipoPago = new ArrayList<>();
            Connection conn = configuration.getConnectionDatabase();
            CallableStatement caller = conn.prepareCall("{CALL tipoPagoList()}");
            ResultSet listGet = caller.executeQuery();
            while (listGet.next()) {
                TipoPago p = new TipoPago();
                p.setIdTipoPago(listGet.getInt("idTipoPago"));
                p.setTipoPago(listGet.getString("descripcion"));
                tipoPago.add(p);
            }
            listGet.close();
            configuration.releaseConnection(conn);
            return tipoPago;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public TipoPago findById(int id) {
        try {
            Connection conn = configuration.getConnectionDatabase();
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM reservation.tipopago WHERE idTipoPago = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                TipoPago tPago = new TipoPago();
                tPago.setIdTipoPago(resultSet.getInt("idTipoPago"));
                tPago.setTipoPago(resultSet.getString("descripcion"));
                resultSet.close();
                statement.close();
                configuration.releaseConnection(conn);
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
