/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import Model.*;
import java.util.Optional;
////import Controller.*;
import static javafx.collections.FXCollections.observableArrayList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.Pagination;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.stage.StageStyle;

/**
 *
 * @author meurveng
 */
public class PrincipalController implements Initializable {
    
    @FXML private Pagination pagination;
    @FXML private BorderPane mainScene;
    @FXML private Menu crear, preferencias, iniciarSesion;
    //@FXML private BorderPane cargando;
    @FXML public static ProgressBar progressBar;
    private ScrollPane scrollPane=new ScrollPane();
    @FXML private ComboBox poblacion, order, numeroViviendas, tipoCasa;
    @FXML private Slider camas;
    @FXML private TextField buscar;
    @FXML private Label camasLabel, habitacionesLabel;
    @FXML public static Label progressBarLabel;
    @FXML private CheckBox alquiler, venta, parrilla, jovenes, alquilerTemporario, garaje, exterior, alarma, desc;
    private boolean filtroAlquiler, filtroDesc, filtroAlquilerTemporario, filtroVenta, filtroGaraje, filtroParrilla, filtroExterior, filtroJovenes, filtroAlarma;
    private String filtroPoblacion, filtroOrden, filtroBuscar, filtroTipoCasa;
    private int filtroCamas;
    private Button ver, modificar, eliminar;
    public static int maximBar;
    public static int actualBar;
    //Controller
////    private InformationController informationController=new InformationController();
////    private AddModifyController addModifyController=new AddModifyController();
////    private OptionsController optionsController=new OptionsController();
////    private PhotoController photoController=new PhotoController();

    //Model
    public static DataBase db;
    private XML xml=new XML();
    private Refresh refresh=new Refresh();

    
        private Main controller;
    //View
////        public Principal view;

    //Own
    private String[] serverData;
    private int minim=0, maxim=10, totalApartments=0;
    
