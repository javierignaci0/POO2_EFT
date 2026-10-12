package dao;

import model.Usuario;

import java.util.List;

public interface UsuarioDAO {

    // CREATE
    boolean create(Usuario usuario);

    // READ
    List<Usuario> readAll();

    // UPDATE
    boolean update(Usuario usuario);

    // DELETE
    boolean delete(int id);

    // LOGIN
    Usuario validarCredenciales(String correo, String password);
}
