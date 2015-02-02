package test;

import Model.*;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import static javafx.collections.FXCollections.observableArrayList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import static test.PrincipalController.db;

public class AddModifyController {

    //Model
    private Size size;
    private Apartment apartment=new Apartment();

    //Own
    private boolean modify;
    private Stage stage;
    @FXML private BorderPane borderPane;
    @FXML private CheckBox parrilla, parque, telefono, internet, lavadero, jovenes, cable, aireAcondicionado, alarma, servicios, heladera, freezer, microondas, cafetera, tostadora, gasNatural, garaje, entradaAuto, alquiler, venta, alquilerTemporario, dueñoPesos, dueñoDolares, inmobiliariaPesos, inmobiliariaDolares, wifi, mascotas, rejas, estacionamiento, estacionamientoTechado, ventilador, calefon, estufa, termotanque;
    @FXML private TextField baños, camas, metrosTerreno, metrosEdificados, nombre, direccion, ambientes, habitaciones, nombreDueño, telefonoDueño, numeroCuenta, correo, cdu, castral, primeraQuincenaDiciembre, primeraQuincenaEnero, primeraQuincenaFebrero, primeraQuincenaMarzo, primeraQuincenaOtro, segundaQuincenaDiciembre, segundaQuincenaEnero, segundaQuincenaFebrero, segundaQuincenaMarzo, segundaQuincenaOtro, semanaDiciembre, semanaEnero, semanaFebrero, semanaMarzo, diaDiciembre, diaEnero, diaFebrero, diaMarzo, codigoArgenprop, codigoInmobiliaria, documento;
    @FXML private TextArea permuta, descripcion;
    @FXML private ComboBox banco, tipoCuenta, poblacion, tipoCasa;
    @FXML private ImageView imageView;
    private boolean photosNull;
    private ArrayList<BorderPaneApartment> borderPaneApartments=new ArrayList<BorderPaneApartment>();
    private ArrayList<ImageView> imageViews=new ArrayList<ImageView>();
    private String[] photos;
    private GridPane gridPane=new GridPane();
    private Desktop dt = Desktop.getDesktop();
    
    /**Open a window to modify an apartment
     * 
     * @param apartment Apartment with the information to modify
     */
    public void openAddModify(Apartment apartment){
        if(apartment==null){
            this.modify=false;
        }else{
            this.apartment=apartment;
            this.modify=true;
            this.getApartmentValues();
            if(apartment.getFotos()!=null){
                photosNull=false;
                photos=apartment.getFotos();
            }
        }
        borderPane.setCenter(gridPane);
        generatePoblacion();
        if(!modify) this.setDefaultValues();
    }

    public void setStage(Stage stage){
        this.stage=stage;
    }

    public void cerrar(){
        stage.close();
    }
    /**Insert an apartment if its a new apartment, or update it if it already exists
     * 
     */
    public void insertUpdate(){
        this.setApartmentValues();
        if(this.modify) PrincipalController.db.updateAparmentClass(this.apartment);
        else PrincipalController.db.insertAparmentClass(this.apartment);
        this.cerrar();
    }

