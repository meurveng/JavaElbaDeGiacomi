package Model;
import javax.swing.JEditorPane;


public class JEditorPaneCode extends JEditorPane{

	//Own
	private static final long serialVersionUID = 1L;
	private String code;

	/**Get the identificator
	 * 
	 * @return The identificator
	 */
	public String getCode() {
		return code;
	}
	
	/**Set the identificator
	 * 
	 * @param code The identificator
	 */
	public void setCode(String code) {
		this.code = code;
	}
	
	/**Set the JEditorPane to edit HTML and won't editable
	 * 
	 */
	public void setOptions(){
		this.setContentType("text/html");
		this.setEditable(false);
	}
	
	/**Insert an image in the html code with the path of the parameter
	 * 
	 * @param path Path of the image
	 */
	public void setImage(String path){
		this.code=path;
		this.setText("<img src=\"file:///"+path+"\"/>");
	}
	
	/**Insert an image in the html code with the path, width and height of parameters
	 * 
	 * @param path Path of the image
	 * @param width Width of the image
	 * @param height Height of the image
	 */
	public void setImage(String path, int width, int height){
		this.code=path;
		this.setText("<img src=\"file:///"+path+"\" width=\""+width+"\" height=\""+height+"\"/>");
	}
}
