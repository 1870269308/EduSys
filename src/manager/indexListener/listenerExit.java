package manager.indexListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import com.demo.ui.Login;

public class listenerExit implements ActionListener{
	private JFrame frame;
		
	public listenerExit(JFrame frame) {
		this.frame = frame;
	}

	public void actionPerformed(ActionEvent e) {
		Login window1 = new Login();
		frame.setVisible(false);
		window1.getFrame().setVisible(true);		
	}
}
