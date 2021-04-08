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
   String query; // sql문을 불러온다.
   
   public Book() {
      
      try {
         //DB연결
         Class.forName("oracle.jdbc.driver.OracleDriver");
         conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "ora_user", "hong");
         //System.out.println("연결완료");
         stmt=conn.createStatement();
         }catch(Exception e) {
         e.printStackTrace();
         }   

       setLayout(null);//레이아웃설정. 레이아웃 사용 안함.
       
       	JLabel l_dept=new JLabel("정렬 순서");
       	l_dept.setBounds(10, 10, 70, 20);
       	add(l_dept);       
        String[] dept={"번호 순","도서명 순","저자 순","출판사 순"};
        JComboBox cb_dept=new JComboBox(dept);
        cb_dept.setBounds(80, 10, 100, 20);
        add(cb_dept);
        cb_dept.addActionListener(new ActionListener() {
         
         @Override
         public void actionPerformed(ActionEvent e) {
            query = "select b.no, b.TITLE, b.AUTHOR, b.publisher, b.loan " 
            		+ "from books b ";
            JComboBox cb=(JComboBox)e.getSource(); // 이벤트가 발생한 콤보박스 구하기
            //동적 쿼리 (실행시 쿼리가 바뀐다.)
            if(cb.getSelectedIndex()==0) {
               //번호 순
               query += " order by b.no"; //앞에 한칸을 무조건 띄운다.
            } else if(cb.getSelectedIndex() == 1) {
               //도서명 순
               query += " order by b.TITLE";
            } else if(cb.getSelectedIndex() == 2) {
               //저자 순
               query += " order by b.AUTHOR";
            } else if(cb.getSelectedIndex() == 3) {
               //출판사 순
               query += " order by b.publisher";
            }
            
            //목록 출력
            list();
         }
      });
        
      String colName[]={"번호","제목","저자","출판사", "대출 현황"};
       model=new DefaultTableModel(colName,0);
       table = new JTable(model);
       table.setPreferredScrollableViewportSize(new Dimension(470,200));
       add(table);
       JScrollPane sp=new JScrollPane(table);
       sp.setBounds(10, 40, 460, 250);
       add(sp);  
        
        setSize(490, 400);//화면크기
        setVisible(true);
   }
   
   public void list(){
       try{
           System.out.println("연결되었습니다.....");
           System.out.println(query);       //쿼리문을 출력해본다 
           // Select문 실행     
           ResultSet rs=stmt.executeQuery(query);
          
        //JTable 초기화
        model.setNumRows(0);
       
        while(rs.next()){
         String[] row=new String[5];//컬럼의 갯수가 5
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