    public Node generateApartment(String id, String titulo, String poblacion, String direccion, String codigo, String camas, String baños, String venta, String alquiler, String permuta, String imagen){
        BorderPaneApartment border=new BorderPaneApartment();
        border.setManaged(true);
        border.setPrefWidth(Control.USE_COMPUTED_SIZE);
        GridPane gridPaneApartment=new GridPane();
        gridPaneApartment.setVgap(10);
        border.setId("apartment");
        border.setCode(id);
        //border.setPrefWidth(1920);
        gridPaneApartment.setMaxWidth(Control.USE_COMPUTED_SIZE);
        gridPaneApartment.setPrefWidth(Control.USE_COMPUTED_SIZE);
        GridPane gridPaneButton=new GridPane();
        border.setRight(gridPaneButton);
        gridPaneApartment.setId("grid");
        BorderPane[] center=new BorderPane[2];
        center[0]=new BorderPane();
        center[1]=new BorderPane();
        try {
            if(titulo.equals("No se han encontrado viviendas con estas características")){
                Label[] labelArray=new Label[9];
                labelArray[0]=new Label(titulo);
                labelArray[0].setId("titulo");
                center[0].setCenter(labelArray[0]);
                border.setCenter(center[0]);
                BorderPane borderImage=new BorderPane();
                borderImage.setId("imagen");
                ImageView imageView=new ImageView(new Image(new FileInputStream(imagen)));
                borderImage.setCenter(imageView);
                imageView.setFitWidth(150);
                imageView.setFitHeight(150);
                border.setLeft(borderImage);
            }else{
                Label[] labelArray=new Label[9];
                labelArray[0]=new Label(titulo);
                labelArray[1]=new Label("Lugar:");
                labelArray[2]=new Label("Dirección: ");
                labelArray[3]=new Label("Codigo inmo/Argen: ");
                labelArray[4]=new Label("Baños:");
                labelArray[5]=new Label("Camas: ");
                labelArray[6]=new Label("Permuta:");
                labelArray[7]=new Label("Alquiler: ");
                labelArray[8]=new Label("Venta:");
                labelArray[0].setId("titulo");
                for (int i = 1; i < labelArray.length; i++) {
                    labelArray[i].setId("labelDescripcion");
                }
                center[0].setCenter(labelArray[0]);
                border.setTop(center[0]);
                BorderPane borderImage=new BorderPane();
                borderImage.setId("imagen");
                ImageView imageView=new ImageView(new Image(new FileInputStream(imagen)));
                borderImage.setCenter(imageView);
                imageView.setFitWidth(150);
                imageView.setFitHeight(150);
                border.setLeft(borderImage);
                gridPaneApartment.add(labelArray[1], 0, 1);
                gridPaneApartment.add(new Label(poblacion), 1, 1);
                gridPaneApartment.add(labelArray[2], 2, 1);
                gridPaneApartment.add(new Label(direccion), 3, 1);

                gridPaneApartment.add(labelArray[3], 0, 2);
                gridPaneApartment.add(new Label(codigo), 1, 2);
                gridPaneApartment.add(labelArray[4], 2, 2);
                gridPaneApartment.add(new Label(baños), 3, 2);

                gridPaneApartment.add(labelArray[5], 0, 3);
                gridPaneApartment.add(new Label(camas), 1, 3);
                gridPaneApartment.add(labelArray[6], 2, 3);

                if(permuta.equals("")){
                    gridPaneApartment.add(new Label("No"), 3, 3);
                }else{
                    gridPaneApartment.add(new Label("Si"), 3, 3);
                }

                gridPaneApartment.add(labelArray[7], 0, 4);

                if(alquiler.equals("0")){
                    gridPaneApartment.add(new Label("No"), 1, 4);
                }else{
                    gridPaneApartment.add(new Label("Si"), 1, 4);
                }

                gridPaneApartment.add(labelArray[8], 2, 4);

                if(venta.equals("0")){
                    gridPaneApartment.add(new Label("No"), 3, 4);
                }else{
                    gridPaneApartment.add(new Label("Si"), 3, 4);
                }
                GridPane gridBotones=new GridPane();
                //gridBotones.setId("borderPadding");
                Button ver=new Button("Ver");
                Button modificar=new Button("Modificar");
                Button borrar=new Button("Borrar");
                ver.setOnMouseClicked((MouseEvent event) -> {
                    openInformation(((BorderPaneApartment)ver.getParent().getParent().getParent()).getCode());
                });
                modificar.setOnMouseClicked((MouseEvent event) -> {
                    openAddModify(((BorderPaneApartment)modificar.getParent().getParent().getParent()).getCode());
                });
                borrar.setOnMouseClicked((MouseEvent event) -> {
                    deleteItem(((BorderPaneApartment)borrar.getParent().getParent().getParent()).getCode());
                });
                gridBotones.setMaxWidth(158);
                gridBotones.add(ver, 0, 0);
                gridBotones.add(modificar, 1, 0);
                gridBotones.add(borrar, 2, 0);
                center[1].setCenter(gridBotones);
                //gridPaneApartment.add(borderImages, 0, 5, 2, 1);
                border.setBottom(center[1]);
                border.setCenter(gridPaneApartment);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }   
        return border;
    }
    
    public void setController(Main controller){
            this.controller=controller;
        }
    
    public static void setValueProgressBar(){
        progressBar.setProgress((actualBar*100)/maximBar);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        desc.setSelected(true);
        ver=new Button("Ver");
        modificar=new Button("Modificar");
        eliminar=new Button("Eliminar");
       
        Label crearLabel = new Label("Crear");
        crearLabel.setOnMouseClicked((MouseEvent event) -> {
            openAddModify();
        });
        crear.setGraphic(crearLabel);
        
        Label preferenciasLabel = new Label("Preferencias");
        preferenciasLabel.setOnMouseClicked((MouseEvent event) -> {
            openOptions();
        });
        preferencias.setGraphic(preferenciasLabel);
        
        /*Label iniciarSesionLabel = new Label("Iniciar sesión");
        iniciarSesionLabel.setOnMouseClicked((MouseEvent event) -> {
            openAddModify();
        });
        iniciarSesion.setGraphic(crearLabel);*/
        
        order.setItems(observableArrayList("Creación", "Nombre", "Lugar", "Camas"));
        order.setValue("Creación");
        
        numeroViviendas.setItems(observableArrayList("5", "10", "15", "20"));
        numeroViviendas.setValue("10");
        
        boolean correct=true;
        camas.valueProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
            if(new_val.intValue()==0){
                camasLabel.setText("Todos");
            }else{
                camasLabel.setText(String.valueOf(new_val.intValue()));
            }
        });
        serverData=xml.readXml();
        for (String serverData1 : serverData) {
            if (serverData1.equals("")) {
                correct=false;
            }
        }
        if(!correct){
            controller.openOptions("", "", "", "", "");
        }else{
            if(db==null) db=new DataBase(serverData[0], serverData[1], serverData[2], serverData[3], serverData[4]);
        }

