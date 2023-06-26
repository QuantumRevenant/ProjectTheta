/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.services;

import com.mycompany.database.Configuration;
import com.mycompany.model.entities.Mesa;
import com.mycompany.model.entities.Pedido;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sebap
 */
public class MesaService implements BaseService<Mesa> {

    private Configuration configuration = Configuration.getConf();

    @Override
    public List<Mesa> findAll() {
        try {
            List<Mesa> mesaList = new ArrayList<>();
            Connection conn = configuration.getConnectionDatabase();
            CallableStatement caller = conn.prepareCall("{CALL mesaList()}");
            ResultSet resultSet = caller.executeQuery();
            while (resultSet.next()) {
                Mesa mesa = new Mesa();
                mesa.setCodigo(resultSet.getInt("idMesa"));
                mesa.setNombreMesa(resultSet.getString("nombreMesa"));
                mesa.setMesa_status(Mesa.MESA_STATUS.valueOf(resultSet.getString("statusMesa")));
                mesaList.add(mesa);
            }
            resultSet.close();
            configuration.releaseConnection(conn);
            return mesaList;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<Mesa> findMinorsTo(int quantity) {
        try {
            List<Mesa> mesaList = new ArrayList<>();
            Connection conn = configuration.getConnectionDatabase();
            CallableStatement caller = conn.prepareCall("{CALL mesaList()}");
            ResultSet resultSet = caller.executeQuery();
            while (resultSet.next()) {
                Mesa mesa = new Mesa();
                mesa.setCodigo(resultSet.getInt("idMesa"));
                mesa.setNombreMesa(resultSet.getString("nombreMesa"));
                mesa.setMesa_status(Mesa.MESA_STATUS.valueOf(resultSet.getString("statusMesa")));
                if (mesa.getCodigo() <= quantity) {
                    mesaList.add(mesa);
                }
            }
            resultSet.close();
            configuration.releaseConnection(conn);
            return mesaList;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void save(Mesa t) {
        try {
            Connection conn = configuration.getConnectionDatabase();
            CallableStatement createMesa = conn.prepareCall("{CALL createMesa(?,?,?)}");
            int idmesa = findMaxId() + 1; // Obtener idCategoria de otra tabla
            createMesa.setInt(1, idmesa);
            createMesa.setString(2, t.getNombreMesa());
            createMesa.setString(3, t.getMesa_status().toString());
            createMesa.executeQuery();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(Mesa t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Mesa t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Mesa findById(int id) {
        try {
            Connection conn = configuration.getConnectionDatabase();
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM reservation.mesa WHERE idMesa=?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Mesa mesa = new Mesa();
                mesa.setCodigo(resultSet.getInt("idMesa"));
                mesa.setNombreMesa(resultSet.getString("nombreMesa"));
                mesa.setMesa_status(Mesa.MESA_STATUS.valueOf(resultSet.getString("statusMesa")));
                resultSet.close();
                configuration.releaseConnection(conn);
                return mesa;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public int findMaxId() {
        try {
            Connection conn = configuration.getConnectionDatabase();
            PreparedStatement statement = conn.prepareStatement("SELECT MAX(idMesa) FROM mesa");
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int value = resultSet.getInt("MAX(idMesa)");
                resultSet.close();
                statement.close();
                configuration.releaseConnection(conn);
                return value;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

}
