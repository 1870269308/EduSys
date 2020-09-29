package manager.indexListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import com.demo.ui.Course;

public class ListenerCourse implements ActionListener{
	private JFrame frame;
	
	public ListenerCourse(JFrame frame) {
		this.frame = frame;
	}

	public void actionPerformed(ActionEvent e) {
		Course window1 = new Course();
		frame.setVisible(false);
		window1.getFrame().setVisible(true);		
	}

}
