package Model;
import java.io.File;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;


public class XML {
	
	//Own
	private String path;
	private DocumentBuilderFactory docFactory;
	private DocumentBuilder docBuilder;
	private Document doc;
	
	/**Create a document XML if not exists
	 * 
	 */
	public XML(){
		try{
			docFactory = DocumentBuilderFactory.newInstance();
		    docBuilder = docFactory.newDocumentBuilder();
			//Check if the path exists
			File file=new File(".");
			path=file.getCanonicalPath()+"/conf/";
			file=new File(path);
			if(!file.exists()) file.mkdir();
			path=path+"conf.xml";
			file=new File(path);
			if(!file.exists()) writeXML();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**Read and XML and get the configuration of the program
	 * 
	 * @return Array of string with the XML's data
	 */
	public String[] readXml(){
		String[] configuration=null;
		try{
			configuration=new String[5];
		    doc = docBuilder.parse (new File(path));
	        configuration[0]=doc.getElementsByTagName("server").item(0).getTextContent().trim();
	        configuration[1]=doc.getElementsByTagName("dataBase").item(0).getTextContent().trim();
	        configuration[2]=doc.getElementsByTagName("user").item(0).getTextContent().trim();
	        configuration[3]=doc.getElementsByTagName("password").item(0).getTextContent().trim();
	        configuration[4]=doc.getElementsByTagName("port").item(0).getTextContent().trim();
		}catch(Exception e){
			e.printStackTrace();
		}
		return configuration;
	}
	
	/**Write and empty XML document
	 * 
	 */
	public void writeXML(){
		try{
			doc = docBuilder.newDocument();
			
			//Create root element
			Element rootElement = doc.createElement("configuration");
			doc.appendChild(rootElement);
			
			//Create childs
			Element server = doc.createElement("server");
			rootElement.appendChild(server);
			
			Element dataBase = doc.createElement("dataBase");
			rootElement.appendChild(dataBase);
			
			Element user = doc.createElement("user");
			rootElement.appendChild(user);
			
			Element password = doc.createElement("password");
			rootElement.appendChild(password);
			
			Element port = doc.createElement("port");
			rootElement.appendChild(port);
			
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(path));
			transformer.transform(source, result);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**Write a XML's document with the configuration
	 * 
	 * @param newServer IP or hostname of the server
	 * @param newDataBase Name of the database
	 * @param newUser Name of the user
	 * @param newPassword Password of the user
	 * @param newPort Port of the server with the application's server
	 */
	public void writeXML(String newServer, String newDataBase, String newUser, String newPassword, String newPort){
		try{
			doc = docBuilder.newDocument();
			
			//Create root element
			Element rootElement = doc.createElement("configuration");
			doc.appendChild(rootElement);
			
			//Create childs
			Element server = doc.createElement("server");
			server.appendChild(doc.createTextNode(newServer));
			rootElement.appendChild(server);
			
			Element dataBase = doc.createElement("dataBase");
			dataBase.appendChild(doc.createTextNode(newDataBase));
			rootElement.appendChild(dataBase);
			
			Element user = doc.createElement("user");
			user.appendChild(doc.createTextNode(newUser));
			rootElement.appendChild(user);
			
			Element password = doc.createElement("password");
			password.appendChild(doc.createTextNode(newPassword));
			rootElement.appendChild(password);
			
			Element port = doc.createElement("port");
			port.appendChild(doc.createTextNode(newPort));
			rootElement.appendChild(port);
			
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(path));
			transformer.transform(source, result);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
