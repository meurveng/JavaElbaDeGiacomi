package Model;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.scene.control.Alert;

import javax.imageio.ImageIO;
import test.PrincipalController;


public class DataBase {
	
    //Model
    private ConnectWithServer cws;

    //Own
    private String password="";
    private String user="";
    private String server="";
    private String dataBase="";
    private Connection connection=null;
    private Statement statement=null;
    private ResultSetMetaData metadata=null;
    private ResultSet rs=null;
    private PreparedStatement prepared=null;
    private String query="";
    private int port=4000;
    private boolean activated=false;

    /**	Create a connection with the default data from the database.
     * 
     */
    public DataBase(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://"+server+"/"+dataBase+"", user, password);
            statement = connection.createStatement();
            createTable();
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error de conexión");
            alert.setContentText("No se ha podido establecer conexión con la base de datos del servidor.");
            alert.showAndWait();
            System.exit(0);
        } 
    }

    /**	Create a connection with some parameter that are the data from the database.
     * 
     * @param password Password from the database
     * @param user User from the database
     * @param server IP or name from the computer that has the database
     * @param dataBase Name from the database
     * @param port Port of the database
     */
    public DataBase(String server, String dataBase, String user, String password, String port){
        this.server=server;
        this.dataBase=dataBase;
        this.user=user;
        this.password=password;
        this.port=Convert.toInt(port);
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://"+server+"/"+dataBase+"", user, password);
            statement = connection.createStatement();
            createTable();
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error de conexión");
            alert.setContentText("No se ha podido establecer conexión con la base de datos del servidor.");
            alert.showAndWait();
            System.exit(0);
        } 
    }

    /**Create the tables in the database.
     * 
     */
    public void createTable() {
            try{
                    query="CREATE TABLE IF NOT EXISTS POBLACION(NOMBRE VARCHAR(255))ENGINE=InnoDB;";
                    statement.addBatch(query);

                    query="CREATE TABLE IF NOT EXISTS VIVIENDA(CODIGO SERIAL, NOMBRE VARCHAR(255), NUMCASTRAL VARCHAR(255), DIRECCION VARCHAR(255), POBLACION VARCHAR(255))ENGINE=InnoDB;";
                    statement.addBatch(query);

                    query="CREATE TABLE IF NOT EXISTS FOTOSVIVIENDA(CODIGOVIVIENDA VARCHAR(255), RUTA VARCHAR(255) )ENGINE=InnoDB;";
                    statement.addBatch(query);

                    query="CREATE TABLE IF NOT EXISTS DUEñOVIVIENDA(CODIGOVIVIENDA VARCHAR(255) PRIMARY KEY, NOMBRE VARCHAR(255), TIPOCUENTA VARCHAR(255), BANCO VARCHAR(255), NUMEROCUENTA VARCHAR(255), CDU VARCHAR(255), CORREO VARCHAR(255), TELEFONO VARCHAR(255), DOCUMENTO VARCHAR(255) )ENGINE=InnoDB;";
                    statement.addBatch(query);

                    query="CREATE TABLE IF NOT EXISTS CARACTERISTICASVIVIENDA(CODIGOVIVIENDA VARCHAR(255) PRIMARY KEY, AMBIENTES INT, HABITACIONES INT, BAñOS INT, SERVICIOS INT, HELADERA INT, FREEZER INT, MICROONDAS INT, CAFETERA INT, TOSTADOR INT, GASNATURAL INT, GARAJE INT, ENTRADAAUTO INT, PARRILLA INT, PARQUE INT, INTERNET INT, TELEFONO INT, LAVADERO INT, JOVENES INT, CABLE INT, CAMAS INT, AIREACONDICIONADO INT, METROSTERRENO INT, METROSEDIFICADOS INT, ALQUILER INT, VENTA INT, DUEñOPESOS INT, DUEñODOLARES INT, INMOBILIARIAPESOS INT, INMOBILIARIADOLARES INT, ALARMA INT , SERVICIOBLANCO INT, PERMUTA VARCHAR(255), ALQUILERTEMPORARIO INT, DESCRIPCION TEXT, WIFI INT, MASCOTAS INT, REJAS INT, ESTACIONAMIENTO INT, ESTACIONAMIENTOTECHADO INT, VENTILADOR INT, CALEFON INT, ESTUFA INT, TERMOTANQUE INT, CODIGOARGENPROP VARCHAR(255), CODIGOPROPIO VARCHAR(255),TIPOCASA VARCHAR(255))ENGINE=InnoDB;";
                    statement.addBatch(query);

                    query="CREATE TABLE IF NOT EXISTS CONFIGURACION(CLAVE VARCHAR(255), VALOR VARCHAR(255))ENGINE=InnoDB;";
                    statement.addBatch(query);

                    query="CREATE TABLE IF NOT EXISTS PRECIOSVIVIENDA(CODIGOVIVIENDA INT, PRIMERAQUINCENADICIEMBRE INT, PRIMERAQUINCENAENERO INT, PRIMERAQUINCENAFEBRERO INT, PRIMERAQUINCENAMARZO INT, PRIMERAQUINCENAOTRO INT, SEGUNDAQUINCENADICIEMBRE INT, SEGUNDAQUINCENAENERO INT, SEGUNDAQUINCENAFEBRERO INT, SEGUNDAQUINCENAMARZO INT, SEGUNDAQUINCENAOTRO INT, MESOTRO INT, SEMANADICIEMBRE INT, SEMANAENERO INT, SEMANAFEBRERO INT, SEMANAMARZO INT, SEMANAOTRO INT, DIADICIEMBRE INT, DIAENERO INT, DIAFEBRERO INT, DIAMARZO INT, DIAOTRO INT)ENGINE=InnoDB;";
                    statement.addBatch(query);

                    statement.executeBatch();

            }catch(Exception e){
                    e.printStackTrace();
            }
    }

    /**Insert the prices of an apartment
     * 
     * @param codigoVivienda
     * @param primeraQuincenaDiciembre
     * @param primeraQuincenaEnero
     * @param primeraQuincenaFebrero
     * @param primeraQuincenaMarzo
     * @param primeraQuincenaOtro
     * @param segundaQuincenaDiciembre
     * @param segundaQuincenaEnero
     * @param segundaQuincenaFebrero
     * @param segundaQuincenaMarzo
     * @param segundaQuincenaOtro
     * @param mesOtro
     * @param semanaDiciembre
     * @param semanaEnero
     * @param semanaFebrero
     * @param semanaMarzo
     * @param semanaOtro
     * @param diaDiciembre
     * @param diaEnero
     * @param diaFebrero
     * @param diaMarzo
     * @param diaOtro
     */
    public void insertApartmentPrices(String codigoVivienda, String primeraQuincenaDiciembre ,String primeraQuincenaEnero ,String primeraQuincenaFebrero ,String primeraQuincenaMarzo ,String primeraQuincenaOtro ,String segundaQuincenaDiciembre ,String segundaQuincenaEnero ,String segundaQuincenaFebrero ,String segundaQuincenaMarzo ,String segundaQuincenaOtro ,String mesOtro ,String semanaDiciembre ,String semanaEnero ,String semanaFebrero ,String semanaMarzo ,String semanaOtro ,String diaDiciembre ,String diaEnero ,String diaFebrero ,String diaMarzo ,String diaOtro) {
            try{

                    query="INSERT INTO PRECIOSVIVIENDA VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

                    int num=0;
                    prepared = connection.prepareStatement(query);
                    prepared.setString(++num,codigoVivienda);
                    prepared.setString(++num,primeraQuincenaDiciembre);
                    prepared.setString(++num,primeraQuincenaEnero);
                    prepared.setString(++num,primeraQuincenaFebrero);
                    prepared.setString(++num,primeraQuincenaMarzo);
                    prepared.setString(++num,primeraQuincenaOtro);
                    prepared.setString(++num,segundaQuincenaDiciembre);
                    prepared.setString(++num,segundaQuincenaEnero);
                    prepared.setString(++num,segundaQuincenaFebrero);
                    prepared.setString(++num,segundaQuincenaMarzo);
                    prepared.setString(++num,segundaQuincenaOtro);
                    prepared.setString(++num,mesOtro);
                    prepared.setString(++num,semanaDiciembre);
                    prepared.setString(++num,semanaEnero);
                    prepared.setString(++num,semanaFebrero);
                    prepared.setString(++num,semanaMarzo);
                    prepared.setString(++num,semanaOtro);
                    prepared.setString(++num,diaDiciembre);
                    prepared.setString(++num,diaEnero);
                    prepared.setString(++num,diaFebrero);
                    prepared.setString(++num,diaMarzo);
                    prepared.setString(++num,diaOtro);
                    prepared.execute();

            }catch(Exception e){
                    e.printStackTrace();
            }
    }

    /**Update the prices of an apartment
     * 
     * @param codigoVivienda
     * @param primeraQuincenaDiciembre
     * @param primeraQuincenaEnero
     * @param primeraQuincenaFebrero
     * @param primeraQuincenaMarzo
     * @param primeraQuincenaOtro
     * @param segundaQuincenaDiciembre
     * @param segundaQuincenaEnero
     * @param segundaQuincenaFebrero
     * @param segundaQuincenaMarzo
     * @param segundaQuincenaOtro
     * @param mesOtro
     * @param semanaDiciembre
     * @param semanaEnero
     * @param semanaFebrero
     * @param semanaMarzo
     * @param semanaOtro
     * @param diaDiciembre
     * @param diaEnero
     * @param diaFebrero
     * @param diaMarzo
     * @param diaOtro
     */
    public void updateApartmentPrices(String codigoVivienda, String primeraQuincenaDiciembre ,String primeraQuincenaEnero ,String primeraQuincenaFebrero ,String primeraQuincenaMarzo ,String primeraQuincenaOtro ,String segundaQuincenaDiciembre ,String segundaQuincenaEnero ,String segundaQuincenaFebrero ,String segundaQuincenaMarzo ,String segundaQuincenaOtro ,String mesOtro ,String semanaDiciembre ,String semanaEnero ,String semanaFebrero ,String semanaMarzo ,String semanaOtro ,String diaDiciembre ,String diaEnero ,String diaFebrero ,String diaMarzo ,String diaOtro) {
            try{

                    query="UPDATE PRECIOSVIVIENDA SET PRIMERAQUINCENADICIEMBRE=? , PRIMERAQUINCENAENERO=? , PRIMERAQUINCENAFEBRERO =? , PRIMERAQUINCENAMARZO=? , PRIMERAQUINCENAOTRO=? , SEGUNDAQUINCENADICIEMBRE=? , SEGUNDAQUINCENAENERO=? , SEGUNDAQUINCENAFEBRERO=? , SEGUNDAQUINCENAMARZO=? , SEGUNDAQUINCENAOTRO=? , MESOTRO=? , SEMANADICIEMBRE=? , SEMANAENERO=? , SEMANAFEBRERO=? , SEMANAMARZO=? , SEMANAOTRO=? , DIADICIEMBRE=? , DIAENERO=? , DIAFEBRERO=? , DIAMARZO=? , DIAOTRO=? WHERE CODIGOVIVIENDA=?;";

                    int num=0;
                    prepared = connection.prepareStatement(query);
                    prepared.setString(++num,primeraQuincenaDiciembre );
                    prepared.setString(++num,primeraQuincenaEnero );
                    prepared.setString(++num,primeraQuincenaFebrero );
                    prepared.setString(++num,primeraQuincenaMarzo);
                    prepared.setString(++num,primeraQuincenaOtro);
                    prepared.setString(++num,segundaQuincenaDiciembre);
                    prepared.setString(++num,segundaQuincenaEnero);
                    prepared.setString(++num,segundaQuincenaFebrero);
                    prepared.setString(++num,segundaQuincenaMarzo);
                    prepared.setString(++num,segundaQuincenaOtro);
                    prepared.setString(++num,mesOtro);
                    prepared.setString(++num,semanaDiciembre);
                    prepared.setString(++num,semanaEnero);
                    prepared.setString(++num,semanaFebrero);
                    prepared.setString(++num,semanaMarzo);
                    prepared.setString(++num,semanaOtro);
                    prepared.setString(++num,diaDiciembre);
                    prepared.setString(++num,diaEnero);
                    prepared.setString(++num,diaFebrero);
                    prepared.setString(++num,diaMarzo);
                    prepared.setString(++num,diaOtro);
                    prepared.setString(++num,codigoVivienda);
                    prepared.execute();

            }catch(Exception e){
                    e.printStackTrace();
            }
    }

    /** Insert an apartment with the parameters received
     * 
     * @param nombre
     * @param numCastral
     * @param direccion The apartment's address
     * @param poblacion The apartment's location
     */
    public void insertApartment(String nombre, String numCastral, String direccion, String poblacion) {
            try{

                    query="INSERT INTO VIVIENDA VALUES(DEFAULT, ?, ?, ?, ?);";

                    int num=0;
                    prepared = connection.prepareStatement(query);
                    prepared.setString(++num, nombre);
                    prepared.setString(++num, numCastral);
                    prepared.setString(++num, direccion);
                    prepared.setString(++num, poblacion);
                    prepared.execute();

            }catch(Exception e){
                    e.printStackTrace();
            }
    }

    /** Update an apartment with the parameters received
     * 
     * @param codigo
     * @param nombre
     * @param numCastral
     * @param direccion
     * @param poblacion
     */
    public void updateApartment(String codigo, String nombre, String numCastral, String direccion, String poblacion) {
            try{

                    query="UPDATE VIVIENDA SET NOMBRE=?, NUMCASTRAL=?, DIRECCION=?, POBLACION=? WHERE CODIGO=?;";

                    int num=0;
                    prepared = connection.prepareStatement(query);
                    prepared.setString(++num, nombre);
                    prepared.setString(++num, numCastral);
                    prepared.setString(++num, direccion);
                    prepared.setString(++num, poblacion);
                    prepared.setString(++num, codigo);
                    prepared.execute();

            }catch(Exception e){
                    e.printStackTrace();
            }
    }

    /** Insert an apartment's image with the parameters received
     * 
     * @param codigo
     * @param ruta
     */
    public void insertApartmentPhoto(String codigo, String ruta) {
            try{
                    cws=new ConnectWithServer(server, port, "send", ruta);
                    if(ruta.contains("/")) ruta=ruta.substring(ruta.lastIndexOf("/")+1, ruta.length());
                    else ruta=ruta.substring(ruta.lastIndexOf("\\")+1, ruta.length());
                    query="INSERT INTO FOTOSVIVIENDA VALUES(?, ?);";

                    int num=0;
                    prepared = connection.prepareStatement(query);
                    prepared.setString(++num, codigo);
                    prepared.setString(++num, ruta);
                    prepared.execute();
            }catch(Exception e){
                    e.printStackTrace();
            }

    }

    /**Delete a location
     * 
     * @param nombre Name of the location
     */
    public void deletePoblacion(String nombre) {
            try{
                    query="DELETE FROM POBLACION WHERE NOMBRE='"+nombre+"';";

                    prepared = connection.prepareStatement(query);
                    prepared.execute();
            }catch(Exception e){
                    e.printStackTrace();
            }

    }


    /**Insert a location
     * 
     * @param nombre Name of the location
     */
    public void insertPoblacion(String nombre) {
            try{
                    query="INSERT INTO POBLACION VALUES(?);";

                    int num=0;
                    prepared = connection.prepareStatement(query);
                    prepared.setString(++num, nombre);
                    prepared.execute();
            }catch(Exception e){
                    e.printStackTrace();
            }
    }

    /** Insert an apartment's owner with the parameters received
     * 
     * @param codigoVivienda
     * @param nombre
     * @param tipoCuenta
     * @param banco
     * @param numeroCuenta
     * @param cbu
     * @param correo
     * @param telefono
     */
    public void insertOwner(String codigoVivienda, String nombre, String tipoCuenta, String banco, String numeroCuenta, String cbu, String correo, String telefono, String documento) {
            try{

                    query="INSERT INTO DUEñOVIVIENDA VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?);";

                    int num=0;
                    prepared = connection.prepareStatement(query);
                    prepared.setString(++num, codigoVivienda);
                    prepared.setString(++num, nombre);
                    prepared.setString(++num, tipoCuenta);
                    prepared.setString(++num, banco);
                    prepared.setString(++num, numeroCuenta);
                    prepared.setString(++num, cbu);
                    prepared.setString(++num, correo);
                    prepared.setString(++num, telefono);
                    prepared.setString(++num, documento);
                    prepared.execute();

            }catch(Exception e){
                    e.printStackTrace();
            }
    }

    /** Update an apartment's owner with the parameters received
     * 
     * @param codigo
     * @param nombre
     * @param tipoCuenta
     * @param banco
     * @param numeroCuenta
     * @param cbu
     * @param correo
     * @param telefono
     */
    public void updateOwner(String codigo, String nombre, String tipoCuenta, String banco, String numeroCuenta, String cbu, String correo, String telefono, String documento) {
            try{

                    query="UPDATE DUEñOVIVIENDA SET NOMBRE=?, TIPOCUENTA=?, BANCO=?, NUMEROCUENTA=?, CDU=?, CORREO=?, TELEFONO=?, DOCUMENTO=? WHERE CODIGOVIVIENDA=?;";

                    int num=0;
                    prepared = connection.prepareStatement(query);
                    prepared.setString(++num, nombre);
                    prepared.setString(++num, tipoCuenta);
                    prepared.setString(++num, banco);
                    prepared.setString(++num, numeroCuenta);
                    prepared.setString(++num, cbu);
                    prepared.setString(++num, correo);
                    prepared.setString(++num, telefono);
                    prepared.setString(++num, documento);
                    prepared.setString(++num, codigo);
                    prepared.execute();

            }catch(Exception e){
                    e.printStackTrace();
            }
    }

    /** Insert an apartment's characteristics with the parameters received
     * 
     * @param codigo
     * @param ambientes
     * @param habitaciones
     * @param baños
     * @param servicios
     * @param heladera
     * @param freezer
     * @param microondas
     * @param cafetera
     * @param tostador
     * @param gasNatural
     * @param garaje
     * @param entradaAuto
     * @param parrilla
     * @param parque
     * @param internet
     * @param telefono
     * @param lavadero
     * @param jovenes
     * @param cable
     * @param camas
     * @param aireAcondicionado
     * @param metrosTerreno
     * @param metrosEdificados
     * @param alarma
     * @param alquiler
     * @param venta
     * @param dueñoPesos
     * @param dueñoDolares
     * @param inmobiliariaPesos
     * @param inmobiliariaDolares
     * @param servicioBlanco
     * @param permuta
     * @param alquilerTemporario
     * @param descripcion
     */
    public void insertAparmentCharacteristics(String codigo, String ambientes, String habitaciones, String baños, String servicios, String heladera, String freezer, String microondas, String cafetera, String tostador, String gasNatural, String garaje, String entradaAuto, String parrilla, String parque, String internet, String telefono, String lavadero, String jovenes, String cable, String camas, String aireAcondicionado, String metrosTerreno, String metrosEdificados, String alarma, String alquiler, String venta, String dueñoPesos, String dueñoDolares, String inmobiliariaPesos, String inmobiliariaDolares, String servicioBlanco, String permuta, String alquilerTemporario, String descripcion, String wifi, String mascotas, String rejas, String estacionamiento, String estacionamientoTechado, String ventilador, String calefon, String estufa, String termotanque, String codigoArgenprop, String codigoInmobiliaria, String tipoCasa) {
            try{

                    query="INSERT INTO CARACTERISTICASVIVIENDA VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

                    int num=0;
                    prepared = connection.prepareStatement(query);
                    prepared.setString(++num, codigo);
                    prepared.setString(++num, ambientes);
                    prepared.setString(++num, habitaciones);
                    prepared.setString(++num, baños);
                    prepared.setString(++num, servicios);
                    prepared.setString(++num, heladera);
                    prepared.setString(++num, freezer);
                    prepared.setString(++num, microondas);
                    prepared.setString(++num, cafetera);
                    prepared.setString(++num, tostador);
                    prepared.setString(++num, gasNatural);
                    prepared.setString(++num, garaje);
                    prepared.setString(++num, entradaAuto);
                    prepared.setString(++num, parrilla);
                    prepared.setString(++num, parque);
                    prepared.setString(++num, internet);
                    prepared.setString(++num, telefono);
                    prepared.setString(++num, lavadero);
                    prepared.setString(++num, jovenes);
                    prepared.setString(++num, cable);
                    prepared.setString(++num, camas);
                    prepared.setString(++num, aireAcondicionado);
                    prepared.setString(++num, metrosTerreno);
                    prepared.setString(++num, metrosEdificados);
                    prepared.setString(++num, alquiler);
                    prepared.setString(++num, venta);
                    prepared.setString(++num, dueñoPesos);
                    prepared.setString(++num, dueñoDolares);
                    prepared.setString(++num, inmobiliariaPesos);
                    prepared.setString(++num, inmobiliariaDolares);
                    prepared.setString(++num, alarma);
                    prepared.setString(++num, servicioBlanco);
                    prepared.setString(++num, permuta);
                    prepared.setString(++num, alquilerTemporario);
                    prepared.setString(++num, descripcion);
                    prepared.setString(++num, wifi);
                    prepared.setString(++num, mascotas);
                    prepared.setString(++num, rejas);
                    prepared.setString(++num, estacionamiento);
                    prepared.setString(++num, estacionamientoTechado);
                    prepared.setString(++num, ventilador);
                    prepared.setString(++num, calefon);
                    prepared.setString(++num, estufa);
                    prepared.setString(++num, termotanque);
                    prepared.setString(++num, codigoArgenprop);
                    prepared.setString(++num, codigoInmobiliaria);
                    prepared.setString(++num, tipoCasa);
                    prepared.execute();

            }catch(Exception e){
                    e.printStackTrace();
            }
    }

    /**Update an apartment's characteristics with the parameters received
     * 
     * @param codigo
     * @param ambientes
     * @param habitaciones
     * @param baños
     * @param servicios
     * @param heladera
     * @param freezer
     * @param microondas
     * @param cafetera
     * @param tostador
     * @param gasNatural
     * @param garaje
     * @param entradaAuto
     * @param parrilla
     * @param parque
     * @param internet
     * @param telefono
     * @param lavadero
     * @param jovenes
     * @param cable
     * @param camas
     * @param aireAcondicionado
     * @param metrosTerreno
     * @param metrosEdificados
     * @param alarma
     * @param alquiler
     * @param venta
     * @param dueñoPesos
     * @param dueñoDolares
     * @param inmobiliariaPesos
     * @param inmobiliariaDolares
     * @param servicioBlanco
     * @param permuta
     * @param alquilerTemporario
     * @param descripcion
     */
    public void updateAparmentCharacteristics(String codigo, String ambientes, String habitaciones, String baños, String servicios, String heladera, String freezer, String microondas, String cafetera, String tostador, String gasNatural, String garaje, String entradaAuto, String parrilla, String parque, String internet, String telefono, String lavadero, String jovenes, String cable, String camas, String aireAcondicionado, String metrosTerreno, String metrosEdificados, String alarma, String alquiler, String venta, String dueñoPesos, String dueñoDolares, String inmobiliariaPesos, String inmobiliariaDolares, String servicioBlanco, String permuta, String alquilerTemporario, String descripcion, String wifi, String mascotas, String rejas, String estacionamiento, String estacionamientoTechado, String ventilador, String calefon, String estufa, String termotanque, String codigoArgenprop, String codigoInmobiliaria, String tipoCasa) {
            try{

                    query="UPDATE CARACTERISTICASVIVIENDA SET AMBIENTES=?, HABITACIONES=?, BAñOS=?, SERVICIOS=?, HELADERA=?, FREEZER=?, MICROONDAS=?, CAFETERA=?, TOSTADOR=?, GASNATURAL=?, GARAJE=?, ENTRADAAUTO=?, PARRILLA=?, PARQUE=?, INTERNET=?, TELEFONO=?, LAVADERO=?, JOVENES=?, CABLE=?, CAMAS=?, AIREACONDICIONADO=?, METROSTERRENO=?, METROSEDIFICADOS=?, ALQUILER=?, VENTA=?, DUEñOPESOS=?, DUEñODOLARES=?, INMOBILIARIAPESOS=?, INMOBILIARIADOLARES=?, ALARMA=?, SERVICIOBLANCO=?, PERMUTA=?, ALQUILERTEMPORARIO=?, DESCRIPCION=?, WIFI=?, MASCOTAS=?, REJAS=?, ESTACIONAMIENTO=?, ESTACIONAMIENTOTECHADO=?, VENTILADOR=?, CALEFON=?, ESTUFA=?, TERMOTANQUE=?, CODIGOARGENPROP=?, CODIGOPROPIO=?, TIPOCASA=? WHERE CODIGOVIVIENDA =?";

                    int num=0;
                    prepared = connection.prepareStatement(query);
                    prepared.setString(++num, ambientes);
                    prepared.setString(++num, habitaciones);
                    prepared.setString(++num, baños);
                    prepared.setString(++num, servicios);
                    prepared.setString(++num, heladera);
                    prepared.setString(++num, freezer);
                    prepared.setString(++num, microondas);
                    prepared.setString(++num, cafetera);
                    prepared.setString(++num, tostador);
                    prepared.setString(++num, gasNatural);
                    prepared.setString(++num, garaje);
                    prepared.setString(++num, entradaAuto);
                    prepared.setString(++num, parrilla);
                    prepared.setString(++num, internet);
                    prepared.setString(++num, telefono);
                    prepared.setString(++num, lavadero);
                    prepared.setString(++num, jovenes);
                    prepared.setString(++num, cable);
                    prepared.setString(++num, camas);
                    prepared.setString(++num, aireAcondicionado);
                    prepared.setString(++num, metrosTerreno);
                    prepared.setString(++num, metrosEdificados);
                    prepared.setString(++num, alquiler);
                    prepared.setString(++num, venta);
                    prepared.setString(++num, dueñoPesos);
                    prepared.setString(++num, dueñoDolares);
                    prepared.setString(++num, dueñoDolares);
                    prepared.setString(++num, inmobiliariaPesos);
                    prepared.setString(++num, inmobiliariaDolares);
                    prepared.setString(++num, alarma);
                    prepared.setString(++num, servicioBlanco);
                    prepared.setString(++num, permuta);
                    prepared.setString(++num, alquilerTemporario);
                    prepared.setString(++num, descripcion);
                    prepared.setString(++num, wifi);
                    prepared.setString(++num, mascotas);
                    prepared.setString(++num, rejas);
                    prepared.setString(++num, estacionamiento);
                    prepared.setString(++num, estacionamientoTechado);
                    prepared.setString(++num, ventilador);
                    prepared.setString(++num, calefon);
                    prepared.setString(++num, estufa);
                    prepared.setString(++num, termotanque);
                    prepared.setString(++num, codigoArgenprop);
                    prepared.setString(++num, codigoInmobiliaria);
                    prepared.setString(++num, tipoCasa);
                    prepared.setString(++num, codigo);
                    prepared.execute();

            }catch(Exception e){
                    e.printStackTrace();
            }
    }

    /**Insert the value of a key
     * 
     * @param clave
     * @param valor
     */
    public void insertConfiguracion(String clave, String valor){
            try{
                    if(!this.executeQueryString("SELECT COUNT(*) FROM CONFIGURACION WHERE CLAVE='"+clave+"'").equals("0")){
                            query="UPDATE CONFIGURACION SET VALOR=? WHERE CLAVE=?;";

                            int num=0;
                            prepared = connection.prepareStatement(query);
                            prepared.setString(++num, valor);
                            prepared.setString(++num, clave);
                            prepared.execute();
                    }
                    else{
                            query="INSERT INTO CONFIGURACION VALUES(?, ?);";

                            int num=0;
                            prepared = connection.prepareStatement(query);
                            prepared.setString(++num, clave);
                            prepared.setString(++num, valor);
                            prepared.execute();
                    }

            }catch(Exception e){
                    e.printStackTrace();
            }
    }

    /**Delete the photos from the apartment
     * 
     * @param codigo
     */
    public void deletePhotos(String codigo){
            try{
                    query="DELETE FROM FOTOSVIVIENDA WHERE CODIGOVIVIENDA= ?;";

                    prepared = connection.prepareStatement(query);
                    prepared.setString(1, codigo);
                    prepared.execute();
            }catch(Exception e){
                    e.printStackTrace();
            }
    }

    /**	Delete an apartment.
     * 
     * @param codigo
     */
    public void deleteApartment(String codigo) {
            try{
                    query="DELETE FROM VIVIENDA WHERE CODIGO=?;";

                    prepared = connection.prepareStatement(query);
                    prepared.setString(1, codigo);
                    prepared.execute();

                    query="DELETE FROM CARACTERISTICASVIVIENDA WHERE CODIGOVIVIENDA= ?;";

                    prepared = connection.prepareStatement(query);
                    prepared.setString(1, codigo);
                    prepared.execute();

                    query="DELETE FROM FOTOSVIVIENDA WHERE CODIGOVIVIENDA= ?;";

                    prepared = connection.prepareStatement(query);
                    prepared.setString(1, codigo);
                    prepared.execute();

                    query="DELETE FROM DUEñOVIVIENDA WHERE CODIGOVIVIENDA= ?;";

                    prepared = connection.prepareStatement(query);
                    prepared.setString(1, codigo);
                    prepared.execute();

                    query="DELETE FROM PRECIOSVIVIENDA WHERE CODIGOVIVIENDA= ?;";

                    prepared = connection.prepareStatement(query);
                    prepared.setString(1, codigo);
                    prepared.execute();

            }catch(Exception e){
                    e.printStackTrace();
            }
    }

    /**	Do a query that received from the parameter and return an array of strings with the result of the query.
     * 
     * @param query The query to execute
     * @return Array of strings with the result of the query
     */
    public String[] executeQueryOne(String query){
            String[] data=null;

            ArrayList<String> arrayList=new ArrayList<String>();

            try {
                    statement = connection.createStatement();
                    rs = statement.executeQuery(query);

                    while(rs.next()){
                            arrayList.add(rs.getString(1));
                    }

                    data=new String[arrayList.size()];
                    for (int i = 0; i < arrayList.size(); i++) {
                            data[i]=arrayList.get(i);
                    }
            } catch (SQLException e1) {
                    e1.printStackTrace();
            }

            return data;
    }

    /**	Do a query that received from the parameter and return an string with the result of the query
     * 
     * @param query The query to execute
     * @return Strings with the result of the query
     */
    public String executeQueryString(String query){
            String data=null;

            try {
                    statement = connection.createStatement();
                    rs = statement.executeQuery(query);

                    while(rs.next()){
                            data=rs.getString(1);
                    }

            } catch (SQLException e1) {
                    e1.printStackTrace();
            }

            return data;
    }

    /**	Do a query that received from the parameter and return an array of strings with heads of the query.
     * 
     * @param query The query to execute
     * @return Array of strings with the result of the query
     */
    public String[] executeQueryHeads(String query){
            String[] data=null;

            try {
                    statement = connection.createStatement();
                    rs = statement.executeQuery(query);
                    metadata=rs.getMetaData();
                    data=new String[metadata.getColumnCount()];
                    for (int i = 0; i < metadata.getColumnCount(); i++) {
                            data[i]=metadata.getColumnName(i+1);
                    }

            } catch (SQLException e1) {
                    e1.printStackTrace();
            }

            return data;
    }

    /**	Do a query that received from the parameter and return a matrix of strings with the result of the query.
     * 
     * @param query The query to execute
     * @return Matrix of strings with the result of the query
     */
    public String[][] executeQueryTwo(String query){
            String[][] data=null;

            ArrayList<String[]> arrayList=new ArrayList<String[]>();
            int columnas=0; //Number of columns from the table

            try {
                    statement = connection.createStatement();
                    rs = statement.executeQuery(query);
                    metadata= rs.getMetaData();
                    columnas=metadata.getColumnCount();

                    while(rs.next()){
                            String[] temp=new String[columnas];
                            for (int i = 0; i < temp.length; i++) {
                                    temp[i]=rs.getString(i+1);
                            }
                            arrayList.add(temp);
                    }
                    
                    if(arrayList.size()!=0){
                            data=new String[arrayList.size()][arrayList.get(0).length];
                            for (int i = 0; i < arrayList.size(); i++) {
                                    for (int j = 0; j < arrayList.get(0).length; j++) {
                                            data[i][j]=arrayList.get(i)[j];
                                    }
                            }
                    }
            } catch (SQLException e1) {
                    e1.printStackTrace();
            }

            return data;
    }

    /** Return the last apartment's number
     * 
     * @return Number of the last apartment
     */
    public String getLastApartmentNumber(){
            query="SELECT * FROM VIVIENDA ORDER BY CODIGO DESC LIMIT 1;";
            String lastNumber="0";

            try {
                    statement = connection.createStatement();
                    rs = statement.executeQuery(query);

                    if(rs.next()) lastNumber=rs.getString(1);

            } catch (SQLException e1) {
                    e1.printStackTrace();
            }

            return lastNumber;
    }

    /** Insert all the information of the class apartment
     * 
     * @param ap Class apartment with the information of the apartment
     */
    public void insertAparmentClass(Apartment ap){

            //Insert the apartment
            insertApartment(ap.getNombre(), ap.getNumeroCastral(), ap.getDireccion(), ap.getPoblacion());

            //Get the last apartment's number
            String lastNumber=getLastApartmentNumber();
////		
            //Insert photos
            if(ap.getFotos()!=null){
                for (int i = 0; i < ap.getFotos().length; i++) {
                    insertApartmentPhoto(lastNumber,ap.getFotos()[i]);
                }
            }

            //Insert apartment's owner
            insertOwner(lastNumber, ap.getNombreDueño(), ap.getTipoCuenta(), ap.getBanco(), ap.getNumeroCuenta(), ap.getCdu(), ap.getCorreo(), ap.getTelefonoDueño(), ap.getDocumento());

            //Insert apartment's characteristics
            insertAparmentCharacteristics(lastNumber, ap.getAmbientes(), ap.getHabitaciones(), ap.getBaños(), ap.getServicios(), ap.getHeladera(), ap.getFreezer(), ap.getMicroondas(), ap.getCafetera(), ap.getTostadora(), ap.getGasNatural(), ap.getGaraje(), ap.getEntradaAuto(), ap.getParrilla(), ap.getParque(), ap.getInternet(), ap.getTelefono(), ap.getLavadero(), ap.getJovenes(), ap.getCable(), ap.getCamas(), ap.getAireAcondicionado(), ap.getMetrosTerreno(), ap.getMetrosEdificados(), ap.getAlarma(), ap.getAlquiler(), ap.getVenta(), ap.getDueñoPesos(), ap.getDueñoDolares(), ap.getInmobiliariaPesos(), ap.getInmobiliariaDolares(), ap.getServicioBlanco(), ap.getPermuta(), ap.getAlquilerTemporario(), ap.getDescripcion(), ap.getWifi(), ap.getMascotas(),ap.getRejas(), ap.getEstacionamiento(), ap.getEstacionamientoTechado(), ap.getVentilador(), ap.getCalefon(), ap.getEstufa(), ap.getTermotanque(), ap.getCodigoArgenprop(), ap.getCodigoInmobiliaria(), ap.getTipoCasa());

            //Insert apartment's prices
            insertApartmentPrices(lastNumber, ap.getPrimeraQuincenaDiciembre(), ap.getPrimeraQuincenaEnero(), ap.getPrimeraQuincenaFebrero(), ap.getPrimeraQuincenaMarzo(), ap.getPrimeraQuincenaOtro(), ap.getSegundaQuincenaDiciembre(), ap.getSegundaQuincenaEnero(), ap.getSegundaQuincenaFebrero(), ap.getSegundaQuincenaMarzo(), ap.getSegundaQuincenaOtro(), ap.getMesOtro(), ap.getSemanaDiciembre(), ap.getSemanaEnero(), ap.getSemanaFebrero(), ap.getSemanaMarzo(), ap.getSemanaOtro(), ap.getDiaDiciembre(), ap.getDiaEnero(), ap.getDiaFebrero(), ap.getDiaMarzo(), ap.getDiaOtro());
    }

    /** Update all the information of the class apartment.
     * 
     * @param ap Class apartment with the information of the apartment
     */
    public void updateAparmentClass(Apartment ap){

            if(ap.getFotos()!=null){
                    ////PrincipalController.maximBar+=ap.getFotos().length;
            }
            ////PrincipalController.maximBar+=5;
            ////PrincipalController.progressBarLabel.setText("Insertando vivienda...");
            //Update the apartment
            updateApartment(ap.getCodigo(), ap.getNombre(), ap.getNumeroCastral(), ap.getDireccion(), ap.getPoblacion());
            ////PrincipalController.actualBar++;
            ////PrincipalController.setValueProgressBar();

            //Update photos
            ////PrincipalController.progressBarLabel.setText("Enviando fotos...");
            deletePhotos(ap.getCodigo());
            ////PrincipalController.actualBar++;
            ////PrincipalController.setValueProgressBar();

            if(ap.getFotos()!=null){
                for (int i = 0; i < ap.getFotos().length; i++) {
                    insertApartmentPhoto(ap.getCodigo(),ap.getFotos()[i]);
                    ////PrincipalController.actualBar++;
                    ////PrincipalController.setValueProgressBar();
                }
            }

            //Update apartment's owner
            updateOwner(ap.getCodigo(), ap.getNombreDueño(), ap.getTipoCuenta(), ap.getBanco(), ap.getNumeroCuenta(), ap.getCdu(), ap.getCorreo(), ap.getTelefonoDueño(), ap.getDocumento());
            ////PrincipalController.actualBar++;
            ////PrincipalController.setValueProgressBar();

            //Update apartment's characteristics
            updateAparmentCharacteristics(ap.getCodigo(), ap.getAmbientes(), ap.getHabitaciones(), ap.getBaños(), ap.getServicios(), ap.getHeladera(), ap.getFreezer(), ap.getMicroondas(), ap.getCafetera(), ap.getTostadora(), ap.getGasNatural(), ap.getGaraje(), ap.getEntradaAuto(), ap.getParrilla(), ap.getParque(), ap.getInternet(), ap.getTelefono(), ap.getLavadero(), ap.getJovenes(), ap.getCable(), ap.getCamas(), ap.getAireAcondicionado(), ap.getMetrosTerreno(), ap.getMetrosEdificados(), ap.getAlarma(), ap.getAlquiler(), ap.getVenta(), ap.getDueñoPesos(), ap.getDueñoDolares(), ap.getInmobiliariaPesos(), ap.getInmobiliariaDolares(), ap.getServicioBlanco(), ap.getPermuta(), ap.getAlquilerTemporario(), ap.getDescripcion(), ap.getWifi(), ap.getMascotas(),ap.getRejas(), ap.getEstacionamiento(), ap.getEstacionamientoTechado(), ap.getVentilador(), ap.getCalefon(), ap.getEstufa(), ap.getTermotanque(), ap.getCodigoArgenprop(), ap.getCodigoInmobiliaria(), ap.getTipoCasa());
            ////PrincipalController.actualBar++;
            ////PrincipalController.setValueProgressBar();

            //Insert apartment's prices
            updateApartmentPrices(ap.getCodigo(), ap.getPrimeraQuincenaDiciembre(), ap.getPrimeraQuincenaEnero(), ap.getPrimeraQuincenaFebrero(), ap.getPrimeraQuincenaMarzo(), ap.getPrimeraQuincenaOtro(), ap.getSegundaQuincenaDiciembre(), ap.getSegundaQuincenaEnero(), ap.getSegundaQuincenaFebrero(), ap.getSegundaQuincenaMarzo(), ap.getSegundaQuincenaOtro(), ap.getMesOtro(), ap.getSemanaDiciembre(), ap.getSemanaEnero(), ap.getSemanaFebrero(), ap.getSemanaMarzo(), ap.getSemanaOtro(), ap.getDiaDiciembre(), ap.getDiaEnero(), ap.getDiaFebrero(), ap.getDiaMarzo(), ap.getDiaOtro());
            ////PrincipalController.actualBar++;
            ////PrincipalController.setValueProgressBar();

    }

    /**	Get the apartment with the code of the parameter.
     * 
     * @param codigo Code of the apartment
     * @return Apartment
     */
    public Apartment getApartmentNumber(String codigo){

            query="SELECT * FROM VIVIENDA, DUEñOVIVIENDA, CARACTERISTICASVIVIENDA, PRECIOSVIVIENDA WHERE VIVIENDA.CODIGO = DUEñOVIVIENDA.CODIGOVIVIENDA AND VIVIENDA.CODIGO = CARACTERISTICASVIVIENDA.CODIGOVIVIENDA  AND VIVIENDA.CODIGO = PRECIOSVIVIENDA.CODIGOVIVIENDA AND VIVIENDA.CODIGO="+codigo;

            Apartment ap;
            String[][] temp1=executeQueryTwo(query);
            String[] temp=new String[temp1[0].length];
            for (int i = 0; i < temp.length; i++) {
                temp[i]=temp1[0][i];
            }
            int num=0;
            ap = new Apartment();

            ap.setCodigo(temp[num]);
            ap.setNombre(temp[++num]);
            ap.setNumeroCastral(temp[++num]);
            ap.setDireccion(temp[++num]);
            ap.setPoblacion(temp[++num]);
            ++num;

            ap.setNombreDueño(temp[++num]);
            ap.setTipoCuenta(temp[++num]);
            ap.setBanco(temp[++num]);
            ap.setNumeroCuenta(temp[++num]);
            ap.setCdu(temp[++num]);
            ap.setCorreo(temp[++num]);
            ap.setTelefonoDueño(temp[++num]);
            ap.setDocumento(temp[++num]);
            ++num;

            ap.setAmbientes(temp[++num]);
            ap.setHabitaciones(temp[++num]);
            ap.setBaños(temp[++num]);
            ap.setServicios(temp[++num]);
            ap.setHeladera(temp[++num]);
            ap.setFreezer(temp[++num]);
            ap.setMicroondas(temp[++num]);
            ap.setCafetera(temp[++num]);
            ap.setTostadora(temp[++num]);
            ap.setGasNatural(temp[++num]);
            ap.setGaraje(temp[++num]);
            ap.setEntradaAuto(temp[++num]);
            ap.setParrilla(temp[++num]);
            ap.setParque(temp[++num]);
            ap.setInternet(temp[++num]);
            ap.setTelefono(temp[++num]);
            ap.setLavadero(temp[++num]);
            ap.setJovenes(temp[++num]);
            ap.setCable(temp[++num]);
            ap.setCamas(temp[++num]);
            ap.setAireAcondicionado(temp[++num]);
            ap.setMetrosTerreno(temp[++num]);
            ap.setMetrosEdificados(temp[++num]);
            ap.setAlquiler(temp[++num]);
            ap.setVenta(temp[++num]);
            ap.setDueñoPesos(temp[++num]);
            ap.setDueñoDolares(temp[++num]);
            ap.setInmobiliariaPesos(temp[++num]);
            ap.setInmobiliariaDolares(temp[++num]);
            ap.setAlarma(temp[++num]);
            ap.setServicioBlanco(temp[++num]);
            ap.setPermuta(temp[++num]);
            ap.setAlquilerTemporario(temp[++num]);
            ap.setDescripcion(temp[++num]);
            ap.setWifi(temp[++num]);
            ap.setMascotas(temp[++num]);
            ap.setRejas(temp[++num]);
            ap.setEstacionamiento(temp[++num]);
            ap.setEstacionamientoTechado(temp[++num]);
            ap.setVentilador(temp[++num]);
            ap.setCalefon(temp[++num]);
            ap.setEstufa(temp[++num]);
            ap.setTermotanque(temp[++num]);
            ap.setCodigoArgenprop(temp[++num]);
            ap.setCodigoInmobiliaria(temp[++num]);
            ap.setTipoCasa(temp[++num]);
            ap.setFotos(getPhotos(ap.getCodigo()));
            ++num;

            ap.setPrimeraQuincenaDiciembre (temp[++num]);
            ap.setPrimeraQuincenaEnero (temp[++num]);
            ap.setPrimeraQuincenaFebrero (temp[++num]);
            ap.setPrimeraQuincenaMarzo(temp[++num]);
            ap.setPrimeraQuincenaOtro(temp[++num]);
            ap.setSegundaQuincenaDiciembre(temp[++num]);
            ap.setSegundaQuincenaEnero(temp[++num]);
            ap.setSegundaQuincenaFebrero(temp[++num]);
            ap.setSegundaQuincenaMarzo(temp[++num]);
            ap.setSegundaQuincenaOtro(temp[++num]);
            ap.setMesOtro(temp[++num]);
            ap.setSemanaDiciembre(temp[++num]);
            ap.setSemanaEnero(temp[++num]);
            ap.setSemanaFebrero(temp[++num]);
            ap.setSemanaMarzo(temp[++num]);
            ap.setSemanaOtro(temp[++num]);
            ap.setDiaDiciembre(temp[++num]);
            ap.setDiaEnero(temp[++num]);
            ap.setDiaFebrero(temp[++num]);
            ap.setDiaMarzo(temp[++num]);
            ap.setDiaOtro(temp[++num]);

            if(!this.executeQueryString("SELECT COUNT(*) FROM CONFIGURACION WHERE CLAVE=\'SABANADOSPLAZAS\'").equals("0")){
                    ap.setSabanaUnaPlaza(this.executeQueryString("SELECT VALOR FROM CONFIGURACION WHERE CLAVE=\'SABANAUNAPLAZA\'"));
            }else ap.setSabanaUnaPlaza("0");

            if(!this.executeQueryString("SELECT COUNT(*) FROM CONFIGURACION WHERE CLAVE=\'SABANADOSPLAZAS\'").equals("0")){
                    ap.setSabanaDosPlazas(this.executeQueryString("SELECT VALOR FROM CONFIGURACION WHERE CLAVE=\'SABANADOSPLAZAS\'"));
            }else ap.setSabanaDosPlazas("0");

            if(!this.executeQueryString("SELECT COUNT(*) FROM CONFIGURACION WHERE CLAVE=\'TOALLAS\'").equals("0")){
                    ap.setToallas(this.executeQueryString("SELECT VALOR FROM CONFIGURACION WHERE CLAVE=\'TOALLAS\'"));
            }else ap.setToallas("0");

            return ap;
    }

    /**Get the count of apartments with a filter
     * 
     * @param poblacion
     * @param camas
     * @param alquiler
     * @param alquilerTemporario
     * @param venta
     * @param parrilla
     * @param exterior
     * @param jovenes
     * @param alarma
     * @param garaje
     * @param minim
     * @param maxim
     * @return Number of apartments
     */
    public int getCountApartments(String poblacion, int camas, boolean alquiler, boolean alquilerTemporario, boolean venta, boolean parrilla, boolean exterior, boolean jovenes, boolean alarma, boolean garaje, int minim, int maxim, String buscar, String tipoCasa){
            query="SELECT COUNT(*) FROM VIVIENDA, DUEñOVIVIENDA, CARACTERISTICASVIVIENDA, PRECIOSVIVIENDA WHERE VIVIENDA.CODIGO = DUEñOVIVIENDA.CODIGOVIVIENDA AND VIVIENDA.CODIGO = CARACTERISTICASVIVIENDA.CODIGOVIVIENDA AND VIVIENDA.CODIGO = PRECIOSVIVIENDA.CODIGOVIVIENDA";
            if(!buscar.trim().equals("")){
                query+=" AND (VIVIENDA.NOMBRE LIKE '%"+buscar+"%'";
                query+=" OR VIVIENDA.DIRECCION LIKE '%"+buscar+"%')";
            }else{
                if((poblacion!=null)&&!poblacion.equals("Todos")&&!(poblacion.equals(""))) query+=" AND VIVIENDA.POBLACION='"+poblacion+"'";
                if((tipoCasa!=null)&&!tipoCasa.equals("Todos")&&!(poblacion.equals(""))) query+=" AND CARACTERISTICASVIVIENDA.TIPOCASA='"+tipoCasa+"'";
                if(camas!=0) query+=" AND CARACTERISTICASVIVIENDA.CAMAS>="+camas;
                if(alquilerTemporario) query+=" AND CARACTERISTICASVIVIENDA.ALQUILERTEMPORARIO="+Convert.toInt(alquilerTemporario);
                if(venta) query+=" AND CARACTERISTICASVIVIENDA.VENTA="+Convert.toInt(venta);
                if(parrilla) query+=" AND CARACTERISTICASVIVIENDA.PARRILLA="+Convert.toInt(parrilla);
                if(alquiler) query+=" AND CARACTERISTICASVIVIENDA.ALQUILER="+Convert.toInt(alquiler);
                if(exterior) query+=" AND CARACTERISTICASVIVIENDA.PARQUE="+Convert.toInt(exterior);
                if(jovenes) query+=" AND CARACTERISTICASVIVIENDA.JOVENES="+Convert.toInt(jovenes);
                if(alarma) query+=" AND CARACTERISTICASVIVIENDA.ALARMA="+Convert.toInt(alarma);
                if(garaje) query+=" AND (CARACTERISTICASVIVIENDA.GARAJE="+Convert.toInt(garaje)+" OR CARACTERISTICASVIVIENDA.ENTRADAAUTO="+Convert.toInt(garaje)+")";
            }

            String[][] temp=executeQueryTwo(query);
            return Convert.toInt(temp[0][0]);
    }

    /**Get all the apartments with a filter
     * 
     * @param poblacion
     * @param camas
     * @param alquiler
     * @param alquilerTemporario
     * @param venta
     * @param parrilla
     * @param exterior
     * @param jovenes
     * @param alarma
     * @param garaje
     * @param minim
     * @param maxim
     * @return Array of apartments
     */
    public Apartment[] getApartments(String poblacion, int camas, boolean alquiler, boolean alquilerTemporario, boolean venta, boolean parrilla, boolean exterior, boolean jovenes, boolean alarma, boolean garaje, int minim, int maxim, String order, boolean desc, String buscar, String tipoCasa){
        query="SELECT * FROM VIVIENDA, DUEñOVIVIENDA, CARACTERISTICASVIVIENDA, PRECIOSVIVIENDA WHERE VIVIENDA.CODIGO = DUEñOVIVIENDA.CODIGOVIVIENDA AND VIVIENDA.CODIGO = CARACTERISTICASVIVIENDA.CODIGOVIVIENDA AND VIVIENDA.CODIGO = PRECIOSVIVIENDA.CODIGOVIVIENDA";
        if(!buscar.trim().equals("")){
            query+=" AND (VIVIENDA.NOMBRE LIKE '%"+buscar+"%'";
            query+=" OR VIVIENDA.DIRECCION LIKE '%"+buscar+"%'";
            query+=" OR CARACTERISTICASVIVIENDA.CODIGOINMOBILIARIA LIKE '%"+buscar+"%'";
            query+=" OR CARACTERISTICASVIVIENDA.CODIGOARGENPROP LIKE '%"+buscar+"%'";
            query+=")";
        }else{
            if((poblacion!=null)&&!poblacion.equals("Todos")&&!(poblacion.equals(""))) query+=" AND VIVIENDA.POBLACION='"+poblacion+"'";
            if((tipoCasa!=null)&&!tipoCasa.equals("Todos")&&!(poblacion.equals(""))) query+=" AND CARACTERISTICASVIVIENDA.TIPOCASA='"+tipoCasa+"'";
            if(camas!=0) query+=" AND CARACTERISTICASVIVIENDA.CAMAS>="+camas;
            if(alquilerTemporario) query+=" AND CARACTERISTICASVIVIENDA.ALQUILERTEMPORARIO="+Convert.toInt(alquilerTemporario);
            if(venta) query+=" AND CARACTERISTICASVIVIENDA.VENTA="+Convert.toInt(venta);
            if(parrilla) query+=" AND CARACTERISTICASVIVIENDA.PARRILLA="+Convert.toInt(parrilla);
            if(alquiler) query+=" AND CARACTERISTICASVIVIENDA.ALQUILER="+Convert.toInt(alquiler);
            if(exterior) query+=" AND CARACTERISTICASVIVIENDA.PARQUE="+Convert.toInt(exterior);
            if(jovenes) query+=" AND CARACTERISTICASVIVIENDA.JOVENES="+Convert.toInt(jovenes);
            if(alarma) query+=" AND CARACTERISTICASVIVIENDA.ALARMA="+Convert.toInt(alarma);
            if(garaje) query+=" AND (CARACTERISTICASVIVIENDA.GARAJE="+Convert.toInt(garaje)+" OR CARACTERISTICASVIVIENDA.ENTRADAAUTO="+Convert.toInt(garaje)+")";
        }

        query+=" ORDER BY ";

        if(order.equals("Creación")) query+="VIVIENDA.CODIGO";
        else if (order.equals("Nombre")) query+="VIVIENDA.NOMBRE";
        else if (order.equals("Población")) query+="VIVIENDA.POBLACION";
        else if (order.equals("Camas")) query+="CARACTERISTICASVIVIENDA.CAMAS";
        if(desc) query+=" DESC";
        else query+=" ASC";
        query+=" LIMIT "+minim+","+maxim+"";
        Apartment[] ap=null;
        String[][] temp=executeQueryTwo(query);

        if(temp==null){
                ap=new Apartment[1];
                ap[0]=new Apartment();
                ap[0].setNombre("No se han encontrado viviendas con estas características");
        }else{
                if(activated){
                        ////PrincipalController.maximBar+=(temp.length*2);
                        for (int i = 0; i < temp.length; i++) {
                                String[][] count=executeQueryTwo("SELECT COUNT(*) FROM FOTOSVIVIENDA WHERE CODIGOVIVIENDA="+temp[i][0]);
                                ////PrincipalController.maximBar+=Convert.toInt(count[0][0]);
                        }
                        ////PrincipalController.progressBarLabel.setText("Cargando viviendas...");
                }
                ap = new Apartment[temp.length];
                for (int i = 0; i < temp.length; i++) {
                        int num=0;
                        ap[i]=new Apartment();
                        ap[i].setCodigo(temp[i][num]);
                        ap[i].setNombre(temp[i][++num]);
                        if(ap[i].getNombre().equals("")) ap[i].setNombre("Vivienda sin nombre");
                        ap[i].setNumeroCastral(temp[i][++num]);
                        ap[i].setDireccion(temp[i][++num]);
                        ap[i].setPoblacion(temp[i][++num]);
                        ++num;

                        ap[i].setNombreDueño(temp[i][++num]);
                        ap[i].setTipoCuenta(temp[i][++num]);
                        ap[i].setBanco(temp[i][++num]);
                        ap[i].setNumeroCuenta(temp[i][++num]);
                        ap[i].setCdu(temp[i][++num]);
                        ap[i].setCorreo(temp[i][++num]);
                        ap[i].setTelefonoDueño(temp[i][++num]);
                        ap[i].setDocumento(temp[i][++num]);
                        ++num;

                        ap[i].setAmbientes(temp[i][++num]);
                        ap[i].setHabitaciones(temp[i][++num]);
                        ap[i].setBaños(temp[i][++num]);
                        ap[i].setServicios(temp[i][++num]);
                        ap[i].setHeladera(temp[i][++num]);
                        ap[i].setFreezer(temp[i][++num]);
                        ap[i].setMicroondas(temp[i][++num]);
                        ap[i].setCafetera(temp[i][++num]);
                        ap[i].setTostadora(temp[i][++num]);
                        ap[i].setGasNatural(temp[i][++num]);
                        ap[i].setGaraje(temp[i][++num]);
                        ap[i].setEntradaAuto(temp[i][++num]);
                        ap[i].setParrilla(temp[i][++num]);
                        ap[i].setParque(temp[i][++num]);
                        ap[i].setInternet(temp[i][++num]);
                        ap[i].setTelefono(temp[i][++num]);
                        ap[i].setLavadero(temp[i][++num]);
                        ap[i].setJovenes(temp[i][++num]);
                        ap[i].setCable(temp[i][++num]);
                        ap[i].setCamas(temp[i][++num]);
                        ap[i].setAireAcondicionado(temp[i][++num]);
                        ap[i].setMetrosTerreno(temp[i][++num]);
                        ap[i].setMetrosEdificados(temp[i][++num]);
                        ap[i].setAlquiler(temp[i][++num]);
                        ap[i].setVenta(temp[i][++num]);
                        ap[i].setDueñoPesos(temp[i][++num]);
                        ap[i].setDueñoDolares(temp[i][++num]);
                        ap[i].setInmobiliariaPesos(temp[i][++num]);
                        ap[i].setInmobiliariaDolares(temp[i][++num]);
                        ap[i].setAlarma(temp[i][++num]);
                        ap[i].setServicioBlanco(temp[i][++num]);
                        ap[i].setPermuta(temp[i][++num]);
                        ap[i].setAlquilerTemporario(temp[i][++num]);
                        ap[i].setDescripcion(temp[i][++num]);
                        ap[i].setWifi(temp[i][++num]);
                        ap[i].setMascotas(temp[i][++num]);
                        ap[i].setRejas(temp[i][++num]);
                        ap[i].setEstacionamiento(temp[i][++num]);
                        ap[i].setEstacionamientoTechado(temp[i][++num]);
                        ap[i].setVentilador(temp[i][++num]);
                        ap[i].setCalefon(temp[i][++num]);
                        ap[i].setEstufa(temp[i][++num]);
                        ap[i].setTermotanque(temp[i][++num]);
                        ap[i].setCodigoArgenprop(temp[i][++num]);
                        ap[i].setCodigoInmobiliaria(temp[i][++num]);
                        ap[i].setTipoCasa(temp[i][++num]);
                        ++num;

                        ap[i].setPrimeraQuincenaDiciembre (temp[i][++num]);
                        ap[i].setPrimeraQuincenaEnero (temp[i][++num]);
                        ap[i].setPrimeraQuincenaFebrero (temp[i][++num]);
                        ap[i].setPrimeraQuincenaMarzo(temp[i][++num]);
                        ap[i].setPrimeraQuincenaOtro(temp[i][++num]);
                        ap[i].setSegundaQuincenaDiciembre(temp[i][++num]);
                        ap[i].setSegundaQuincenaEnero(temp[i][++num]);
                        ap[i].setSegundaQuincenaFebrero(temp[i][++num]);
                        ap[i].setSegundaQuincenaMarzo(temp[i][++num]);
                        ap[i].setSegundaQuincenaOtro(temp[i][++num]);
                        ap[i].setMesOtro(temp[i][++num]);
                        ap[i].setSemanaDiciembre(temp[i][++num]);
                        ap[i].setSemanaEnero(temp[i][++num]);
                        ap[i].setSemanaFebrero(temp[i][++num]);
                        ap[i].setSemanaMarzo(temp[i][++num]);
                        ap[i].setSemanaOtro(temp[i][++num]);
                        ap[i].setDiaDiciembre(temp[i][++num]);
                        ap[i].setDiaEnero(temp[i][++num]);
                        ap[i].setDiaFebrero(temp[i][++num]);
                        ap[i].setDiaMarzo(temp[i][++num]);
                        ap[i].setDiaOtro(temp[i][++num]);

                        if(!this.executeQueryString("SELECT COUNT(*) FROM CONFIGURACION WHERE CLAVE=\'SABANADOSPLAZAS\'").equals("0")){
                                ap[i].setSabanaUnaPlaza(this.executeQueryString("SELECT VALOR FROM CONFIGURACION WHERE CLAVE=\'SABANAUNAPLAZA\'"));
                        }else ap[i].setSabanaUnaPlaza("0");

                        if(!this.executeQueryString("SELECT COUNT(*) FROM CONFIGURACION WHERE CLAVE=\'SABANADOSPLAZAS\'").equals("0")){
                                ap[i].setSabanaDosPlazas(this.executeQueryString("SELECT VALOR FROM CONFIGURACION WHERE CLAVE=\'SABANADOSPLAZAS\'"));
                        }else ap[i].setSabanaDosPlazas("0");

                        if(!this.executeQueryString("SELECT COUNT(*) FROM CONFIGURACION WHERE CLAVE=\'TOALLAS\'").equals("0")){
                                ap[i].setToallas(this.executeQueryString("SELECT VALOR FROM CONFIGURACION WHERE CLAVE=\'TOALLAS\'"));
                        }else ap[i].setToallas("0");

                        String[] x=getPhotos(ap[i].getCodigo());
                        if(x!=null){
                                ap[i].setFotos(x);
                        }
                        if(activated){
                            ////PrincipalController.actualBar++;
                            ////PrincipalController.setValueProgressBar();
                        }
                }
        }
        return ap;
    }

    /**Is it's activated the progress bar will work
     * 
     * @param activated 
     */
    public void setActivated(boolean activated){
            this.activated=activated;
    }

    /**Get the activated value
     * 
     * @return activated
     */
    public boolean getActivated(){
            return this.activated;
    }

    /**Get all the photos of an apartment
     * 
     * @param codigo
     * @return Array of strings with the photos
     */
    public String [] getPhotos(String codigo){
        String[] photos=null;
        try{
            String[][] temp=executeQueryTwo("SELECT COUNT(*) FROM FOTOSVIVIENDA WHERE CODIGOVIVIENDA="+codigo);
            if(!temp[0][0].equals("0")){
                File file=new File(".");
                String pathRoot=file.getCanonicalPath()+"/cache/";
                temp=executeQueryTwo("SELECT * FROM FOTOSVIVIENDA WHERE CODIGOVIVIENDA="+codigo);
                photos=new String[temp.length];
                for (int i = 0; i < photos.length; i++) {
                    photos[i]=temp[i][1];
                    file=new File(pathRoot+photos[i]);

                    if(!file.exists()){
                        cws=new ConnectWithServer(server, port, "get", photos[i]);
                    }
                    photos[i]=pathRoot+photos[i];
                    Size size=new Size(photos[i]);
                    size.resize();
                }
                if(activated){
                    ////PrincipalController.progressBarLabel.setText("Descargando fotos...");
                    ////PrincipalController.actualBar++;
                    ////PrincipalController.setValueProgressBar();
                }
            }
        }catch(Exception e){
                e.printStackTrace();
        }

        return photos;
    }

    /**Close all the connections to the database
     * 
     */
    public void disconnect(){
            try{
                    if(statement!=null) statement.close();
                    if(prepared!=null) prepared.close();
                    if(rs!=null) rs.close();
                    if(connection!=null) connection.close();
            }catch(Exception e){
                    e.printStackTrace();
            }
    }
}
