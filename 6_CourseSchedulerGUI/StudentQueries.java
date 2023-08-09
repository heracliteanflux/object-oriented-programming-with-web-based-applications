import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentQueries extends java.lang.Object {

  private static        Connection conn;
  private static PreparedStatement insertStudent;
  private static PreparedStatement selectAllStudents;
  private static PreparedStatement selectStudent;
  private static PreparedStatement deleteStudent;
  private static         ResultSet resultSet;

  public void addStudent (StudentEntry student) {
    conn = DBConnection.getConnection();
    try {
      insertStudent = conn.prepareStatement("insert into students (studentID, firstName, lastName) values (?, ?, ?)");
      insertStudent.setString(1, student.getStudentID());
      insertStudent.setString(2, student.getFirstName());
      insertStudent.setString(3, student.getLastName());
      insertStudent.executeUpdate();
    }
    catch (SQLException se) {
      se.printStackTrace();
    }
  }

  public ArrayList<StudentEntry> getAllStudents () {
    conn = DBConnection.getConnection();
    ArrayList<StudentEntry> results = new ArrayList<StudentEntry>();
    try {
      selectAllStudents = conn.prepareStatement("select * from students");
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

  public StudentEntry getStudent (String studentID) {
    conn = DBConnection.getConnection();
    try {
      selectStudent = conn.prepareStatement("select * from students where studentID = ?");
      selectStudent.setString(1, studentID);
      resultSet = selectAllStudents.executeQuery();
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

  public void dropStudent (String studentID) {
    conn = DBConnection.getConnection();
    try {
      deleteStudent = conn.prepareStatement("delete from students where studentID = ?");
      deleteStudent.setString(1, studentID);
      deleteStudent.executeUpdate();
    }
    catch (SQLException se) {
      se.printStackTrace();
    }
  }

}
