import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

class RentLogin extends JDialog {

	private JTextField name = new JTextField(10);
	private JTextField id = new JTextField(10);
	private JButton okBtn = new JButton("Ok");
	
	private static HackSa hacksa;
	
	public RentLogin(JFrame frame, String title) {
		
		super(frame, title, true); //모달리스
		setLayout(new FlowLayout());
		JLabel nameLabel = new JLabel("이름");
		nameLabel.setBounds(10, 10, 80, 20);
	    add(nameLabel);
		add(name);
		
		JLabel idLabel = new JLabel("학번");
		idLabel.setBounds(40, 10, 80, 20);
	    add(idLabel);
		add(id);
		add(okBtn);
		setSize(400, 90);
		
		okBtn.addActionListener(new ActionListener() {
			RentLoginRefuse refuse = new RentLoginRefuse(hacksa, "ERROR");
			@Override
			public void actionPerformed(ActionEvent e) {
				refuse.setVisible(true);
				setVisible(false);
			}
		});
	}
}
	

