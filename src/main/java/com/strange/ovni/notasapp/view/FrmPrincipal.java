package com.strange.ovni.notasapp.view;

import com.strange.ovni.notasapp.config.NotasAppConfig;
import com.strange.ovni.notasapp.model.interfaces.OnChangeTheme;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author mrhazard
 */
public class FrmPrincipal extends javax.swing.JFrame implements OnChangeTheme{
    private final NotasAppConfig config = NotasAppConfig.getInstance();

    public FrmPrincipal() {
        initComponents();
       
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        containerTree = new javax.swing.JScrollPane();
        TreeDirectorios = new javax.swing.JTree();
        lblTitulo = new javax.swing.JLabel();
        txtTituloNota = new javax.swing.JTextField();
        containerTextNota = new javax.swing.JScrollPane();
        txtContenidoNota = new javax.swing.JEditorPane();
        containerBotones = new javax.swing.JPanel();
        btnGuardar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnCerrarSinGuardar = new javax.swing.JButton();
        btnRestablecer = new javax.swing.JButton();
        containerAcciones = new javax.swing.JPanel();
        btnAgregarComentario = new javax.swing.JButton();
        btnAgregarFecha1 = new javax.swing.JButton();
        MenuPrincipal = new javax.swing.JMenuBar();
        mnuNota = new javax.swing.JMenu();
        mnuItemNuevo = new javax.swing.JMenuItem();
        mnuItemGuardar = new javax.swing.JMenuItem();
        mnuItemEliminar = new javax.swing.JMenuItem();
        mnuItemSalir = new javax.swing.JMenuItem();
        mnuConfiguracion = new javax.swing.JMenu();
        mnuItemFuente = new javax.swing.JMenuItem();
        mnuItemTema = new javax.swing.JMenuItem();
        mnuItemRutaPrincipal = new javax.swing.JMenuItem();
        mnuAcerca = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        containerTree.setViewportView(TreeDirectorios);

        lblTitulo.setFont(new java.awt.Font("Tahoma", 2, 24)); // NOI18N
        lblTitulo.setText("Titulo:");

        containerTextNota.setViewportView(txtContenidoNota);

        containerBotones.setBorder(javax.swing.BorderFactory.createTitledBorder("Acciones"));

        btnGuardar.setToolTipText("Guardar nota");

        btnLimpiar.setToolTipText("Borrar lo escrito");

        btnCerrarSinGuardar.setToolTipText("Cerrar nota sin guardar");

        btnRestablecer.setToolTipText("Restablecer el contenido inicial");

        javax.swing.GroupLayout containerBotonesLayout = new javax.swing.GroupLayout(containerBotones);
        containerBotones.setLayout(containerBotonesLayout);
        containerBotonesLayout.setHorizontalGroup(
            containerBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(containerBotonesLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(containerBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRestablecer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCerrarSinGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLimpiar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(19, 19, 19))
        );
        containerBotonesLayout.setVerticalGroup(
            containerBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(containerBotonesLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(btnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnLimpiar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnCerrarSinGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnRestablecer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(14, 14, 14))
        );

        containerAcciones.setBorder(javax.swing.BorderFactory.createTitledBorder("Agregar"));
        containerAcciones.setToolTipText("");

        btnAgregarComentario.setToolTipText("Agregar Comentario");

        btnAgregarFecha1.setToolTipText("Agregar fecha actual");

        javax.swing.GroupLayout containerAccionesLayout = new javax.swing.GroupLayout(containerAcciones);
        containerAcciones.setLayout(containerAccionesLayout);
        containerAccionesLayout.setHorizontalGroup(
            containerAccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, containerAccionesLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(btnAgregarComentario, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
                .addGap(18, 18, 18))
            .addGroup(containerAccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(containerAccionesLayout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addComponent(btnAgregarFecha1, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
                    .addGap(20, 20, 20)))
        );
        containerAccionesLayout.setVerticalGroup(
            containerAccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(containerAccionesLayout.createSequentialGroup()
                .addGap(92, 92, 92)
                .addComponent(btnAgregarComentario, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
            .addGroup(containerAccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(containerAccionesLayout.createSequentialGroup()
                    .addGap(21, 21, 21)
                    .addComponent(btnAgregarFecha1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(105, Short.MAX_VALUE)))
        );

        mnuNota.setText("Nota");

        mnuItemNuevo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mnuItemNuevo.setText("Nuevo");
        mnuItemNuevo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        mnuNota.add(mnuItemNuevo);

        mnuItemGuardar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mnuItemGuardar.setText("Guardar");
        mnuNota.add(mnuItemGuardar);

        mnuItemEliminar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mnuItemEliminar.setText("Eliminar");
        mnuNota.add(mnuItemEliminar);

        mnuItemSalir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_DOWN_MASK));
        mnuItemSalir.setText("Salir");
        mnuNota.add(mnuItemSalir);

        MenuPrincipal.add(mnuNota);

        mnuConfiguracion.setText("Configuracion");

        mnuItemFuente.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mnuItemFuente.setText("Fuente");
        mnuConfiguracion.add(mnuItemFuente);

        mnuItemTema.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mnuItemTema.setText("Tema");
        mnuConfiguracion.add(mnuItemTema);

        mnuItemRutaPrincipal.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mnuItemRutaPrincipal.setText("Ruta Principal");
        mnuConfiguracion.add(mnuItemRutaPrincipal);

        MenuPrincipal.add(mnuConfiguracion);

        mnuAcerca.setText("Acerca");
        MenuPrincipal.add(mnuAcerca);

        setJMenuBar(MenuPrincipal);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(containerTree, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(containerTextNota, javax.swing.GroupLayout.PREFERRED_SIZE, 827, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(188, 188, 188)
                        .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTituloNota, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(containerAcciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(containerBotones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(containerTree, javax.swing.GroupLayout.DEFAULT_SIZE, 559, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                            .addComponent(txtTituloNota))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(containerTextNota)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(containerBotones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(containerAcciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)))))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public JMenuBar getMenuPrincipal() {
        return MenuPrincipal;
    }

    public JTree getTreeDirectorios() {
        return TreeDirectorios;
    }

    public JButton getBtnAgregarComentario() {
        return btnAgregarComentario;
    }

    public JButton getBtnAgregarFecha1() {
        return btnAgregarFecha1;
    }

    public JButton getBtnCerrarSinGuardar() {
        return btnCerrarSinGuardar;
    }

    public JButton getBtnGuardar() {
        return btnGuardar;
    }

    public JButton getBtnLimpiar() {
        return btnLimpiar;
    }

    public JPanel getContainerAcciones() {
        return containerAcciones;
    }

    public JPanel getContainerBotones() {
        return containerBotones;
    }

    public JScrollPane getContainerTextNota() {
        return containerTextNota;
    }

    public JScrollPane getContainerTree() {
        return containerTree;
    }

    public JLabel getLblTitulo() {
        return lblTitulo;
    }

    public JMenu getMnuAcerca() {
        return mnuAcerca;
    }

    public JMenu getMnuConfiguracion() {
        return mnuConfiguracion;
    }

    public JMenuItem getMnuItemEliminar() {
        return mnuItemEliminar;
    }

    public JMenuItem getMnuItemFuente() {
        return mnuItemFuente;
    }

    public JMenuItem getMnuItemGuardar() {
        return mnuItemGuardar;
    }

    public JMenuItem getMnuItemNuevo() {
        return mnuItemNuevo;
    }

    public JMenuItem getMnuItemSalir() {
        return mnuItemSalir;
    }

    public JMenuItem getMnuItemTema() {
        return mnuItemTema;
    }

    public JMenu getMnuNota() {
        return mnuNota;
    }

    public JEditorPane getTxtContenidoNota() {
        return txtContenidoNota;
    }

    public JTextField getTxtTituloNota() {
        return txtTituloNota;
    }

    public JMenuItem getMnuItemRutaPrincipal() {
        return mnuItemRutaPrincipal;
    }

    public JButton getBtnRestablecer() {
        return btnRestablecer;
    }
    
  
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar MenuPrincipal;
    private javax.swing.JTree TreeDirectorios;
    private javax.swing.JButton btnAgregarComentario;
    private javax.swing.JButton btnAgregarFecha1;
    private javax.swing.JButton btnCerrarSinGuardar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnRestablecer;
    private javax.swing.JPanel containerAcciones;
    private javax.swing.JPanel containerBotones;
    private javax.swing.JScrollPane containerTextNota;
    private javax.swing.JScrollPane containerTree;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JMenu mnuAcerca;
    private javax.swing.JMenu mnuConfiguracion;
    private javax.swing.JMenuItem mnuItemEliminar;
    private javax.swing.JMenuItem mnuItemFuente;
    private javax.swing.JMenuItem mnuItemGuardar;
    private javax.swing.JMenuItem mnuItemNuevo;
    private javax.swing.JMenuItem mnuItemRutaPrincipal;
    private javax.swing.JMenuItem mnuItemSalir;
    private javax.swing.JMenuItem mnuItemTema;
    private javax.swing.JMenu mnuNota;
    private javax.swing.JEditorPane txtContenidoNota;
    private javax.swing.JTextField txtTituloNota;
    // End of variables declaration//GEN-END:variables

    private void cambiarTema() {
        try {
            UIManager.setLookAndFeel(config.getTheme());
            SwingUtilities.updateComponentTreeUI(this);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException 
                | UnsupportedLookAndFeelException e) {
        }
    }
    @Override
    public void changeTheme() {
cambiarTema();
    }
}
