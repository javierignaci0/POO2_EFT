package view;

import controller.ControladorUsuarios;
import model.Usuario;

import javax.swing.*;

public class Login extends JFrame {
    private JTextField campoUser;
    private JTextField campoPassword;
    private JButton ingresarButton;
    private JLabel lblPassword;
    private JLabel lblUser;
    private JPanel panelLogin;

    private ControladorUsuarios controladorUsuarios =  new ControladorUsuarios();

    public Login(){
        setContentPane(panelLogin);
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400,400);
        setLocationRelativeTo(null);

        ingresarButton.addActionListener(e -> ingresar());

    }


    public void ingresar(){
        String correo = campoUser.getText().trim();
        String password = campoPassword.getText().trim();

        if (correo.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese correo y contrasena.");
            return;
        }

        Usuario usuario = controladorUsuarios.iniciarSesion(correo, password);

        if (usuario != null) {
            JOptionPane.showMessageDialog(this, "Bienvenido " + usuario.getNombre());

            GestorBiblioteca gestor = new GestorBiblioteca(usuario);
            gestor.setVisible(true);
            gestor.setLocationRelativeTo(null);

            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Correo o contrasena incorrectos.");
        }
    }
}


