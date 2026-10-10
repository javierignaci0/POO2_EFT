package dao.impl;

import dao.LibroDAO;
import model.Libro;
import util.ConexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibroDAOImpl implements LibroDAO {
    @Override
    public boolean create(Libro libro){

        String sql = "INSERT INTO libros (titulo, autor, isbn, editorial, stock, id_categoria) VALUES (?, ?, ?, ?, ?, ?)";


        try (Connection con = ConexionBD.getInstance().conectar()) {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, libro.getTitulo());
            ps.setString(2, libro.getAutor());
            ps.setString(3, libro.getIsbn());
            ps.setString(4, libro.getEditorial());
            ps.setInt(5, libro.getStock());
            ps.setInt(6, libro.getIdCategoria());

            int filasAfectadas =  ps.executeUpdate();
            if (filasAfectadas > 0){
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        libro.setId(rs.getInt(1));
                    }
                }
                return true;
            }

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            return false;
        }
        return false;
    }

    @Override
    public List<Libro> readAll() {

        String sql = "SELECT id, titulo, autor, isbn, editorial, stock, id_categoria FROM libros ORDER BY id";

        List<Libro> libros = new ArrayList<>();

        try (Connection conexion = ConexionBD.getInstance().conectar()) {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Libro libro = new Libro(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getString("isbn"),
                        rs.getString("editorial"),
                        rs.getInt("stock"),
                        rs.getInt("id_categoria")
                );

                libros.add(libro);
            }
        }catch (SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
            return null;
        }
        return libros;
    }

    @Override
    public boolean update(Libro libro){
        String sql = "UPDATE libros SET titulo=?, autor=?, isbn=?, editorial=?, stock=?, id_categoria=? WHERE id=?";

        try (Connection conexion = ConexionBD.getInstance().conectar()) {
            PreparedStatement ps = conexion.prepareStatement(sql);

            ps.setString(1, libro.getTitulo());
            ps.setString(2, libro.getAutor());
            ps.setString(3, libro.getIsbn());
            ps.setString(4, libro.getEditorial());
            ps.setInt(5, libro.getStock());
            ps.setInt(6, libro.getIdCategoria());
            ps.setInt(7, libro.getId());

            return ps.executeUpdate() > 0;

        }catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(int id){
        String sql = "DELETE FROM libros WHERE id=?";

        try (Connection con = ConexionBD.getInstance().conectar()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            return false;
        }
    }
}