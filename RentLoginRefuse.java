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
		super(frame, title, true); //��޸���
		setLayout(new FlowLayout());
		JLabel nameLabel = new JLabel("��ϵ��� ���� �̸� �Ǵ� �й��Դϴ�.");
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
	


