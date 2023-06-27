package com.mycompany.database;

import com.mycompany.model.generics.Print;
import lombok.NoArgsConstructor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.AllArgsConstructor;
import lombok.Data;
@AllArgsConstructor
@Data
public class Configuration implements ConectionPool{

    private String url;
    private String user;
    private String password;
    private List<Connection> connectionPool;
    private List<Connection> usedConnections = new ArrayList<>();
    private static int INITIAL_POOL_SIZE = 50;
    
    private static Configuration thisConfiguration;
    
    public static Configuration getConf(){
        if(thisConfiguration!=null)
        {
            return thisConfiguration;
        }else
        {
            try {
                thisConfiguration=create("jdbc:mysql://localhost:3306/reservation","root", "");
                return thisConfiguration;
            } catch (SQLException ex) {
                Logger.getLogger(Configuration.class.getName()).log(Level.SEVERE, null, ex);          
                            Print.error("NO PUDIMOS CONECTAR CON LA DB (Cerramos el Programa por seguridad)");
                System.exit(0);
            }
        }
        return null;
    }
    
    
    public Configuration(String url, String user, String password, List<Connection> connectionPool) {
        this.url = url;
        this.user = user;
        this.password = password;
        this.connectionPool = connectionPool;
    }
    
    public static Configuration create(
      String url, String user, 
      String password) throws SQLException {
 
        List<Connection> pool = new ArrayList<>(INITIAL_POOL_SIZE);
        for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
            pool.add(createConnection(url, user, password));
        }
        return new Configuration(url, user, password, pool);
    }    
    // standard constructors
    
    @Override
    public Connection getConnectionDatabase() {
        Connection connection = connectionPool
          .remove(connectionPool.size() - 1);
        usedConnections.add(connection);
        return connection;
    }
    
    @Override
    public boolean releaseConnection(Connection connection) {
        connectionPool.add(connection);
        return usedConnections.remove(connection);
    }
    
    private static Connection createConnection(
      String url, String user, String password) 
      throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
    
    public int getSize() {
        return connectionPool.size() + usedConnections.size();
    }    
}
