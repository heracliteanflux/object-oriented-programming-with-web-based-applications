import java.lang.Object;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionEmbedded extends Object {

  private static final     String URL  = "jdbc:derby:coursescheduler";
  private static final     String USER = "derby";
  private static final     String PASS = "derby";
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