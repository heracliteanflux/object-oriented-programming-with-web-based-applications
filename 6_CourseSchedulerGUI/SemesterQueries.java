import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SemesterQueries extends java.lang.Object {

  private static        Connection conn;
  private static PreparedStatement insertSemester;
  private static PreparedStatement selectSemesterList;
  private static PreparedStatement deleteSemester;
  private static         ResultSet resultSet;

  public static void addSemester (String semester) {
    conn = DBConnection.getConnection();
    try {
      insertSemester = conn.prepareStatement("insert into semesters (semester) values (?)");
      insertSemester.setString(1, semester);
      insertSemester.executeUpdate();
    }
    catch (SQLException se) {
      se.printStackTrace();
    }
  }

  public static ArrayList<String> getSemesterList () {
    conn = DBConnection.getConnection();
    ArrayList<String> results = new ArrayList<String>();
    try {
      selectSemesterList = conn.prepareStatement("select * from semesters");
      resultSet = selectSemesterList.executeQuery();
      while (resultSet.next()) {
        results.add(resultSet.getString("semester"));
      }
    }
    catch (SQLException se) {
      se.printStackTrace();
    }
    return results;
  }

  public static void dropSemester (String semester) {
    conn = DBConnection.getConnection();
    try {
      deleteSemester = conn.prepareStatement("delete from semesters where semester = ?");
      deleteSemester.setString(1, semester);
      deleteSemester.executeUpdate();
    }
    catch (SQLException se) {
      se.printStackTrace();
    }
  }

}
