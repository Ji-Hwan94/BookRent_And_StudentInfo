import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

class RentLogin extends JDialog {

	private JTextField name = new JTextField(10);
	private JTextField id = new JTextField(10);
	private JButton okBtn = new JButton("Ok");
	
//	Book book;
	
	Connection conn = null;
	JButton btn = null;
	Statement stmt;  
	String query;
	
	
	public RentLogin(JFrame frame, String title) {
		
		super(frame, title, true); //모달리스
		
		try {
	         //DB연결
	         Class.forName("oracle.jdbc.driver.OracleDriver");
	         conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "ora_user", "hong");
	         //System.out.println("연결완료");
	         stmt=conn.createStatement();
	         }
	      catch(Exception e) {
	         e.printStackTrace();
	         }  
		
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
			@Override
			public void actionPerformed(ActionEvent e) {
				
					if(name.getText().length()==0) {
						JOptionPane.showMessageDialog(null, "이름을 등록해주세요.");
						return;
					}
					if(id.getText().length()==0) {
						JOptionPane.showMessageDialog(null, "학번을 입력해주세요");
						return;
					}
					try {
		               Statement stmt = conn.createStatement();
		               ResultSet rs = stmt.executeQuery("select*from student where name = '"+name.getText()+"'"); 
		               
		               System.out.println(Book.bookCode);
		               
		               while(rs.next()) {
		            	   
			               System.out.println(rs.getString("id"));
		            	   stmt.executeUpdate("insert into bookrent values('00011','"+rs.getString("id")+"','"+Book.bookCode+"', sysdate)");
		            	   
		            	   
		               }
		               
		               rs.close();
		               stmt.close();
		               
		            } catch (Exception e1) {
		               e1.printStackTrace();
		            } 
					
			}
			
		});
//		book.repaint();
	}
}
	

