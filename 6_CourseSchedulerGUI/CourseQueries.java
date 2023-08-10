import java.lang.Object;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CourseQueries extends Object {

  private static        Connection conn;
  private static PreparedStatement selectAllCourses;
  private static PreparedStatement insertCourse;
  private static PreparedStatement selectAllCourseCodes;
  private static PreparedStatement selectCourseSeats;
  private static PreparedStatement deleteCourse;
  private static         ResultSet resultSet;


  public static ArrayList<CourseEntry> getAllCourses (String semester) {
    conn = DBConnection.getConnection();
    ArrayList<CourseEntry> results = new ArrayList<CourseEntry>();
    try {
      selectAllCourses = conn.prepareStatement("select * from app.courses where semester = ?");
      selectAllCourses.setString(1, semester);
      resultSet = selectAllCourses.executeQuery();
      while (resultSet.next()) {
        results.add(new CourseEntry(
          resultSet.getString("semester"),
          resultSet.getString("courseCode"),
          resultSet.getString("description"),
          resultSet.getInt("seats")
        ));
      }
    }
    catch (SQLException se) {
      se.printStackTrace();
    }
    return results;
  }


  public static void addCourse (CourseEntry course) {
    conn = DBConnection.getConnection();
    try {
      insertCourse = conn.prepareStatement("insert into app.courses (semester, courseCode, description, seats) values (?, ?, ?, ?)");
      insertCourse.setString(1, course.getSemester());
      insertCourse.setString(2, course.getCourseCode());
      insertCourse.setString(3, course.getDescription());
      insertCourse.setInt(   4,    course.getSeats());
      insertCourse.executeUpdate();
    }
    catch (SQLException se) {
      se.printStackTrace();
    }
  }


  public static ArrayList<String> getAllCourseCodes (String semester) {
    conn = DBConnection.getConnection();
    ArrayList<String> results = new ArrayList<String>();
    try {
      selectAllCourseCodes = conn.prepareStatement("select courseCode from app.courses where semester = ?");
      selectAllCourseCodes.setString(1, semester);
      resultSet = selectAllCourseCodes.executeQuery();
      while (resultSet.next()) {
        results.add(resultSet.getString("courseCode"));
      }
    }
    catch (SQLException se) {
      se.printStackTrace();
    }
    return results;
  }


  public static int getCourseSeats (String semester,
                                    String courseCode)
  {
    conn = DBConnection.getConnection();
    try {
      selectCourseSeats = conn.prepareStatement("select seats from app.courses where semester = ? and courseCode = ?");
      selectCourseSeats.setString(1, semester);
      selectCourseSeats.setString(2, courseCode);
      resultSet = selectCourseSeats.executeQuery();
      resultSet.next();
      return resultSet.getInt("seats");
    }
    catch (SQLException se) {
      se.printStackTrace();
      return 0;
    }
  }

	
  public static void dropCourse (String semester,
                                 String courseCode)
  {
    conn = DBConnection.getConnection();
    try {
      deleteCourse = conn.prepareStatement("delete from app.courses where semester = ? and courseCode = ?");
      deleteCourse.setString(1, semester);
      deleteCourse.setString(2, courseCode);
      deleteCourse.executeUpdate();
    }
    catch (SQLException se) {
      se.printStackTrace();
    }
  }

}