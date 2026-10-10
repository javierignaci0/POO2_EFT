package dao;

import model.Categoria;

import java.sql.SQLException;
import java.util.List;

public interface CategoriaDAO {
    // CREATE
    boolean create(Categoria categoria);

    // READ
    List<Categoria> readAll();

    // UPDATE
    boolean update(Categoria categoria);

    // DELETE
    boolean delete(int id);
}
