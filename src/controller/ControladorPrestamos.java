package controller;

import dao.EstudianteDAO;
import dao.LibroDAO;
import dao.PrestamoDAO;

import dao.impl.EstudianteDAOImpl;
import dao.impl.LibroDAOImpl;
import dao.impl.PrestamoDAOImpl;
import model.Libro;
import model.Prestamo;

import java.util.List;

public class ControladorPrestamos {
    private final PrestamoDAO prestamoDAO;
    private final LibroDAO libroDAO;
    private final EstudianteDAO estudianteDAO;

    public ControladorPrestamos(){
        prestamoDAO = new PrestamoDAOImpl();
        libroDAO = new LibroDAOImpl();
        estudianteDAO = new EstudianteDAOImpl();
    }

    public synchronized boolean registrarPrestamo(Prestamo prestamo, int idLibro) {
        Libro libro = libroDAO.buscarPorId(idLibro);

        if (libro != null && libro.getStock() > 0) {

            boolean prestamoCreado = prestamoDAO.create(prestamo);

            if (prestamoCreado) {
                libro.setStock(libro.getStock() - 1);
                libroDAO.update(libro);
                return true;
            }
        }
        return false;
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

    public String obtenerReporteMasPrestados() {
        return prestamoDAO.reporteLibrosMasPrestados(); // Asumiendo que instanciaste prestamoDAO
    }

    public String obtenerReporteEnPrestamo() {
        return prestamoDAO.reporteLibrosEnPrestamo();
    }


    public String obtenerReporteHistorial(String rut) {
        return prestamoDAO.reporteHistorialEstudiante(rut);
    }
}