    /**Open a window to choose the photos to add to the apartment
     * 
     */
    public void getPhotosName(){
        FileChooser fc=new FileChooser();
        
        fc.setTitle("Selecciona las imágenes");
        fc.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Imágenes", "*.png", "*.jpg", "*.bmp"),
            new FileChooser.ExtensionFilter("All Files", "*.*"));
        List<File> list=fc.showOpenMultipleDialog(stage);
        if(list!=null){
           String[] photos=new String[list.size()];
            for (int i = 0; i < photos.length; i++) {
                    photos[i]=list.get(i).getAbsolutePath();
            }
            addPhotosToArray(photos);
        }
    }

    /**Delete an image
     * 
     */
    public void deleteImage(){
        //TODO
           /* panelPhotos.remove(jpanecode.get(lastItem));
            jpanecode.remove(lastItem);
            revalidate();*/
    }

    public void generatePoblacion(){
        poblacion.setItems(observableArrayList(db.executeQueryOne("SELECT POBLACION FROM VIVIENDA GROUP BY POBLACION")));
        tipoCasa.setItems(observableArrayList(db.executeQueryOne("SELECT TIPOCASA FROM CARACTERISTICASVIVIENDA GROUP BY TIPOCASA")));
        if(poblacion.getItems().size()!=0) poblacion.setValue(poblacion.getItems().get(0));
        tipoCuenta.setItems(observableArrayList(db.executeQueryOne("SELECT TIPOCUENTA FROM DUEÑOVIVIENDA GROUP BY TIPOCUENTA")));
        banco.setItems(observableArrayList(db.executeQueryOne("SELECT BANCO FROM DUEÑOVIVIENDA GROUP BY BANCO")));
    }
    
    /**Add to the arrayList the photos of the array of string
     * 
     * @param photos Array of string with the photos's path
     */
    public void addPhotosToArray(String[] photos){
        Size size;
        try {
            for (int i = 0; i < photos.length; i++) {
                size=new Size(photos[i]);
                String tempPath=photos[i];
                photos[i]=size.resizeCache();
                
                borderPaneApartments.add(new BorderPaneApartment());
                int z=borderPaneApartments.size()-1;
                borderPaneApartments.get(z).setId("border-image");
                borderPaneApartments.get(z).setCode(tempPath);
                FileInputStream fis=new FileInputStream(photos[i]);
                Image imagen=new Image(fis);
                imageViews.add(new ImageView(imagen));
                /*fis=null;
                imagen=null;
                System.gc();*/
                imageViews.get(imageViews.size()-1).setFitHeight(150);
                imageViews.get(imageViews.size()-1).setFitWidth(150);
                borderPaneApartments.get(z).setCenter(imageViews.get(imageViews.size()-1));
                borderPaneApartments.get(z).onMouseEnteredProperty().set((EventHandler<MouseEvent>) (MouseEvent mouseEvent) -> {
                        addPhoto(((BorderPaneApartment)mouseEvent.getSource()).getCode());
                    });
                borderPaneApartments.get(z).setOnMouseClicked((MouseEvent mouseEvent) -> {
                    if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                        if(mouseEvent.getClickCount() == 2){
                            try{
                                dt.open(new File(((BorderPaneApartment)mouseEvent.getSource()).getCode()));
                            }catch(Exception e){
                                e.printStackTrace();
                            }
                        }
                    }
                });
                gridPane.add(borderPaneApartments.get(z), gridPane.getChildrenUnmodifiable().size(), 0);
            }
            
        } catch (Exception e) {
        }
    }
    
    /**Add a principal photo and resize it to the 50% of the window
    * 
    */
    public void addPhoto(String path){
        try{
            if(!photosNull){
                FileInputStream fis=new FileInputStream(path);
                Image imagen=new Image(fis);
                imageView.setImage(imagen);
                imageView.setFitWidth(500);
                imageView.setFitHeight(430);
                fis=null;
                imagen=null;
                System.gc();
            }
        }catch(Exception e){
                e.printStackTrace();
        }
    }
    
    /**Set the actual apartment's values
     * 
     */
    public void setApartmentValues(){
        try{
            apartment.setNombre(nombre.getText());
            apartment.setNumeroCastral(castral.getText());
            apartment.setDescripcion(descripcion.getText());
            apartment.setDireccion(direccion.getText());
            apartment.setPoblacion((String) poblacion.getValue());
            apartment.setAmbientes(ambientes.getText());
            apartment.setHabitaciones(habitaciones.getText());
            apartment.setBaños(baños.getText());
            apartment.setCamas(camas.getText());
            apartment.setMetrosTerreno(metrosTerreno.getText());
            apartment.setMetrosEdificados(metrosEdificados.getText());
            apartment.setAlquiler(Convert.toString(Convert.toInt(alquiler.isSelected())));
            apartment.setVenta(Convert.toString(Convert.toInt(venta.isSelected())));
            apartment.setDueñoPesos(Convert.toString(Convert.toInt(dueñoPesos.isSelected())));
            apartment.setDueñoDolares(Convert.toString(Convert.toInt(dueñoDolares.isSelected())));
            apartment.setInmobiliariaPesos(Convert.toString(Convert.toInt(inmobiliariaPesos.isSelected())));
            apartment.setInmobiliariaDolares(Convert.toString(Convert.toInt(inmobiliariaDolares.isSelected())));
            apartment.setParrilla(Convert.toString(Convert.toInt(parrilla.isSelected())));
            apartment.setParque(Convert.toString(Convert.toInt(parque.isSelected())));
            apartment.setInternet(Convert.toString(Convert.toInt(internet.isSelected())));
            apartment.setTelefono(Convert.toString(Convert.toInt(telefono.isSelected())));
            apartment.setLavadero(Convert.toString(Convert.toInt(lavadero.isSelected())));
            apartment.setCable(Convert.toString(Convert.toInt(cable.isSelected())));
            apartment.setAireAcondicionado(Convert.toString(Convert.toInt(aireAcondicionado.isSelected())));
            apartment.setAlarma(Convert.toString(Convert.toInt(alarma.isSelected())));
            apartment.setServicios(Convert.toString(Convert.toInt(servicios.isSelected())));
            apartment.setHeladera(Convert.toString(Convert.toInt(heladera.isSelected())));
            apartment.setFreezer(Convert.toString(Convert.toInt(freezer.isSelected())));
            apartment.setMicroondas(Convert.toString(Convert.toInt(microondas.isSelected())));
            apartment.setCafetera(Convert.toString(Convert.toInt(cafetera.isSelected())));
            apartment.setTostadora(Convert.toString(Convert.toInt(tostadora.isSelected())));
            apartment.setGasNatural(Convert.toString(Convert.toInt(gasNatural.isSelected())));
            apartment.setGaraje(Convert.toString(Convert.toInt(garaje.isSelected())));
            apartment.setEntradaAuto(Convert.toString(Convert.toInt(entradaAuto.isSelected())));
            apartment.setNombreDueño(nombreDueño.getText());
            apartment.setTipoCuenta((String) tipoCuenta.getValue());
            apartment.setTelefonoDueño(telefono.getText());
            apartment.setNumeroCuenta(numeroCuenta.getText());
            apartment.setCdu(cdu.getText());
            apartment.setCorreo(correo.getText());
            apartment.setBanco((String) banco.getValue());
            apartment.setPermuta(permuta.getText());
            apartment.setServicios(Convert.toString(Convert.toInt(servicios.isSelected())));
            apartment.setAlquilerTemporario(Convert.toString(Convert.toInt(alquilerTemporario.isSelected())));
            apartment.setPrimeraQuincenaDiciembre(primeraQuincenaDiciembre.getText());
            apartment.setPrimeraQuincenaEnero(primeraQuincenaEnero.getText());
            apartment.setPrimeraQuincenaFebrero(primeraQuincenaFebrero.getText());
            apartment.setPrimeraQuincenaMarzo(primeraQuincenaMarzo.getText());
            apartment.setPrimeraQuincenaOtro(primeraQuincenaOtro.getText());
            apartment.setSegundaQuincenaDiciembre(segundaQuincenaDiciembre.getText());
            apartment.setSegundaQuincenaEnero(segundaQuincenaEnero.getText());
            apartment.setSegundaQuincenaFebrero(segundaQuincenaFebrero.getText());
            apartment.setSegundaQuincenaMarzo(segundaQuincenaMarzo.getText());
            apartment.setSegundaQuincenaOtro(segundaQuincenaOtro.getText());
            apartment.setSemanaDiciembre(semanaDiciembre.getText());
            apartment.setSemanaEnero(semanaEnero.getText());
            apartment.setSemanaFebrero(semanaFebrero.getText());
            apartment.setSemanaMarzo(semanaMarzo.getText());
            apartment.setDiaDiciembre(diaDiciembre.getText());
            apartment.setDiaEnero(diaEnero.getText());
            apartment.setDiaFebrero(diaFebrero.getText());
            apartment.setDiaMarzo(diaMarzo.getText());
            apartment.setDocumento(documento.getText());
            apartment.setWifi(Convert.toString(Convert.toInt(wifi.isSelected())));
            apartment.setMascotas(Convert.toString(Convert.toInt(mascotas.isSelected())));
            apartment.setRejas(Convert.toString(Convert.toInt(rejas.isSelected())));
            apartment.setEstacionamiento(Convert.toString(Convert.toInt(estacionamiento.isSelected())));
            apartment.setEstacionamientoTechado(Convert.toString(Convert.toInt(estacionamientoTechado.isSelected())));
            apartment.setVentilador(Convert.toString(Convert.toInt(ventilador.isSelected())));
            apartment.setCalefon(Convert.toString(Convert.toInt(calefon.isSelected())));
            apartment.setEstufa(Convert.toString(Convert.toInt(estufa.isSelected())));
            apartment.setTermotanque(Convert.toString(Convert.toInt(termotanque.isSelected())));
            apartment.setCodigoArgenprop(codigoArgenprop.getText());
            apartment.setCodigoInmobiliaria(codigoInmobiliaria.getText());
            apartment.setTipoCasa((String) tipoCasa.getValue());
            if(borderPaneApartments.size()!=0){
                String[] photos=new String[borderPaneApartments.size()];
                for (int i = 0; i < borderPaneApartments.size(); i++) {
                    photos[i]=borderPaneApartments.get(i).getCode();
                }
                apartment.setFotos(photos);
            }
        }catch(Exception e){
                e.printStackTrace();
        }
    }
    public void setDefaultValues(){
        ambientes.setText("0");
        habitaciones.setText("0");
        baños.setText("0");
        camas.setText("0");
        metrosTerreno.setText("0");
        metrosEdificados.setText("0");
        primeraQuincenaDiciembre.setText("0");
        primeraQuincenaEnero.setText("0");
        primeraQuincenaFebrero.setText("0");
        primeraQuincenaMarzo.setText("0");
        primeraQuincenaOtro.setText("0");
        segundaQuincenaDiciembre.setText("0");
        segundaQuincenaEnero.setText("0");
        segundaQuincenaFebrero.setText("0");
        segundaQuincenaMarzo.setText("0");
        segundaQuincenaOtro.setText("0");
        semanaDiciembre.setText("0");
        semanaEnero.setText("0");
        semanaFebrero.setText("0");
        semanaMarzo.setText("0");
        diaDiciembre.setText("0");
        diaEnero.setText("0");
        diaFebrero.setText("0");
        diaMarzo.setText("0");
    }
    
    /**Get the values from a new apartment
     * 
     */
    public void getApartmentValues(){
        nombre.setText(apartment.getNombre());
        castral.setText(apartment.getNumeroCastral());
        direccion.setText(apartment.getDireccion());
        poblacion.setValue(apartment.getPoblacion());
        ambientes.setText(apartment.getAmbientes());
        habitaciones.setText(apartment.getHabitaciones());
        baños.setText(apartment.getBaños());
        descripcion.setText(apartment.getDescripcion());
        camas.setText(apartment.getCamas());
        alquilerTemporario.setSelected(Convert.toBoolean(Convert.toInt(apartment.getAlquilerTemporario())));
        servicios.setSelected(Convert.toBoolean(Convert.toInt(apartment.getServicios())));
        metrosTerreno.setText(apartment.getMetrosTerreno());
        metrosEdificados.setText(apartment.getMetrosEdificados());
        alquiler.setSelected(Convert.toBoolean(Convert.toInt(apartment.getAlquiler())));
        venta.setSelected(Convert.toBoolean(Convert.toInt(apartment.getVenta())));
        dueñoPesos.setSelected(Convert.toBoolean(Convert.toInt(apartment.getDueñoPesos())));
        dueñoDolares.setSelected(Convert.toBoolean(Convert.toInt(apartment.getDueñoDolares())));
        inmobiliariaPesos.setSelected(Convert.toBoolean(Convert.toInt(apartment.getInmobiliariaPesos())));
        inmobiliariaDolares.setSelected(Convert.toBoolean(Convert.toInt(apartment.getInmobiliariaDolares())));
        parrilla.setSelected(Convert.toBoolean(Convert.toInt(apartment.getParrilla())));
        parque.setSelected(Convert.toBoolean(Convert.toInt(apartment.getParque())));
        internet.setSelected(Convert.toBoolean(Convert.toInt(apartment.getInternet())));
        telefono.setSelected(Convert.toBoolean(Convert.toInt(apartment.getTelefono())));
        lavadero.setSelected(Convert.toBoolean(Convert.toInt(apartment.getLavadero())));
        jovenes.setSelected(Convert.toBoolean(Convert.toInt(apartment.getJovenes())));
        cable.setSelected(Convert.toBoolean(Convert.toInt(apartment.getCable())));
        aireAcondicionado.setSelected(Convert.toBoolean(Convert.toInt(apartment.getAireAcondicionado())));
        alarma.setSelected(Convert.toBoolean(Convert.toInt(apartment.getAlarma())));
        servicios.setSelected(Convert.toBoolean(Convert.toInt(apartment.getServicios())));
        heladera.setSelected(Convert.toBoolean(Convert.toInt(apartment.getHeladera())));
        freezer.setSelected(Convert.toBoolean(Convert.toInt(apartment.getFreezer())));
        microondas.setSelected(Convert.toBoolean(Convert.toInt(apartment.getMicroondas())));
        cafetera.setSelected(Convert.toBoolean(Convert.toInt(apartment.getCafetera())));
        tostadora.setSelected(Convert.toBoolean(Convert.toInt(apartment.getTostadora())));
        gasNatural.setSelected(Convert.toBoolean(Convert.toInt(apartment.getGasNatural())));
        garaje.setSelected(Convert.toBoolean(Convert.toInt(apartment.getGaraje())));
        entradaAuto.setSelected(Convert.toBoolean(Convert.toInt(apartment.getEntradaAuto())));
        nombreDueño.setText(apartment.getNombreDueño());
        tipoCuenta.setValue(apartment.getTipoCuenta());
        telefonoDueño.setText(apartment.getTelefonoDueño());
        numeroCuenta.setText(apartment.getNumeroCuenta());
        cdu.setText(apartment.getCdu());
        correo.setText(apartment.getCorreo());
        banco.setValue(apartment.getBanco());
        permuta.setText(apartment.getPermuta());
        primeraQuincenaDiciembre.setText(apartment.getPrimeraQuincenaDiciembre());
        primeraQuincenaEnero.setText(apartment.getPrimeraQuincenaEnero());
        primeraQuincenaFebrero.setText(apartment.getPrimeraQuincenaFebrero());
        primeraQuincenaMarzo.setText(apartment.getPrimeraQuincenaMarzo());
        primeraQuincenaOtro.setText(apartment.getPrimeraQuincenaOtro());
        segundaQuincenaDiciembre.setText(apartment.getSegundaQuincenaDiciembre());
        segundaQuincenaEnero.setText(apartment.getSegundaQuincenaEnero());
        segundaQuincenaFebrero.setText(apartment.getSegundaQuincenaFebrero());
        segundaQuincenaMarzo.setText(apartment.getSegundaQuincenaMarzo());
        segundaQuincenaOtro.setText(apartment.getSegundaQuincenaOtro());
        semanaDiciembre.setText(apartment.getSemanaDiciembre());
        semanaEnero.setText(apartment.getSemanaEnero());
        semanaFebrero.setText(apartment.getSemanaFebrero());
        semanaMarzo.setText(apartment.getSemanaMarzo());
        diaDiciembre.setText(apartment.getDiaDiciembre());
        diaEnero.setText(apartment.getDiaEnero());
        diaFebrero.setText(apartment.getDiaFebrero());
        diaMarzo.setText(apartment.getDiaMarzo());
        documento.setText(apartment.getDocumento());
        wifi.setSelected(Convert.toBoolean(Convert.toInt(apartment.getWifi())));
        mascotas.setSelected(Convert.toBoolean(Convert.toInt(apartment.getMascotas())));
        rejas.setSelected(Convert.toBoolean(Convert.toInt(apartment.getRejas())));
        estacionamiento.setSelected(Convert.toBoolean(Convert.toInt(apartment.getEstacionamiento())));
        estacionamientoTechado.setSelected(Convert.toBoolean(Convert.toInt(apartment.getEstacionamientoTechado())));
        ventilador.setSelected(Convert.toBoolean(Convert.toInt(apartment.getVentilador())));
        calefon.setSelected(Convert.toBoolean(Convert.toInt(apartment.getCalefon())));
        estufa.setSelected(Convert.toBoolean(Convert.toInt(apartment.getEstufa())));
        termotanque.setSelected(Convert.toBoolean(Convert.toInt(apartment.getTermotanque())));
        codigoArgenprop.setText(apartment.getCodigoArgenprop());
        codigoInmobiliaria.setText(apartment.getCodigoInmobiliaria());
        tipoCasa.setValue(apartment.getTipoCuenta());
        if(apartment.getFotos()!=null){
            addPhotosToArray(apartment.getFotos());
        }
    }
}
