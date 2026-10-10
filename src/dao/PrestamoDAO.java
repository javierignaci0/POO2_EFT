package dao;

import model.Prestamo;
import model.Usuario;

import java.sql.SQLException;
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
}
