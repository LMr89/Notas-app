/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.strange.ovni.notasapp.view;

import com.google.common.io.Files;
import com.strange.ovni.notasapp.config.NotasAppConfig;
import com.strange.ovni.notasapp.controller.NameItemController;
import com.strange.ovni.notasapp.listeners.OnPassData;
import com.strange.ovni.notasapp.model.dao.NoteImpl;
import com.strange.ovni.notasapp.model.entities.CustomTreeNode;
import com.strange.ovni.notasapp.model.interfaces.ObjectObservable;
import com.strange.ovni.notasapp.model.interfaces.Observator;
import com.strange.ovni.notasapp.util.NoteLogRecord;
import com.strange.ovni.notasapp.util.RecognizeItem;
import com.strange.ovni.notasapp.util.ResizeImage;
import com.strange.ovni.notasapp.util.UrlImages;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author mrhazard
 */
public class PopupTreeMenu extends JPopupMenu implements ActionListener, OnPassData, ObjectObservable {

    private JMenuItem mnuItemNewNote;
    private JMenuItem mnuItemOpenNote;
    private JMenuItem mnuItemRenameNote;
    private JMenuItem mnuItemDeleteNote;
    private JMenuItem mnuItemNewDirectory;
    private JMenuItem mnuItemRenameDirectory;
    private JMenuItem mnuItemDeleteDirectory;

    private JMenuItem mnuImportFile;
    private JMenuItem mnuItemReadPdf;

    private JFileChooser directoryChooser;

    private NotasAppConfig config;

    //Pasar por referencia el textarea para poder modificarlo y el textField del titulo
    private JEditorPane txtContentNote;
    private JTextField txtTitle;

    private CustomTreeNode currentNodeSelected;
    private NameItemController nameItemDialogController;
    private NoteImpl notaActions;

    //Create variable del observador
    private Observator treeObservator;

    public PopupTreeMenu(NameItemController nameItemDialog) {
        this.nameItemDialogController = nameItemDialog;
        this.notaActions = new NoteImpl();
        this.directoryChooser = new JFileChooser();
        directoryChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        config = NotasAppConfig.getInstance();
        init();
    }

    public void setReferentComponents(JEditorPane txtContentNote, JTextField txtTitle) {
        this.txtContentNote = txtContentNote;
        this.txtTitle = txtTitle;
    }

    public void joinObservator(Observator o) {
        this.treeObservator = o;
    }

