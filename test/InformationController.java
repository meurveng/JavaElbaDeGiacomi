/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import Model.Apartment;
import Model.BorderPaneApartment;
import Model.Convert;
import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.stage.DirectoryChooser;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author meurveng
 */
public class InformationController implements Initializable {
    private Apartment apartment;
    @FXML private CheckBox parrilla, comision, parque, telefono, internet, lavadero, jovenes, cable, aireAcondicionado, alarma, servicioBlanco, servicios, heladera, freezer, microondas, cafetera, tostadora, gasNatural, garaje, entradaAuto, alquiler, venta, alquilerTemporario, dueñoPesos, dueñoDolares, inmobiliariaPesos, inmobiliariaDolares, wifi, mascotas, rejas, estacionamiento, estacionamientoTechado, ventilador, calefon, estufa, termotanque;
    @FXML private Label tipoCasa, codigoInmobiliaria, codigoArgenprop, documento, baños, camas, metrosTerreno, metrosEdificados, nombre, direccion, poblacion, ambientes, habitaciones, nombreDueño, telefonoDueño, tipoCuenta, numeroCuenta, banco, correo, cdu, castral, primeraQuincenaDiciembre, primeraQuincenaEnero, primeraQuincenaFebrero, primeraQuincenaMarzo, primeraQuincenaOtro, segundaQuincenaDiciembre, segundaQuincenaEnero, segundaQuincenaFebrero, segundaQuincenaMarzo, segundaQuincenaOtro, semanaDiciembre, semanaEnero, semanaFebrero, semanaMarzo, diaDiciembre, diaEnero, diaFebrero, diaMarzo, sabanaDosPlazas, sabanaUnaPlaza, toallas;
    @FXML private TextArea descripcion, permuta;
    @FXML private BorderPane borderPane;
    @FXML private ImageView imageView;
    private boolean photosNull;
    private BorderPaneApartment[] borderPaneApartments;
    private ImageView[] imageViews;
    private String[] photos;
    private double comisionAplicated=1;
    private Desktop dt = Desktop.getDesktop();
    private double comisionPrimera;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    public void close(){
        if(photos!=null){
            for (int i = 0; i < photos.length; i++) {
                imageViews[i]=null;
                borderPaneApartments[i]=null;
            }
        }
        
        System.gc();
       
    }
    
