import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;
import javax.swing.JFrame;


class Window extends JFrame{

	public Window() {
		setTitle("Clock");
		setSize(400,450);
		setResizable(true);
		Date date = new Date(); //current date;
		Clock clock = new Clock();
		getContentPane().add(clock);
                addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        dispose();
                        System.exit(0);
                    }
                               
                });		
	}
	
};
