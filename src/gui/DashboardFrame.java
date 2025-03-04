/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui;

import java.awt.Color;
import javax.swing.JOptionPane;
import util.Sesion;

/**
 *
 * @author Kevin Salinas
 */
public class DashboardFrame extends javax.swing.JFrame {

    int xMouse, yMouse;
    private String usuario;
    private String rol;
    /**
     * Creates new form DashboardFrame
     */
    public DashboardFrame() {
        initComponents();
        configurarAccesos();
    }
    private void configurarAccesos() {
        String usuario = Sesion.getUsuario();
        String rol = Sesion.getRol();

        lblUser.setText(usuario);
        lblRol.setText(rol);
        
         // Asignar icono según el rol
        if (rol.equals("Administrador")) {
            img_rol.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/admin.png")));
        } else if (rol.equals("Empleado")) {
            img_rol.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/employee.png")));
        } else if (rol.equals("Usuario Regular")) {
            img_rol.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/user.png")));
        }
        
        // Configurar permisos de acceso
        btnGU.setEnabled(rol.equals("Administrador"));
        btnGP.setEnabled(rol.equals("Administrador") || rol.equals("Empleado"));
        btnGL.setEnabled(rol.equals("Administrador") || rol.equals("Empleado") || rol.equals("Usuario Regular"));
        btnRep.setEnabled(rol.equals("Administrador"));
        btnLibroDis.setEnabled(rol.equals("Usuario Regular")); // Libros Disponibles solo para Usuario Regular
    }
    
    private boolean verificarPermiso(String permisoRequerido) {
        String rol = Sesion.getRol();
        
        if (permisoRequerido.equals("Administrador")) {
            return rol.equals("Administrador");
        } else if (permisoRequerido.equals("Empleado")) {
            return rol.equals("Administrador") || rol.equals("Empleado");
        } else if (permisoRequerido.equals("Usuario Regular")) {
            return rol.equals("Usuario Regular");
        }
        return false;
    }
    
