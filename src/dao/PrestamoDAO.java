package dao;

import model.Prestamo;
import java.util.List;

public interface PrestamoDAO {

    // CREATE
    boolean create(Prestamo prestamo);

    // READ
    List<Prestamo> readAll();

    // UPDATE
    boolean update(Prestamo prestamo);

    // DELETE
    boolean delete(int id);

    // REPORTES
    String reporteLibrosMasPrestados();
    String reporteLibrosEnPrestamo();
    String reporteHistorialEstudiante(String rut);
}
