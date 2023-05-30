package com.mycompany.services;

import com.mycompany.database.Configuration;
import com.mycompany.model.entities.*;
import lombok.RequiredArgsConstructor;

import java.util.*;
import java.sql.*;

public class PedidoService{

    public List<Pedido> findAll() {
        try {
            List<Pedido> orders = new ArrayList<>();
            CallableStatement caller = Configuration.getConnectionDatabase().prepareCall("{CALL ordersList()}");
            ResultSet listGet = caller.executeQuery();
            while (listGet.next()) {
                Pedido pedido= new Pedido();

                int clienteId = listGet.getInt("idCliente");
                ClienteService clienteService = new ClienteService();
                Cliente cliente = clienteService.findById(clienteId);

                int personalId = listGet.getInt("idPersonal");
                PersonalService personalService = new PersonalService();
                Personal personal = personalService.findById(personalId);


                int tPedidoId = listGet.getInt("idTipoPedido");
                TipoPedidoService tPedidoService = new TipoPedidoService();
                TipoPedido tPedido = tPedidoService.findById(tPedidoId);

                int tPagoId = listGet.getInt("idTipoPago");
                TipoPagoService tipoPagoService = new TipoPagoService();
                TipoPago tPago = tipoPagoService.findById(tPagoId);

                pedido.setStatus(listGet.getString("status"));
                pedido.setIdPedido(listGet.getInt("idPedido"));
                pedido.setDescripcion(listGet.getString("descripcion"));
                pedido.setTotal(listGet.getDouble("total"));
                pedido.setFechaPedido(listGet.getString("fechaPedido"));
                pedido.setIdPersonal(personal);
                pedido.setIdTipoPedido(tPedido);

                pedido.setIdCliente(cliente);
                pedido.setIdTipoPago(tPago);
                pedido.setIgv(listGet.getDouble("igv"));
                orders.add(pedido);
            }
            Configuration.getConnectionDatabase().close();
            listGet.close();
            return orders;
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


    public void save(Pedido pedido) {
        try {
            CallableStatement createOrder  = Configuration.getConnectionDatabase().prepareCall("{CALL saveOrder(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
            createOrder.setInt(1, pedido.getIdPedido());
            createOrder.setString(2, pedido.getDescripcion());
            createOrder.setDouble(3, pedido.getTotal());
            createOrder.setString(4, pedido.getFechaPedido());
            createOrder.setString(5, pedido.getStatus());
            createOrder.setInt(6, pedido.getIdPersonal().getIdPersonal());
            createOrder.setInt(7, pedido.getIdTipoPedido().getIdTipoPedido());
            createOrder.setInt(8, pedido.getIdCliente().getIdCliente());
            createOrder.setInt(9, pedido.getIdTipoPago().getIdTipoPago());
            createOrder.setDouble(10, pedido.getIgv());
            createOrder.executeUpdate();
            Configuration.getConnectionDatabase().close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public void update(Pedido pedido) {
            try {
                CallableStatement caller = Configuration.getConnectionDatabase().prepareCall("{CALL updateOrder(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
                caller.setInt(1, pedido.getIdPedido());
                caller.setString(2, pedido.getDescripcion());
                caller.setDouble(3, pedido.getTotal());
                caller.setString(4, pedido.getFechaPedido());
                caller.setString(5, pedido.getStatus());
                caller.setInt(6, pedido.getIdPersonal().getIdPersonal());
                caller.setInt(7, pedido.getIdTipoPedido().getIdTipoPedido());
                caller.setInt(8, pedido.getIdCliente().getIdCliente());
                caller.setInt(9, pedido.getIdTipoPago().getIdTipoPago());
                caller.setDouble(10, pedido.getIgv());

                caller.executeUpdate();
                caller.close();
                Configuration.getConnectionDatabase().close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
    }

    public void delete(Pedido pedido) {
        try {
            CallableStatement caller = Configuration.getConnectionDatabase().prepareCall("{CALL deleteOrder(?)}");
            caller.setInt(1, pedido.getIdPedido());
            caller.executeUpdate();
            caller.close();
            Configuration.getConnectionDatabase().close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public Pedido findById(int id) {
        try {
            PreparedStatement statement = Configuration.getConnectionDatabase().prepareStatement("SELECT * FROM pedidos WHERE idPedido = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Pedido pedido = new Pedido();
                int clienteId = resultSet.getInt("idCliente");
                ClienteService clienteService = new ClienteService();
                Cliente cliente = clienteService.findById(clienteId);

                int personalId = resultSet.getInt("idPersonal");
                PersonalService personalService = new PersonalService();
                Personal personal = personalService.findById(personalId);


                int tPedidoId = resultSet.getInt("idTipoPedido");
                TipoPedidoService tPedidoService = new TipoPedidoService();
                TipoPedido tPedido = tPedidoService.findById(tPedidoId);

                int tPagoId = resultSet.getInt("idTipoPago");
                TipoPagoService tipoPagoService = new TipoPagoService();
                TipoPago tPago = tipoPagoService.findById(tPagoId);
                pedido.setIdPedido(resultSet.getInt("idPedido"));
                pedido.setDescripcion(resultSet.getString("descripcion"));
                pedido.setTotal(resultSet.getDouble("total"));
                pedido.setFechaPedido(resultSet.getString("fecha"));
                pedido.setStatus(resultSet.getString("estado"));
                pedido.setIdPersonal(personal);
                pedido.setIdTipoPedido(tPedido);
                pedido.setIdCliente(cliente);
                pedido.setIdTipoPago(tPago);
                resultSet.close();
                statement.close();
                Configuration.getConnectionDatabase().close();
                return pedido;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }




}