    private void mostrarAccesoDenegado() {
        JOptionPane.showMessageDialog(this, "No tienes permisos para acceder a esta sección", "Acceso Denegado", JOptionPane.ERROR_MESSAGE);
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
        head = new javax.swing.JPanel();
        btnExit = new javax.swing.JPanel();
        lblExit = new javax.swing.JLabel();
        btnGL = new javax.swing.JPanel();
        lblGL = new javax.swing.JLabel();
        btnGU = new javax.swing.JPanel();
        lblGU = new javax.swing.JLabel();
        btnRep = new javax.swing.JPanel();
        lblGU1 = new javax.swing.JLabel();
        btnGP = new javax.swing.JPanel();
        lblGP = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        lblUser = new javax.swing.JLabel();
        lblRol = new javax.swing.JLabel();
        btnCS = new javax.swing.JPanel();
        lblCS = new javax.swing.JLabel();
        img_rol = new javax.swing.JLabel();
        btnLibroDis = new javax.swing.JPanel();
        lblLibroDis = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

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
                .addGap(0, 554, Short.MAX_VALUE))
        );
        headLayout.setVerticalGroup(
            headLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        btnGL.setBackground(new java.awt.Color(143, 159, 179));
        btnGL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnGLMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnGLMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnGLMouseExited(evt);
            }
        });

        lblGL.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        lblGL.setForeground(new java.awt.Color(255, 255, 255));
        lblGL.setText("Gestión Libros");

        javax.swing.GroupLayout btnGLLayout = new javax.swing.GroupLayout(btnGL);
        btnGL.setLayout(btnGLLayout);
        btnGLLayout.setHorizontalGroup(
            btnGLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnGLLayout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addComponent(lblGL)
                .addGap(22, 22, 22))
        );
        btnGLLayout.setVerticalGroup(
            btnGLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnGLLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblGL, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnGU.setBackground(new java.awt.Color(143, 159, 179));
        btnGU.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnGUMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnGUMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnGUMouseExited(evt);
            }
        });

        lblGU.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        lblGU.setForeground(new java.awt.Color(255, 255, 255));
        lblGU.setText("Gestión Usuarios");

        javax.swing.GroupLayout btnGULayout = new javax.swing.GroupLayout(btnGU);
        btnGU.setLayout(btnGULayout);
        btnGULayout.setHorizontalGroup(
            btnGULayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnGULayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(lblGU)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        btnGULayout.setVerticalGroup(
            btnGULayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnGULayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblGU, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnRep.setBackground(new java.awt.Color(227, 181, 78));
        btnRep.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRepMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnRepMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnRepMouseExited(evt);
            }
        });

        lblGU1.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        lblGU1.setText("Reportes");

        javax.swing.GroupLayout btnRepLayout = new javax.swing.GroupLayout(btnRep);
        btnRep.setLayout(btnRepLayout);
        btnRepLayout.setHorizontalGroup(
            btnRepLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnRepLayout.createSequentialGroup()
                .addContainerGap(50, Short.MAX_VALUE)
                .addComponent(lblGU1)
                .addGap(42, 42, 42))
        );
        btnRepLayout.setVerticalGroup(
            btnRepLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblGU1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)
        );

        btnGP.setBackground(new java.awt.Color(143, 159, 179));
        btnGP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnGPMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnGPMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnGPMouseExited(evt);
            }
        });

        lblGP.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        lblGP.setForeground(new java.awt.Color(255, 255, 255));
        lblGP.setText("Gestión Préstamos");

        javax.swing.GroupLayout btnGPLayout = new javax.swing.GroupLayout(btnGP);
        btnGP.setLayout(btnGPLayout);
        btnGPLayout.setHorizontalGroup(
            btnGPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnGPLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(lblGP)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        btnGPLayout.setVerticalGroup(
            btnGPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnGPLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblGP, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                .addContainerGap())
        );

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        lblUser.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        lblUser.setText("Usuario");

        lblRol.setText("Rol");

        btnCS.setBackground(new java.awt.Color(255, 0, 0));
        btnCS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCSMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCSMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCSMouseExited(evt);
            }
        });

        lblCS.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        lblCS.setForeground(new java.awt.Color(255, 255, 255));
        lblCS.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCS.setText("Cerrar Sesión");

        javax.swing.GroupLayout btnCSLayout = new javax.swing.GroupLayout(btnCS);
        btnCS.setLayout(btnCSLayout);
        btnCSLayout.setHorizontalGroup(
            btnCSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnCSLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblCS, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
                .addContainerGap())
        );
        btnCSLayout.setVerticalGroup(
            btnCSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnCSLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblCS, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        img_rol.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/admin.png"))); // NOI18N

        btnLibroDis.setBackground(new java.awt.Color(143, 159, 179));
        btnLibroDis.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLibroDisMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnLibroDisMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnLibroDisMouseExited(evt);
            }
        });

        lblLibroDis.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        lblLibroDis.setForeground(new java.awt.Color(255, 255, 255));
        lblLibroDis.setText("Libros Disponibles");

        javax.swing.GroupLayout btnLibroDisLayout = new javax.swing.GroupLayout(btnLibroDis);
        btnLibroDis.setLayout(btnLibroDisLayout);
        btnLibroDisLayout.setHorizontalGroup(
            btnLibroDisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnLibroDisLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(lblLibroDis)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        btnLibroDisLayout.setVerticalGroup(
            btnLibroDisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnLibroDisLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblLibroDis, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnGL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnGP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(47, 47, 47)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnLibroDis, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnGU, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(127, 127, 127)
                        .addComponent(btnRep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(32, 32, 32)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblRol, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelLayout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addComponent(img_rol))
                            .addGroup(panelLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnCS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(panelLayout.createSequentialGroup()
                .addComponent(head, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addComponent(head, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnGL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnGU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(42, 42, 42)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnGP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnLibroDis, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(btnRep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(img_rol)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblUser)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblRol)
                        .addGap(89, 89, 89)
                        .addComponent(btnCS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void btnGLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGLMouseClicked
        // TODO add your handling code here:
        if (verificarPermiso("Empleado")) {
            new LibrosFrame().setVisible(true);
        } else {
            mostrarAccesoDenegado();
        }
    }//GEN-LAST:event_btnGLMouseClicked

    private void btnGLMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGLMouseEntered
        // TODO add your handling code here:
        Color fondo = new Color(173,193,217);
        btnGL.setBackground(fondo);
        lblGL.setForeground(Color.BLACK);
    }//GEN-LAST:event_btnGLMouseEntered

    private void btnGLMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGLMouseExited
        // TODO add your handling code here:
        Color fondo = new Color(143,159,179);
        btnGL.setBackground(fondo);
        lblGL.setForeground(Color.WHITE);
    }//GEN-LAST:event_btnGLMouseExited

    private void btnGUMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGUMouseClicked
        // TODO add your handling code here:
        if (verificarPermiso("Administrador")) {
                    new UsuarioFrame().setVisible(true);
                } else {
                    mostrarAccesoDenegado();
                } 
    }//GEN-LAST:event_btnGUMouseClicked

    private void btnGUMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGUMouseEntered
        // TODO add your handling code here:
        Color fondo = new Color(173,193,217);
        btnGU.setBackground(fondo);
        lblGU.setForeground(Color.BLACK);
        
    }//GEN-LAST:event_btnGUMouseEntered

    private void btnGUMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGUMouseExited
        // TODO add your handling code here:
        Color fondo = new Color(143,159,179);
        btnGU.setBackground(fondo);
        lblGU.setForeground(Color.WHITE);
    }//GEN-LAST:event_btnGUMouseExited

    private void btnRepMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRepMouseClicked
        // TODO add your handling code here:
                if (verificarPermiso("Administrador")) {
                    new ReporteFrame().setVisible(true);
                } else {
                    mostrarAccesoDenegado();
                }
            
    }//GEN-LAST:event_btnRepMouseClicked

    private void btnRepMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRepMouseEntered
        // TODO add your handling code here:
        Color fondo = new Color(255,203,88);
        btnRep.setBackground(fondo);
        btnRep.setForeground(Color.WHITE);
    }//GEN-LAST:event_btnRepMouseEntered

    private void btnRepMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRepMouseExited
        // TODO add your handling code here:
        Color fondo = new Color(227,181,78);
        btnRep.setBackground(fondo);
        btnRep.setForeground(Color.BLACK);
    }//GEN-LAST:event_btnRepMouseExited

    private void btnGPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGPMouseClicked
        // TODO add your handling code here:
        if (verificarPermiso("Empleado")) {
                    new PrestamoFrame().setVisible(true);
                } else {
                    mostrarAccesoDenegado();
                }
    }//GEN-LAST:event_btnGPMouseClicked

    private void btnGPMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGPMouseEntered
        // TODO add your handling code here:
        Color fondo = new Color(173,193,217);
        btnGP.setBackground(fondo);
        lblGP.setForeground(Color.BLACK);
    }//GEN-LAST:event_btnGPMouseEntered

    private void btnGPMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGPMouseExited
        // TODO add your handling code here:
        Color fondo = new Color(143,159,179);
        btnGP.setBackground(fondo);
        lblGP.setForeground(Color.WHITE);
    }//GEN-LAST:event_btnGPMouseExited

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
         this.setLocationRelativeTo(null);
    }//GEN-LAST:event_formWindowOpened

    private void btnCSMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCSMouseClicked
        int confirm = JOptionPane.showConfirmDialog(this, "¿Estás seguro de que quieres cerrar sesión?", "Cerrar Sesión", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            Sesion.iniciarSesion("", ""); // Reiniciar la sesión
            new LoginFrame().setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_btnCSMouseClicked

    private void btnCSMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCSMouseEntered
        // TODO add your handling code here:
        btnCS.setBackground(Color.WHITE);
        lblCS.setForeground(Color.RED);
    }//GEN-LAST:event_btnCSMouseEntered

    private void btnCSMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCSMouseExited
        // TODO add your handling code here:
        btnCS.setBackground(Color.RED);
        lblCS.setForeground(Color.WHITE);
    }//GEN-LAST:event_btnCSMouseExited

    private void btnLibroDisMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLibroDisMouseClicked
        // TODO add your handling code here:
        if (verificarPermiso("Usuario Regular")) {
            new DisponibleFrame().setVisible(true);
        } else {
            mostrarAccesoDenegado();
        }
    }//GEN-LAST:event_btnLibroDisMouseClicked

    private void btnLibroDisMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLibroDisMouseEntered
        // TODO add your handling code here:
        Color fondo = new Color(173,193,217);
        btnLibroDis.setBackground(fondo);
        lblLibroDis.setForeground(Color.BLACK);
    }//GEN-LAST:event_btnLibroDisMouseEntered

    private void btnLibroDisMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLibroDisMouseExited
        // TODO add your handling code here:
        Color fondo = new Color(143,159,179);
        btnLibroDis.setBackground(fondo);
        lblLibroDis.setForeground(Color.WHITE);
    }//GEN-LAST:event_btnLibroDisMouseExited

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
            java.util.logging.Logger.getLogger(DashboardFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DashboardFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DashboardFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DashboardFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DashboardFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel btnCS;
    private javax.swing.JPanel btnExit;
    private javax.swing.JPanel btnGL;
    private javax.swing.JPanel btnGP;
    private javax.swing.JPanel btnGU;
    private javax.swing.JPanel btnLibroDis;
    private javax.swing.JPanel btnRep;
    private javax.swing.JPanel head;
    private javax.swing.JLabel img_rol;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblCS;
    private javax.swing.JLabel lblExit;
    private javax.swing.JLabel lblGL;
    private javax.swing.JLabel lblGP;
    private javax.swing.JLabel lblGU;
    private javax.swing.JLabel lblGU1;
    private javax.swing.JLabel lblLibroDis;
    private javax.swing.JLabel lblRol;
    private javax.swing.JLabel lblUser;
    private javax.swing.JPanel panel;
    // End of variables declaration//GEN-END:variables
}
