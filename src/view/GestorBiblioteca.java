package view;

import controller.*;
import model.Libro;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class GestorBiblioteca extends JFrame {
    private JPanel panelPrincipal;
    private JTabbedPane tabbedPane1;
    private JTable tablaLibros;
    private JTextField campoIdLibro;
    private JTextField campoTituloLibro;
    private JTextField campoAutorLibro;
    private JTextField campoIsbnLibro;
    private JTextField campoEditorialLibro;
    private JTextField campoCategoriaLibro;
    private JButton salirButton;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JButton eliminarLibroBtn;
    private JButton verLibroBtn;
    private JButton editarLibroBtn;
    private JButton guardarLibroBtn;
    private JTable tablaEstudiantes;
    private JTextField campoIdEstudiante;
    private JTextField campoNombreEstudiante;
    private JTextField campoCursoEstudiante;
    private JTextField campoCorreoEstudiante;
    private JButton guardarEstudianteBtn;
    private JButton verEstudiantesBtn;
    private JButton editarEstudianteBtn;
    private JButton eliminarEstudianteBtn;
    private JPanel panelSecundario;
    private JTextField campoIdLibroPrestamo;
    private JTextField campoIdEstudiantePrestamo;
    private JTextField campoFechaPrestamo;
    private JTextField campoFechaDevolucion;
    private JTable tablaPrestamos;
    private JButton verLibrosMasPrestadosButton;
    private JButton listadoPrestamosActualButton;
    private JButton historialPrestamosButton;
    private JTable tablaReportes;
    private JTextField campoIdPrestamo;
    private JTextField campoDevolucion;
    private JTextField campoRutEstudiante;
    private JTextField campoStockLibro;
    private JButton guardarPrestamoBtn;
    private JButton verPrestamoBtn;
    private JButton editarPrestamoBtn;
    private JButton eliminarPrestamoBtn;
    private JTable table5;
    private JButton verListaButton3;
    private JLabel txtIdLibro;
    private JLabel txtTituloLibro;
    private JLabel txtAutorLibro;
    private JLabel txtIsbnLibro;
    private JLabel txtEditorialLibro;
    private JLabel txtStockLibro;
    private JLabel txtCategoriaLibro;
    private JLabel txtIdEstudiante;
    private JLabel txtNombreEstudiante;
    private JLabel txtRutEstudiante;
    private JLabel txtCursoEstudiante;
    private JLabel txtCorreoEstudiante;
    private JLabel txtIdPrestamo;
    private JLabel txtIdLibroPrestamo;
    private JLabel txtIdEstudiantePrestamo;
    private JLabel txtFechaPrestamo;
    private JLabel txtFechaDevolucion;
    private JLabel txtDevuelto;
    private JLabel lblSeleccionLibro;
    private JButton limpiarLibrosBtn;
    private JLabel lblStockLibros;
    private JTextField campoBuscar;
    private JButton buscarBtn;

    ControladorPrestamos controladorPrestamos = new ControladorPrestamos();
    ControladorCategorias controladorCategorias = new ControladorCategorias();
    ControladorEstudiantes controladorEstudiantes = new ControladorEstudiantes();
    ControladorLibros controladorLibros = new ControladorLibros();
    ControladorUsuarios controladorUsuarios = new ControladorUsuarios();

    DefaultTableModel modeloTablaLibro;
    DefaultTableModel modeloTablaPrestamo;
    DefaultTableModel modeloTablaEstudiante;

    TableRowSorter<DefaultTableModel> filtroLibros;


    int idLibroSeleccionado = -1;
    String tituloInicial;
    String autorInicial;
    String isbnInicial;
    String editorialInicial;
    int stockInicial;
    int idCategoriaInicial;




    public GestorBiblioteca(){
        setTitle("Gestion Biblioteca");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setContentPane(panelPrincipal);
        setSize(1050,600);
        setLocationRelativeTo(null);


        // configuracion tabla estudiante
        String[] columnasEstudiante ={"id", "nombre", "rut", "curso", "correo"};
        modeloTablaEstudiante = new DefaultTableModel(columnasEstudiante, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaEstudiantes.setModel(modeloTablaEstudiante);

        // configuracion tabla libro
        tablaLibros.setRowSorter(null);
        String[] columnasLibro ={"id", "titulo", "autor", "isbn", "editorial", "stock", "id_categoria"};
        modeloTablaLibro = new DefaultTableModel(columnasLibro, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaLibros.setModel(modeloTablaLibro);

        filtroLibros = new TableRowSorter<>(modeloTablaLibro);
        tablaLibros.setRowSorter(filtroLibros);



        // configuracion tabla prestamo
        String[] columnasPrestamo ={"id", "id_libro", "id_estudiante", "fecha_prestamo", "fecha_devolucion", "devuelto"};
        modeloTablaPrestamo = new DefaultTableModel(columnasPrestamo, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaPrestamos.setModel(modeloTablaPrestamo);

        // Listener Seleccion fila tabla libros
        tablaLibros.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int filaClickeada = tablaLibros.getSelectedRow();

                if (filaClickeada >= 0) {
                    int fila = tablaLibros.convertRowIndexToModel(filaClickeada);
                    // guarda los valores iniciales para comparar luego en los UPDATE
                    idLibroSeleccionado = Integer.parseInt(tablaLibros.getValueAt(fila, 0).toString());
                    tituloInicial = tablaLibros.getValueAt(fila, 1).toString();
                    autorInicial = tablaLibros.getValueAt(fila, 2).toString();
                    isbnInicial = tablaLibros.getValueAt(fila, 3).toString();
                    editorialInicial = tablaLibros.getValueAt(fila, 4).toString();
                    stockInicial = Integer.parseInt(tablaLibros.getValueAt(fila, 5).toString());
                    idCategoriaInicial = Integer.parseInt(tablaLibros.getValueAt(fila, 6).toString());

                    campoIdLibro.setText(String.valueOf(idLibroSeleccionado));
                    campoTituloLibro.setText(tituloInicial);
                    campoAutorLibro.setText(autorInicial);
                    campoIsbnLibro.setText(isbnInicial);
                    campoEditorialLibro.setText(editorialInicial);
                    campoStockLibro.setText(String.valueOf(stockInicial));
                    campoCategoriaLibro.setText(String.valueOf(idCategoriaInicial));

                    lblSeleccionLibro.setText("Libro seleccionado: "  + idLibroSeleccionado + " | " + tituloInicial);
                    lblStockLibros.setText("Stock actual: " + stockInicial);
                }
            }
        });


        guardarLibroBtn.addActionListener(e -> registrarLibro()); //FUNCIONA
        verLibroBtn.addActionListener(e -> actualizarTablaLibros());
        editarLibroBtn.addActionListener(e -> editarLibro());
        eliminarLibroBtn.addActionListener(e -> eliminarLibro());

        limpiarLibrosBtn.addActionListener(e -> limpiarCampos());
        buscarBtn.addActionListener(e -> buscarLibro());


        actualizarTablaLibros();
    }

    public void registrarLibro(){
        try {
            String titulo = campoTituloLibro.getText().trim();
            String autor = campoAutorLibro.getText().trim();
            String isbn = campoIsbnLibro.getText().trim();
            String editorial = campoEditorialLibro.getText().trim();
            String txtStock = campoStockLibro.getText().trim();
            String txtIdCategoria = campoIdLibro.getText().trim();

            int stock = Integer.parseInt(txtStock);
            int idCategoria = Integer.parseInt(txtIdCategoria);

            if (titulo.isEmpty() || autor.isEmpty() || isbn.isEmpty() || editorial.isEmpty() || stock <= 0 || idCategoria < 0) {
                JOptionPane.showMessageDialog(null, "Ingrese todos los campos");
                return;
            }

            // validacion isbn 10 o 13
            if (isbn.length() != 10 && isbn.length() != 13) {
                JOptionPane.showMessageDialog(null, "ISBN solo puede tener 10 o 13 caracteres.");
                return;
            }

            Libro libroNuevo = new Libro(titulo, autor, isbn, editorial, stock, idCategoria);

            // V -> C
            boolean exito = controladorLibros.guardarLibro(libroNuevo);
            if (exito){
                // se usa el ps.getGeneratedKeys de libroDAO para obtener id
                JOptionPane.showMessageDialog(null, "Libro #" + libroNuevo.getId() + " creado con exito.", "Sistema registro", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, "Los datos ingresados no son validos");
        }

        actualizarTablaLibros();
    }
    //funciona
    public void actualizarTablaLibros(){
        modeloTablaLibro.setRowCount(0);

        List<Libro> listaLibros = controladorLibros.listarLibros();
        for (Libro libro : listaLibros) {
            Object[] fila = {
                    libro.getId(),
                    libro.getTitulo(),
                    libro.getAutor(),
                    libro.getIsbn(),
                    libro.getEditorial(),
                    libro.getStock(),
                    libro.getIdCategoria()
            };
            modeloTablaLibro.addRow(fila);
        }
    }

    public void editarLibro(){
        try {
            //validacion tipo pedido
            if (idLibroSeleccionado == -1) {
                JOptionPane.showMessageDialog(null, "Selecciona un libro valido para editar.");
            }

            String titulo = campoTituloLibro.getText().trim();
            String autor = campoAutorLibro.getText().trim();
            String isbn = campoIsbnLibro.getText().trim();
            String editorial = campoEditorialLibro.getText().trim();
            String txtStock = campoStockLibro.getText().trim();
            String txtIdCategoria = campoIdLibro.getText().trim();


            int stock = Integer.parseInt(txtStock);
            int idCategoria = Integer.parseInt(txtIdCategoria);


            if (titulo.isEmpty() || autor.isEmpty() || isbn.isEmpty() || editorial.isEmpty() || stock <= 0 || idCategoria < 0) {
                JOptionPane.showMessageDialog(null, "Ingrese todos los campos");
                return;
            }

            if (titulo.equals(tituloInicial) || autor.equals(autorInicial) || isbn.equals(isbnInicial)
                    || editorial.equals(editorialInicial) || stock == stockInicial || idCategoria == idCategoriaInicial) {
                JOptionPane.showMessageDialog(null,"Debes modificar al menos un valor para editar el libro seleccionado.");
                return;
            }

            // validacion isbn 10 o 13
            if (isbn.length() != 10 && isbn.length() != 13) {
                JOptionPane.showMessageDialog(null, "ISBN solo puede tener 10 o 13 caracteres.");
                return;
            }

            Libro libroNuevo = new Libro(idLibroSeleccionado, titulo, autor, isbn, editorial, stock, idCategoria);

            // V -> C
            boolean exito = controladorLibros.editarLibro(libroNuevo);
            if (exito){
                // se usa el ps.getGeneratedKeys de libroDAO para obtener id
                JOptionPane.showMessageDialog(null, "Libro #" + libroNuevo.getId() + " editado con exito.", "Sistema registro", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, "Los datos ingresados no son validos");
        }

        actualizarTablaLibros();
    }

    public void eliminarLibro(){
        try {
            //validacion tipo pedido
            if (idLibroSeleccionado == -1) {
                JOptionPane.showMessageDialog(null, "Selecciona un libro valido.");
            }

            int confirmar = JOptionPane.showConfirmDialog(null,
                    "Esta seguro que desea eliminar el libro #" + idLibroSeleccionado + "?",
                    "Eliminar libro", JOptionPane.YES_NO_OPTION);
            if (confirmar != JOptionPane.YES_OPTION) {
                return;
            }
            // V -> C
            boolean exito = controladorLibros.eliminarLibro(idLibroSeleccionado);
            if (exito){
                JOptionPane.showMessageDialog(null, "Libro #" + idLibroSeleccionado + " eliminado con exito.", "Sistema registro", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, "Los datos ingresados no son validos");
        }
    }

    public void limpiarCampos(){
        campoIdLibro.setText("");
        campoTituloLibro.setText("");
        campoAutorLibro.setText("");
        campoIsbnLibro.setText("");
        campoEditorialLibro.setText("");
        campoStockLibro.setText("");
        campoCategoriaLibro.setText("");
        idLibroSeleccionado = -1;
    }

    public void buscarLibro(){
        String termino = campoBuscar.getText().trim();
        if (!termino.isEmpty()){
            filtroLibros.setRowFilter(RowFilter.regexFilter("(?i)" + termino));
        }else{
            filtroLibros.setRowFilter(null);
        }
    }

}
