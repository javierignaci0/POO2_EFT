package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private static ConexionBD conexion;

    private ConexionBD() {}

    public static synchronized ConexionBD getInstance() {
        if (conexion == null) {
            conexion = new ConexionBD();
        }
        return conexion;
    }

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
