package dao.impl;

import dao.EstudianteDAO;
import model.Estudiante;
import util.ConexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EstudianteDAOImpl implements EstudianteDAO {
    @Override
    public boolean create(Estudiante estudiante){
        String sql = "INSERT INTO estudiantes (nombre, rut, curso, correo) VALUES (?, ?, ?, ?)";

        try (Connection conexion = ConexionBD.getInstance().conectar()) {
            PreparedStatement ps = conexion.prepareStatement(sql);

            ps.setString(1, estudiante.getNombre());
            ps.setString(2, estudiante.getRut());
            ps.setString(3, estudiante.getCurso());
            ps.setString(4, estudiante.getCorreo());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al crear el estudiante" + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Estudiante> readAll(){
        String sql = "SELECT id, nombre, rut, curso, correo FROM estudiantes ORDER BY id";

        List<Estudiante> estudiantes = new ArrayList<>();

        try (Connection conexion = ConexionBD.getInstance().conectar()) {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Estudiante estudiante = new Estudiante(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("rut"),
                        rs.getString("curso"),
                        rs.getString("correo")

                );

                estudiantes.add(estudiante);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar los estudiantes." + e.getMessage());
            return null;
        }

        return estudiantes;
    }

    @Override
    public boolean update(Estudiante estudiante){
        String sql = "UPDATE estudiantes SET nombre=?, rut=?, curso=?, correo=? WHERE id=?";

        try (Connection conexion = ConexionBD.getInstance().conectar()) {
            PreparedStatement ps = conexion.prepareStatement(sql);

            ps.setString(1, estudiante.getNombre());
            ps.setString(2, estudiante.getRut());
            ps.setString(3, estudiante.getCurso());
            ps.setString(4, estudiante.getCorreo());
            ps.setInt(5, estudiante.getId());

            return ps.executeUpdate() > 0; // ROWS
        } catch (SQLException e) {
            System.out.println("Error al editar el estudiante" + e.getMessage());
            return false;
        }
    }


    @Override
    public boolean delete(int id){
        String sql = "DELETE FROM estudiantes WHERE id=?";

        try (Connection conexion = ConexionBD.getInstance().conectar()) {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al eliminar el estudiante." + e.getMessage());
            return false;
        }
    }
}