        correct=true;

        serverData=xml.readXml();
        for (String serverData1 : serverData) {
            if (serverData1.equals("")) {
                correct=false;
            }
        }

        if(!correct){
            Alert alert=new Alert(AlertType.ERROR);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Error");
            alert.setHeaderText("Conexión a la base de datos fallida");
            alert.setContentText("Los datos de conexión a la base de datos son incorrectos");
            alert.showAndWait();
            System.exit(0);
        }else{
            this.generatePoblacion();
            this.filterApartments();
            //refresh.run(300);
            this.generateAll();
        }
        
    }    


    /**Open a information window with the data of the apartment 
     * 
     * @param number Code of the apartment
     */
    public void openInformation(String number){
        controller.openInformation(db.getApartmentNumber(number));
        this.generateAll();
    }

    /**Open a new window to set the configuration of the program 
     * 
     */
    public void openOptions(){
        try {
            this.disable(true);
            serverData=xml.readXml();
            controller.openOptions(serverData[0],serverData[1],serverData[2],serverData[3],serverData[4]);
            this.generatePoblacion();
            this.generateAll();
        } catch (Exception e) {
        }
    }

    /**Delete the item selected
     * 
     * @param number Code of the apartment
     */
    public void deleteItem(String number){
        try{
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmar");
            alert.setHeaderText("Se eliminará una vivienda");
            alert.setContentText("¿Realmente desea eliminar esta vivienda?");
            Optional<ButtonType> result = alert.showAndWait();
            
            if (result.get() == ButtonType.OK){
                db.deleteApartment(number);
                this.generatePoblacion();
                this.generateAll();
            }
        }catch(Exception e){
                e.printStackTrace();
        }
    }
    
    /**Open a new window to add an apartment
     * 
     */
    public void openAddModify(){
        try{
            controller.openAddModify(null);
            this.generatePoblacion();
            this.generateAll();
        }catch(Exception e){
                e.printStackTrace();
        }
    }

    /**Open a new window to modify an apartment
     * 
     * @param number Code of the apartment
     */
    public void openAddModify(String number){
        try{
            controller.openAddModify(db.getApartmentNumber(number));
            this.generatePoblacion();
            this.generateAll();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**Close the connection to the database and the principal window
     * 
     */
    public void close(){
        try {
            if(db!=null) db.disconnect();
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**Get the location from the database
     * 
     */
    public void generatePoblacion(){
        String[] stringArray1=db.executeQueryOne("SELECT POBLACION FROM VIVIENDA GROUP BY POBLACION");
        String[] stringArray2=new String[stringArray1.length+1];
        stringArray2[0]="Todos";
        System.arraycopy(stringArray1, 0, stringArray2, 1, stringArray1.length);
        poblacion.setItems(observableArrayList(stringArray2));
        poblacion.setValue("Todos");
        
        stringArray1=db.executeQueryOne("SELECT TIPOCASA FROM CARACTERISTICASVIVIENDA GROUP BY TIPOCASA");
        stringArray2=new String[stringArray1.length+1];
        stringArray2[0]="Todos";
        System.arraycopy(stringArray1, 0, stringArray2, 1, stringArray1.length);
        tipoCasa.setItems(observableArrayList(stringArray2));
        tipoCasa.setValue("Todos");
        
        this.disable(false);
    }
    
    public void filterApartments(){
        System.gc();
        filtroPoblacion=poblacion.getValue().toString();
        filtroTipoCasa=tipoCasa.getValue().toString();
        filtroBuscar=buscar.getText();
        filtroCamas=(int) camas.getValue();
        filtroAlquiler=alquiler.isSelected();
        filtroAlquilerTemporario=alquilerTemporario.isSelected();
        filtroVenta=venta.isSelected();
        filtroGaraje=garaje.isSelected();
        filtroParrilla=parrilla.isSelected();
        filtroExterior=exterior.isSelected();
        filtroJovenes=jovenes.isSelected();
        filtroAlarma=alarma.isSelected();
        filtroDesc=desc.isSelected();
        filtroOrden=(String) order.getValue();
        maxim=Integer.parseInt((String) numeroViviendas.getValue());
        totalApartments=db.getCountApartments(filtroPoblacion, filtroCamas, filtroAlquiler, filtroAlquilerTemporario, filtroVenta, filtroParrilla, filtroExterior, filtroJovenes, filtroAlarma, filtroGaraje, minim, maxim, filtroBuscar, filtroTipoCasa);
        if(totalApartments<maxim){
            pagination.setPageCount(1);
        }else{
            pagination.autosize();
            pagination.setPageCount((int)Math.ceil(totalApartments/maxim));
        }
        pagination.setPageFactory((Integer pageIndex) -> {
            minim=pageIndex*maxim;
            return generateAll();
        });
    }
    
    /**Generate all the apartments
     * 
     */
    public Node generateAll(){
        this.disable(true);
        db.setActivated(true);
        
        String defaultPhoto="";
        if (db.getPhotos("0")[0]!=null) {
                defaultPhoto=db.getPhotos("0")[0];
        }
        try{
            Apartment[] ap=db.getApartments(filtroPoblacion, filtroCamas, filtroAlquiler, filtroAlquilerTemporario, filtroVenta, filtroParrilla, filtroExterior, filtroJovenes, filtroAlarma, filtroGaraje, minim, maxim, filtroOrden, filtroDesc, filtroBuscar, filtroTipoCasa);
            
            GridPane gridPane=new GridPane();
            gridPane.setGridLinesVisible(true);
            ColumnConstraints column1 = new ColumnConstraints();
            column1.setPercentWidth(100);
            gridPane.getColumnConstraints().add(column1);
            scrollPane.setFitToHeight(true);
            scrollPane.setFitToWidth(true);
            scrollPane.setContent(gridPane);
            int num=0;
            for (Apartment apartment : ap) {
               String photo="";
               if(apartment.getFotos()!=null){
                   String tempPath=apartment.getFotos()[0];
                   photo+=tempPath.substring(0, tempPath.lastIndexOf("."))+"_min."+tempPath.substring(tempPath.indexOf(".")+1);
               }else{
                   String tempPath=defaultPhoto;
                   photo+=tempPath.substring(0, tempPath.lastIndexOf("."))+"_min."+tempPath.substring(tempPath.indexOf(".")+1);
               }
               gridPane.add( generateApartment(apartment.getCodigo(), apartment.getNombre(), apartment.getPoblacion(), apartment.getDireccion(), apartment.getCodigoInmobiliaria()+"/"+apartment.getCodigoArgenprop(), apartment.getCamas(), apartment.getBaños(), apartment.getVenta(), apartment.getAlquiler(), apartment.getPermuta(), photo), 0, num++);
           }
           
            db.setActivated(false);
            this.disable(false);
        }catch(Exception e){
                e.printStackTrace();
        }
        return scrollPane;
    }
    
    public void reestablecer(){
        order.setValue("Creación");
        desc.setSelected(false);
        buscar.setText("");
        numeroViviendas.setValue("10");
        poblacion.setValue("Todos");
        camas.setValue(0);
        alquiler.setSelected(false);
        alquilerTemporario.setSelected(false);
        venta.setSelected(false);
        garaje.setSelected(false);
        parrilla.setSelected(false);
        exterior.setSelected(false);
        jovenes.setSelected(false);
        alarma.setSelected(false);
        tipoCasa.setValue("Todos");
    }
    
    /**Set the disable property of the components width the value of the parameter
     * 
     * @param disable Set if it is disabled or not
     */
    public void disable(boolean disable){
        mainScene.setDisable(disable);
    }
}
