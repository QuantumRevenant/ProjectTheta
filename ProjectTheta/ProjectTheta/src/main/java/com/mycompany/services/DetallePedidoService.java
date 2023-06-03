package com.mycompany.services;

import com.mycompany.database.Configuration;
import com.mycompany.model.entities.*;
import com.mycompany.model.entities.Menu;

import java.sql.*;
import java.util.*;
import java.util.List;


public class DetallePedidoService {
    public List<DetallePedido> findAll() {
        try {
            List<DetallePedido> orderDetails = new ArrayList<>();
            CallableStatement caller = Configuration.getConnectionDatabase().prepareCall("{CALL orderDetailsList()}");
            ResultSet listGet = caller.executeQuery();
            while (listGet.next()) {
                DetallePedido details = new DetallePedido();

                int serviciosId = listGet.getInt("idServicio");
                MenuService menuService = new MenuService();
                Menu menu = menuService.findById(serviciosId);
                int pedidoId = listGet.getInt("idPedido");
                PedidoService pedidoService = new PedidoService();
                Pedido pedido = pedidoService.findById(pedidoId);

                details.setIdPedido(pedido);
                details.setIdMenu(menu);
                details.setCantidadPlatos(listGet.getInt("cantidadPlatos"));
                details.setSubTotal(listGet.getDouble("subtotal"));
                orderDetails.add(details);
            }
            Configuration.getConnectionDatabase().close();
            listGet.close();
            return orderDetails;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void save(DetallePedido detallePedido) {
        try {
            CallableStatement caller = Configuration.getConnectionDatabase().prepareCall("{CALL createOrderDetails(?,?,?,?)}");
            caller.setInt(1, detallePedido.getIdPedido().getIdPedido());
            caller.setInt(2, detallePedido.getIdMenu().getIdMenu());
            caller.setInt(3, detallePedido.getCantidadPlatos());
            caller.setDouble(4, detallePedido.getSubTotal());
            caller.executeUpdate();
            caller.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
