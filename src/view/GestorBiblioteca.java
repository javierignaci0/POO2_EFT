package view;

import controller.*;
import model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.List;

public class GestorBiblioteca extends JFrame {


    private Usuario usuarioLogueado;

    private JPanel panelPrincipal;
    private JTabbedPane tabbedPane1;
    private JTable tablaLibros;
    private JTextField campoIdLibro;
    private JTextField campoTituloLibro;
    private JTextField campoAutorLibro;
    private JTextField campoIsbnLibro;
    private JTextField campoEditorialLibro;
    private JTextField campoIdCategoriaLibro;
    private JButton salirButton;
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
    private JTextField campoIdPrestamo;
    private JTextField campoDevolucion;
    private JTextField campoRutEstudiante;
    private JTextField campoStockLibro;
    private JButton guardarPrestamoBtn;
    private JButton verPrestamoBtn;
    private JButton devolverPrestamoBtn;
    private JButton eliminarPrestamoBtn;
    private JLabel txtIdLibro;
    private JLabel txtTituloLibro;
    private JLabel txtAutorLibro;
    private JLabel txtIsbnLibro;
    private JLabel txtEditorialLibro;
    private JLabel txtStockLibro;
    private JLabel txtIdCategoriaLibro;
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
    private JTextField campoBuscarLibro;
    private JButton buscarLibrosBtn;
    private JTextField campoBuscarEstudiante;
    private JButton buscarEstudianteBtn;
    private JTextField campoBuscarPrestamo;
    private JButton buscarPrestamoBtn;
    private JLabel lblSeleccionEst;
    private JLabel lblSeleccionPrestamos;
    private JButton limpiarCamposEstudiantesBtn;
    private JButton limpiarCamposPrestamoBtn;
    private JLabel lblUsuario;
    private JLabel lblRol;
    private JTextArea areaResultado;
    private JLabel lblsugerencia1;
    private JLabel lblSugerencia2;

    ControladorPrestamos controladorPrestamos = new ControladorPrestamos();
    ControladorCategorias controladorCategorias = new ControladorCategorias();
    ControladorEstudiantes controladorEstudiantes = new ControladorEstudiantes();
    ControladorLibros controladorLibros = new ControladorLibros();
    ControladorUsuarios controladorUsuarios = new ControladorUsuarios();

    DefaultTableModel modeloTablaLibro;
    DefaultTableModel modeloTablaPrestamo;
    DefaultTableModel modeloTablaEstudiante;

    TableRowSorter<DefaultTableModel> filtroLibros;
    TableRowSorter<DefaultTableModel> filtroEstudiantes;
    TableRowSorter<DefaultTableModel> filtroPrestamos;

    //libro
    int idLibroSeleccionado = -1;
    String tituloInicial;
    String autorInicial;
    String isbnInicial;
    String editorialInicial;
    int stockInicial;
    int idCategoriaInicial;
    //ESTUDIANTE
    int idEstudianteSeleccionado = -1;
    String nombreInicial;
    String rutInicial;
    String cursoInicial;
    String correoInicial;
    //PRESTAMO
    int idPrestamoSeleccionado = -1;
    int idEstudiantePrestamoInicial;
    int idLibroPrestamoInicial;
    LocalDate fechaPrestamoInicial;
    LocalDate fechaDevolucionInicial;
    boolean devueltoInicial;




