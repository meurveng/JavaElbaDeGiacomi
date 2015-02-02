package Model;

public class Convert {
	
	/**Convert a String into a boolean
	 * 
	 * @param data String with the data
	 * @return A boolean
	 */
	public static boolean toBoolean(String data){
		if(data.equals("true")||data.equals("1")) return true;
		else return false;
	}
	
        public static String toSiNo(boolean data){
            if(data) return "Si";
            else return "No";
        }
        
	/**Convert a int into a boolean
	 * 
	 * @param data int with the data
	 * @return A boolean
	 */
	public static boolean toBoolean(int data){
		if(data==1) return true;
		else return false;
	}
	
	/**Convert a double into a boolean
	 * 
	 * @param data double with the data
	 * @return A boolean
	 */
	public static boolean toBoolean(double data){
		if(data==1) return true;
		else return false;
	}
	
	/**Convert a float into a boolean
	 * 
	 * @param data float with the data
	 * @return A boolean
	 */
	public static boolean toBoolean(float data){
		if(data==1) return true;
		else return false;
	}
	
	/**Convert a boolean into a String
	 * 
	 * @param data boolean with the data
	 * @return A String
	 */
	public static String toString(boolean data){
		if(data) return "true";
		else return "false";
	}
	
	/**Convert a int into a String
	 * 
	 * @param data int with the data
	 * @return A String
	 */
	public static String toString(int data){
		return String.valueOf(data);
	}
	
	/**Convert a double into a String
	 * 
	 * @param data double with the data
	 * @return A String
	 */
	public static String toString(double data){
		return String.valueOf(data);
	}
	
	/**Convert a float into a String
	 * 
	 * @param data float with the data
	 * @return A String
	 */
	public static String toString(float data){
		return String.valueOf(data);
	}
	
	/**Convert a float into a int
	 * 
	 * @param data String with the data
	 * @return An int
	 */
	public static int toInt(String data){
		return Integer.parseInt(data);
	}
	
	/**Convert a boolean into a int
	 * 
	 * @param data boolean with the data
	 * @return An int
	 */
	public static int toInt(boolean data){
		if(data) return 1;
		else return 0;
	}
	
	/**Convert a double into a int
	 * 
	 * @param data double with the data
	 * @return An int
	 */
	public static int toInt(double data){
		return (int)data;
	}
	
	/**Convert a float into a int
	 * 
	 * @param data float with the data
	 * @return An int
	 */
	public static int toInt(float data){
		return (int)data;
	}
	
	/**Convert a String into a double
	 * 
	 * @param data String with the data
	 * @return A double
	 */
	public static double toDouble(String data){
		return Double.parseDouble(data);
	}
	
	/**Convert a boolean into a double
	 * 
	 * @param data boolean with the data
	 * @return A double
	 */
	public static double toDouble(boolean data){
		if(data) return 1;
		else return 0;
	}
	
	/**Convert a int into a double
	 * 
	 * @param data int with the data
	 * @return A double
	 */
	public static double toDouble(int data){
		return (double)data;
	}
	
	/**Convert a float into a double
	 * 
	 * @param data float with the data
	 * @return A double
	 */
	public static double toDouble(float data){
		return (double)data;
	}
	
	/**Convert a String into a float
	 * 
	 * @param data String with the data
	 * @return A float
	 */
	public static float toFloat(String data){
		return Float.parseFloat(data);
	}
	
	/**Convert a boolean into a float
	 * 
	 * @param data boolean with the data
	 * @return A float
	 */
	public static float toFloat(boolean data){
		if(data) return 1;
		else return 0;
	}
	
	/**Convert a int into a float
	 * 
	 * @param data int with the data
	 * @return A float
	 */
	public static float toFloat(int data){
		return (float)data;
	}
	
	/**Convert a double into a float
	 * 
	 * @param data double with the data
	 * @return A float
	 */
	public static float toFloat(double data){
		return (float)data;
	}
}
