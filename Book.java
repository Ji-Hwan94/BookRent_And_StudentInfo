import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

public class Book extends JPanel {
   DefaultTableModel model = null;
   JTable table = null;
   Connection conn = null;
   JButton btn = null;
   Statement stmt;  
   String query; // sql문을 불러온다.
   
   public Book() {
      
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

      setLayout(null);//레이아웃설정. 레이아웃 사용 안함.
      
      JLabel l_dept=new JLabel("모든 도서 보기");
      l_dept.setBounds(10, 10, 80, 20);
      add(l_dept);
  
      String colName[]={"번호","제목","저자","출판사", "대출 현황", "대출하기"}; 
      
      model=new DefaultTableModel(colName,0);
      table = new JTable(model);
      
      table.getColumnModel().getColumn(5).setCellRenderer(new TableCell());;
      table.getColumnModel().getColumn(5).setCellEditor(new TableCell());;
      
      table.setPreferredScrollableViewportSize(new Dimension(470,200));
      add(table);
      JScrollPane sp=new JScrollPane(table);
      sp.setBounds(10, 40, 460, 250);
      add(sp);  
      
      query = "select b.no, b.TITLE, b.AUTHOR, b.publisher, b.loan " 
         		+ "from books b order by b.no";  
      list(); // 목록 출력
      
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
        	   String[] row=new String[6];//컬럼의 갯수가 5
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
   
   class TableCell extends AbstractCellEditor implements TableCellEditor, TableCellRenderer{

		JButton btn;
		
		public TableCell() {
			
			btn = new JButton("대출");
			
			// 이 부분에서 버튼을 누르는 경우 이벤트를 발생 시킨다
			btn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println(table.getValueAt(table.getSelectedRow(), 1));
					
				}
			});
		
		}
		
		@Override
		public Object getCellEditorValue() {
			return null;
		}

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
			return btn;
		}

		@Override
		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
			return btn;
		}
		
	}
}