    public GestorBiblioteca(Usuario usuario){

        this.usuarioLogueado = usuario;

        setTitle("Gestion Biblioteca");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setContentPane(panelPrincipal);
        setSize(1050,600);
        setLocationRelativeTo(null);
        initComponents();



        lblUsuario.setText("Usuario: " + usuarioLogueado.getNombre());
        lblRol.setText("Rol: " + usuarioLogueado.getRol());

        if (usuarioLogueado.getRol() == RolUsuario.ESTUDIANTE) {
            tabbedPane1.setEnabledAt(1, false);
            tabbedPane1.setEnabledAt(2, false);
            tabbedPane1.setEnabledAt(3, false);

            guardarLibroBtn.setEnabled(false);
            editarLibroBtn.setEnabled(false);
            eliminarLibroBtn.setEnabled(false);

            verLibrosMasPrestadosButton.setVisible(false);
            historialPrestamosButton.setVisible(false);
            listadoPrestamosActualButton.setVisible(false);
        }


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


        // configuracion tabla estudiante
        String[] columnasEstudiante ={"id", "nombre", "rut", "curso", "correo"};
        modeloTablaEstudiante = new DefaultTableModel(columnasEstudiante, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaEstudiantes.setModel(modeloTablaEstudiante);

        filtroEstudiantes = new TableRowSorter<>(modeloTablaEstudiante);
        tablaEstudiantes.setRowSorter(filtroEstudiantes);

        // configuracion tabla prestamo
        String[] columnasPrestamo ={"id", "id_libro", "id_estudiante", "fecha_prestamo", "fecha_devolucion", "devuelto"};
        modeloTablaPrestamo = new DefaultTableModel(columnasPrestamo, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaPrestamos.setModel(modeloTablaPrestamo);

        filtroPrestamos = new TableRowSorter<>(modeloTablaPrestamo);
        tablaPrestamos.setRowSorter(filtroPrestamos);

        // Listener Seleccion fila tabla libros
        tablaLibros.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int filaClickeada = tablaLibros.getSelectedRow();

                if (filaClickeada >= 0) {
                    int fila = tablaLibros.convertRowIndexToModel(filaClickeada);

                    var modelo = modeloTablaLibro;
                    // guarda los valores iniciales para comparar luego en los UPDATE
                    idLibroSeleccionado = Integer.parseInt(modelo.getValueAt(fila, 0).toString());
                    tituloInicial = modelo.getValueAt(fila, 1).toString();
                    autorInicial = modelo.getValueAt(fila, 2).toString();
                    isbnInicial = modelo.getValueAt(fila, 3).toString();
                    editorialInicial = modelo.getValueAt(fila, 4).toString();
                    stockInicial = Integer.parseInt(modelo.getValueAt(fila, 5).toString());
                    idCategoriaInicial = Integer.parseInt(modelo.getValueAt(fila, 6).toString());

                    campoIdLibro.setText(String.valueOf(idLibroSeleccionado));
                    campoTituloLibro.setText(tituloInicial);
                    campoAutorLibro.setText(autorInicial);
                    campoIsbnLibro.setText(isbnInicial);
                    campoEditorialLibro.setText(editorialInicial);
                    campoStockLibro.setText(String.valueOf(stockInicial));
                    campoIdCategoriaLibro.setText(String.valueOf(idCategoriaInicial));

                    lblSeleccionLibro.setText("Libro seleccionado: "  + idLibroSeleccionado + " | " + tituloInicial);
                    lblStockLibros.setText("Stock actual: " + stockInicial);
                }
            }
        });

        // Listener Seleccion fila tabla estudiantes
        tablaEstudiantes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int filaClickeada = tablaEstudiantes.getSelectedRow();

