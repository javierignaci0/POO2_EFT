package controller;

import dao.UsuarioDAO;

import dao.impl.UsuarioDAOImpl;
import model.Usuario;


import java.util.List;

public class ControladorUsuarios {
    private final UsuarioDAO usuarioDAO;

    public ControladorUsuarios(){
        usuarioDAO = new UsuarioDAOImpl();
    }

    public boolean guardarUsuario(Usuario usuario){
        if (usuario.getNombre().isEmpty() || usuario.getRut().isEmpty() || usuario.getCorreo().isEmpty()
                || usuario.getPassword().isEmpty() || usuario.getRol() == null){
            System.out.println("Error en los valores al guardar usuario");
            return false;
        }
        return usuarioDAO.create(usuario);
    }

    public List<Usuario> listarUsuarios(){
        return usuarioDAO.readAll();
    }

    public boolean editarUsuario(Usuario usuario){
        if (usuario.getNombre().isEmpty() || usuario.getRut().isEmpty() || usuario.getCorreo().isEmpty()
                || usuario.getPassword().isEmpty() || usuario.getRol() == null){
            System.out.println("Error en los valores al editar usuario");
            return false;
        }
        return usuarioDAO.update(usuario);
    }

    public boolean eliminarUsuario(int id){
        if (id < 0){
            System.out.println("Error en el id seleccionado al eliminar usuario");
            return false;
        }
        return usuarioDAO.delete(id);
    }

    public Usuario iniciarSesion(String correo, String password) {
        if (correo.isEmpty() || password.isEmpty()){
            System.out.println("Error en los valores al iniciar Sesion");
            return null;
        }
        return usuarioDAO.validarCredenciales(correo, password);
    }
}
