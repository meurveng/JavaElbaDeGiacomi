/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import Model.Apartment;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author meurveng
 */
public class Main extends Application {
    private Stage principalStage;
    private Stage optionsStage;
    private Stage informationStage;
    private Stage addModifyStage;
    private Stage loginStage;
    private PrincipalController principalController;
    private OptionsController optionsController;
    private InformationController informationController;
    private AddModifyController addModifyController;
    
    @Override
    public void start(Stage stage) throws Exception {
        principalStage=stage;
        openPrincipalController();
    }
    
    public void openPrincipalController() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("FXMLDocument.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            principalStage.setMaximized(true);
            principalStage.setTitle("Inmobiliaria Elba De Giacomi");
            principalStage.setScene(scene);
            principalController = loader.getController();
            principalController.setController(this);
            principalStage.show();
        } catch (IOException e) {
            //tratar la excepción.
        }
   }
    public void openOptions(String server, String dataBase, String user, String password, String port) {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("FXMLOptions.fxml"));
            Parent root = loader.load();
            optionsStage = new Stage();
            optionsStage.setResizable(false);
            optionsStage.setTitle("Preferencias");
            optionsStage.initOwner(principalStage);
            optionsStage.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(root);
            optionsStage.setScene(scene);
            optionsController = loader.getController();
            optionsController.setStage(optionsStage);
            optionsController.openOptions(server, dataBase, user, password, port);
            optionsStage.show();
        } catch (Exception e) {
            //tratar la excepción
        }
    }
    
    public void openAddModify(Apartment apartment) {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("FXMLCrear.fxml"));
            Parent root = loader.load();
            addModifyStage = new Stage();
            addModifyStage.setTitle("Crear/Modificar");
            addModifyStage.setMaximized(true);
            addModifyStage.initOwner(principalStage);
            addModifyStage.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(root);
            addModifyStage.setScene(scene);
            addModifyController = loader.getController();
            addModifyController.setStage(addModifyStage);
            addModifyController.openAddModify(apartment);
            addModifyStage.setOnCloseRequest((WindowEvent e) -> {
                addModifyController.cerrar();
                addModifyStage=null;
                System.gc();
            });
            addModifyStage.show();
        } catch (Exception e) {
            //tratar la excepción
        }
    }
    
    public void openInformation(Apartment apartment) {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("FXMLInformation.fxml"));
            Parent root = loader.load();
            informationStage = new Stage();
            informationStage.setMaximized(true);
            informationStage.setTitle(apartment.getNombre());
            Scene scene = new Scene(root);
            informationStage.setScene(scene);
            informationController = loader.getController();
            informationController.openInformation(apartment);
            informationStage.setOnCloseRequest((WindowEvent e) -> {
                informationController.close();
                informationStage=null;
                System.gc();
            });
            informationStage.show();
        } catch (Exception e) {
            //tratar la excepción
        }
    }
    
    public void openLogin() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("FXMLLogin.fxml"));
            Parent root = loader.load();
            loginStage = new Stage();
            loginStage.setTitle("Iniciar sesión");
            Scene scene = new Scene(root);
            loginStage.setScene(scene);
            loginStage.setResizable(false);
            loginStage.initOwner(principalStage);
            loginStage.show();
        } catch (Exception e) {
            //tratar la excepción
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
