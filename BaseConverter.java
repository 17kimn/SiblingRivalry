import javax.swing.JFileChooser;
import java.util.Scanner;
import java.io.File;

/**
 * BaseConverter.java
 * @author Natalie
 * Base Conversion Lab
 * @version 10/30/2015
 */ 

public class BaseConverter {
	private String hexmap = "0123456789ABCDEF";
	/**
	 * Constructor for class.
	 */
	public BaseConverter() {
		
	}
	
	/**
	 * Convert a String num in fromBase to base-10 int.
	 * @param num
	 * @param fromBase
	 * @return
	 */
	public int strToInt(String num, String fromBase) {
		int sum = 0; 
		int exp = 0; 
		int base = Integer.parseInt(fromBase);
		int intVal = 0; 
		for (int i = num.length()-1;  i >= 0; i--){
			intVal += (int)(hexmap.indexOf(num.charAt(i)) * Math.pow(base, exp++));
		}
		return intVal; 
	}
	/**
	 * Convert a base-10 int to a String number of base toBase.
	 * @param num
	 * @param toBase
	 * @return
	 */
	public String intToStr(int num, int toBase) {
		String strVal = new String();
		int remainder = 0; 
		if (num == 0) 
			return "0"; 
		while(num > 0) {
			remainder = num%toBase;
			num/= toBase; 
			strVal = hexmap.charAt(remainder) + strVal; 
		}
		return strVal; 
	}
	/**
	 *  Opens the file stream, inputs data one line at a time, converts, prints
	 *  the result to the console window and writes data to the output stream. 
	 */
	public void inputConvertPrintWrite() {
        File file = null;
        Scanner in = null;
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        int val = chooser.showOpenDialog(null);
        if(val == JFileChooser.APPROVE_OPTION)  {
            file = chooser.getSelectedFile();
        }
        try {
            in = new Scanner(file);
            String[] temp = new String[4];
            while(in.hasNext()) {
                temp = in.nextLine().split("\t");
                System.out.print(temp[0] + " base " + temp[1] + " = ");
                System.out.println(intToStr(strToInt(temp[0], temp[1]), Integer.parseInt(temp[3])) + " base " + temp[3]);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally {
            in.close();
        }
	}
	
	/**public static void main(String[]args) {
		BaseConverter b = new BaseConverter();
		System.out.println("AF13 base-16 = " + b.strToInt("AF13", "16") + " base-10");
		System.out.println("012012012 base-3 = " + b.strToInt("012012012", "3") + " base-3");
	}**/
/**
 * Main method for class BaseConverter. 
 */
public static void main(String[] args) {
	BaseConverter app = new BaseConverter();
	app.inputConvertPrintWrite(); }

}
