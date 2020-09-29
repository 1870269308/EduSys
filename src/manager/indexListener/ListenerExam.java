package manager.indexListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import com.demo.ui.Exam;

public class ListenerExam implements ActionListener{
	private JFrame frame;
	
	public ListenerExam(JFrame frame) {
		this.frame = frame;
	}

	public void actionPerformed(ActionEvent e) {
		Exam window1 = new Exam();
		frame.setVisible(false);
		window1.getFrame().setVisible(true);		
	}
}
