/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.services;

import com.mycompany.database.Configuration;
import com.mycompany.model.entities.Mesa;
import com.mycompany.model.entities.Pedido;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sebap
 */
public class MesaService implements BaseService<Mesa> {

    @Override
    public List<Mesa> findAll() {
        try {
            List<Mesa> mesaList = new ArrayList<>();
            CallableStatement caller = Configuration.getConnectionDatabase().prepareCall("{CALL mesaList()}");
            ResultSet resultSet = caller.executeQuery();
            while (resultSet.next()) {
                Mesa mesa = new Mesa();
                mesa.setCodigo(resultSet.getInt("idMesa"));
                mesa.setNombreMesa(resultSet.getString("nombreMesa"));
                mesa.setMesa_status(Mesa.MESA_STATUS.valueOf(resultSet.getString("statusMesa")));
                mesaList.add(mesa);
            }
            Configuration.getConnectionDatabase().close();
            resultSet.close();
            return mesaList;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<Mesa> findMinorsTo(int quantity) {
        try {
            List<Mesa> mesaList = new ArrayList<>();
            CallableStatement caller = Configuration.getConnectionDatabase().prepareCall("{CALL mesaList()}");
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
            Configuration.getConnectionDatabase().close();
            resultSet.close();
            return mesaList;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void save(Mesa t) {
        try {
            CallableStatement createMesa = Configuration.getConnectionDatabase().prepareCall("{CALL createMesa(?,?,?)}");
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
            PreparedStatement statement = Configuration.getConnectionDatabase().prepareStatement("SELECT * FROM reservation.mesa WHERE idMesa=?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Mesa mesa = new Mesa();
                mesa.setCodigo(resultSet.getInt("idMesa"));
                mesa.setNombreMesa(resultSet.getString("nombreMesa"));
                mesa.setMesa_status(Mesa.MESA_STATUS.valueOf(resultSet.getString("statusMesa")));
                return mesa;
            }
            Configuration.getConnectionDatabase().close();
            resultSet.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public int findMaxId() {
        try {
            PreparedStatement statement = Configuration.getConnectionDatabase().prepareStatement("SELECT MAX(idMesa) FROM mesa");
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int value = resultSet.getInt("MAX(idMesa)");
                resultSet.close();
                statement.close();
                Configuration.getConnectionDatabase().close();
                return value;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

}
