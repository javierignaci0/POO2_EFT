package dao.impl;

import dao.CategoriaDAO;
import model.Categoria;
import util.ConexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAOImpl implements CategoriaDAO {
    @Override
    public boolean create(Categoria categoria){
        String sql = "INSERT INTO categorias (nombre) VALUES (?)";

        try (Connection conexion = ConexionBD.getInstance().conectar()) {
            PreparedStatement ps = conexion.prepareStatement(sql);

            ps.setString(1, categoria.getNombre());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Categoria> readAll(){
        String sql = "SELECT (id, nombre) FROM categorias ORDER BY id";

        List<Categoria> categorias = new ArrayList<>();

        try (Connection conexion = ConexionBD.getInstance().conectar()) {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Categoria categoria = new Categoria(
                        rs.getInt("id"),
                        rs.getString("nombre")
                );

                categorias.add(categoria);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return categorias;
    }

    @Override
    public boolean update(Categoria categoria){
        String sql = "UPDATE categorias SET nombre=? WHERE id=?";

        try (Connection conexion = ConexionBD.getInstance().conectar()) {
            PreparedStatement ps = conexion.prepareStatement(sql);

            ps.setString(1, categoria.getNombre());
            ps.setInt(2, categoria.getId());

            return ps.executeUpdate() > 0; // ROWS
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public boolean delete(int id){
        String sql = "DELETE FROM categorias WHERE id=?";

        try (Connection conexion = ConexionBD.getInstance().conectar()) {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
