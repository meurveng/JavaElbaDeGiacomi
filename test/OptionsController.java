package test;

import Model.*;
import java.io.File;
import java.io.FileInputStream;
import javafx.beans.value.ObservableValue;
import static javafx.collections.FXCollections.observableArrayList;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class OptionsController {
	
	//Model
	private XML xml=new XML();
	
	//Own
        @FXML private TextField serverTextField, dataBaseTextField, userTextField, passwordTextField, portTextField, sabanaUnaPlaza, sabanaDosPlazas, toallas;
        @FXML private ListView poblacion;
        @FXML private Slider comision;
        @FXML private Button guardar;
        @FXML private ImageView imageView;
        @FXML private Label comisionLabel;
	private String server="";
	private String dataBase="";
	private String user="";
	private String password="";
	private String port="";
	private String photo;
        private Stage stage;
	
	/**Open a new window to set the configuration of the program 
	 * 
	 */
	public void openOptions(String server, String dataBase, String user, String password, String port){
            this.server=server;
            this.dataBase=dataBase;
            this.user=user;
            this.password=password;
            this.port=port;
            this.setData();
            comision.valueProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
                comisionLabel.setText(String.valueOf(new_val.intValue())+"%");
            });
	}
        
	/**Set the actual configuration of the program
	 * 
	 */
	public void setData(){
            serverTextField.setText(this.server);
            dataBaseTextField.setText(this.dataBase);
            userTextField.setText(this.user);
            passwordTextField.setText(this.password);
            portTextField.setText(this.port);
            if(!PrincipalController.db.executeQueryString("SELECT COUNT(*) FROM CONFIGURACION WHERE CLAVE=\'COMISION\'").equals("0")){
                comision.setValue(Convert.toInt(PrincipalController.db.executeQueryString("SELECT VALOR FROM CONFIGURACION WHERE CLAVE=\'COMISION\'")));
                comisionLabel.setText(((int)comision.getValue())+"%");
            }
            if(!PrincipalController.db.executeQueryString("SELECT COUNT(*) FROM CONFIGURACION WHERE CLAVE=\'SABANAUNAPLAZA\'").equals("0")){
                sabanaUnaPlaza.setText(PrincipalController.db.executeQueryString("SELECT VALOR FROM CONFIGURACION WHERE CLAVE=\'SABANAUNAPLAZA\'"));
            }
            if(!PrincipalController.db.executeQueryString("SELECT COUNT(*) FROM CONFIGURACION WHERE CLAVE=\'SABANADOSPLAZAS\'").equals("0")){
                sabanaDosPlazas.setText(PrincipalController.db.executeQueryString("SELECT VALOR FROM CONFIGURACION WHERE CLAVE=\'SABANADOSPLAZAS\'"));
            }
            if(!PrincipalController.db.executeQueryString("SELECT COUNT(*) FROM CONFIGURACION WHERE CLAVE=\'TOALLAS\'").equals("0")){
                toallas.setText(PrincipalController.db.executeQueryString("SELECT VALOR FROM CONFIGURACION WHERE CLAVE=\'TOALLAS\'"));
            }
            if(!PrincipalController.db.executeQueryOne("SELECT COUNT(*) FROM FOTOSVIVIENDA WHERE CODIGOVIVIENDA=0")[0].equals("0")){
                photo=PrincipalController.db.getPhotos("0")[0];
            }
            this.setPhoto();
	}
	
	/**Add to the panel the chosen photo
	 * 
	 */
	public void setPhoto(){
            try{
                int height=125;
                int width;
                Size size=new Size(photo);
                width=size.getWidth(height);
                imageView.setImage(new Image(new FileInputStream(photo)));
                imageView.setFitWidth(width);
                imageView.setFitHeight(height);
            }catch(Exception e){
                    e.printStackTrace();
            }
	}
	
	
	public void setStage(Stage stage){
            this.stage=stage;
        }
        
	/**Open a window to choose the default photo
	 * 
	 */
	public void getPhotosName(){
            FileChooser fc=new FileChooser();
            fc.setTitle("Selecciona las imágenes");
            fc.getExtensionFilters().addAll(
                new ExtensionFilter("Imágenes", "*.png", "*.jpg", "*.bmp"),
                new ExtensionFilter("All Files", "*.*"));
            File archivo=fc.showOpenDialog(stage);
            if(archivo!=null){
                photo=archivo.getAbsolutePath();
                PrincipalController.db.deletePhotos("0");
                PrincipalController.db.insertApartmentPhoto("0", photo);
                this.setPhoto();
            }
	}
	
        public void close(){
            Stage stage = (Stage) guardar.getScene().getWindow();
            stage.close();
        }
        
	/**Check if the data is correct
	 * 
	 */
	public void check(){
            boolean correct=true;

            if(serverTextField.getText().equals("")) correct=false;
            if(dataBaseTextField.getText().equals("")) correct=false;
            if(userTextField.getText().equals("")) correct=false;
            if(passwordTextField.getText().equals("")) correct=false;
            if(portTextField.getText().equals("")) correct=false;
            if(correct){
                xml.writeXML(serverTextField.getText(), dataBaseTextField.getText(), userTextField.getText(), passwordTextField.getText(), portTextField.getText());
                String [] serverData=xml.readXml();
                if(PrincipalController.db==null) PrincipalController.db=new DataBase(serverData[0], serverData[1], serverData[2], serverData[3], serverData[4]);
                PrincipalController.db.insertConfiguracion("SABANAUNAPLAZA", sabanaUnaPlaza.getText());
                PrincipalController.db.insertConfiguracion("SABANADOSPLAZAS", sabanaDosPlazas.getText());
                PrincipalController.db.insertConfiguracion("TOALLAS", toallas.getText());
                PrincipalController.db.insertConfiguracion("COMISION", Convert.toString((int)comision.getValue()));
                close();
            }else{
                //JOptionPane.showMessageDialog(view, "Se deben establecer todos los datos.", "Datos incompletos", JOptionPane.ERROR_MESSAGE);
            }
	}
}
