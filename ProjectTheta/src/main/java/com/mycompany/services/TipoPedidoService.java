package com.mycompany.services;

import com.mycompany.database.Configuration;
import com.mycompany.model.entities.TipoPedido;
import java.sql.CallableStatement;
import java.sql.Connection;
import lombok.RequiredArgsConstructor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TipoPedidoService {

    private Configuration configuration = Configuration.getConf();

    public List<TipoPedido> findAll() {
        try {
            List<TipoPedido> tipoPedido = new ArrayList<>();
            Connection conn = configuration.getConnectionDatabase();
            CallableStatement caller = conn.prepareCall("{CALL tipoPedidoList()}");
            ResultSet listGet = caller.executeQuery();
            while (listGet.next()) {
                TipoPedido p = new TipoPedido();
                p.setIdTipoPedido(listGet.getInt("idTipoPedido"));
                p.setTipoPedido(listGet.getString("descripcion"));
                tipoPedido.add(p);
            }
            listGet.close();
            configuration.releaseConnection(conn);
            return tipoPedido;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public TipoPedido findById(int id) {
        try {
            Connection conn = configuration.getConnectionDatabase();
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM reservation.tipopedido WHERE idTipoPedido = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                TipoPedido tipoPedido = new TipoPedido();
                tipoPedido.setIdTipoPedido(resultSet.getInt("idTipoPedido"));
                tipoPedido.setTipoPedido(resultSet.getString("descripcion"));
                resultSet.close();
                statement.close();
                configuration.releaseConnection(conn);
                return tipoPedido;
            }
            resultSet.close();
            statement.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public TipoPedido findByName(String name) {
        try {
            Connection conn = configuration.getConnectionDatabase();
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM reservation.tipopedido WHERE UPPER(descripcion) LIKE UPPER(?)");
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                TipoPedido tipoPedido = new TipoPedido();
                tipoPedido.setIdTipoPedido(resultSet.getInt("idTipoPedido"));
                tipoPedido.setTipoPedido(resultSet.getString("descripcion"));
                resultSet.close();
                statement.close();
                configuration.releaseConnection(conn);
                return tipoPedido;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