                if (filaClickeada >= 0) {
                    int fila = tablaEstudiantes.convertRowIndexToModel(filaClickeada);
                    var modelo = modeloTablaEstudiante;
                    // guarda los valores iniciales para comparar luego en los UPDATE
                    idEstudianteSeleccionado = Integer.parseInt(modelo.getValueAt(fila, 0).toString());
                    nombreInicial = modelo.getValueAt(fila, 1).toString();
                    rutInicial = modelo.getValueAt(fila, 2).toString();
                    cursoInicial = modelo.getValueAt(fila, 3).toString();
                    correoInicial = modelo.getValueAt(fila, 4).toString();

                    campoIdEstudiante.setText(String.valueOf(idEstudianteSeleccionado));
                    campoNombreEstudiante.setText(nombreInicial);
                    campoRutEstudiante.setText(rutInicial);
                    campoCursoEstudiante.setText(cursoInicial);
                    campoCorreoEstudiante.setText(correoInicial);


                    lblSeleccionEst.setText("Estudiante seleccionado: "  + idEstudianteSeleccionado + " | " + nombreInicial);
                }
            }
        });

        // Listener Seleccion fila tabla prestamos
        tablaPrestamos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int filaClickeada = tablaPrestamos.getSelectedRow();

                if (filaClickeada >= 0) {
                    int fila = tablaPrestamos.convertRowIndexToModel(filaClickeada);

                    var modelo = modeloTablaPrestamo;
                    // guarda los valores iniciales para comparar luego en los UPDATE
                    idPrestamoSeleccionado = Integer.parseInt(modelo.getValueAt(fila, 0).toString());
                    idEstudiantePrestamoInicial = Integer.parseInt(modelo.getValueAt(fila, 1).toString());
                    idLibroPrestamoInicial = Integer.parseInt(modelo.getValueAt(fila, 2).toString());
                    fechaPrestamoInicial = LocalDate.parse(modelo.getValueAt(fila, 3).toString());
                    fechaDevolucionInicial = LocalDate.parse(modelo.getValueAt(fila, 4).toString());
                    devueltoInicial = (boolean) modelo.getValueAt(fila, 5);

                    campoIdPrestamo.setText(String.valueOf(idPrestamoSeleccionado));
                    campoIdEstudiantePrestamo.setText(String.valueOf(idEstudiantePrestamoInicial));
                    campoIdLibroPrestamo.setText(String.valueOf(idLibroPrestamoInicial));
                    campoFechaPrestamo.setText(String.valueOf(fechaPrestamoInicial));
                    campoFechaDevolucion.setText(String.valueOf(fechaDevolucionInicial));
                    campoDevolucion.setText(String.valueOf(devueltoInicial));

                    lblSeleccionPrestamos.setText("Prestamo seleccionado: " + idPrestamoSeleccionado + " | Estudiante: " + idEstudiantePrestamoInicial + " | Libro: " + idLibroPrestamoInicial);
                }
            }
        });

        campoFechaPrestamo.setText(java.time.LocalDate.now().toString());
        campoFechaDevolucion.setText(java.time.LocalDate.now().plusDays(7).toString());

        // BOTONES LIBROS
        guardarLibroBtn.addActionListener(e -> registrarLibro()); //FUNCIONA
        verLibroBtn.addActionListener(e -> actualizarTablaLibros());
        editarLibroBtn.addActionListener(e -> editarLibro());
        eliminarLibroBtn.addActionListener(e -> eliminarLibro());

        //BOTONES ESTUDIANTES
        guardarEstudianteBtn.addActionListener(e -> registrarEstudiante());
        verEstudiantesBtn.addActionListener(e -> actualizarTablaEstudiantes());
        editarEstudianteBtn.addActionListener(e -> editarEstudiante());
        eliminarEstudianteBtn.addActionListener(e -> eliminarEstudiante());

        //BOTONES PRESTAMO
        guardarPrestamoBtn.addActionListener(e -> registarPrestamo());
        verPrestamoBtn.addActionListener(e -> actualizarTablaPrestamos());
        devolverPrestamoBtn.addActionListener(e -> registrarDevolucion());
        eliminarPrestamoBtn.addActionListener(e -> eliminarPrestamo());

        //util
        limpiarLibrosBtn.addActionListener(e -> limpiarCamposLibros());
        limpiarCamposEstudiantesBtn.addActionListener(e -> limpiarCamposEstudiantes());
        limpiarCamposPrestamoBtn.addActionListener(e -> limpiarCamposPrestamos());
        salirButton.addActionListener(e -> dispose());

        buscarLibrosBtn.addActionListener(e -> buscarLibro());
        buscarEstudianteBtn.addActionListener(e -> buscarEstudiante());
        buscarPrestamoBtn.addActionListener(e -> buscarPrestamo());

        //REPORTES
        verLibrosMasPrestadosButton.addActionListener(e -> reporteLibrosMasPrestados());
        historialPrestamosButton.addActionListener(e -> reporteHistorial());
        listadoPrestamosActualButton.addActionListener(e -> reporteLibrosEnPrestamo());

        actualizarTablaLibros();
        actualizarTablaEstudiantes();
        actualizarTablaPrestamos();
    }

    private void initComponents() {
    }

    // CREATE LIBRO
    public void registrarLibro(){
        try {
            String titulo = campoTituloLibro.getText().trim();
            String autor = campoAutorLibro.getText().trim();
            String isbn = campoIsbnLibro.getText().trim();
            String editorial = campoEditorialLibro.getText().trim();
            String txtStock = campoStockLibro.getText().trim();
            String txtIdCategoria = campoIdCategoriaLibro.getText().trim();



            if (titulo.isEmpty() || autor.isEmpty() || isbn.isEmpty() || editorial.isEmpty() || txtStock.isEmpty() || txtIdCategoria.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Ingrese todos los campos");
                return;
            }

            // validacion isbn 10 o 13
            if (isbn.length() != 10 && isbn.length() != 13) {
                JOptionPane.showMessageDialog(null, "ISBN solo puede tener 10 o 13 caracteres.");
                return;
            }

            int stock = Integer.parseInt(txtStock);
            int idCategoria = Integer.parseInt(txtIdCategoria);

            if (stock <= 0 ||  idCategoria <= 0 ) {
                JOptionPane.showMessageDialog(null, "Stock debe ser mayor que cero. Categoria debe ser un numero valido.");
            }

            Libro libroNuevo = new Libro(titulo, autor, isbn, editorial, stock, idCategoria);

            // V -> C
            boolean exito = controladorLibros.guardarLibro(libroNuevo);
            if (exito){
                // se usa el ps.getGeneratedKeys de libroDAO para obtener id
                JOptionPane.showMessageDialog(null, "Libro #" + libroNuevo.getId() + " creado con exito.", "Sistema registro", JOptionPane.INFORMATION_MESSAGE);
                actualizarTablaLibros();
                limpiarCamposLibros();
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Los datos ingresados no son validos");
            System.out.println(ex.getCause() + ex.getMessage());
        }
    }
    // READ LIBROS
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
    // UPDATE LIBROS
    public void editarLibro(){
        try {
            //validacion tipo pedido
            if (idLibroSeleccionado == -1) {
                JOptionPane.showMessageDialog(null, "Selecciona un libro valido para editar.");
                return;
            }

            String titulo = campoTituloLibro.getText().trim();
            String autor = campoAutorLibro.getText().trim();
            String isbn = campoIsbnLibro.getText().trim();
            String editorial = campoEditorialLibro.getText().trim();
            String txtStock = campoStockLibro.getText().trim();
            String txtIdCategoria = campoIdLibro.getText().trim();

            if (titulo.isEmpty() || autor.isEmpty() || isbn.isEmpty() || editorial.isEmpty() || txtStock.isEmpty() || txtIdCategoria.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Ingrese todos los campos");
                return;
            }
            // validacion isbn 10 o 13
            if (isbn.length() != 10 && isbn.length() != 13) {
                JOptionPane.showMessageDialog(null, "ISBN solo puede tener 10 o 13 caracteres.");
                return;
            }

            int stock = Integer.parseInt(txtStock);
            int idCategoria = Integer.parseInt(txtIdCategoria);

            if (titulo.equals(tituloInicial) && autor.equals(autorInicial) && isbn.equals(isbnInicial)
                    && editorial.equals(editorialInicial) && stock == stockInicial && idCategoria == idCategoriaInicial) {
                JOptionPane.showMessageDialog(null,"Debes modificar al menos un valor para editar el libro seleccionado.");
                return;
            }

            Libro libroNuevo = new Libro(idLibroSeleccionado, titulo, autor, isbn, editorial, stock, idCategoria);

            // V -> C
            boolean exito = controladorLibros.editarLibro(libroNuevo);
            if (exito){
                JOptionPane.showMessageDialog(null, "Libro #" + libroNuevo.getId() + " editado con exito.", "Sistema registro", JOptionPane.INFORMATION_MESSAGE);
                idLibroSeleccionado = -1;
                actualizarTablaLibros();

            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null,"Stock y categoria deben ser numeros enteros");
        }

        actualizarTablaLibros();
    }
    // DELETE LIBRO
    public void eliminarLibro(){
        if (idLibroSeleccionado == -1) {
            JOptionPane.showMessageDialog(null, "Selecciona un libro valido.");
            return;
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
            actualizarTablaLibros();
            idLibroSeleccionado = -1;
            limpiarCamposLibros();
        }else{
            JOptionPane.showMessageDialog(null, "No se pudo completar la eliminacion");
        }
    }

    //ESTUDIANTE
    // CREATE ESTUDIANTE
    public void registrarEstudiante() {
        String nombre = campoNombreEstudiante.getText().trim();
        String rut = campoRutEstudiante.getText().trim();
        String curso = campoCursoEstudiante.getText().trim();
        String correo = campoCorreoEstudiante.getText().trim();

        if (rut.isEmpty() || nombre.isEmpty() || curso.isEmpty() || correo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Rellena todos los campos.");
            return;
        }

        if (!correo.contains("@") || !correo.contains(".")) {
            JOptionPane.showMessageDialog(this, "Ingrese un correo electronico valido");
            return;
        }

        if (rut.length() < 8) {
            JOptionPane.showMessageDialog(this, "El RUT ingresado es muy corto.");
            return;
        }

        try {
            Estudiante estudianteNuevo = new Estudiante(nombre, rut, curso, correo);
            boolean exito = controladorEstudiantes.guardarEstudiante(estudianteNuevo);

            if (exito) {
                JOptionPane.showMessageDialog(this, "Estudiante registrado con exito.");
                actualizarTablaEstudiantes();
                limpiarCamposEstudiantes();
            } else {
                JOptionPane.showMessageDialog(this, "El RUT o correo ya está registrado.");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Hubo un error al guardar: ");
        }
    }
    //READ ESTUDIANTE
    public void actualizarTablaEstudiantes(){
        modeloTablaEstudiante.setRowCount(0);

        List<Estudiante> listaEstudiantes = controladorEstudiantes.listarEstudiantes();
        for (Estudiante estudiante : listaEstudiantes) {
            Object[] fila = {
                    estudiante.getId(),
                    estudiante.getNombre(),
                    estudiante.getRut(),
                    estudiante.getCurso(),
                    estudiante.getCorreo()
            };
            modeloTablaEstudiante.addRow(fila);
        }
    }
    //UPODATE ESTUDIANTE
    public void editarEstudiante() {
        if (idEstudianteSeleccionado == -1) {
            JOptionPane.showMessageDialog(null, "Selecciona un estudiante valido.");
            return;
        }

        String nombre = campoNombreEstudiante.getText().trim();
        String rut = campoRutEstudiante.getText().trim();
        String curso = campoCursoEstudiante.getText().trim();
        String correo = campoCorreoEstudiante.getText().trim();

        if (rut.isEmpty() || nombre.isEmpty() || curso.isEmpty() || correo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Rellena todos los campos.");
            return;
        }

        if (!correo.contains("@") || !correo.contains(".")) {
            JOptionPane.showMessageDialog(this, "Ingrese un correo electronico valido");
            return;
        }

        if (rut.length() < 8) {
            JOptionPane.showMessageDialog(this, "El RUT ingresado es muy corto.");
            return;
        }

        if (nombre.equals(nombreInicial) && rut.equals(rutInicial) && curso.equals(cursoInicial) && correo.equals(correoInicial)) {
            JOptionPane.showMessageDialog(this, "Debes modificar al menos un dato para Editar");
            return;
        }

        try {
            Estudiante estudianteNuevo = new Estudiante(idEstudianteSeleccionado, nombre, rut, curso, correo);
            boolean exito = controladorEstudiantes.guardarEstudiante(estudianteNuevo);

            if (exito) {
                JOptionPane.showMessageDialog(this, "Estudiante registrado con exito.");
                actualizarTablaEstudiantes();
                limpiarCamposEstudiantes();
            } else {
                JOptionPane.showMessageDialog(this, "El RUT o correo ya esta registrado.");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Hubo un error al guardar: ");
        }
    }
    // DELETE ESTUDIANTE
    public void eliminarEstudiante(){
        if (idEstudianteSeleccionado == -1) {
            JOptionPane.showMessageDialog(null, "Selecciona un estudiante valido.");
            return;
        }

        int confirmar = JOptionPane.showConfirmDialog(null,
                "Esta seguro que desea eliminar el Estudiante #" + idEstudianteSeleccionado + "?",
                "Eliminar estudiante", JOptionPane.YES_NO_OPTION);
        if (confirmar != JOptionPane.YES_OPTION) {
            return;
        }
        // V -> C
        boolean exito = controladorEstudiantes.eliminarEstudiante(idEstudianteSeleccionado);
        if (exito){
            JOptionPane.showMessageDialog(null, "Estudiante #" + idEstudianteSeleccionado + " eliminado con exito.", "Sistema Eliminacion", JOptionPane.INFORMATION_MESSAGE);
            actualizarTablaEstudiantes();
            limpiarCamposEstudiantes();
        }else{
            JOptionPane.showMessageDialog(null, "No se pudo completar la eliminacion");
        }
    }


    //  CREATE PRESTAMO
    public void registarPrestamo() {
        try {
            int idPrestamo = campoIdPrestamo.getText().trim().isEmpty() ? 0 : Integer.parseInt(campoIdPrestamo.getText().trim());
            int idLibro = Integer.parseInt(campoIdLibroPrestamo.getText().trim());
            int idEstudiante = Integer.parseInt(campoIdEstudiantePrestamo.getText().trim());


            String textoFechaP = campoFechaPrestamo.getText().trim();
            String textoFechaD = campoFechaDevolucion.getText().trim();

            Libro libro = controladorLibros.buscarPorId(idLibro);
            if (libro == null || libro.getStock() <= 0) {
                JOptionPane.showMessageDialog(null, "Error: El libro no existe o no hay stock disponible.");
                return;
            }

            LocalDate fechaPrestamo;
            LocalDate fechaDevolucion;

            try {
                fechaPrestamo = LocalDate.parse(textoFechaP);
                fechaDevolucion = LocalDate.parse(textoFechaD);
            } catch (java.time.format.DateTimeParseException e) {
                JOptionPane.showMessageDialog(this,
                        "Formato de fecha incorrecto. Debe ser AAAA-MM-DD (Ejemplo: 2026-10-11).",
                        "Error en Fechas",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            Prestamo nuevoPrestamo = new Prestamo(0, idEstudiante, idLibro, fechaPrestamo, fechaDevolucion, false);

            Thread hiloPrestamo = new Thread(() -> {
                try {
                    boolean exito = controladorPrestamos.registrarPrestamo(nuevoPrestamo, idLibro);

                    SwingUtilities.invokeLater(() -> {
                        if (exito) {
                            JOptionPane.showMessageDialog(null, "Prestamo registrado exitosamente en segundo plano.");
                            actualizarTablaPrestamos();
                            actualizarTablaLibros();
                        } else {
                            JOptionPane.showMessageDialog(null, "Error: No hay stock o hubo un problema de concurrencia.");
                        }
                    });

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });

            hiloPrestamo.start();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Debes ingresar numeros validos.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error inesperado.");
        }
    }

    // READ PRESTAMO
    public void actualizarTablaPrestamos(){
        modeloTablaPrestamo.setRowCount(0);

        List<Prestamo> listaPrestamos = controladorPrestamos.listarPrestamos();
        for (Prestamo prestamo : listaPrestamos) {
            Object[] fila = {
                    prestamo.getId(),
                    prestamo.getIdEstudiante(),
                    prestamo.getIdLibro(),
                    prestamo.getFechaPrestamo(),
                    prestamo.getFechaDevolucion(),
                    prestamo.isDevuelto()
            };
            modeloTablaPrestamo.addRow(fila);
        }
    }

    // UPDATE PRESTAMO
    public void registrarDevolucion(){
        if (idPrestamoSeleccionado == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un prestamo de la tabla primero");
            return;
        }
        if (devueltoInicial) {
            JOptionPane.showMessageDialog(this, "Este libro ya fue devuelto antes.");
            return;
        }

        Prestamo prestamoActualizado = new Prestamo(
                idPrestamoSeleccionado,
                idEstudiantePrestamoInicial,
                idLibroPrestamoInicial,
                fechaPrestamoInicial,
                fechaDevolucionInicial,
                true
        );


        if (controladorPrestamos.editarPrestamo(prestamoActualizado)) {
            Libro libro = controladorLibros.buscarPorId(idLibroPrestamoInicial);
            if (libro != null) {
                libro.setStock(libro.getStock() + 1);
                controladorLibros.editarLibro(libro);
            }

            JOptionPane.showMessageDialog(this, "Libro devuelto con exito. El stock ha sido actualizado.");
            actualizarTablaPrestamos();


            idPrestamoSeleccionado = -1;

        }
    }
    //DELETE PRESTAMO
    public void eliminarPrestamo(){
        if (idPrestamoSeleccionado == -1) {
            JOptionPane.showMessageDialog(null, "Selecciona un prestamo valido.");
            return;
        }

        int confirmar = JOptionPane.showConfirmDialog(null,
                "Esta seguro que desea eliminar el Prestamo #" + idPrestamoSeleccionado + "?",
                "Eliminar prestamo", JOptionPane.YES_NO_OPTION);
        if (confirmar != JOptionPane.YES_OPTION) {
            return;
        }
        // V -> C
        boolean exito = controladorPrestamos.eliminarPrestamo(idPrestamoSeleccionado);
        if (exito){
            JOptionPane.showMessageDialog(null, "Prestamo #" + idPrestamoSeleccionado + " eliminado con exito.", "Sistema Eliminacion", JOptionPane.INFORMATION_MESSAGE);
            actualizarTablaPrestamos();
            limpiarCamposPrestamos();
        }else{
            JOptionPane.showMessageDialog(null, "No se pudo completar la eliminacion");
        }
    }

    //LIMPIAR CAMPOS
    public void limpiarCamposLibros(){
        campoIdLibro.setText("");
        campoTituloLibro.setText("");
        campoAutorLibro.setText("");
        campoIsbnLibro.setText("");
        campoEditorialLibro.setText("");
        campoStockLibro.setText("");
        campoIdCategoriaLibro.setText("");
        idLibroSeleccionado = -1;
    }

    public void limpiarCamposEstudiantes(){
        campoIdEstudiante.setText("");
        campoNombreEstudiante.setText("");
        campoRutEstudiante.setText("");
        campoCursoEstudiante.setText("");
        campoCorreoEstudiante.setText("");
        idEstudianteSeleccionado = -1;
    }

    public void limpiarCamposPrestamos(){
        campoIdPrestamo.setText("");
        campoIdEstudiantePrestamo.setText("");
        campoIdLibroPrestamo.setText("");
        campoFechaPrestamo.setText("");
        campoFechaDevolucion.setText("");
        campoDevolucion.setText("");
        idPrestamoSeleccionado = -1;
    }

    //barras de busqueda
    public void buscarLibro(){
        String termino = campoBuscarLibro.getText().trim();
        if (!termino.isEmpty()){
            filtroLibros.setRowFilter(RowFilter.regexFilter("(?i)" + termino));
        }else{
            filtroLibros.setRowFilter(null);
        }
    }

    public void buscarEstudiante(){
        String termino = campoBuscarEstudiante.getText().trim();
        if (!termino.isEmpty()){
            filtroEstudiantes.setRowFilter(RowFilter.regexFilter("(?i)" + termino));
        }else{
            filtroEstudiantes.setRowFilter(null);
        }
    }

    public void buscarPrestamo(){
        String termino = campoBuscarPrestamo.getText().trim();
        if (!termino.isEmpty()){
            filtroPrestamos.setRowFilter(RowFilter.regexFilter("(?i)" + termino));
        }else{
            filtroPrestamos.setRowFilter(null);
        }
    }

    //reportes
    public void reporteLibrosMasPrestados(){
        String resultado = controladorPrestamos.obtenerReporteMasPrestados();
        areaResultado.setText(resultado);
        tabbedPane1.setSelectedIndex(3);
    }

    public void reporteLibrosEnPrestamo(){
        String resultado = controladorPrestamos.obtenerReporteEnPrestamo();
        areaResultado.setText(resultado);
        tabbedPane1.setSelectedIndex(3);
    }

    public void reporteHistorial(){
        String rut = JOptionPane.showInputDialog(this, "Para historial ingresa el rut del estudiante (ej: 98765432-1):");

        if (rut != null && !rut.trim().isEmpty()) {
            String resultado = controladorPrestamos.obtenerReporteHistorial(rut.trim());
            areaResultado.setText(resultado);
            tabbedPane1.setSelectedIndex(3);
        }
    }
}
