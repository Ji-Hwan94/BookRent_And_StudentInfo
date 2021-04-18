import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

class RentLoginRefuse extends JDialog {
	private JButton okBtn = new JButton("Ok");
	
	public RentLoginRefuse(JFrame frame, String title) {
		super(frame, title, true); //모달리스
		setLayout(new FlowLayout());
		JLabel nameLabel = new JLabel("등록되지 않은 이름 또는 학번입니다.");
	    add(nameLabel, CENTER_ALIGNMENT);
	
		add(okBtn);
		setSize(400, 90);
		
		okBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				setVisible(false);
			}
		});
	}
}
	


