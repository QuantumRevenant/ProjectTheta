package com.mycompany.services;

import com.mycompany.database.Configuration;
import com.mycompany.model.entities.TipoPedido;
import java.sql.*;

public class TipoPedidoService {
    public TipoPedido findById(int id){
        try {
            PreparedStatement statement = Configuration.getConnectionDatabase().prepareStatement("SELECT * FROM reservation.tipopedido WHERE idTipoPedido = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                TipoPedido tipoPedido = new TipoPedido();
                tipoPedido.setIdTipoPedido(resultSet.getInt("idTipoPedido"));
                tipoPedido.setTipoPedido(TipoPedido.PEDIDOS.valueOf(resultSet.getString("descripcion")));
                return tipoPedido;
            }
            resultSet.close();
            statement.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
