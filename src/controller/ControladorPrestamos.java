package controller;

import dao.PrestamoDAO;

import dao.impl.PrestamoDAOImpl;
import model.Prestamo;

import java.util.List;

public class ControladorPrestamos {
    private final PrestamoDAO prestamoDAO;

    public ControladorPrestamos(){
        prestamoDAO = new PrestamoDAOImpl();
    }

    public boolean guardarPrestamo(Prestamo prestamo){
        if (prestamo.getIdEstudiante() == 0 || prestamo.getIdLibro() == 0 || prestamo.getFechaPrestamo() == null
                || prestamo.getFechaDevolucion() == null){
            System.out.println("Error en los valores al guardar prestamo");
            return false;
        }
        return prestamoDAO.create(prestamo);
    }

    public List<Prestamo> listarPrestamos(){
        return prestamoDAO.readAll();
    }

    public boolean editarPrestamo(Prestamo prestamo){
        if (prestamo.getIdEstudiante() == 0 || prestamo.getIdLibro() == 0 || prestamo.getFechaPrestamo() == null
                || prestamo.getFechaDevolucion() == null){
            System.out.println("Error en los valores al editar prestamo");
            return false;
        }
        return prestamoDAO.update(prestamo);
    }

    public boolean eliminarPrestamo(int id){
        if (id < 0){
            System.out.println("Error en el id seleccionado al eliminar prestamo");
            return false;
        }
        return prestamoDAO.delete(id);
    }
}