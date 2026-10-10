package dao.impl;

import dao.PrestamoDAO;

import model.Prestamo;
import util.ConexionBD;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PrestamoDAOImpl implements PrestamoDAO {
    @Override
    public synchronized boolean create(Prestamo prestamo){
        String sql = "INSERT INTO prestamos (id_estudiante, id_libro, fecha_prestamo, fecha_devolucion, devuelto) VALUES (?, ?, ?, ?, ?)";

        try (Connection conexion = ConexionBD.getInstance().conectar()) {
            PreparedStatement ps = conexion.prepareStatement(sql);

            ps.setInt(1, prestamo.getIdEstudiante());
            ps.setInt(2, prestamo.getIdLibro());
            ps.setDate(3, Date.valueOf(prestamo.getFechaPrestamo()));
            ps.setDate(4, Date.valueOf(prestamo.getFechaDevolucion()));
            ps.setBoolean(5, prestamo.isDevuelto());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Prestamo> readAll(){
        String sql = "SELECT (id, id_estudiante, id_libro, fecha_prestamo, fecha_devolucion, devuelto) FROM prestamos ORDER BY id";

        List<Prestamo> prestamos = new ArrayList<>();

        try (Connection conexion = ConexionBD.getInstance().conectar()) {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Prestamo prestamo = new Prestamo(
                        rs.getInt("id"),
                        rs.getInt("id_estudiante"),
                        rs.getInt("id_libro"),
                        rs.getDate("fecha_prestamo").toLocalDate(),
                        rs.getDate("fecha_devolucion").toLocalDate(),
                        rs.getBoolean("devuelto")
                );

                prestamos.add(prestamo);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return prestamos;
    }

    @Override
    public boolean update(Prestamo prestamo){
        String sql = "UPDATE prestamos SET id_estudiante=?, id_libro=?, fecha_prestamo=?, fecha_devolucion=?, devuelto=? WHERE id=?";

        try (Connection conexion = ConexionBD.getInstance().conectar()) {
            PreparedStatement ps = conexion.prepareStatement(sql);

            ps.setInt(1, prestamo.getIdEstudiante());
            ps.setInt(2, prestamo.getIdLibro());
            ps.setDate(3, Date.valueOf(prestamo.getFechaPrestamo()));
            ps.setDate(4, Date.valueOf(prestamo.getFechaDevolucion()));
            ps.setBoolean(5, prestamo.isDevuelto());
            ps.setInt(6, prestamo.getId());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public boolean delete(int id){
        String sql = "DELETE FROM prestamos WHERE id=?";

        try (Connection conexion = ConexionBD.getInstance().conectar()) {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
