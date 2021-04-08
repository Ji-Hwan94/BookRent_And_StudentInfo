import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Book extends JPanel {
   DefaultTableModel model=null;
   JTable table=null;
   Connection conn=null;
   
   Statement stmt;  
   String query; // sql���� �ҷ��´�.
   
   public Book() {
      
      try {
         //DB����
         Class.forName("oracle.jdbc.driver.OracleDriver");
         conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "ora_user", "hong");
         //System.out.println("����Ϸ�");
         stmt=conn.createStatement();
         }catch(Exception e) {
         e.printStackTrace();
         }   

       setLayout(null);//���̾ƿ�����. ���̾ƿ� ��� ����.
       
       	JLabel l_dept=new JLabel("���� ����");
       	l_dept.setBounds(10, 10, 70, 20);
       	add(l_dept);       
        String[] dept={"��ȣ ��","������ ��","���� ��","���ǻ� ��"};
        JComboBox cb_dept=new JComboBox(dept);
        cb_dept.setBounds(80, 10, 100, 20);
        add(cb_dept);
        cb_dept.addActionListener(new ActionListener() {
         
         @Override
         public void actionPerformed(ActionEvent e) {
            query = "select b.no, b.TITLE, b.AUTHOR, b.publisher, b.loan " 
            		+ "from books b ";
            JComboBox cb=(JComboBox)e.getSource(); // �̺�Ʈ�� �߻��� �޺��ڽ� ���ϱ�
            //���� ���� (����� ������ �ٲ��.)
            if(cb.getSelectedIndex()==0) {
               //��ȣ ��
               query += " order by b.no"; //�տ� ��ĭ�� ������ ����.
            } else if(cb.getSelectedIndex() == 1) {
               //������ ��
               query += " order by b.TITLE";
            } else if(cb.getSelectedIndex() == 2) {
               //���� ��
               query += " order by b.AUTHOR";
            } else if(cb.getSelectedIndex() == 3) {
               //���ǻ� ��
               query += " order by b.publisher";
            }
            
            //��� ���
            list();
         }
      });
        
      String colName[]={"��ȣ","����","����","���ǻ�", "���� ��Ȳ"};
       model=new DefaultTableModel(colName,0);
       table = new JTable(model);
       table.setPreferredScrollableViewportSize(new Dimension(470,200));
       add(table);
       JScrollPane sp=new JScrollPane(table);
       sp.setBounds(10, 40, 460, 250);
       add(sp);  
        
        setSize(490, 400);//ȭ��ũ��
        setVisible(true);
   }
   
   public void list(){
       try{
           System.out.println("����Ǿ����ϴ�.....");
           System.out.println(query);       //�������� ����غ��� 
           // Select�� ����     
           ResultSet rs=stmt.executeQuery(query);
          
        //JTable �ʱ�ȭ
        model.setNumRows(0);
       
        while(rs.next()){
         String[] row=new String[5];//�÷��� ������ 5
         row[0]=rs.getString("no");
         row[1]=rs.getString("TITLE");
         row[2]=rs.getString("AUTHOR");
         row[3]=rs.getString("publisher");
         row[4]=rs.getString("loan");
         model.addRow(row);
        }
        rs.close();
       }
       catch(Exception e1){
        //e.getStackTrace();
        System.out.println(e1.getMessage());
       }                     
    }
}