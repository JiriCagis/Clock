import java.util.Date;

import javax.swing.JDialog;


public class Window extends JDialog{

	public Window() {
		setTitle("Clock");
		setSize(400,450);
		setResizable(false);
		Date date = new Date(); //current date;
		Clock clock = new Clock(date,400,400);
		getContentPane().add(clock);
		
		
	}
	public static void main(String args[]){
		new Window().setVisible(true);
                
		
	}
}
