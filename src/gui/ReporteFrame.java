/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui;

import java.awt.Color;
import java.awt.print.PrinterException;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import services.ReporteService;
import util.JTextFieldAutoCompleter;
import util.PDFGenerator;

/**
 *
 * @author Kevin Salinas
 */
public class ReporteFrame extends javax.swing.JFrame {
    int xMouse, yMouse;
    private ReporteService reporteService;
    /**
     * Creates new form ReporteFrame
     */
    public ReporteFrame() {
        initComponents();
        // Inicializa el servicio de reportes
        reporteService = new ReporteService();
        
        // Configura autocompletar para los campos Usuario y Libro
        new JTextFieldAutoCompleter(txtUsuario, reporteService.obtenerUsuarios());
        new JTextFieldAutoCompleter(txtLibro, reporteService.obtenerLibros());
        
        // Configura el combo de Estado con "Todos", "Disponible" y "No Disponible"
        cargarComboEstado();
        // Configura el combo de Exportar con las opciones dadas
        cargarComboExportar();
        
        // Genera el reporte inicial (puede ser sin filtros o con un intervalo por defecto)
        generarReporteBusqueda();
    }
    
    // Carga el combo de Estado
    private void cargarComboEstado() {
        DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<>();
        modelo.addElement("Todos");
        modelo.addElement("Disponible");
        modelo.addElement("No Disponible");
        jComboBox1.setModel(modelo);
        jComboBox1.setSelectedIndex(0);
    }
    
    // Carga el combo de Exportar
    private void cargarComboExportar() {
        DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<>();
        modelo.addElement("Libros más prestados en los últimos 6 meses.");
        modelo.addElement("Usuarios con más préstamos realizados en los últimos 3 meses.");
        modelo.addElement("Porcentaje de libros por género.");
        modelo.addElement("Listar todos los libros que no se han prestado en el último año.");
        modelo.addElement("Mostrar usuarios con reservas pendientes o vencidas.");
        cb_reportes.setModel(modelo);
    }
    
    // Genera el reporte según los filtros ingresados en la sección Buscar
    private void generarReporteBusqueda() {
        String fechaPrestamo = txtFechaPrestamo.getText().trim();
        String fechaDevolucion = txtFechaDevolucion.getText().trim();
        String usuario = txtUsuario.getText().trim();
        String libro = txtLibro.getText().trim();
        String estado = (String) jComboBox1.getSelectedItem();
        
        List<Object[]> datos = reporteService.buscarReportes(fechaPrestamo, fechaDevolucion, usuario, libro, estado);
        limpiarTablaReportes();
        llenarTablaReportes(datos);
    }
    
    // Llena la tabla de reportes con los datos obtenidos
    private void llenarTablaReportes(List<Object[]> datos) {
        String[] columnas = {"ID", "Fecha", "Usuario", "Libro", "Estado", "Detalle"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);
        for (Object[] fila : datos) {
            modelo.addRow(fila);
        }
        tabla_reportes.setModel(modelo);
    }
    
    // Limpia la tabla de reportes para evitar duplicados
    private void limpiarTablaReportes() {
        DefaultTableModel modelo = (DefaultTableModel) tabla_reportes.getModel();
        modelo.setRowCount(0);
    }
    
    // Exporta el contenido de la tabla a PDF
    private void exportarPDF() {
        String contenido = "";
        DefaultTableModel modelo = (DefaultTableModel) tabla_reportes.getModel();
        // Cabecera de columnas
        for (int i = 0; i < modelo.getColumnCount(); i++) {
            contenido += modelo.getColumnName(i) + "\t";
        }
        contenido += "\n";
        // Filas
        for (int i = 0; i < modelo.getRowCount(); i++) {
            for (int j = 0; j < modelo.getColumnCount(); j++) {
                contenido += modelo.getValueAt(i, j) + "\t";
            }
            contenido += "\n";
        }
        PDFGenerator.generarPDF(contenido);
        JOptionPane.showMessageDialog(this, "PDF generado correctamente.");
    }
    
    // Exporta a Excel (en este ejemplo, solo se notifica)
    private void exportarExcel() {
        JOptionPane.showMessageDialog(this, "Función de exportación a Excel no implementada aún.");
    }
    
    // Imprime el reporte
    private void imprimirReporte() {
        try {
            boolean complete = tabla_reportes.print();
            if (complete) {
                JOptionPane.showMessageDialog(this, "Reporte enviado a impresora.");
            } else {
                JOptionPane.showMessageDialog(this, "Impresión cancelada.");
            }
        } catch (PrinterException ex) {
            JOptionPane.showMessageDialog(this, "Error al imprimir: " + ex.getMessage());
        }
    }
    
