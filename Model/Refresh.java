package Model;

public class Refresh extends Thread{
	
	/**Constructor of the Thread
	 * 
	 */
	public Refresh(){
		super();
	}
	
	/**Refresh the window every X seconds
	 * 
	 * @param seconds Time that will wait until refresh the window
	 */
	public void run(int seconds){
		try{
			while(true){
				sleep(seconds*1000);
////				PrincipalController.generateAll();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
