package dao;

import model.Estudiante;

import java.sql.SQLException;
import java.util.List;

public interface EstudianteDAO {

    // CREATE
    boolean create(Estudiante estudiante);

    // READ
    List<Estudiante> readAll();

    // UPDATE
    boolean update(Estudiante estudiante);

    // DELETE
    boolean delete(int id);
}
