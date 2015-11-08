import javax.swing.JFileChooser;
import java.util.Scanner;
import java.io.File;

/**
 * JFileChooserExample shows how to open a file through
 * a UI dialog rather than having to hard code the file
 * name and location into Scanner constructor statement.
 * @author jcochran
 * @version 11/03/2015
 */

public class JFileChooserExample {
	public void getFileUI()	{
		File file = null;
		Scanner in = null;
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new java.io.File("."));
		int val = chooser.showOpenDialog(null);
		if(val == JFileChooser.APPROVE_OPTION)	{
			file = chooser.getSelectedFile();
		}
		try	{
			in = new Scanner(file);
			while(in.hasNext())	{
				System.out.println(in.nextLine());
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally	{
			in.close();
		}
	}
	
	public static void main(String[] args){
		JFileChooserExample app = new JFileChooserExample();
		app.getFileUI();
	}
}