    private void init() {
        mnuItemNewNote = new JMenuItem("Nueva nota");
        mnuItemNewNote.setIcon(ResizeImage.resize(UrlImages.NEW_NOTE_ICON, 20, 20));
        add(this.mnuItemNewNote);

        mnuItemOpenNote = new JMenuItem("Abrir nota");
        mnuItemOpenNote.setIcon(ResizeImage.resize(UrlImages.READ_NOTE_ICON, 20, 20));
        add(this.mnuItemOpenNote);

        mnuItemRenameNote = new JMenuItem("Renombrar nota");
        mnuItemRenameNote.setIcon(ResizeImage.resize(UrlImages.RENAME_NOTE_ICON, 20, 20));
        add(this.mnuItemRenameNote);

        mnuItemDeleteNote = new JMenuItem("Eliminar nota");
        mnuItemDeleteNote.setIcon(ResizeImage.resize(UrlImages.DELETE_NOTE_ICON, 20, 20));
        add(this.mnuItemDeleteNote);

        add(new Separator());

        mnuItemNewDirectory = new JMenuItem("Nuevo folder");
        mnuItemNewDirectory.setIcon(ResizeImage.resize(UrlImages.NEW_DIRECTORY_ICON, 20, 20));
        add(this.mnuItemNewDirectory);

        mnuItemRenameDirectory = new JMenuItem("Renombrar folder");
        mnuItemRenameDirectory.setIcon(ResizeImage.resize(UrlImages.RENAME_DIRECTORY_ICON, 20, 20));
        add(this.mnuItemRenameDirectory);

        mnuItemDeleteDirectory = new JMenuItem("Eliminar Folder");
        mnuItemDeleteDirectory.setIcon(ResizeImage.resize(UrlImages.DELETE_DIRECTORY_ICON, 20, 20));
        add(this.mnuItemDeleteDirectory);

        add(new Separator());

        mnuItemReadPdf = new JMenuItem("Leer documento");
        mnuItemReadPdf.setIcon(ResizeImage.resize(UrlImages.SHOW_PDF_FILE, 20, 20));
        add(this.mnuItemReadPdf);

        add(new Separator());

        mnuImportFile = new JMenuItem("Traer Archivo");
        mnuImportFile.setIcon(ResizeImage.resize(UrlImages.IMPORT_FILE_ICON, 20, 20));
        add(this.mnuImportFile);

        //Set Listeners
        mnuItemNewNote.addActionListener(this);
        mnuItemRenameNote.addActionListener(this);
        mnuItemOpenNote.addActionListener(this);
        mnuItemDeleteNote.addActionListener(this);
        mnuItemNewDirectory.addActionListener(this);
        mnuItemRenameDirectory.addActionListener(this);
        mnuItemDeleteDirectory.addActionListener(this);
        mnuItemReadPdf.addActionListener(this);
        mnuImportFile.addActionListener(this);

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
                        sendNotification(); //mando la notificacion para recargar el arbol de directorios
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

                this.nameItemDialogController.showDialog();
                String userInput = this.nameItemDialogController.getUserInput();

                if (!userInput.isBlank()) {
                    String oldFile = currentNodeSelected.getAbsolutePath();
                    notaActions.setFullPath(oldFile);//Le mando a la clase nota la antigua ruta
                    String newFileToRename = new File(currentNodeSelected.getAbsolutePath()).getParent() + File.separator + userInput + ".txt";
                    System.out.println("Antiguo: " + oldFile);
                    try {
                        if (notaActions.renameNote(newFileToRename)) {
                            Notifications.showInformation("Nota renombrada con exito");
                            sendNotification(); //mando la notificacion para recargar el arbol de directorios
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
                    this.nameItemDialogController.showDialog();
                    String userInput = this.nameItemDialogController.getUserInput();
                    String newFile = currentNodeSelected.getAbsolutePath() + File.separator + userInput + ".txt";
                    new File(newFile).createNewFile();
                    sendNotification(); //mando la notificacion para recargar el arbol de directorios
                    sendPathToOtherView(newFile); //Envio la ruta creada para que sea operada en otra vista

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

                sendPathToOtherView(currentNodeSelected.getAbsolutePath()); //Envio la ruta creada para que sea operada en otra vista
                //Set the note title
                printNoteTitle(this.notaActions.getTitle());

                //Se escribe temporalmente el contenido de esta nota en el archivo LOG
                //para luego ser obtenida en caso el usuario quiera no guardar lo escrito
                NoteLogRecord.writeLogContent(this.notaActions.readNote());

            }

        } catch (NullPointerException ex) {
            Notifications.showWarning("Porfavor selecciona un item para poder crear una nota o directorio");
        }
    }

    private void createNewFolder() {
        try {
            //Primero se verifica si el traspaso de datos no sea nulo por que si lo es habra errores
            if (currentNodeSelected.getAbsolutePath() != null) {

                //Se verifica que lo que haya escogido el usuario sera un directorio y no un archivo
                if (RecognizeItem.isPathAFile(currentNodeSelected.getAbsolutePath())) {
                    Notifications.showWarning("Porfavor seleccionar un folder");
                } else {
                    this.nameItemDialogController.showDialog();
                    String userInputForNewDirectory = this.nameItemDialogController.getUserInput();
                    String newFile = currentNodeSelected.getAbsolutePath() + File.separator + userInputForNewDirectory;
                    new File(newFile).mkdir();
                    sendNotification(); //mando la notificacion para recargar el arbol de directorios
                    sendPathToOtherView(newFile); //Envio la ruta creada para que sea operada en otra vista
                }

            }

        } catch (NullPointerException ex) {
            Notifications.showWarning("Porfavor selecciona un item para poder crear una nota o directorio");
        }

    }

    private void renameDirectory() {

        try {
            //Primero se verifica si el traspaso de datos no sea nulo por que si lo es habra errores
            if (currentNodeSelected.getAbsolutePath() != null) {
                //Se verifica que lo que haya escogido el usuario sera un directorio y no un archivo
                if (RecognizeItem.isPathAFile(currentNodeSelected.getAbsolutePath())) {
                    Notifications.showWarning("Porfavor seleccionar un folder para poder renombrarlo");
                } else {
                    this.nameItemDialogController.showDialog();
                    String userInput = this.nameItemDialogController.getUserInput();

                    if (!userInput.isBlank()) {
                        String oldDirectoryName = currentNodeSelected.getAbsolutePath();

                        String newDirectoryName = new File(currentNodeSelected.getAbsolutePath()).getParent() + File.separator + userInput;

                        try {
                            FileUtils.moveDirectory(new File(oldDirectoryName), new File(newDirectoryName));
                            //Files.move(new File(oldDirectoryName), newDirectoryName);
                            //new File(oldDirectoryName).renameTo(newDirectoryName);
                            Notifications.showInformation("Folder  renombrado con exito");
                            sendNotification(); //mando la notificacion para recargar el arbol de directorios

                        } catch (Exception ex) {
                            Logger.getLogger(PopupTreeMenu.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                }

            }

        } catch (NullPointerException ex) {
            Notifications.showWarning("Porfavor selecciona un item para poder crear una nota o directorio");
        }
    }

    private void deleteDirectory() {
        try {
            //Primero se verifica si el traspaso de datos no sea nulo por que si lo es habra errores
            if (currentNodeSelected.getAbsolutePath() != null) {
                if (RecognizeItem.isPathAFile(currentNodeSelected.getAbsolutePath())) {
                    Notifications.showWarning("Porfavor seleccionar un folder para poder eliminarlo");
                } else {

                    int confirmeMessage = Notifications.askForSomething("¿Deseas eliminar este folder?");
                    if (confirmeMessage == JOptionPane.YES_OPTION) {
                        //Se usa la libreria de apache commons para eliminar el directorio
                        FileUtils.deleteDirectory(new File(currentNodeSelected.getAbsolutePath()));
                        //Notifications.showInformation("Folder eliminado con exito");
                        sendNotification(); //mando la notificacion para recargar el arbol de directorios

                    }

                }
            }

        } catch (NullPointerException ex) {
            Notifications.showWarning("Porfavor selecciona un item para poder crear una nota o directorio");
        } catch (IOException ex) {
            Logger.getLogger(PopupTreeMenu.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    void printContentIntoEditor(String msg
    ) {
        this.txtContentNote.setText("");
        this.txtContentNote.setText(msg);

    }

    void printNoteTitle(String title
    ) {
        this.txtTitle.setText("");
        this.txtTitle.setText(title);
    }

    void sendPathToOtherView(String path
    ) {
        this.treeObservator.transferPath(path);
    }

    @Override
    public void actionPerformed(ActionEvent e
    ) {

        if (e.getSource() == this.mnuItemNewNote) {
            createNewNote();
        }
        if (e.getSource() == this.mnuItemOpenNote) {
            readNote();
        }
        if (e.getSource() == this.mnuItemRenameNote) {
            renameNote();
        }
        if (e.getSource() == this.mnuItemDeleteNote) {
            deleteNote();
        }

        /*Listener para los botone de trabajo con folders*/
        if (e.getSource() == this.mnuItemNewDirectory) {
            createNewFolder();

        }

        if (e.getSource() == this.mnuItemRenameDirectory) {
            renameDirectory();
        }

        if (e.getSource() == this.mnuItemDeleteDirectory) {
            deleteDirectory();

        }

        /*Listener para los botone importar y ver pdf*/
        if (e.getSource() == this.mnuImportFile) {
            importFileToMainDirectory();

        }

        if (e.getSource() == this.mnuItemReadPdf) {
            showPdfInNavigator();
        }

    }

    //Al implementar este metodo callback lo que hara es transferirme lo seleccionado en el tree model 
    //Para poder operarlo en esta clase 
    @Override
    public void transferData(Object component
    ) {
        currentNodeSelected = (CustomTreeNode) component;
    }

    @Override
    public void sendNotification() {
        this.treeObservator.updateState();

    }

    private void importFileToMainDirectory() {

        try {
            //Primero se verifica si el traspaso de datos no sea nulo por que si lo es habra errores
            if (currentNodeSelected.getAbsolutePath() != null) {

                //Se verifica que lo que haya escogido el usuario sera un directorio y no un archivo
                if (RecognizeItem.isPathAFile(currentNodeSelected.getAbsolutePath())) {
                    Notifications.showWarning("Porfavor seleccionar un folder");
                } else {

                    int option = directoryChooser.showOpenDialog(this);

                    if (option == JFileChooser.APPROVE_OPTION) {
                        File fileToTransfer = directoryChooser.getSelectedFile();
                        String selectedDirectory = currentNodeSelected.getAbsolutePath() + File.separator + fileToTransfer.getName();

                        FileUtils.moveFile(fileToTransfer, new File(selectedDirectory));
                        sendNotification(); //mando la notificacion para recargar el arbol de directorios
                    }
                }

            }

        } catch (NullPointerException ex) {
            Notifications.showWarning("Porfavor selecciona un folder para poder importar un archivo");
        } catch (IOException ex) {
            Logger.getLogger(PopupTreeMenu.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    

    private void showPdfInNavigator() {
        try {
            //Primero se verifica si el traspaso de datos no sea nulo por que si lo es habra errores
            if (currentNodeSelected.getAbsolutePath() != null) {

                //Se verifica que lo que haya escogido el usuario sera un directorio y no un archivo
                if (!Files.getFileExtension(currentNodeSelected.getFileName()).equals("pdf")) {
                    Notifications.showWarning("Porfavor un documento pdf para visualizarlo");
                } else {
                    //InputStream stream = new FileInputStream(new File(currentNodeSelected.getAbsolutePath()));
                    Desktop.getDesktop().open(new File(currentNodeSelected.getAbsolutePath()));
                    //JasperViewer viewe = new JasperViewer(stream, false);
                    //viewe.setLocationRelativeTo(null);
                    //viewe.setVisible(true);
                    //Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + new File(currentNodeSelected.getAbsolutePath()));
                }

            }

        } catch (NullPointerException ex) {
            Notifications.showWarning("Porfavor selecciona un folder para poder importar un archivo");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PopupTreeMenu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PopupTreeMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
