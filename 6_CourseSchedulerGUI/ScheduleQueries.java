import java.lang.Object;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class ScheduleQueries extends Object {

  private static        Connection conn;
  private static PreparedStatement insertScheduleEntry;
  private static PreparedStatement selectScheduleByStudent;
  private static PreparedStatement selectScheduledStudentCount;
  // private static PreparedStatement selectScheduledStudentsByCourse;
  // private static PreparedStatement selectWaitlistedStudentsByCourse;
  // private static PreparedStatement deleteStudentScheduleByCourse;
  // private static PreparedStatement deleteScheduleByCourse;
  // private static PreparedStatement updateScheduleEntry;
  private static         ResultSet resultSet;

  // selectScheduledStudentsByCourse  = conn.prepareStatement("select count(*) from schedules where status = 'S' and courseCode = ?");
  // selectWaitlistedStudentsByCourse = conn.prepareStatement("select count(*) from schedules where status = 'W' and courseCode = ?");
  // deleteStudentScheduleByCourse    = conn.prepareStatement("delete from schedules where semester = ? and courseCode = ? and studentID = ? ");
  // deleteScheduleByCourse           = conn.prepareStatement("delete from schedules where semester = ? and courseCode = ?");
  // updateScheduleEntry              = conn.prepareStatement("update schedules set courseCode = ?, studentID = ?, status = ?, timestamp = ? where semester = ?");

  
  public static void addScheduleEntry (ScheduleEntry entry) {
    conn = DBConnection.getConnection();
    try {
      insertScheduleEntry = conn.prepareStatement("insert into app.schedules (semester, courseCode, studentID, status, timestamp) values (?, ?, ?, ?, ?)");
      insertScheduleEntry.setString(1, entry.getSemester());
      insertScheduleEntry.setString(2, entry.getCourseCode());
      insertScheduleEntry.setString(3, entry.getStudentID());
      insertScheduleEntry.setString(4, Character.toString(entry.getStatus()));
      insertScheduleEntry.setTimestamp(5, entry.getTimestamp());
      insertScheduleEntry.executeUpdate();
    }
    catch (SQLException se) {
      se.printStackTrace();
    }
  }

  
  public static ArrayList<ScheduleEntry> getScheduleByStudent (String semester,
                                                               String studentID)
  {
    conn = DBConnection.getConnection();
    ArrayList<ScheduleEntry> results = new ArrayList<ScheduleEntry>();
    try {
      selectScheduleByStudent = conn.prepareStatement("select * from app.schedules where semester = ? and studentID = ?");
      selectScheduleByStudent.setString(1, semester);
      selectScheduleByStudent.setString(2, studentID);
      resultSet = selectScheduleByStudent.executeQuery();
      while (resultSet.next()) {
        results.add(new ScheduleEntry(
          resultSet.getString("semester"),
          resultSet.getString("courseCode"),
          resultSet.getString("studentID"),
          resultSet.getString("status").charAt(0),
          resultSet.getTimestamp("timestamp")
        ));
      }
    }
    catch (SQLException se) {
      se.printStackTrace();
    }
    return results;
  }

  
  public static int getScheduledStudentCount (String semester,
                                              String courseCode)
  {
    conn = DBConnection.getConnection();
    int count = 0;
    try {
      selectScheduledStudentCount = conn.prepareStatement("select count(*) from app.schedules where semester = ? and courseCode = ?");
      selectScheduledStudentCount.setString(1, semester);
      selectScheduledStudentCount.setString(2, courseCode);
      resultSet = selectScheduledStudentCount.executeQuery();
      // resultSet.last();
      // numberOfRows = resultSet.getRow();
      resultSet.next();
      count = resultSet.getInt(1);
    }
    catch (SQLException se) {
      se.printStackTrace();
    }
    return count;
  }
  
}