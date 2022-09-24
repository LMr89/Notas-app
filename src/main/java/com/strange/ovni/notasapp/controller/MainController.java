package com.strange.ovni.notasapp.controller;

import com.strange.ovni.notasapp.config.NotasAppConfig;
import com.strange.ovni.notasapp.listeners.OnPassData;
import com.strange.ovni.notasapp.listeners.TreeActionListener;
import com.strange.ovni.notasapp.model.dao.NoteImpl;
import com.strange.ovni.notasapp.model.defaultModel.CustomTreeModel;
import com.strange.ovni.notasapp.model.entities.CustomTreeNode;
import com.strange.ovni.notasapp.model.interfaces.Observator;
import com.strange.ovni.notasapp.model.render.RenderTreeDirectorios;
import com.strange.ovni.notasapp.util.NoteLogRecord;
import com.strange.ovni.notasapp.util.PoolThread;
import com.strange.ovni.notasapp.util.UrlImages;
import com.strange.ovni.notasapp.view.FrmPrincipal;
import com.strange.ovni.notasapp.view.LoadingDialog;
import com.strange.ovni.notasapp.view.NameItemDialog;
import com.strange.ovni.notasapp.view.Notifications;
import com.strange.ovni.notasapp.view.PopupTreeMenu;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import org.drjekyll.fontchooser.FontDialog;

/**
 *
 * @author MrRob0t
 */
public final class MainController implements ActionListener, Observator, OnPassData {

    private FrmPrincipal mainView;
    private LoadingDialog loadDialog;
    private NameItemDialog nameItemDialog;
    private FontDialog fontDialog; //Dialogo para escoger la fuente
    private CustomTreeModel modelTree = new CustomTreeModel();

    private DirectoryDialogController dialogController;
    private NameItemController nameItemController;

    //PopupMenu para pasarlo como referencia al TreeActionListener
    private PopupTreeMenu popMenu;
    private ExecutorService executorThreads;
    private TreeActionListener treeActionListener;

    //Clase nota para operar
    private final NoteImpl notaActions;

    //Clase encargada de escribir los cambios del usuario en el archivo properties
    private NotasAppConfig appConfig;

    //Este string almacenara la ruta enviada desde el popup menu hasta aqui
    //Ambas vistas compartirar este dato
    String pathTransfered;

    private Future<Object> resultAsyncTask;
    private CustomTreeNode currentNodeSelected;
 

    public MainController(FrmPrincipal mainView) {
        this.mainView = mainView;
        chargeIconsIntoButtons();

        //Clases de una sola instancia patron SINGLETON
        executorThreads = PoolThread.getInstance();
        loadDialog = LoadingDialog.getInstance();
        nameItemDialog = NameItemDialog.getInstance(mainView, true);
        appConfig = NotasAppConfig.getInstance();
        fontDialog = new FontDialog(this.mainView, NotasAppConfig.APP_NAME, true);

        this.notaActions = new NoteImpl();

        //inicializo la variable pathTransfered
        pathTransfered = "";
    }

    public void startApp() {
        //Cargar la fuente desde el archivo properties
        loadFontFromProperties();

        //Se desactiva la caja de texto del titulo
        this.mainView.getTxtTituloNota().setEnabled(false);
        //Setting main
        this.mainView.setLocationRelativeTo(null);
        this.mainView.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.mainView.setVisible(true);

        //Setting directory and NameItem dialog
        this.dialogController = new DirectoryDialogController(this.mainView);
        this.nameItemController = new NameItemController(this.nameItemDialog);

        //Add listeners 
        this.mainView.getBtnAgregarComentario().addActionListener(this);
        this.mainView.getBtnAgregarFecha1().addActionListener(this);
        this.mainView.getBtnCerrarSinGuardar().addActionListener(this);
        this.mainView.getBtnGuardar().addActionListener(this);
        this.mainView.getBtnLimpiar().addActionListener(this);
        this.mainView.getBtnRestablecer().addActionListener(this);

        //Add listener to menu items
        this.mainView.getMnuItemRutaPrincipal().addActionListener(this);
        this.mainView.getMnuItemGuardar().addActionListener(this);
        this.mainView.getMnuItemNuevo().addActionListener(this);
         this.mainView.getMnuItemEliminar().addActionListener(this);
        this.mainView.getMnuItemSalir().addActionListener(this);
        this.mainView.getMnuItemFuente().addActionListener(this);
        this.mainView.getMnuItemTema().addActionListener(this);

        //Cargar Árbol de manera asincrona
        resultAsyncTask = executorThreads.submit(AsyncTask);

        //Setting up popmenu and pass some components by reference
        this.popMenu = new PopupTreeMenu(this.nameItemController);
        this.popMenu.setReferentComponents(this.mainView.getTxtContenidoNota(), this.mainView.getTxtTituloNota());
        this.popMenu.joinObservator(this);

        treeActionListener = new TreeActionListener(this.mainView.getTreeDirectorios(), this.popMenu);
        treeActionListener.addMainControllerListenerOnPassData(this);

    }