    /**Add or remove the comision
    * 
    */
    public void calculateComision(){
        if(comision.isSelected()) this.comisionAplicated=comisionPrimera;
        else this.comisionAplicated=1;
        this.setInformation();
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
    
    public Node generatePhotos(){
        GridPane gridPane=new GridPane();
        try{
            if(!photosNull){
                for (int i = 0; i < photos.length; i++) {
                    borderPaneApartments[i]=new BorderPaneApartment();
                    borderPaneApartments[i].setId("border-image");
                    String tempPath=photos[i];
                    borderPaneApartments[i].setCode(tempPath);
                    FileInputStream fis=new FileInputStream(tempPath.substring(0, tempPath.lastIndexOf("."))+"_min."+tempPath.substring(tempPath.indexOf(".")+1));
                    Image imagen=new Image(fis);
                    imageViews[i]=new ImageView(imagen);
                    fis=null;
                    imagen=null;
                    System.gc();
                    borderPaneApartments[i].setCenter(imageViews[i]);
                    borderPaneApartments[i].onMouseEnteredProperty().set((EventHandler<MouseEvent>) (MouseEvent mouseEvent) -> {
                        addPhoto(((BorderPaneApartment)mouseEvent.getSource()).getCode());
                    });
                    borderPaneApartments[i].setOnMouseClicked((MouseEvent mouseEvent) -> {
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
                    gridPane.add(borderPaneApartments[i], i, 0);
                }   
            }
        }catch(Exception e){
                e.printStackTrace();
        }
        return gridPane;
    }
    
    public void exportar(){
        try {
            DirectoryChooser dc=new DirectoryChooser();
            File file=dc.showDialog(null);
            file=new File(file.getCanonicalPath()+"/"+apartment.getNombre());
            if(!file.exists()) file.mkdir();
            FileWriter fw = new FileWriter(file.getCanonicalPath()+"/Datos.txt");
            BufferedWriter bfw = new BufferedWriter(fw);
            
            bfw.write("Codigo inmobiliaria: "+apartment.getCodigoInmobiliaria()+"\n");
            bfw.write("Codigo Argenprop: "+apartment.getCodigoArgenprop()+"\n");
            bfw.write("Nombre: "+apartment.getNombre()+"\n");
            bfw.write("Direccion: "+apartment.getDireccion()+"\n");
            bfw.write("Lugar: "+apartment.getPoblacion()+"\n");
            bfw.write("Tipo: "+apartment.getTipoCasa()+"\n");
            bfw.write("Ambientes: "+apartment.getAmbientes()+"\n");
            bfw.write("Habitaciones: "+apartment.getHabitaciones()+"\n");
            bfw.write("Baños: "+apartment.getBaños()+"\n");
            bfw.write("Camas: "+apartment.getCamas()+"\n");
            bfw.write("M.terreno: "+apartment.getMetrosTerreno()+"\n");
            bfw.write("M.edificados: "+apartment.getMetrosEdificados()+"\n");
            bfw.write("Catastral: "+apartment.getNumeroCastral()+"\n");
            bfw.write("Descripcion: "+apartment.getDescripcion()+"\n\n");
            
            bfw.write("Parrilla: "+Convert.toSiNo(Convert.toBoolean(apartment.getParrilla()))+"\n");
            bfw.write("Servicios: "+Convert.toSiNo(Convert.toBoolean(apartment.getServicios()))+"\n");
            bfw.write("Parque: "+Convert.toSiNo(Convert.toBoolean(apartment.getParque()))+"\n");
            bfw.write("Heladera: "+Convert.toSiNo(Convert.toBoolean(apartment.getHeladera()))+"\n");
            bfw.write("Telefono: "+Convert.toSiNo(Convert.toBoolean(apartment.getTelefono()))+"\n");
            bfw.write("Freezer: "+Convert.toSiNo(Convert.toBoolean(apartment.getFreezer()))+"\n");
            bfw.write("Internet: "+Convert.toSiNo(Convert.toBoolean(apartment.getInternet()))+"\n");
            bfw.write("Microondas: "+Convert.toSiNo(Convert.toBoolean(apartment.getMicroondas()))+"\n");
            bfw.write("Lavadero: "+Convert.toSiNo(Convert.toBoolean(apartment.getLavadero()))+"\n");
            bfw.write("Cafetera: "+Convert.toSiNo(Convert.toBoolean(apartment.getCafetera()))+"\n");
            bfw.write("Jovenes: "+Convert.toSiNo(Convert.toBoolean(apartment.getJovenes()))+"\n");
            bfw.write("Tostadora: "+Convert.toSiNo(Convert.toBoolean(apartment.getTostadora()))+"\n");
            bfw.write("Cable: "+Convert.toSiNo(Convert.toBoolean(apartment.getCable()))+"\n");
            bfw.write("Gas natural: "+Convert.toSiNo(Convert.toBoolean(apartment.getGasNatural()))+"\n");
            bfw.write("Aire acondicionado: "+Convert.toSiNo(Convert.toBoolean(apartment.getAireAcondicionado()))+"\n");
            bfw.write("Garaje: "+Convert.toSiNo(Convert.toBoolean(apartment.getGaraje()))+"\n");
            bfw.write("Wi-Fi: "+Convert.toSiNo(Convert.toBoolean(apartment.getWifi()))+"\n");
            bfw.write("Mascotas: "+Convert.toSiNo(Convert.toBoolean(apartment.getMascotas()))+"\n");
            bfw.write("Estacionamiento: "+Convert.toSiNo(Convert.toBoolean(apartment.getEstacionamiento()))+"\n");
            bfw.write("Estacionamiento techado: "+Convert.toSiNo(Convert.toBoolean(apartment.getEstacionamientoTechado()))+"\n");
            bfw.write("Ventilador: "+Convert.toSiNo(Convert.toBoolean(apartment.getVentilador()))+"\n");
            bfw.write("Rejas: "+Convert.toSiNo(Convert.toBoolean(apartment.getRejas()))+"\n");
            bfw.write("Calefon: "+Convert.toSiNo(Convert.toBoolean(apartment.getCalefon()))+"\n");
            bfw.write("Estufa: "+Convert.toSiNo(Convert.toBoolean(apartment.getEstufa()))+"\n");
            bfw.write("Termotanque: "+Convert.toSiNo(Convert.toBoolean(apartment.getTermotanque()))+"\n");
            bfw.write("Alarma: "+Convert.toSiNo(Convert.toBoolean(apartment.getAlarma()))+"\n");
            bfw.write("Entrada de auto: "+Convert.toSiNo(Convert.toBoolean(apartment.getEntradaAuto()))+"\n\n");
            
            bfw.write("Diciembre\n");
            bfw.write("Primera quincena: "+apartment.getPrimeraQuincenaDiciembre()+"\n");
            bfw.write("Segunda quincena: "+apartment.getSegundaQuincenaDiciembre()+"\n");
            bfw.write("Semana: "+apartment.getSemanaDiciembre()+"\n");
            bfw.write("Joven por dia: "+apartment.getDiaDiciembre()+"\n\n");
            
            bfw.write("Enero\n");
            bfw.write("Primera quincena: "+apartment.getPrimeraQuincenaEnero()+"\n");
            bfw.write("Segunda quincena: "+apartment.getSegundaQuincenaEnero()+"\n");
            bfw.write("Semana: "+apartment.getSemanaEnero()+"\n");
            bfw.write("Joven por dia: "+apartment.getDiaEnero()+"\n\n");
            
            bfw.write("Febrero\n");
            bfw.write("Primera quincena: "+apartment.getPrimeraQuincenaFebrero()+"\n");
            bfw.write("Segunda quincena: "+apartment.getSegundaQuincenaFebrero()+"\n");
            bfw.write("Semana: "+apartment.getSemanaFebrero()+"\n");
            bfw.write("Joven por dia: "+apartment.getDiaFebrero()+"\n\n");
            
            bfw.write("Marzo\n");
            bfw.write("Primera quincena: "+apartment.getPrimeraQuincenaMarzo()+"\n");
            bfw.write("Segunda quincena: "+apartment.getSegundaQuincenaMarzo()+"\n");
            bfw.write("Semana: "+apartment.getSemanaMarzo()+"\n");
            bfw.write("Joven por dia: "+apartment.getDiaMarzo()+"\n\n");
            
            bfw.write("Otros\n");
            bfw.write("Anual: "+apartment.getPrimeraQuincenaOtro()+"\n");
            bfw.write("Fin de semana: "+apartment.getSegundaQuincenaOtro()+"\n");

            
            
            bfw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**Open a information window with the data of the apartment 
    * 
    * @param apartment Apartment class with the data
    */
    public void openInformation(Apartment apartment){
        try{
            if(!PrincipalController.db.executeQueryString("SELECT COUNT(*) FROM CONFIGURACION WHERE CLAVE=\'COMISION\'").equals("0")){
                this.comisionPrimera=1+(Convert.toDouble(PrincipalController.db.executeQueryString("SELECT VALOR FROM CONFIGURACION WHERE CLAVE=\'COMISION\'"))/100);
            }
            this.apartment=apartment;

            if(this.apartment.getFotos()!=null){
                this.photosNull=false;
                this.photos=apartment.getFotos();
                borderPaneApartments=new BorderPaneApartment[photos.length];
                imageViews=new ImageView[photos.length];
                this.addPhoto(photos[0]);
                borderPane.setCenter(generatePhotos());
            }
            
            setInformation();
        }catch(Exception e){
                e.printStackTrace();
        }
    }
        
        /**Set the information of the apartment in the panels
	 * 
	 */
	public void setInformation(){
            nombreDueño.setText(apartment.getNombreDueño());
            tipoCuenta.setText(apartment.getTipoCuenta());
            telefonoDueño.setText(apartment.getTelefonoDueño());
            numeroCuenta.setText(apartment.getNumeroCuenta());
            banco.setText(apartment.getCdu());
            castral.setText(apartment.getNumeroCastral());
            permuta.setText(apartment.getPermuta());
            correo.setText(apartment.getCorreo());
            cdu.setText(apartment.getCdu());
            alquiler.setSelected(Convert.toBoolean(apartment.getAlquiler()));
            venta.setSelected(Convert.toBoolean(apartment.getVenta()));
            dueñoPesos.setSelected(Convert.toBoolean(apartment.getDueñoPesos()));
            dueñoDolares.setSelected(Convert.toBoolean(apartment.getDueñoDolares()));
            inmobiliariaPesos.setSelected(Convert.toBoolean(apartment.getInmobiliariaPesos()));
            inmobiliariaDolares.setSelected(Convert.toBoolean(apartment.getInmobiliariaDolares()));
            alquiler.setSelected(Convert.toBoolean(apartment.getAlquiler()));
            alquilerTemporario.setSelected(Convert.toBoolean(apartment.getAlquilerTemporario()));
            primeraQuincenaDiciembre.setText(Convert.toString(Convert.toInt(apartment.getPrimeraQuincenaDiciembre())*this.comisionAplicated));
            primeraQuincenaEnero.setText(Convert.toString(Convert.toInt(apartment.getPrimeraQuincenaEnero())*this.comisionAplicated));
            primeraQuincenaFebrero.setText(Convert.toString(Convert.toInt(apartment.getPrimeraQuincenaFebrero())*this.comisionAplicated));
            primeraQuincenaMarzo.setText(Convert.toString(Convert.toInt(apartment.getPrimeraQuincenaMarzo())*this.comisionAplicated));
            primeraQuincenaOtro.setText(Convert.toString(Convert.toInt(apartment.getPrimeraQuincenaOtro())*this.comisionAplicated));
            segundaQuincenaDiciembre.setText(Convert.toString(Convert.toInt(apartment.getSegundaQuincenaDiciembre())*this.comisionAplicated));
            segundaQuincenaEnero.setText(Convert.toString(Convert.toInt(apartment.getSegundaQuincenaEnero())*this.comisionAplicated));
            segundaQuincenaFebrero.setText(Convert.toString(Convert.toInt(apartment.getSegundaQuincenaFebrero())*this.comisionAplicated));
            segundaQuincenaMarzo.setText(Convert.toString(Convert.toInt(apartment.getSegundaQuincenaMarzo())*this.comisionAplicated));
            segundaQuincenaOtro.setText(Convert.toString(Convert.toInt(apartment.getSegundaQuincenaOtro())*this.comisionAplicated));
            semanaDiciembre.setText(Convert.toString(Convert.toInt(apartment.getSemanaDiciembre())*this.comisionAplicated));
            semanaEnero.setText(Convert.toString(Convert.toInt(apartment.getSemanaEnero())*this.comisionAplicated));
            semanaFebrero.setText(Convert.toString(Convert.toInt(apartment.getSemanaFebrero())*this.comisionAplicated));
            semanaMarzo.setText(Convert.toString(Convert.toInt(apartment.getSemanaMarzo())*this.comisionAplicated));
            diaDiciembre.setText(Convert.toString(Convert.toInt(apartment.getDiaDiciembre())*this.comisionAplicated));
            diaEnero.setText(Convert.toString(Convert.toInt(apartment.getDiaEnero())*this.comisionAplicated));
            diaFebrero.setText(Convert.toString(Convert.toInt(apartment.getDiaFebrero())*this.comisionAplicated));
            diaMarzo.setText(Convert.toString(Convert.toInt(apartment.getDiaMarzo())*this.comisionAplicated));
            sabanaDosPlazas.setText(Convert.toString(Convert.toInt(apartment.getSabanaDosPlazas())));
            sabanaUnaPlaza.setText(Convert.toString(Convert.toInt(apartment.getSabanaUnaPlaza())));
            toallas.setText(Convert.toString(Convert.toInt(apartment.getToallas())));
            nombre.setText(apartment.getNombre());
            direccion.setText(apartment.getDireccion());
            descripcion.setText(apartment.getDescripcion());
            poblacion.setText(apartment.getPoblacion());
            nombre.setText(apartment.getNombre());
            direccion.setText(apartment.getDireccion());
            ambientes.setText(apartment.getAmbientes());
            habitaciones.setText(apartment.getHabitaciones());
            baños.setText(apartment.getBaños());
            camas.setText(apartment.getCamas());
            metrosTerreno.setText(apartment.getMetrosTerreno()+" m²");
            metrosEdificados.setText(apartment.getMetrosEdificados()+" m²");
            parrilla.setSelected(Convert.toBoolean(Convert.toInt(apartment.getParrilla())));
            parque.setSelected(Convert.toBoolean(Convert.toInt(apartment.getParque())));
            internet.setSelected(Convert.toBoolean(Convert.toInt(apartment.getInternet())));
            telefono.setSelected(Convert.toBoolean(Convert.toInt(apartment.getTelefono())));
            lavadero.setSelected(Convert.toBoolean(Convert.toInt(apartment.getLavadero())));
            jovenes.setSelected(Convert.toBoolean(Convert.toInt(apartment.getJovenes())));
            cable.setSelected(Convert.toBoolean(Convert.toInt(apartment.getCable())));
            aireAcondicionado.setSelected(Convert.toBoolean(Convert.toInt(apartment.getAireAcondicionado())));
            alarma.setSelected(Convert.toBoolean(Convert.toInt(apartment.getAlarma())));
            heladera.setSelected(Convert.toBoolean(Convert.toInt(apartment.getHeladera())));
            freezer.setSelected(Convert.toBoolean(Convert.toInt(apartment.getFreezer())));
            microondas.setSelected(Convert.toBoolean(Convert.toInt(apartment.getMicroondas())));
            cafetera.setSelected(Convert.toBoolean(Convert.toInt(apartment.getCafetera())));
            tostadora.setSelected(Convert.toBoolean(Convert.toInt(apartment.getTostadora())));
            gasNatural.setSelected(Convert.toBoolean(Convert.toInt(apartment.getGasNatural())));
            garaje.setSelected(Convert.toBoolean(Convert.toInt(apartment.getGaraje())));
            entradaAuto.setSelected(Convert.toBoolean(Convert.toInt(apartment.getEntradaAuto())));
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
            tipoCasa.setText(apartment.getTipoCasa());
	}
}
