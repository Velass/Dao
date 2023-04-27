package dao.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcSingleton {
    private final static String URL = "jdbc:mysql://localhost/dao";
    private final static String DRIVER = "com.mysql.cj.jdbc.Driver";
    private final static String USER = "dao";
    private final static String PWD = "dao";
    
    private Connection connection =null;

    private final static JdbcSingleton INSTANCE = new JdbcSingleton();

    private JdbcSingleton() {

    }

    public static JdbcSingleton getInstance() {
        return INSTANCE;
    }

    public Connection getConnection() {
        
        if (connection == null) {
  
            try {
                Class.forName(DRIVER);
                connection = DriverManager.getConnection(URL, USER, PWD);
            } catch (ClassNotFoundException e) {    
                e.printStackTrace();
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }

        return connection;
    }
    
    public void close() {
        if(connection!=null) {
            try {
                connection.close();
                System.out.println("Connexion close.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}