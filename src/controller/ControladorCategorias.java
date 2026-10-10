package controller;

import dao.CategoriaDAO;

import dao.impl.CategoriaDAOImpl;
import model.Categoria;

import java.util.List;

public class ControladorCategorias {
    private final CategoriaDAO categoriaDAO;

    public ControladorCategorias(){
        categoriaDAO = new CategoriaDAOImpl();
    }

    public boolean guardarCategoria(Categoria categoria) {
        if (categoria.getNombre().isEmpty()){
            System.out.println("Error en los valores al guardar categoria");
            return false;
        }
        return categoriaDAO.create(categoria);
    }

    public List<Categoria> listarCategorias() {
        return categoriaDAO.readAll();
    }

    public boolean editarCategoria(Categoria categoria) {
        if (categoria.getNombre().isEmpty()){
            System.out.println("Error en los valores al editar categoria");
            return false;
        }
        return categoriaDAO.update(categoria);
    }

    public boolean eliminarCategoria(int id) {
        if (id < 0){
            System.out.println("Error en el id seleccionado al eliminar categoria");
            return false;
        }
        return categoriaDAO.delete(id);
    }
}