    // Genera el reporte de exportación según la opción seleccionada en cb_reportes
    private void generarReporteExportar() {
        String opcion = (String) cb_reportes.getSelectedItem();
        limpiarTablaReportes();
        List<Object[]> datos = reporteService.generarReporteExportar(opcion);
        llenarTablaReportes(datos);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        head = new javax.swing.JPanel();
        btnExit = new javax.swing.JPanel();
        lblExit = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblNombre3 = new javax.swing.JLabel();
        txtFechaPrestamo = new javax.swing.JTextField();
        lblNombre2 = new javax.swing.JLabel();
        txtFechaDevolucion = new javax.swing.JTextField();
        lblNombre1 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        lblNombre = new javax.swing.JLabel();
        txtLibro = new javax.swing.JTextField();
        btnReporte = new javax.swing.JPanel();
        lblReporte = new javax.swing.JLabel();
        lblNombre4 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_reportes = new javax.swing.JTable();
        btnPDF = new javax.swing.JPanel();
        lblPDF = new javax.swing.JLabel();
        btnEXCEL = new javax.swing.JPanel();
        lblEXCEL = new javax.swing.JLabel();
        btnPRINT = new javax.swing.JPanel();
        lblPRINT = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        cb_reportes = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

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
                .addGap(0, 909, Short.MAX_VALUE))
        );
        headLayout.setVerticalGroup(
            headLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel1.setFont(new java.awt.Font("Consolas", 1, 18)); // NOI18N
        jLabel1.setText("Buscar");

        lblNombre3.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        lblNombre3.setText("Fecha préstamo:");

        lblNombre2.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        lblNombre2.setText("Fecha Devolución:");

        lblNombre1.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        lblNombre1.setText("Usuario:");

        lblNombre.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        lblNombre.setText("Libro:");

        btnReporte.setBackground(new java.awt.Color(143, 159, 179));
        btnReporte.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnReporteMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnReporteMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnReporteMouseExited(evt);
            }
        });

        lblReporte.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        lblReporte.setForeground(new java.awt.Color(255, 255, 255));
        lblReporte.setText("Generar Reporte");

        javax.swing.GroupLayout btnReporteLayout = new javax.swing.GroupLayout(btnReporte);
        btnReporte.setLayout(btnReporteLayout);
        btnReporteLayout.setHorizontalGroup(
            btnReporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnReporteLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(lblReporte)
                .addContainerGap(38, Short.MAX_VALUE))
        );
        btnReporteLayout.setVerticalGroup(
            btnReporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnReporteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblReporte, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                .addContainerGap())
        );

        lblNombre4.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        lblNombre4.setText("Estado:");

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel2.setFont(new java.awt.Font("Consolas", 1, 18)); // NOI18N
        jLabel2.setText("Exportar");

        tabla_reportes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tabla_reportes);

        btnPDF.setBackground(new java.awt.Color(143, 159, 179));
        btnPDF.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPDFMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnPDFMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnPDFMouseExited(evt);
            }
        });

        lblPDF.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        lblPDF.setForeground(new java.awt.Color(255, 255, 255));
        lblPDF.setText("PDF");

        javax.swing.GroupLayout btnPDFLayout = new javax.swing.GroupLayout(btnPDF);
        btnPDF.setLayout(btnPDFLayout);
        btnPDFLayout.setHorizontalGroup(
            btnPDFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnPDFLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(lblPDF)
                .addContainerGap(38, Short.MAX_VALUE))
        );
        btnPDFLayout.setVerticalGroup(
            btnPDFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnPDFLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblPDF, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnEXCEL.setBackground(new java.awt.Color(143, 159, 179));
        btnEXCEL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEXCELMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEXCELMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnEXCELMouseExited(evt);
            }
        });

        lblEXCEL.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        lblEXCEL.setForeground(new java.awt.Color(255, 255, 255));
        lblEXCEL.setText("EXCEL");

        javax.swing.GroupLayout btnEXCELLayout = new javax.swing.GroupLayout(btnEXCEL);
        btnEXCEL.setLayout(btnEXCELLayout);
        btnEXCELLayout.setHorizontalGroup(
            btnEXCELLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnEXCELLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(lblEXCEL)
                .addContainerGap(31, Short.MAX_VALUE))
        );
        btnEXCELLayout.setVerticalGroup(
            btnEXCELLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnEXCELLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblEXCEL, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnPRINT.setBackground(new java.awt.Color(143, 159, 179));
        btnPRINT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPRINTMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnPRINTMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnPRINTMouseExited(evt);
            }
        });

        lblPRINT.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        lblPRINT.setForeground(new java.awt.Color(255, 255, 255));
        lblPRINT.setText("IMPRIMIR");

        javax.swing.GroupLayout btnPRINTLayout = new javax.swing.GroupLayout(btnPRINT);
        btnPRINT.setLayout(btnPRINTLayout);
        btnPRINTLayout.setHorizontalGroup(
            btnPRINTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnPRINTLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(lblPRINT)
                .addContainerGap(18, Short.MAX_VALUE))
        );
        btnPRINTLayout.setVerticalGroup(
            btnPRINTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnPRINTLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblPRINT, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                .addContainerGap())
        );

        cb_reportes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_reportesActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        jLabel3.setText("Seleccionar:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(head, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblNombre2, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblNombre1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblNombre4)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblNombre3, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblNombre, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtUsuario, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtLibro, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtFechaDevolucion, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtFechaPrestamo, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jComboBox1, 0, 96, Short.MAX_VALUE)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(btnReporte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnEXCEL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnPDF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnPRINT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(jLabel2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cb_reportes, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 477, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(head, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblNombre3)
                                    .addComponent(txtFechaPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(26, 26, 26)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(lblNombre2)
                                        .addGap(22, 22, 22)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(lblNombre1)
                                            .addComponent(txtLibro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(36, 36, 36)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(lblNombre)
                                            .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(txtFechaDevolucion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblNombre4))
                                .addGap(41, 41, 41)
                                .addComponent(btnReporte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(46, 46, 46))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(12, 12, 12)
                                        .addComponent(jLabel3)
                                        .addGap(53, 53, 53)
                                        .addComponent(cb_reportes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(86, 86, 86)
                                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnPDF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnEXCEL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnPRINT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(24, 24, 24))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 943, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void btnReporteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReporteMouseClicked
        // TODO add your handling code here:
        generarReporteBusqueda();
    }//GEN-LAST:event_btnReporteMouseClicked

    private void btnPDFMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPDFMouseClicked
        // TODO add your handling code here:
        exportarPDF();
    }//GEN-LAST:event_btnPDFMouseClicked

    private void btnPDFMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPDFMouseEntered
        // TODO add your handling code here:
        Color fondo = new Color(173,193,217);
        btnPDF.setBackground(fondo);
        lblPDF.setForeground(Color.BLACK);
    }//GEN-LAST:event_btnPDFMouseEntered

    private void btnPDFMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPDFMouseExited
        // TODO add your handling code here:
        Color fondo = new Color(143,159,179);
        btnPDF.setBackground(fondo);
        lblPDF.setForeground(Color.WHITE);
    }//GEN-LAST:event_btnPDFMouseExited

    private void btnEXCELMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEXCELMouseClicked
        // TODO add your handling code here:
        exportarExcel();
    }//GEN-LAST:event_btnEXCELMouseClicked

    private void btnEXCELMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEXCELMouseEntered
        // TODO add your handling code here:
        Color fondo = new Color(173,193,217);
        btnEXCEL.setBackground(fondo);
        lblEXCEL.setForeground(Color.BLACK);
    }//GEN-LAST:event_btnEXCELMouseEntered

    private void btnEXCELMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEXCELMouseExited
        // TODO add your handling code here:
        Color fondo = new Color(143,159,179);
        btnEXCEL.setBackground(fondo);
        lblEXCEL.setForeground(Color.WHITE);
    }//GEN-LAST:event_btnEXCELMouseExited

    private void btnPRINTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPRINTMouseClicked
        // TODO add your handling code here:
        imprimirReporte();
    }//GEN-LAST:event_btnPRINTMouseClicked

    private void btnPRINTMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPRINTMouseEntered
        // TODO add your handling code here:
        Color fondo = new Color(173,193,217);
        btnPRINT.setBackground(fondo);
        lblPRINT.setForeground(Color.BLACK);
    }//GEN-LAST:event_btnPRINTMouseEntered

    private void btnPRINTMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPRINTMouseExited
        // TODO add your handling code here:
        Color fondo = new Color(143,159,179);
        btnPRINT.setBackground(fondo);
        lblPRINT.setForeground(Color.WHITE);
    }//GEN-LAST:event_btnPRINTMouseExited

    private void btnReporteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReporteMouseEntered
        // TODO add your handling code here:
        Color fondo = new Color(173,193,217);
        btnReporte.setBackground(fondo);
        lblReporte.setForeground(Color.BLACK);
    }//GEN-LAST:event_btnReporteMouseEntered

    private void btnReporteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReporteMouseExited
        // TODO add your handling code here:
        Color fondo = new Color(143,159,179);
        btnReporte.setBackground(fondo);
        lblReporte.setForeground(Color.WHITE);
    }//GEN-LAST:event_btnReporteMouseExited

    private void cb_reportesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_reportesActionPerformed
        // TODO add your handling code here:
        generarReporteExportar();
    }//GEN-LAST:event_cb_reportesActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws ClassNotFoundException {
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
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReporteFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReporteFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReporteFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ReporteFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel btnEXCEL;
    private javax.swing.JPanel btnExit;
    private javax.swing.JPanel btnPDF;
    private javax.swing.JPanel btnPRINT;
    private javax.swing.JPanel btnReporte;
    private javax.swing.JComboBox<String> cb_reportes;
    private javax.swing.JPanel head;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblEXCEL;
    private javax.swing.JLabel lblExit;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblNombre1;
    private javax.swing.JLabel lblNombre2;
    private javax.swing.JLabel lblNombre3;
    private javax.swing.JLabel lblNombre4;
    private javax.swing.JLabel lblPDF;
    private javax.swing.JLabel lblPRINT;
    private javax.swing.JLabel lblReporte;
    private javax.swing.JTable tabla_reportes;
    private javax.swing.JTextField txtFechaDevolucion;
    private javax.swing.JTextField txtFechaPrestamo;
    private javax.swing.JTextField txtLibro;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
