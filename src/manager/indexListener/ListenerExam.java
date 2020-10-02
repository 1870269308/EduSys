package manager.indexListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import com.demo.ui.AdminFrm;

public class ListenerExam implements ActionListener{
	private JFrame frame;
	
	public ListenerExam(JFrame frame) {
		this.frame = frame;
	}

	public void actionPerformed(ActionEvent e) {
		AdminFrm window1 = new AdminFrm();
		frame.setVisible(false);
		window1.getFrame().setVisible(true);		
	}
}
