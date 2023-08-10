import java.lang.Object;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection extends Object {
    
  private static final     String URL  = "jdbc:derby://localhost:1527/CourseSchedulerDB";
  private static final     String USER = "java";
  private static final     String PASS = "java";
  private static       Connection conn;

  public static Connection getConnection () {
    if (conn == null) {
      try {
        conn = DriverManager.getConnection(URL, USER, PASS);
      }
      catch (SQLException se) {
        se.printStackTrace();
        System.out.println("Could not open database.");
        System.exit(1);
      }
    }
    return conn;
  }
    
}