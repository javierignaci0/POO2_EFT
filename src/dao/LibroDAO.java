package dao;

import model.Libro;
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

    Libro buscarPorId(int id);
}