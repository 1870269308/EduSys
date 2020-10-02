package manager.indexListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import com.demo.ui.Longin;

public class listenerExit implements ActionListener{
	private JFrame frame;
		
	public listenerExit(JFrame frame) {
		this.frame = frame;
	}

	public void actionPerformed(ActionEvent e) {
		Longin window1 = new Longin();
		frame.setVisible(false);
		window1.getFrame().setVisible(true);		
	}
}