    //Método encargado de inicializar e instanciar los objetos necesarios para llenar el arbol
    void initDirectoriesTree() {
        //Add defaulttreemodel to JTREE
        loadDialog.setVisible(true);
        //this.mainView.getTreeDirectorios().setModel(null);

        //modelTree.setMainRootDirectory(new File("/home/mrhazard/Escritorio/Projects_Java"));
        modelTree.reCreateModelTree();
        this.mainView.getTreeDirectorios().setModel(modelTree.getModel());
        this.mainView.getTreeDirectorios().setCellRenderer(new RenderTreeDirectorios());
        loadDialog.setVisible(false);

    }

    void chargeIconsIntoButtons() {
        this.mainView.getBtnGuardar().setIcon(new ImageIcon(UrlImages.SAVE_ICON));
        this.mainView.getBtnLimpiar().setIcon(new ImageIcon(UrlImages.DELETE_CONTENT_ICON));
        this.mainView.getBtnCerrarSinGuardar().setIcon(new ImageIcon(UrlImages.CLOSE_NOTE_ICON));
        this.mainView.getBtnAgregarComentario().setIcon(new ImageIcon(UrlImages.ADD_COMMENT_ICON));
        this.mainView.getBtnAgregarFecha1().setIcon(new ImageIcon(UrlImages.ADD_DATE_ICON));
        this.mainView.getBtnRestablecer().setIcon(new ImageIcon(UrlImages.UNDO_NOTE_ICON));
    }

    void closeNotasApp() {
        System.exit(0);
    }

    void cleanFields() {
        this.mainView.getTxtTituloNota().setText("");
        this.mainView.getTxtContenidoNota().setText("");
    }

    private void addComment() {
        this.mainView.getTxtContenidoNota().setText(this.mainView.getTxtContenidoNota().getText()
                + "\n" + ("---------------------").repeat(3) + "<              >" + ("---------------------").repeat(3) + "\n");
    }

    private void addDate() {
        String date = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date());

