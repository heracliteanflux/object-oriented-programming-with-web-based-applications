import java.lang.Object;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentQueries extends Object {

  private static        Connection conn;
  private static PreparedStatement insertStudent;
  private static PreparedStatement selectAllStudents;
  private static PreparedStatement selectStudentByName;
  private static PreparedStatement selectStudent;
  private static PreparedStatement deleteStudent;
  private static         ResultSet resultSet;

  
  public static void addStudent (StudentEntry student) {
    conn = DBConnection.getConnection();
    try {
      insertStudent = conn.prepareStatement("insert into app.students (studentID, firstName, lastName) values (?, ?, ?)");
      insertStudent.setString(1, student.getStudentID());
      insertStudent.setString(2, student.getFirstName());
      insertStudent.setString(3, student.getLastName());
      insertStudent.executeUpdate();
    }
    catch (SQLException se) {
      se.printStackTrace();
    }
  }

  
  public static ArrayList<StudentEntry> getAllStudents () {
    conn = DBConnection.getConnection();
    ArrayList<StudentEntry> results = new ArrayList<StudentEntry>();
    try {
      selectAllStudents = conn.prepareStatement("select * from app.students");
      resultSet = selectAllStudents.executeQuery();
      while (resultSet.next()) {
        results.add(new StudentEntry(
          resultSet.getString("studentID"),
          resultSet.getString("firstName"),
          resultSet.getString("lastName")
        ));
      }
    }
    catch (SQLException se) {
      se.printStackTrace();
    }
    return results;
  }
  
  
  public static StudentEntry getStudentByName (String firstName,
                                               String lastName)
  {
    conn = DBConnection.getConnection();
    try {
      selectStudentByName = conn.prepareStatement("select * from app.students where firstName = ? and lastName = ?");
      selectStudentByName.setString(1, firstName);
      selectStudentByName.setString(2, lastName);
      resultSet = selectStudentByName.executeQuery();
      resultSet.next();
      return new StudentEntry(
        resultSet.getString(1),
        resultSet.getString(2),
         resultSet.getString(3)
      );
    }
    catch (SQLException se) {
      se.printStackTrace();
      return new StudentEntry();
    }
  }

  
  public static StudentEntry getStudent (String studentID) {
    conn = DBConnection.getConnection();
    try {
      selectStudent = conn.prepareStatement("select * from app.students where studentID = ?");
      selectStudent.setString(1, studentID);
      resultSet = selectStudent.executeQuery();
      return new StudentEntry(
        resultSet.getString("studentID"),
        resultSet.getString("firstName"),
        resultSet.getString("lastName")
      );
    }
    catch (SQLException se) {
      se.printStackTrace();
      return new StudentEntry();
    }
  }

  
  public static void dropStudent (String studentID) {
    conn = DBConnection.getConnection();
    try {
      deleteStudent = conn.prepareStatement("delete from app.students where studentID = ?");
      deleteStudent.setString(1, studentID);
      deleteStudent.executeUpdate();
    }
    catch (SQLException se) {
      se.printStackTrace();
    }
  }

}