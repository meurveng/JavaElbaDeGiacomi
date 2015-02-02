package Model;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.imageio.ImageIO;

public class Size {
	
	//Own
	private double x;
	private int height;
	private int width;
        private String path;
	
	/**Get the width, the height and the relation of aspect of the image
	 * 
	 * @param path Path of the image
	 */
	public Size(String path){
		try {
			BufferedImage bimg = ImageIO.read(new File(path));
                        this.path=path;
			this.width=bimg.getWidth();
			this.height=bimg.getHeight();
			x=(double)((double)width/(double)this.height);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
        
        public String resizeCache(){
            String output="";
            try {
                output=path.substring(0, path.lastIndexOf("."))+"_min."+path.substring(path.indexOf(".")+1);
                File fileRoot=new File(".");
                File file = new File(output);
                file=new File(fileRoot.getCanonicalPath()+"/cache/"+file.getName());
                output=file.getCanonicalPath();
                if(!file.exists()){
                    int height;
                    int width;
                    if(this.getWidth()>this.getHeight()){
                        height=150;
                        width=this.getWidth(height);
                    }else{
                        width=150;
                        height=this.getHeight(width);
                    }

                    BufferedImage bim=ImageIO.read(new FileInputStream(path));
                    Image resizedImage=bim.getScaledInstance(width,height,Image.SCALE_FAST);
                    BufferedImage rBimg=new BufferedImage(width,height,bim.getType());

                    Graphics2D g=rBimg.createGraphics();
                    g.drawImage(resizedImage,0,0,null);
                    g.dispose();
                    
                    int x=0;
                    int y=0;
                    if(rBimg.getWidth()>rBimg.getHeight()){
                        x=(rBimg.getWidth()-150)/2;
                        rBimg=rBimg.getSubimage(x, y, 150, 150);
                    }else{
                        y=(rBimg.getHeight()-150)/2;
                        rBimg=rBimg.getSubimage(x, y, 150, 150);
                    }
                    ImageIO.write(rBimg,output.substring(output.indexOf(".")+1),new FileOutputStream(output));
                }
            } catch (Exception e) {
            }
            return output;
        }
	public void resize(){
            try {
                String output=path.substring(0, path.lastIndexOf("."))+"_min."+path.substring(path.indexOf(".")+1);
                File file = new File(output);
                if(!file.exists()){
                    int height;
                    int width;
                    if(this.getWidth()>this.getHeight()){
                        height=150;
                        width=this.getWidth(height);
                    }else{
                        width=150;
                        height=this.getHeight(width);
                    }

                    BufferedImage bim=ImageIO.read(new FileInputStream(path));
                    Image resizedImage=bim.getScaledInstance(width,height,Image.SCALE_FAST);
                    BufferedImage rBimg=new BufferedImage(width,height,bim.getType());

                    Graphics2D g=rBimg.createGraphics();
                    g.drawImage(resizedImage,0,0,null);
                    g.dispose();
                    //Recorto la imagen para que todas tengan 150x150
                    int x=0;
                    int y=0;
                    if(rBimg.getWidth()>rBimg.getHeight()){
                        x=(rBimg.getWidth()-150)/2;
                        rBimg=rBimg.getSubimage(x, y, 150, 150);
                    }else{
                        y=(rBimg.getHeight()-150)/2;
                        rBimg=rBimg.getSubimage(x, y, 150, 150);
                    }
                    ImageIO.write(rBimg,output.substring(output.indexOf(".")+1),new FileOutputStream(output));
                }
            } catch (Exception e) {
            }
        }
	/**Return the height of the image
	 * 
	 * @return Height of the image
	 */
	public int getHeight(){
		return this.height;
	}
	
	/**Return the width of the image
	 * 
	 * @return Width of the image
	 */
	public int getWidth(){
		return this.width;
	}
	
	/**Calculate the height of the image given the width keeeping the relation of aspect
	 * 
	 * @param z Width of the image
	 * @return The height of the image
	 */
	public int getHeight(double z){
		return (int)(z/x);
	}
	
	/**Calculate the height of the image given the width keeeping the relation of aspect
	 * 
	 * @param z Width of the image
	 * @return The height of the image
	 */
	public int getHeight(int z){
		return (int)(z/x);
	}
	
	/**Calculate the width of the image given the height keeeping the relation of aspect
	 * 
	 * @param z Height of the image
	 * @return The width of the image
	 */
	public int getWidth(double z){
		return (int)(z*x);
	}
	
	/**Calculate the width of the image given the height keeeping the relation of aspect
	 * 
	 * @param z Height of the image
	 * @return The width of the image
	 */
	public int getWidth(int z){
		return (int)(z*x);
	}
}
