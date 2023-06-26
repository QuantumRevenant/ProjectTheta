package com.mycompany.services;

import com.mycompany.database.Configuration;
import com.mycompany.model.entities.Menu;

import java.sql.*;
import java.util.*;

public class MenuService implements BaseService<Menu> {


    @Override
    public List<Menu> findAll() {
        try {
            List<Menu> menuList = new ArrayList<>();
            CallableStatement caller = Configuration.getConnectionDatabase().prepareCall("{CALL menuList()}");
            ResultSet resultSet = caller.executeQuery();
            while (resultSet.next()) {
                Menu menu = new Menu();
                menu.setIdMenu(resultSet.getInt("idMenu"));
                int idCategoria = resultSet.getInt("idCategoria");
                menu.setIdCategoria(getCategoriaId(idCategoria));
                menu.setTipo(resultSet.getString("tipo"));
                menu.setDescripcion(resultSet.getString("descripcion"));
                menu.setPrecio(resultSet.getDouble("precio"));
                menuList.add(menu);
            }
            Configuration.getConnectionDatabase().close();
            resultSet.close();
            return menuList;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void save(Menu menu) {
        try {
            CallableStatement createMenu =
                    Configuration.getConnectionDatabase().prepareCall("{CALL createMenu(?,?,?,?)}");
            int idCategoria = getCategoriaId(menu.getIdCategoria()); // Obtener idCategoria de otra tabla
            createMenu.setInt(1, idCategoria);
            createMenu.setString(2, menu.getTipo());
            createMenu.setString(3, menu.getDescripcion());
            createMenu.setDouble(4, menu.getPrecio());
            createMenu.executeQuery();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(Menu menu) {
        try {
            CallableStatement updateMenu =
                    Configuration.getConnectionDatabase().prepareCall("{CALL updateMenu(?,?,?,?,?)}");
            updateMenu.setInt(1, menu.getIdMenu());
            int idCategoria = getCategoriaId(menu.getIdCategoria()); // Obtener idCategoria de otra tabla
            updateMenu.setInt(2, idCategoria);
            updateMenu.setString(3, menu.getTipo());
            updateMenu.setString(4, menu.getDescripcion());
            updateMenu.setDouble(5, menu.getPrecio());
            updateMenu.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(Menu menu) {
        try {
            CallableStatement deleteMenu =
                    Configuration.getConnectionDatabase().prepareCall("{CALL deleteMenu(?)}");
            deleteMenu.setInt(1, menu.getIdMenu());
            deleteMenu.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Menu findById(int id) {
        try {
            PreparedStatement statement = Configuration.getConnectionDatabase().prepareStatement("SELECT * FROM reservation.menu WHERE idMenu = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Menu menu = new Menu();
                menu.setIdMenu(resultSet.getInt("idMenu"));
                int idCategoria = resultSet.getInt("idCategoria");
                menu.setIdCategoria(getCategoriaId(idCategoria)); // Obtener idCategoria de otra tabla
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

    /*JOIN PARA TABLA CATEGORIA*/
    private int getCategoriaId(int idCategoria) {
        int categoriaId = 0;
        try {
            PreparedStatement statement = Configuration.getConnectionDatabase().prepareStatement("SELECT idCategoria FROM reservation.categoria WHERE idCategoria = ?");
            statement.setInt(1, idCategoria);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                categoriaId = resultSet.getInt("idCategoria");
            }
            resultSet.close();
            statement.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return categoriaId;
    }
}
