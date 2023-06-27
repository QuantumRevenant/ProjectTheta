package com.mycompany.services;

import com.mycompany.database.Configuration;
import com.mycompany.model.entities.*;
import com.mycompany.model.entities.Menu;

import java.sql.*;
import java.util.*;
import java.util.List;

public class DetallePedidoService {

    private Configuration configuration = Configuration.getConf();

    public List<DetallePedido> findAll() {
        try {
            List<DetallePedido> orderDetails = new ArrayList<>();
            Connection conn = configuration.getConnectionDatabase();
            CallableStatement caller = conn.prepareCall("{CALL orderDetailsList()}");
            ResultSet listGet = caller.executeQuery();
            while (listGet.next()) {
                DetallePedido details = new DetallePedido();

                int serviciosId = listGet.getInt("idMenu");
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
            listGet.close();
            configuration.releaseConnection(conn);
            return orderDetails;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void save(DetallePedido detallePedido) {
        try {
            Connection conn = configuration.getConnectionDatabase();
            CallableStatement caller = conn.prepareCall("{CALL createOrderDetails(?,?,?,?)}");
            caller.setInt(1, detallePedido.getIdPedido().getIdPedido());
            caller.setInt(2, detallePedido.getIdMenu().getIdMenu());
            caller.setInt(3, detallePedido.getCantidadPlatos());
            caller.setDouble(4, detallePedido.getSubTotal());
            caller.executeUpdate();
            caller.close();
            configuration.releaseConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public DetallePedido findByID(int idPersonal, int idMenu) {
        try {
            Connection conn = configuration.getConnectionDatabase();
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM `pedidos` WHERE idPersonal = ? AND idCliente= ?");
            statement.setInt(1, idPersonal);
            statement.setInt(2, idMenu);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                DetallePedido details = new DetallePedido();
                int serviciosId = resultSet.getInt("idMenu");
                MenuService menuService = new MenuService();
                Menu menu = menuService.findById(serviciosId);
                int pedidoId = resultSet.getInt("idPedido");
                PedidoService pedidoService = new PedidoService();
                Pedido pedido = pedidoService.findById(pedidoId);

                details.setIdPedido(pedido);
                details.setIdMenu(menu);
                details.setCantidadPlatos(resultSet.getInt("cantidadPlatos"));
                details.setSubTotal(resultSet.getDouble("subtotal"));
                resultSet.close();
                statement.close();
                configuration.releaseConnection(conn);
                return details;
            }
            resultSet.close();
            statement.close();
            configuration.releaseConnection(conn);
            return null;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<DetallePedido> findByPedidoID(int id) {
        try {
            List<DetallePedido> orderDetails = new ArrayList<>();
            Connection conn = configuration.getConnectionDatabase();
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM reservation.detallepedido WHERE idPedido = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                DetallePedido details = new DetallePedido();
                int serviciosId = resultSet.getInt("idMenu");
                MenuService menuService = new MenuService();
                Menu menu = menuService.findById(serviciosId);
                int pedidoId = resultSet.getInt("idPedido");
                PedidoService pedidoService = new PedidoService();
                Pedido pedido = pedidoService.findById(pedidoId);

                details.setIdPedido(pedido);
                details.setIdMenu(menu);
                details.setCantidadPlatos(resultSet.getInt("cantidadPlatos"));
                details.setSubTotal(resultSet.getDouble("subtotal"));
                orderDetails.add(details);
            }
            resultSet.close();
            statement.close();
            configuration.releaseConnection(conn);
            return orderDetails;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<DetallePedido> findByMenuID(int id) {
        try {
            List<DetallePedido> orderDetails = new ArrayList<>();
            Connection conn = configuration.getConnectionDatabase();
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM reservation.detallepedido WHERE idMenu = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                DetallePedido details = new DetallePedido();
                int serviciosId = resultSet.getInt("idMenu");
                MenuService menuService = new MenuService();
                Menu menu = menuService.findById(serviciosId);
                int pedidoId = resultSet.getInt("idPedido");
                PedidoService pedidoService = new PedidoService();
                Pedido pedido = pedidoService.findById(pedidoId);

                details.setIdPedido(pedido);
                details.setIdMenu(menu);
                details.setCantidadPlatos(resultSet.getInt("cantidadPlatos"));
                details.setSubTotal(resultSet.getDouble("subtotal"));
                orderDetails.add(details);
            }
            resultSet.close();
            statement.close();
            configuration.releaseConnection(conn);
            return orderDetails;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;

    }

    public void deletePerIdPedido(Pedido idPedido) {
        try {
            Connection conn = configuration.getConnectionDatabase();
            CallableStatement caller = conn.prepareCall("{CALL deleteOrderDetailsPerPedido(?)}");
            caller.setInt(1, idPedido.getIdPedido());
            caller.executeUpdate();
            caller.close();
            configuration.releaseConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
