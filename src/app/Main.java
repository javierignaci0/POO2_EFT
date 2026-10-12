package app;

import util.ConexionBD;
import view.Login;

import javax.swing.*;


public class Main {
    public static void main(String[] args) {

        if (!ConexionBD.existeDB()) {
            System.err.println("Error de conexion, la base de datos no esta creada o no existe");
            System.err.println("Debes crear la base de datos antes de iniciar la aplicacion");
            System.exit(1);
        }
        System.out.println("Iniciando la base de datos");

        SwingUtilities.invokeLater(() -> { Login ventanaLogin = new Login();
            ventanaLogin.setVisible(true);
            ventanaLogin.setLocationRelativeTo(null);
        });
    }
}