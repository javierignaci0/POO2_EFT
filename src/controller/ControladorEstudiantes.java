package controller;

import dao.EstudianteDAO;

import dao.impl.EstudianteDAOImpl;
import model.Estudiante;


import java.util.List;

public class ControladorEstudiantes {
    private final EstudianteDAO estudianteDAO;

    public ControladorEstudiantes(){
        estudianteDAO = new EstudianteDAOImpl();
    }

    public boolean guardarEstudiante(Estudiante estudiante) {
        if (estudiante.getNombre().isEmpty() || estudiante.getRut().isEmpty() || estudiante.getCorreo().isEmpty()
                || estudiante.getCurso().isEmpty()){
            System.out.println("Error en los valores al guardar estudiante");
            return false;
        }
        return estudianteDAO.create(estudiante);
    }

    public List<Estudiante> listarEstudiantes() {
        return estudianteDAO.readAll();
    }

    public boolean editarEstudiante(Estudiante estudiante) {
        if (estudiante.getNombre().isEmpty() || estudiante.getRut().isEmpty() || estudiante.getCorreo().isEmpty()
                || estudiante.getCurso().isEmpty()){
            System.out.println("Error en los valores al editar estudiante");
            return false;
        }
        return estudianteDAO.update(estudiante);
    }

    public boolean eliminarEstudiante(int id) {
        if (id < 0){
            System.out.println("Error en el id seleccionado al eliminar estudiante");
            return false;
        }
        return estudianteDAO.delete(id);
    }
}