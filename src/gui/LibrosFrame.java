/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui;

import java.awt.Color;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import models.Categoria;
import models.Libro;
import services.CategoriaService;
import services.LibroService;
import util.JTextFieldAutoCompleter;



/**
 *
 * @author Kevin Salinas
 */
public class LibrosFrame extends javax.swing.JFrame {

    int xMouse, yMouse;
    private LibroService libroService;
    private CategoriaService categoriaService;
    /**
     * Creates new form LibrosFrame
     */
    public LibrosFrame() {
        initComponents();
        // Inicializa los servicios
        libroService = new LibroService();
        categoriaService = new CategoriaService();
        
        // Configura autocompletar para la sección de búsqueda
        new JTextFieldAutoCompleter(b_txtTitulo, libroService.obtenerTitulosLibros());
        new JTextFieldAutoCompleter(b_txtAutor, libroService.obtenerAutoresLibros());
        
        // Carga el combo de categorías para búsqueda
        cargarCategoriasBusqueda();
        
        // Llena la tabla con TODOS los libros al iniciar (sin filtros)
        buscarLibros();
    }
    // Método para llenar el combo de búsqueda con "Todas" y las categorías disponibles
    private void cargarCategoriasBusqueda() {
        List<Categoria> lista = categoriaService.listarCategorias();
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        model.addElement("Todas");
        for (Categoria cat : lista) {
            // Se muestra "id - nombre"
            model.addElement(cat.getIdCategoria() + " - " + cat.getNombre());
        }
        b_cbCateg.setModel(model);
        b_cbCateg.setSelectedIndex(0);
    }
    
    // --- Métodos de funcionalidad ---
    
