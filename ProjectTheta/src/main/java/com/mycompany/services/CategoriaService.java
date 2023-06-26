package com.mycompany.services;

import com.mycompany.database.Configuration;
import com.mycompany.model.entities.Categoria;
import java.sql.*;
import java.util.*;

public class CategoriaService implements BaseService<Categoria> {

    private Configuration configuration = Configuration.getConf();

    @Override
    public List<Categoria> findAll() {
        /* try {
            List<Categoria> lst = new ArrayList<>();
            CallableStatement caller = conn.prepareCall("{CALL categoryList()}");
            ResultSet listGet = caller.executeQuery();
            while (listGet.next()) {
                Categoria c = new Categoria();
                c.setIdCategoria(listGet.getInt("idCategoria"));
                c.setNombre(listGet.getString("nombre"));
                lst.add(c);
            }
            conn.close();
            listGet.close();
            return lst;
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;*/    //<- FUNCION PREDETERMINADA, EN CASO NO FUNCIONE
        List<Categoria> lst = new ArrayList<>();
        Connection conn = configuration.getConnectionDatabase();
        try (CallableStatement caller = conn.prepareCall("{CALL categoryList()}")) {
            try (ResultSet listGet = caller.executeQuery()) {
                while (listGet.next()) {
                    Categoria c = new Categoria();
                    c.setIdCategoria(listGet.getInt("idCategoria"));
                    c.setNombre(listGet.getString("nombre"));
                    lst.add(c);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        configuration.releaseConnection(conn);
        return lst.isEmpty() ? null : lst;
    }

    @Override
    public void save(Categoria t) {
        try {
            Connection conn = configuration.getConnectionDatabase();
            CallableStatement createCategory = conn.prepareCall(("{CALL createCategory(?)}"));
            createCategory.setString(1, t.getNombre());
            createCategory.executeQuery();
            configuration.releaseConnection(conn);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(Categoria t) {
    }

    @Override
    public void delete(Categoria t) {
    }

    public Categoria findById(int id) {
        try {
            Connection conn = configuration.getConnectionDatabase();
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM reservation.categoria WHERE idCategoria = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Categoria categoria = new Categoria();
                categoria.setIdCategoria(resultSet.getInt("idCategoria"));
                categoria.setNombre(resultSet.getString("nombre"));
                resultSet.close();
                statement.close();
                configuration.releaseConnection(conn);
                return categoria;
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
}
