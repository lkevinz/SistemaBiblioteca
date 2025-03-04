/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;

public class Conexion {
    private static Connection connection;

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                Properties prop = new Properties();
                String filePath = "src/config/config.properties";  // Ruta absoluta
                
                try (InputStream input = new FileInputStream(filePath)) {
                    prop.load(input);
                    String url = prop.getProperty("db.url");
                    String user = prop.getProperty("db.user");
                    String password = prop.getProperty("db.password");

                    connection = DriverManager.getConnection(url, user, password);
                    System.out.println("✅ Conexión establecida con éxito.");
                } catch (IOException e) {
                    System.out.println("❌ Error: No se encontró el archivo config.properties en " + filePath);
                }
            }
        } catch (SQLException e) {
            System.out.println("❌ Error de conexión: " + e.getMessage());
            e.printStackTrace();
        }
        return connection;
    }
}


