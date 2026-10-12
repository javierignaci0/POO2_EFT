package dao.impl;

import dao.UsuarioDAO;

import model.RolUsuario;
import model.Usuario;
import util.ConexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAOImpl implements UsuarioDAO {
    @Override
    public boolean create(Usuario usuario){

        String sql = "INSERT INTO usuarios (nombre, rut, correo, contraseña, rol) VALUES (?, ?, ?, ?, ?)";


        try (Connection con = ConexionBD.getInstance().conectar()) {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getRut());
            ps.setString(3, usuario.getCorreo());
            ps.setString(4, usuario.getPassword());
            ps.setString(5, String.valueOf(usuario.getRol()));

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al insertar el usuario." + e.getMessage());
            return false;
        }

    }

    @Override
    public List<Usuario> readAll(){

        String sql = "SELECT id, nombre, rut, correo, contraseña, rol FROM usuarios ORDER BY id";

        List<Usuario> usuarios = new ArrayList<>();

        try (Connection con = ConexionBD.getInstance().conectar()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Usuario usuario = new Usuario(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("rut"),
                        rs.getString("correo"),
                        rs.getString("contraseña"),
                        RolUsuario.valueOf(rs.getString("rol").toUpperCase())
                );

                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar los usuarios." + e.getMessage());
            return null;
        }

        return usuarios;
    }

    @Override
    public boolean update(Usuario usuario){
        String sql = "UPDATE usuarios SET nombre=?, rut=?, correo=?, contraseña=?, rol=? WHERE id=?";

        try (Connection con = ConexionBD.getInstance().conectar()) {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1,usuario.getNombre());
            ps.setString(2,usuario.getRut());
            ps.setString(3,usuario.getCorreo());
            ps.setString(4,usuario.getPassword());
            ps.setString(5, String.valueOf(usuario.getRol()));
            ps.setInt(6,usuario.getId());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al editar usuario." + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM usuarios WHERE id=?";

        try (Connection con = ConexionBD.getInstance().conectar()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar el usuario." + e.getMessage());
            return false;
        }

    }

    public Usuario validarCredenciales(String correo, String password) {
        String sql = "SELECT id, nombre, rut, correo, contraseña, rol FROM usuarios WHERE correo = ? AND contraseña = ?";
        Usuario usuarioLogueado = null;

        try (Connection conexion = ConexionBD.getInstance().conectar()) {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, correo);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                usuarioLogueado = new Usuario(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("rut"),
                        rs.getString("correo"),
                        rs.getString("contraseña"),
                        RolUsuario.valueOf(rs.getString("rol").toUpperCase())
                );
            }
        } catch (SQLException e) {
            System.out.println("Error al intentar validar usuario." + e.getMessage());
            return null;
        }

        return usuarioLogueado;
    }

}

