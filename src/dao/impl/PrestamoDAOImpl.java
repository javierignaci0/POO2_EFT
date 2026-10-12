package dao.impl;

import dao.PrestamoDAO;

import model.Prestamo;
import util.ConexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrestamoDAOImpl implements PrestamoDAO {
    @Override
    public boolean create(Prestamo prestamo){
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
            System.out.println("Error al insertar el prestamo. " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Prestamo> readAll(){
        String sql = "SELECT id, id_estudiante, id_libro, fecha_prestamo, fecha_devolucion, devuelto FROM prestamos ORDER BY id";

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
            System.out.println("Error al intentar recuperar la lista de prestamos." + e.getMessage());
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
            System.out.println("Error al intentar editar el prestamo." + e.getMessage());
            return false;
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
            System.out.println("Error al intentar eliminar el prestamo." + e.getMessage());
            return false;
        }
    }

    //reportes
    public String reporteLibrosMasPrestados() {
        StringBuilder reporte = new StringBuilder("=== TOP LIBROS MAS PRESTADOS ===\n\n");
        String sql = "SELECT l.titulo, COUNT(p.id_libro) as cantidad " +
                "FROM prestamos p JOIN libros l ON p.id_libro = l.id " +
                "GROUP BY l.id, l.titulo ORDER BY cantidad DESC LIMIT 5";

        try (Connection conexion = ConexionBD.getInstance().conectar();
             PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                reporte.append("- ").append(rs.getString("titulo"))
                        .append(" (Prestado ").append(rs.getInt("cantidad")).append(" veces)\n");
            }
        } catch (SQLException e) {
            return "Error al generar reporte. " + e.getMessage();
        }
        return reporte.toString();
    }

    public String reporteLibrosEnPrestamo() {
        StringBuilder reporte = new StringBuilder("=== LIBROS ACTUALMENTE EN PRESTAMO ===\n\n");
        String sql = "SELECT l.titulo, e.nombre, p.fecha_prestamo " +
                "FROM prestamos p " +
                "JOIN libros l ON p.id_libro = l.id " +
                "JOIN estudiantes e ON p.id_estudiante = e.id " +
                "WHERE p.devuelto = FALSE";

        try (Connection conexion = ConexionBD.getInstance().conectar();
             PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                reporte.append("Libro: ").append(rs.getString("titulo"))
                        .append(" | Estudiante: ").append(rs.getString("nombre"))
                        .append(" | Fecha: ").append(rs.getString("fecha_prestamo")).append("\n");
            }
        } catch (SQLException e) {
            return "Error al generar reporte: " + e.getMessage();
        }
        return reporte.toString();
    }

    public String reporteHistorialEstudiante(String rutEstudiante) {
        StringBuilder reporte = new StringBuilder("=== HISTORIAL DEL ESTUDIANTE: " + rutEstudiante + " ===\n\n");
        String sql = "SELECT l.titulo, p.fecha_prestamo, p.devuelto " +
                "FROM prestamos p " +
                "JOIN libros l ON p.id_libro = l.id " +
                "JOIN estudiantes e ON p.id_estudiante = e.id " +
                "WHERE e.rut = ?";

        try (Connection conexion = ConexionBD.getInstance().conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, rutEstudiante);
            ResultSet rs = ps.executeQuery();

            boolean tieneRegistros = false;
            while (rs.next()) {
                tieneRegistros = true;
                String estado = rs.getBoolean("devuelto") ? "[DEVUELTO]" : "[PENDIENTE]";
                reporte.append(estado).append(" - ").append(rs.getString("titulo"))
                        .append(" (Fecha: ").append(rs.getString("fecha_prestamo")).append(")\n");
            }

            if (!tieneRegistros) reporte.append("No hay prestamos registrados para este RUT.");

        } catch (SQLException e) {
            return "Error al generar reporte: " + e.getMessage();
        }
        return reporte.toString();
    }
}
