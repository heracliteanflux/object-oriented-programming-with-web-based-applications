public class CourseQueriesTest {
  public static void main (String[] args) {

    CourseQueries courses = new CourseQueries();
    System.out.printf("%s", courses.getAllCourses(""));
    courses.addCourse(new CourseEntry("Summer 2023", "12345", "Course description here.", 25));
    courses.addCourse(new CourseEntry(  "Fall 2023", "67890","Course description there.", 30));
    System.out.printf("%s", courses.getAllCourses("Summer 2023"));
  
  }
}