    // 1. Registrar Libro: Lee los valores de los textfields de registro y llama al DAO para insertarlo
    private void registrarLibro() {
        try {
            String titulo = txtTitle.getText().trim();
            String autor = txtAutor.getText().trim();
            // Suponemos que para registrar la categoría se ingresa el id en txtAutor1 (o, si tienes otro componente, cámbialo)
            int idCategoria = 0;
            try {
                idCategoria = Integer.parseInt(txtAutor1.getText().trim());
            } catch(NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "El id de la categoría debe ser numérico.");
                return;
            }
            String isbn = txtISBN.getText().trim();
            int cantidadDisponible = Integer.parseInt(txtCantDisponible.getText().trim());
            String fechaPublicacion = txtFechaPub.getText().trim();
            
            // Se crea el objeto Libro (se asume que el constructor y setters han sido actualizados para los nuevos atributos)
            Libro libro = new Libro(0, titulo, autor, idCategoria, true);
            libro.setIsbn(isbn);
            libro.setCantidadDisponible(cantidadDisponible);
            libro.setFechaPublicacion(fechaPublicacion);
            
            // Se registra el libro llamando al método del DAO a través del service
            libroService.getLibroDAO().agregarLibro(libro);
            
            JOptionPane.showMessageDialog(this, "Libro registrado correctamente.");
            limpiarCamposRegistro();
            buscarLibros();
        } catch(Exception e) {
            JOptionPane.showMessageDialog(this, "Error al registrar el libro: " + e.getMessage());
        }
    }
    
    // Limpia los textfields de la sección de registro
    private void limpiarCamposRegistro() {
        txtTitle.setText("");
        txtAutor.setText("");
        txtAutor1.setText("");
        txtISBN.setText("");
        txtCantDisponible.setText("");
        txtFechaPub.setText("");
    }
    
    // 2. Buscar Libros: Obtiene los filtros de búsqueda y llena la tabla
    private void buscarLibros() {
        // Se obtienen los filtros de la sección buscar
        String titulo = b_txtTitulo.getText().trim();
        String autor = b_txtAutor.getText().trim();
        int idCategoria = 0;
        String catSel = (String) b_cbCateg.getSelectedItem();
        if (catSel != null && !catSel.equals("Todas")) {
            try {
                idCategoria = Integer.parseInt(catSel.split(" - ")[0]);
            } catch(NumberFormatException e) {
                idCategoria = 0;
            }
        }
        
        // Se listan TODOS los libros (sin filtrar por disponibilidad)
        List<Libro> lista = libroService.listarTodosLibros();
        
        // Se aplican los filtros si se han ingresado valores
        if (!titulo.isEmpty()) {
            lista = lista.stream()
                    .filter(lib -> lib.getTitulo().toLowerCase().contains(titulo.toLowerCase()))
                    .collect(Collectors.toList());
        }
        if (!autor.isEmpty()) {
            lista = lista.stream()
                    .filter(lib -> lib.getAutor().toLowerCase().contains(autor.toLowerCase()))
                    .collect(Collectors.toList());
        }
        if (idCategoria != 0) {
            final int finalIdCategoria = idCategoria;
            lista = lista.stream()
            .filter(lib -> lib.getIdCategoria() == finalIdCategoria)
            .collect(Collectors.toList());
        }

        
        llenarTabla(lista);
    }
    
    // Llena la tabla table_libros con la lista de libros
    private void llenarTabla(List<Libro> libros) {
        // Define las columnas (incluyendo los nuevos atributos)
        String[] columnas = {"ID", "Título", "Autor", "Categoría", "ISBN", "Cant. Disponible", "Año Publicación", "Disponible"};
        DefaultTableModel model = new DefaultTableModel(columnas, 0);
        
        for (Libro libro : libros) {
            Object[] fila = new Object[8];
            fila[0] = libro.getIdLibro();
            fila[1] = libro.getTitulo();
            fila[2] = libro.getAutor();
            fila[3] = libro.getIdCategoria(); // O, si tienes el nombre de la categoría, úsalo
            fila[4] = libro.getIsbn();
            fila[5] = libro.getCantidadDisponible();
            fila[6] = libro.getFechaPublicacion();
            fila[7] = libro.isDisponible() ? "Sí" : "No";
            model.addRow(fila);
        }
        
        table_libros.setModel(model);
    }
    
    // 3. Modificar Libro: Toma la fila seleccionada, extrae los datos editados y actualiza la BD
    private void modificarLibro() {
        int fila = table_libros.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un libro para modificar.");
            return;
        }
        try {
            int idLibro = (int) table_libros.getValueAt(fila, 0);
            String nuevoTitulo = table_libros.getValueAt(fila, 1).toString();
            String nuevoAutor = table_libros.getValueAt(fila, 2).toString();
            int nuevaCategoria = Integer.parseInt(table_libros.getValueAt(fila, 3).toString());
            String nuevoIsbn = table_libros.getValueAt(fila, 4).toString();
            int nuevaCant = Integer.parseInt(table_libros.getValueAt(fila, 5).toString());
            String nuevaFechaPub = table_libros.getValueAt(fila, 6).toString();
            boolean disponible = table_libros.getValueAt(fila, 7).toString().equalsIgnoreCase("Sí");
            
            Libro libro = new Libro(idLibro, nuevoTitulo, nuevoAutor, nuevaCategoria, disponible);
            libro.setIsbn(nuevoIsbn);
            libro.setCantidadDisponible(nuevaCant);
            libro.setFechaPublicacion(nuevaFechaPub);
            
            libroService.actualizarLibro(libro);
            JOptionPane.showMessageDialog(this, "Libro modificado correctamente.");
            buscarLibros();
        } catch(Exception e) {
            JOptionPane.showMessageDialog(this, "Error al modificar: " + e.getMessage());
        }
    }
    
    // 4. Eliminar Libro: Elimina el registro del libro seleccionado
    private void eliminarLibro() {
        int fila = table_libros.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un libro para eliminar.");
            return;
        }
        int idLibro = (int) table_libros.getValueAt(fila, 0);
        int conf = JOptionPane.showConfirmDialog(this, "¿Está seguro de eliminar este libro?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (conf != JOptionPane.YES_OPTION) {
            return;
        }
        libroService.eliminarLibro(idLibro);
        JOptionPane.showMessageDialog(this, "Libro eliminado correctamente.");
        buscarLibros();
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        txtTitle = new javax.swing.JTextField();
        lblNombre1 = new javax.swing.JLabel();
        txtAutor = new javax.swing.JTextField();
        lblNombre3 = new javax.swing.JLabel();
        txtISBN = new javax.swing.JTextField();
        lblNombre2 = new javax.swing.JLabel();
        txtCantDisponible = new javax.swing.JTextField();
        lblNombre4 = new javax.swing.JLabel();
        lblNombre5 = new javax.swing.JLabel();
        txtFechaPub = new javax.swing.JPasswordField();
        btnSave = new javax.swing.JPanel();
        lblSave = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        lblNombre6 = new javax.swing.JLabel();
        b_txtTitulo = new javax.swing.JTextField();
        lblNombre7 = new javax.swing.JLabel();
        b_txtAutor = new javax.swing.JTextField();
        b_cbCateg = new javax.swing.JComboBox<>();
        lblNombre8 = new javax.swing.JLabel();
        btnBuscar = new javax.swing.JPanel();
        lblBuscar = new javax.swing.JLabel();
        btnDelete = new javax.swing.JPanel();
        lblDelete = new javax.swing.JLabel();
        btnMod = new javax.swing.JPanel();
        lblMod = new javax.swing.JLabel();
        head = new javax.swing.JPanel();
        btnExit = new javax.swing.JPanel();
        lblExit = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        table_libros = new javax.swing.JTable();
        txtAutor1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jLabel1.setFont(new java.awt.Font("Consolas", 1, 18)); // NOI18N
        jLabel1.setText("Registrar");

        lblNombre.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        lblNombre.setText("Título: ");

        lblNombre1.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        lblNombre1.setText("Autor: ");

        lblNombre3.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        lblNombre3.setText("Categoría: ");

        lblNombre2.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        lblNombre2.setText("Cantidad disponible:");

        lblNombre4.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        lblNombre4.setText("ISBN:");

        lblNombre5.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        lblNombre5.setText("Año de publicación:");

        btnSave.setBackground(new java.awt.Color(143, 159, 179));
        btnSave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSaveMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSaveMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSaveMouseExited(evt);
            }
        });

        lblSave.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        lblSave.setForeground(new java.awt.Color(255, 255, 255));
        lblSave.setText("Guardar");

        javax.swing.GroupLayout btnSaveLayout = new javax.swing.GroupLayout(btnSave);
        btnSave.setLayout(btnSaveLayout);
        btnSaveLayout.setHorizontalGroup(
            btnSaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnSaveLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(lblSave)
                .addContainerGap(17, Short.MAX_VALUE))
        );
        btnSaveLayout.setVerticalGroup(
            btnSaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnSaveLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblSave, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                .addContainerGap())
        );

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel2.setFont(new java.awt.Font("Consolas", 1, 18)); // NOI18N
        jLabel2.setText("Buscar");

        lblNombre6.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        lblNombre6.setText("Título: ");

        lblNombre7.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        lblNombre7.setText("Autor: ");

        b_cbCateg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_cbCategActionPerformed(evt);
            }
        });

        lblNombre8.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        lblNombre8.setText("Categoría: ");

        btnBuscar.setBackground(new java.awt.Color(143, 159, 179));
        btnBuscar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBuscarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnBuscarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnBuscarMouseExited(evt);
            }
        });

        lblBuscar.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        lblBuscar.setForeground(new java.awt.Color(255, 255, 255));
        lblBuscar.setText("Buscar");

        javax.swing.GroupLayout btnBuscarLayout = new javax.swing.GroupLayout(btnBuscar);
        btnBuscar.setLayout(btnBuscarLayout);
        btnBuscarLayout.setHorizontalGroup(
            btnBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnBuscarLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblBuscar)
                .addContainerGap(21, Short.MAX_VALUE))
        );
        btnBuscarLayout.setVerticalGroup(
            btnBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnBuscarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnDelete.setBackground(new java.awt.Color(143, 159, 179));
        btnDelete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDeleteMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnDeleteMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnDeleteMouseExited(evt);
            }
        });

        lblDelete.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        lblDelete.setForeground(new java.awt.Color(255, 255, 255));
        lblDelete.setText("Eliminar");

        javax.swing.GroupLayout btnDeleteLayout = new javax.swing.GroupLayout(btnDelete);
        btnDelete.setLayout(btnDeleteLayout);
        btnDeleteLayout.setHorizontalGroup(
            btnDeleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnDeleteLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(lblDelete)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        btnDeleteLayout.setVerticalGroup(
            btnDeleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnDeleteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblDelete, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnMod.setBackground(new java.awt.Color(143, 159, 179));
        btnMod.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnModMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnModMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnModMouseExited(evt);
            }
        });

        lblMod.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        lblMod.setForeground(new java.awt.Color(255, 255, 255));
        lblMod.setText("Modificar");

        javax.swing.GroupLayout btnModLayout = new javax.swing.GroupLayout(btnMod);
        btnMod.setLayout(btnModLayout);
        btnModLayout.setHorizontalGroup(
            btnModLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnModLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(lblMod)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        btnModLayout.setVerticalGroup(
            btnModLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnModLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblMod, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                .addContainerGap())
        );

        head.setBackground(new java.awt.Color(143, 159, 179));
        head.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                headMouseDragged(evt);
            }
        });
        head.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                headMousePressed(evt);
            }
        });

        btnExit.setBackground(new java.awt.Color(255, 0, 0));
        btnExit.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(143, 159, 179)));
        btnExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnExitMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnExitMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnExitMouseExited(evt);
            }
        });

        lblExit.setFont(new java.awt.Font("Consolas", 1, 18)); // NOI18N
        lblExit.setForeground(new java.awt.Color(255, 255, 255));
        lblExit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblExit.setText("X");

        javax.swing.GroupLayout btnExitLayout = new javax.swing.GroupLayout(btnExit);
        btnExit.setLayout(btnExitLayout);
        btnExitLayout.setHorizontalGroup(
            btnExitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnExitLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblExit, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        btnExitLayout.setVerticalGroup(
            btnExitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnExitLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblExit, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout headLayout = new javax.swing.GroupLayout(head);
        head.setLayout(headLayout);
        headLayout.setHorizontalGroup(
            headLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headLayout.createSequentialGroup()
                .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 861, Short.MAX_VALUE))
        );
        headLayout.setVerticalGroup(
            headLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        table_libros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(table_libros);

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lblNombre3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblNombre1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblNombre2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblNombre4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblNombre5, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1)
                                .addComponent(lblNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtAutor)
                            .addComponent(txtISBN)
                            .addComponent(txtFechaPub)
                            .addComponent(txtCantDisponible)
                            .addComponent(txtAutor1)
                            .addComponent(txtTitle)))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(98, 98, 98)
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel2)
                        .addGroup(panelLayout.createSequentialGroup()
                            .addGap(207, 207, 207)
                            .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 523, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(panelLayout.createSequentialGroup()
                            .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(b_txtTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblNombre6, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(68, 68, 68)
                            .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(panelLayout.createSequentialGroup()
                                    .addComponent(lblNombre7, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(b_cbCateg, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblNombre8))
                                    .addGap(16, 16, 16))
                                .addComponent(b_txtAutor, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnMod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(77, 77, 77)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(108, 108, 108)))
                .addGap(31, 31, 31))
            .addGroup(panelLayout.createSequentialGroup()
                .addComponent(head, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addComponent(head, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelLayout.createSequentialGroup()
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(panelLayout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(26, 26, 26)
                                        .addComponent(lblNombre6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(b_txtTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelLayout.createSequentialGroup()
                                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(lblNombre7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(lblNombre8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(b_txtAutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(b_cbCateg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(18, 18, 18)
                                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 21, Short.MAX_VALUE)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnMod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(34, 34, 34)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNombre)
                            .addComponent(txtTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNombre1)
                            .addComponent(txtAutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNombre3)
                            .addComponent(txtAutor1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNombre2)
                            .addComponent(txtCantDisponible, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNombre4)
                            .addComponent(txtISBN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNombre5)
                            .addComponent(txtFechaPub, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(61, 61, 61)
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExitMouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_btnExitMouseClicked

    private void btnExitMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExitMouseEntered
        // TODO add your handling code here:
        btnExit.setBackground(Color.WHITE);
        lblExit.setForeground(Color.RED);
    }//GEN-LAST:event_btnExitMouseEntered

    private void btnExitMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExitMouseExited
        // TODO add your handling code here:
        btnExit.setBackground(Color.RED);
        lblExit.setForeground(Color.WHITE);
    }//GEN-LAST:event_btnExitMouseExited

    private void headMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_headMouseDragged
        // TODO add your handling code here:
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        setLocation(x-xMouse,y-yMouse);
    }//GEN-LAST:event_headMouseDragged

    private void headMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_headMousePressed
        // TODO add your handling code here:
        xMouse= evt.getX();
        yMouse= evt.getY();
    }//GEN-LAST:event_headMousePressed

    private void btnSaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSaveMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSaveMouseClicked

    private void btnSaveMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSaveMouseEntered
        // TODO add your handling code here:
        Color fondo = new Color(173,193,217);
        btnSave.setBackground(fondo);
        lblSave.setForeground(Color.BLACK);
    }//GEN-LAST:event_btnSaveMouseEntered

    private void btnSaveMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSaveMouseExited
        // TODO add your handling code here:
        Color fondo = new Color(143,159,179);
        btnSave.setBackground(fondo);
        lblSave.setForeground(Color.WHITE);
    }//GEN-LAST:event_btnSaveMouseExited

    private void b_cbCategActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_cbCategActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b_cbCategActionPerformed

    private void btnBuscarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBuscarMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscarMouseClicked

    private void btnBuscarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBuscarMouseEntered
        // TODO add your handling code here:
        Color fondo = new Color(173,193,217);
        btnBuscar.setBackground(fondo);
        lblBuscar.setForeground(Color.BLACK);
    }//GEN-LAST:event_btnBuscarMouseEntered

    private void btnBuscarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBuscarMouseExited
        // TODO add your handling code here:
        Color fondo = new Color(143,159,179);
        btnBuscar.setBackground(fondo);
        lblBuscar.setForeground(Color.WHITE);
    }//GEN-LAST:event_btnBuscarMouseExited

    private void btnDeleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDeleteMouseClicked

    private void btnDeleteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteMouseEntered
        // TODO add your handling code here:
        Color fondo = new Color(173,193,217);
        btnDelete.setBackground(fondo);
        lblDelete.setForeground(Color.BLACK);
    }//GEN-LAST:event_btnDeleteMouseEntered

    private void btnDeleteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteMouseExited
        // TODO add your handling code here:
        Color fondo = new Color(143,159,179);
        btnDelete.setBackground(fondo);
        lblDelete.setForeground(Color.WHITE);
    }//GEN-LAST:event_btnDeleteMouseExited

    private void btnModMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnModMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnModMouseClicked

    private void btnModMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnModMouseEntered
        // TODO add your handling code here:
        Color fondo = new Color(173,193,217);
        btnMod.setBackground(fondo);
        lblMod.setForeground(Color.BLACK);
    }//GEN-LAST:event_btnModMouseEntered

    private void btnModMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnModMouseExited
        // TODO add your handling code here:
        Color fondo = new Color(143,159,179);
        btnMod.setBackground(fondo);
        lblMod.setForeground(Color.WHITE);
    }//GEN-LAST:event_btnModMouseExited

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LibrosFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LibrosFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LibrosFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LibrosFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LibrosFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> b_cbCateg;
    private javax.swing.JTextField b_txtAutor;
    private javax.swing.JTextField b_txtTitulo;
    private javax.swing.JPanel btnBuscar;
    private javax.swing.JPanel btnDelete;
    private javax.swing.JPanel btnExit;
    private javax.swing.JPanel btnMod;
    private javax.swing.JPanel btnSave;
    private javax.swing.JPanel head;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblBuscar;
    private javax.swing.JLabel lblDelete;
    private javax.swing.JLabel lblExit;
    private javax.swing.JLabel lblMod;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblNombre1;
    private javax.swing.JLabel lblNombre2;
    private javax.swing.JLabel lblNombre3;
    private javax.swing.JLabel lblNombre4;
    private javax.swing.JLabel lblNombre5;
    private javax.swing.JLabel lblNombre6;
    private javax.swing.JLabel lblNombre7;
    private javax.swing.JLabel lblNombre8;
    private javax.swing.JLabel lblSave;
    private javax.swing.JPanel panel;
    private javax.swing.JTable table_libros;
    private javax.swing.JTextField txtAutor;
    private javax.swing.JTextField txtAutor1;
    private javax.swing.JTextField txtCantDisponible;
    private javax.swing.JPasswordField txtFechaPub;
    private javax.swing.JTextField txtISBN;
    private javax.swing.JTextField txtTitle;
    // End of variables declaration//GEN-END:variables
}
