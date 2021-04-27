import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.jdbc.JDBCPieDataset;

public class JDBCPieChart{
	
	public static void main(String arg[]) throws ClassNotFoundException, SQLException{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection(
				"jdbc:oracle:thin:@localhost:1521:xe","ora_user","hong");
		DefaultPieDataset pieDataset = new JDBCPieDataset(
				conn, "select s.dept, count(r.no) as book_dept "
			        	+ "from student s, bookrent r "
			        	+ "where s.id = r.id "
			        	+ "group by s.dept");
		
		//Create the chart
		JFreeChart chart = ChartFactory.createPieChart(
			"Bug Stat Pie Chart", pieDataset, true, true, true);

		//Render the frame
		ChartFrame chartFrame = new ChartFrame("JDPC Pie Chart", chart);
		chartFrame.setVisible(true);
		chartFrame.setSize(420, 300);
	}
}