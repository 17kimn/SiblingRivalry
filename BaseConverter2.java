import javax.swing.JFileChooser;
import java.util.Scanner;
import java.io.*;

/**
 * BaseConverter2.java
 * @author Natalie
 * Base Conversion Lab
 * @version 10/30/2015
 * Rohan helped fix some logical errors 
 */ 

public class BaseConverter2 {
	private String hexmap = "0123456789ABCDEF";
	/**
	 * Constructor for class.
	 */
	public BaseConverter() {
		//hexmap = new String(); 
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
        	FileWriter fw = new FileWriter("converted.dat");
        	PrintWriter pw = new PrintWriter(fw);
            in = new Scanner(file);
            String[] temp = new String[4];
            while(in.hasNext()) {
                temp = in.nextLine().split("\t");
                if (Integer.parseInt(temp[1]) < 2 || Integer.parseInt(temp[1]) > 16) {
                	System.out.println("Invalid input base " + temp[1]); 
                }
                if (Integer.parseInt(temp[2]) < 2 || Integer.parseInt(temp[2]) > 16) {
                	System.out.println("Invalid output base " + temp[2]); 
                }
                else {
                	System.out.print(temp[0] + " base " + temp[1] + " = ");
                    System.out.println(intToStr(strToInt(temp[0], temp[1]), Integer.parseInt(temp[2])) + " base " + temp[2]);
                    pw.print(temp[0] + "\t" + temp[1] + "\t" + intToStr(strToInt(temp[0], temp[1]), Integer.parseInt(temp[2])) + "\t" + temp[2] + "\n"); 
                }
            }
            fw.flush();
            fw.close(); 
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally {
            in.close();
        }
	}


	/**
	 * Main method for class BaseConverter. 
	 */
	public static void main(String[] args) {
		BaseConverter app = new BaseConverter();
		app.inputConvertPrintWrite(); }

}
