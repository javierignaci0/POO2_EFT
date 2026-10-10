package dao;

import model.Libro;
import java.sql.SQLException;
import java.util.List;

public interface LibroDAO {
    // CREATE
    boolean create(Libro libro);

    // READ
    List<Libro> readAll();

    // UPDATE
    boolean update(Libro libro);

    // DELETE
    boolean delete(int id);
}