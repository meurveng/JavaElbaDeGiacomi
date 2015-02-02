package Model;

import java.io.*;
import java.net.Socket;

import javax.swing.JOptionPane;


public class ConnectWithServer{
	
	//Own
	private Socket socket;
    private DataOutputStream outData;
    private OutputStream outStream;
    private BufferedReader bufferReader;
    private DataInputStream inData;
    private String pathRoot;
    private InputStream inStream;
    private File file;
    private String server;
    private int port;
    private String order;
    private String filePath;
    
    /**	Send or get a photo from the server with the IP and the port
     * 
     * @param server Server's IP
     * @param port	Server's port
     * @param order	send or get
     * @param path	File's path or name
     */
    public ConnectWithServer(String server, int port, String order, String filePath) {
    	try{
    		this.server=server;
        	this.port=port;
        	this.order=order;
            this.filePath=filePath;
            
            file=new File(".");
            this.pathRoot=file.getCanonicalPath()+"/cache/";
            file=new File(pathRoot);
            if(!file.exists()) file.mkdir();
          
    		socket = new Socket(server, port);
    		outData = new DataOutputStream(socket.getOutputStream());
    		inData = new DataInputStream(socket.getInputStream());
        	
    		
    		if(order.equals("get")) getPhoto();
			else if(order.equals("send")) sendPhoto();
			else System.out.println("Incorrect order");
        	disconnect();
    	} catch (Exception e) {
            e.printStackTrace();
////    		JOptionPane.showMessageDialog(PrincipalController.view, "No se ha podido establecer conexión con la aplicación de servidor.", "Error de conexión", JOptionPane.ERROR_MESSAGE);
////			PrincipalController.close();
		}
    }
    
    /**Get the path where there are the photos
     * 
     * @return The path where there are the photos
     */
    public String getPathRoot(){
    	return this.pathRoot;
    }
    
    /**Connect with the server and get a photo
     * 
     */
    public void getPhoto(){
    	try{
    		do{
    			//Send order(send or get)
        		outData.writeUTF(order);
        		
        		//Send file's path
    			outData.writeUTF(filePath);
    			
        		String path=pathRoot+filePath;
        		if (inData != null && outData != null) {
                	inStream=socket.getInputStream();
    		    	file = new File (path);
    		    	if(file.length()==0) file.delete();
    		    	if(!file.exists()){
    		    		outStream = new FileOutputStream (file);
    			    	int numberBytes = inData.available (); //Num de bytes que pueden ser leidos sin bloqueo
    			    	byte buf[] = new byte [1024];
    			    	while ((numberBytes=inData.read(buf)) >= 0 ) {
    			    		outStream.write (buf, 0, numberBytes);
    			    	}
    		    	}
    		    }
    		}while(file.length()==0);
    		
    	} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    /**Connect with the server and send a photo
     * 
     */
	public void sendPhoto(){
		try {
			//Send order(send or get)
    		outData.writeUTF(order);
    		
			//Send file's path
			outData.writeUTF(filePath);
			
			outStream = socket.getOutputStream();
			bufferReader = new BufferedReader( new InputStreamReader(socket.getInputStream()) );
			if (socket != null && bufferReader != null && outStream != null) {
				file = new File (filePath);
				if (!file.canRead()) System.out.println ("Can\'t read the file");
				InputStream sin = new FileInputStream (file);
				int n = sin.available (); //Num de bytes que pueden ser leidos sin bloqueo
				byte buf[] = new byte [1024];
				while ((n=sin.read(buf)) >= 0){
					outStream.write (buf, 0, n);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	
	/**Close all the connections to the server
	 * 
	 */
	public void disconnect() {
        try {
        	if(outData!=null) outData.close();
        	if(inData!=null) inData.close();
        	if(bufferReader!=null) bufferReader.close();
        	if(outStream!=null){
        		outStream.flush();
        		outStream.close();
        	}
        	if(inStream!=null) inStream.close();
        	if(socket!=null) socket.close();
        } catch (Exception e) {
			e.printStackTrace();
		}
    }
}