        this.mainView.getTxtContenidoNota().setText(this.mainView.getTxtContenidoNota().getText()
                + "\n" + ("--------------------------").repeat(3) + "<" + date + ">" + ("----------------------------").repeat(3) + "\n");
    }

    private void writeUserInputIntoNote() {
        //Verifico que el usuario haya abierto o creado una nota
        if (!pathTransfered.isEmpty()) {
            String userInput = this.mainView.getTxtContenidoNota().getText();
            notaActions.writeNote(userInput);
            cleanFields();
            Notifications.showInformation("Nota guardad con exito");
            NoteLogRecord.cleanLog();//LIMPIAR EL LOG PARA QUE NO HAY HUELLAS XD
        }

    }

    private void closeWithuoutSave() {
        String restoredContent = NoteLogRecord.readLogContent();
        if (!restoredContent.isEmpty() && !pathTransfered.isBlank()) {
            notaActions.writeNote(restoredContent);
            cleanFields();
            Notifications.showInformation("Nota restaurada con exito");
            NoteLogRecord.cleanLog();//LIMPIAR EL LOG PARA QUE NO HAY HUELLAS XD

        }
    }

    void printInformation(String msg) {
        this.mainView.getTxtContenidoNota().setText(msg);
    }

    private void restoreContentFromLogFile() {
        String contentRestored = NoteLogRecord.readLogContent();
        if (contentRestored.isEmpty()) {
            Notifications.showWarning("No hay contenido para restaurar");

        } else {
            printInformation(contentRestored);
        }
        NoteLogRecord.cleanLog();//LIMPIAR EL LOG PARA QUE NO HAY HUELLAS XD
    }

    void changeOAllComponentsFont(Font newFont) {
        //Guardamos la configuracion del usuario en el archvio properties
        appConfig.setFontUser(newFont);
        appConfig.saveAllChanges();
        //Se aplica los cambios ya en el programa
        this.mainView.getLblTitulo().setFont(newFont);
        this.mainView.getTreeDirectorios().setFont(newFont);
        this.mainView.getMnuAcerca().setFont(newFont);
        this.mainView.getMnuConfiguracion().setFont(newFont);
        this.mainView.getMnuItemEliminar().setFont(newFont);
        this.mainView.getMnuItemFuente().setFont(newFont);
        this.mainView.getMnuItemGuardar().setFont(newFont);
        this.mainView.getMnuItemNuevo().setFont(newFont);
        this.mainView.getMnuItemRutaPrincipal().setFont(newFont);
        this.mainView.getMnuItemSalir().setFont(newFont);
        this.mainView.getMnuItemTema().setFont(newFont);
        this.mainView.getMnuNota().setFont(newFont);

    }

    void loadFontFromProperties() {
        if (appConfig.getFontUser() != null) {
            changeOAllComponentsFont(appConfig.getFontUser());
        }

    }

    void saveFontIntoProperties(Font newFont) {
        changeOAllComponentsFont(newFont);

    }

    void deleteNote() {
        try {
            //Primero se verifica si el traspaso de datos no sea nulo por que si lo es habra errores
            if (currentNodeSelected.getAbsolutePath() != null) {

                notaActions.setFullPath(currentNodeSelected.getAbsolutePath());//Le mando a la clase nota para poder eliminarla

                int confirmeMessage = Notifications.askForSomething("¿Deseas eliminar esta nota?");
                if (confirmeMessage == JOptionPane.YES_OPTION) {
                    if (notaActions.deleteNote()) {
                        Notifications.showInformation("Nota eliminada con exito");
                    }
                }

            }

        } catch (NullPointerException ex) {
            Notifications.showWarning("Porfavor selecciona un item para poder crear una nota o directorio");
        }
    }

    void renameNote() {
        try {
            //Primero se verifica si el traspaso de datos no sea nulo por que si lo es habra errores
            if (currentNodeSelected.getAbsolutePath() != null) {

                this.nameItemController.showDialog();
                String userInput = this.nameItemController.getUserInput();

                if (!userInput.isBlank()) {
                    String oldFile = currentNodeSelected.getAbsolutePath();
                    notaActions.setFullPath(oldFile);//Le mando a la clase nota la antigua ruta
                    String newFileToRename = new File(currentNodeSelected.getAbsolutePath()).getParent() + File.separator + userInput + ".txt";
                    System.out.println("Antiguo: " + oldFile);
                    try {
                        if (notaActions.renameNote(newFileToRename)) {
                            Notifications.showInformation("Nota renombrada con exito");
                        }
                    } catch (Exception ex) {
                        Logger.getLogger(PopupTreeMenu.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }

            }

        } catch (NullPointerException ex) {
            Notifications.showWarning("Porfavor selecciona un item para poder crear una nota o directorio");
        }
    }

    void createNewNote() {
        try {
            //Primero se verifica si el traspaso de datos no sea nulo por que si lo es habra errores
            if (currentNodeSelected.getAbsolutePath() != null) {

                try {
                    this.nameItemController.showDialog();
                    String userInput = this.nameItemController.getUserInput();
                    String newFile = currentNodeSelected.getAbsolutePath() + File.separator + userInput + ".txt";
                    new File(newFile).createNewFile();

                } catch (IOException ex) {
                    Logger.getLogger(PopupTreeMenu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } catch (NullPointerException ex) {
            Notifications.showWarning("Porfavor selecciona un item para poder crear una nota o directorio");
        }
    }

    void readNote() {
        try {
            //Primero se verifica si el traspaso de datos no sea nulo por que si lo es habra errores
            if (currentNodeSelected.getAbsolutePath() != null) {

                this.notaActions.setFullPath(currentNodeSelected.getAbsolutePath());
                printContentIntoEditor(this.notaActions.readNote());

                printNoteTitle(this.notaActions.getTitle());

                //Se escribe temporalmente el contenido de esta nota en el archivo LOG
                //para luego ser obtenida en caso el usuario quiera no guardar lo escrito
                NoteLogRecord.writeLogContent(this.notaActions.readNote());

            }

        } catch (NullPointerException ex) {
            Notifications.showWarning("Porfavor selecciona un item para poder crear una nota o directorio");
        }
    }

    void printContentIntoEditor(String msg
    ) {
        this.mainView.getTxtContenidoNota().setText("");
        this.mainView.getTxtContenidoNota().setText(msg);

    }

    void printNoteTitle(String title
    ) {
        this.mainView.getTxtTituloNota().setText("");
        this.mainView.getTxtTituloNota().setText(title);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Listener de los botones principales
        if (e.getSource() == this.mainView.getBtnGuardar()) {
            writeUserInputIntoNote();

        }
        if (e.getSource() == this.mainView.getBtnLimpiar()) {
            cleanFields();

        }
        if (e.getSource() == this.mainView.getBtnCerrarSinGuardar()) {
            closeWithuoutSave();

        }
        if (e.getSource() == this.mainView.getBtnAgregarFecha1()) {
            addDate();

        }
        if (e.getSource() == this.mainView.getBtnAgregarComentario()) {
            addComment();

        }
        if (e.getSource() == this.mainView.getBtnRestablecer()) {
            restoreContentFromLogFile();

        }

        //Listener de eventos para los menuItem
        if (e.getSource() == this.mainView.getMnuItemRutaPrincipal()) {
            this.dialogController.showDialog();
            modelTree.reCreateModelTree();
            initDirectoriesTree();

            //JOptionPane.showMessageDialog(this.mainView, "Menu item", "PropertiesFIle", JOptionPane.INFORMATION_MESSAGE);
        }

        if (e.getSource() == this.mainView.getMnuItemFuente()) {
            fontDialog.setLocationRelativeTo(null);
            fontDialog.setVisible(true);

            if (!fontDialog.isCancelSelected()) {
                //System.out.println(fontDialog.getSelectedFont());
                saveFontIntoProperties(fontDialog.getSelectedFont());
            }

        }
        
         if (e.getSource() == this.mainView.getMnuItemNuevo()) {
             createNewNote();
        }
          if (e.getSource() == this.mainView.getMnuItemGuardar()) {
              writeUserInputIntoNote();
        }
           if (e.getSource() == this.mainView.getMnuItemEliminar()) {
               deleteNote();
        }
        
        if (e.getSource() == this.mainView.getMnuItemSalir()) {
            closeNotasApp();

        }
    }

    //Formo la llamada asincrona con callableStatement
    Callable<Object> AsyncTask = () -> {

        initDirectoriesTree();

        return null;

    };

    @Override
    public void updateState() {
        //Aplicando el patron observable para recargar el tree de directorios para  cuando haya cambios
        //Cargar Árbol de manera asincrona

        try {
            AsyncTask.call();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //Cargar Árbol de manera asincrona
        //executorThreads.invokeAll(new HashSet<Callable<Object>>().add(AsyncTask));
        //System.out.println("Se agrego un fichero");

    }

    @Override
    public void transferPath(String path) {
        //Obtengo la ruta y la guardo dentro de la propiedad de la clase nota
        notaActions.setFullPath(path);

        pathTransfered = path;

    }

    @Override
    public void transferData(Object component) {
        currentNodeSelected = (CustomTreeNode) component;
    }

}
