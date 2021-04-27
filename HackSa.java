import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class HackSa extends JFrame {
   
   JTextField tfId = null; // ���������� �����Ѵ�.
   JTextField tfName = null;
   JTextField tfDepart = null;
   JTextField tfAddress = null;
   
   JTextArea taList = null;
   
   JButton btnInsert = null;
   JButton btnSelect = null;
   JButton btnUpdate = null;
   JButton btnDelete = null;
   
   JButton btnSearch = null;
   
   Connection conn = null;
   
   Statement stmt = null;
   
   JTable table = null;
   DefaultTableModel model = null;
   
   static JPanel panel;
   
   public HackSa() {
      this.setTitle("�л� ����");
      
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      //db connection
      try {
         //oracle jdbc����̹� �ε�
         Class.forName("oracle.jdbc.driver.OracleDriver");// jdbc driver load, ��Ű��.CLASS�̸�
         //Connection
         conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","ora_user","hong");// xe : ���� �����ͺ��̽� �̸�(sid), ora_user: ID, hong:PW
         //
         System.out.println("����Ϸ�");
         
         
      } catch(Exception e) {
         e.printStackTrace();
      }
      //db close, �����찡 ���� �ɶ� ����
      this.addWindowListener(new WindowListener() {
         
         @Override
         public void windowOpened(WindowEvent e) {}
         
         @Override
         public void windowIconified(WindowEvent e) {}
         
         @Override
         public void windowDeiconified(WindowEvent e) {}
         
         @Override
         public void windowDeactivated(WindowEvent e) {}
         
         @Override
         public void windowClosing(WindowEvent e) {
            // �����찡 close �� �� 
            try {
               if(conn != null) {
                  conn.close();
               }
            } catch(Exception e1) {
               e1.printStackTrace();
            }
            
         }
         @Override
         public void windowClosed(WindowEvent e) {}
         
         @Override
         public void windowActivated(WindowEvent e) {}
      });
      
      JMenuBar bar=new JMenuBar();
      JMenu m_student=new JMenu("�л�����");//File�޴�
      bar.add(m_student);
      JMenu m_book=new JMenu("��������");
      bar.add(m_book);
      JMenu m_chart=new JMenu("���� ��Ȳ");
      bar.add(m_chart);
     
      JMenuItem mi_list=new JMenuItem("�л�����"); // �л����� �ΰ� �޴�
      m_student.add(mi_list);
      mi_list.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
	          panel.removeAll(); //���������Ʈ ����
	          panel.revalidate(); //�ٽ� Ȱ��ȭ
	          panel.repaint();    //�ٽ� �׸���
	          panel.add(new Student()); //ȭ�� ����.
	          panel.setLayout(null); //���̾ƿ��������
	          setSize(316, 550);
		}
	});
      
      
      JMenuItem mi_book = new JMenuItem("��� ����"); // �������� �ΰ� �޴� 1
      m_book.add(mi_book);
      mi_book.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			panel.removeAll(); //���������Ʈ ����
            panel.revalidate(); //�ٽ� Ȱ��ȭ
            panel.repaint();    //�ٽ� �׸���
            panel.add(new Book()); //ȭ�� ����.
            panel.setLayout(null); //���̾ƿ��������
            setSize(500, 400);
			
		}
	});
      
      JMenuItem mi_bookRent = new JMenuItem("������"); // �������� �ΰ� �޴� 2
      m_book.add(mi_bookRent);
      mi_bookRent.addActionListener(new ActionListener() {
         
         @Override
         public void actionPerformed(ActionEvent e) {
            panel.removeAll(); //���������Ʈ ����
            panel.revalidate(); //�ٽ� Ȱ��ȭ
            panel.repaint();    //�ٽ� �׸���
            panel.add(new BookRent()); //ȭ�� ����.
            panel.setLayout(null); //���̾ƿ��������
            setSize(500, 400);
            
         }
      });
      
      JMenuItem mi_bookRead = new JMenuItem("�а��� ����");
      m_chart.add(mi_bookRead);
      mi_bookRead.addActionListener(new ActionListener() {
         
         @Override
         public void actionPerformed(ActionEvent e) {
            panel.removeAll(); //���������Ʈ ����
            panel.revalidate(); //�ٽ� Ȱ��ȭ
            panel.repaint();    //�ٽ� �׸���
            try {
				panel.add(new BookChart());
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
            panel.setLayout(null); //���̾ƿ��������
            setSize(710, 500);
           
            
         }
      });
      
      this.setJMenuBar(bar);
      
      panel = new JPanel();
      add(panel);
      
      
      this.setSize(700, 500);
      this.setVisible(true);
   }
   
   public static void main(String[] args) {
      new HackSa();

   }
   
}
