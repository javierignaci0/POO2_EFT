package controller;

import dao.LibroDAO;
import dao.impl.LibroDAOImpl;
import model.Libro;


import java.util.List;


public class ControladorLibros {

    private final LibroDAO libroDAO;

    public ControladorLibros(){
        libroDAO = new LibroDAOImpl();
    }

    public boolean guardarLibro(Libro libro){
        if (libro.getTitulo().isEmpty() || libro.getAutor().isEmpty() || libro.getEditorial().isEmpty()
                || libro.getIsbn().isEmpty() || libro.getIdCategoria() == 0 || libro.getStock() == 0){
            System.out.println("Error en los valores al guardar libro");
            return false;
        }
        return libroDAO.create(libro);
    }

    public List<Libro> listarLibros(){
        return libroDAO.readAll();
    }

    public boolean editarLibro(Libro libro){
        if (libro.getTitulo().isEmpty() || libro.getAutor().isEmpty() || libro.getEditorial().isEmpty()
                || libro.getIsbn().isEmpty() || libro.getIdCategoria() == 0 || libro.getStock() == 0){
            System.out.println("Error en los valores al editar libro");
            return false;
        }
        return libroDAO.update(libro);
    }

    public boolean eliminarLibro(int id){
        if (id < 0){
            System.out.println("Error en el id seleccionado al eliminar libro");
            return false;
        }
        return libroDAO.delete(id);
    }
}
