package com.mycompany.services;

import com.mycompany.database.Configuration;
import com.mycompany.model.entities.Servicios;
import com.mycompany.model.entities.TipoPago;
import lombok.RequiredArgsConstructor;

import java.awt.*;
import java.sql.*;
import java.util.*;
import java.util.List;


public class MenuService implements BaseService<Servicios> {

    @Override
    public List<Servicios> findAll() {
        try {
            List<Servicios> services = new ArrayList<>();
            CallableStatement caller = Configuration.getConnectionDatabase().prepareCall("{CALL menuList()}");
            ResultSet listGet = caller.executeQuery();
            while (listGet.next()) {
                Servicios s = new Servicios();
                s.setIdServicio(listGet.getInt("idServicio"));
                s.setTipo(listGet.getString("tipo"));
                s.setDescripcion(listGet.getString("descripcion"));
                s.setPrecio(listGet.getDouble("precio"));
                services.add(s);
            }
            Configuration.getConnectionDatabase().close();
            listGet.close();
            return services;
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void save(Servicios menu) {
        try{
            CallableStatement createMenu =
                    Configuration.getConnectionDatabase().prepareCall("{CALL createMenu(?,?,?,?)}");
            createMenu.setInt(1, menu.getIdServicio());
            createMenu.setString(2, menu.getTipo());
            createMenu.setString(3, menu.getDescripcion());
            createMenu.setDouble(4, menu.getPrecio());
            createMenu.executeQuery();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(Servicios menu) {
        try {
            CallableStatement upadateMenu =
                    Configuration.getConnectionDatabase().prepareCall("{CALL updateMenu(?,?,?,?)}");
            upadateMenu.setInt(1, menu.getIdServicio());
            upadateMenu.setString(2, menu.getTipo());
            upadateMenu.setString(3, menu.getDescripcion());
            upadateMenu.setDouble(4, menu.getPrecio());
            upadateMenu.executeUpdate();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(Servicios menu) {
        try {
            CallableStatement deleteMenu =
                    Configuration.getConnectionDatabase().prepareCall("{CALL deleteMenu(?)}");
            deleteMenu.setInt(1, menu.getIdServicio());
            deleteMenu.executeUpdate();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Servicios findById(int id){
        try {
            PreparedStatement statement = Configuration.getConnectionDatabase().prepareStatement("SELECT * FROM servicios WHERE idServicio = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Servicios menu = new Servicios();
                menu.setIdServicio(resultSet.getInt("idServicio"));
                menu.setTipo(resultSet.getString("tipo"));
                menu.setDescripcion(resultSet.getString("descripcion"));
                menu.setPrecio(resultSet.getDouble("precio"));
                return menu;
            }
            resultSet.close();
            statement.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
