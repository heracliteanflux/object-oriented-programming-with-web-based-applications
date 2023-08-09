public class SemesterQueriesTest extends java.lang.Object {
  public static void main (String[] args) {

    SemesterQueries semesters = new SemesterQueries();
    for (String semester : semesters.getSemesterList()) {
      semesters.dropSemester(semester);
    }

    System.out.printf("%s%n%n", semesters.getSemesterList());
    semesters.addSemester("2022 Fall");
    semesters.addSemester("2023 Spring");
    semesters.addSemester("2023 Summer");
    semesters.addSemester("2023 Fall");
    System.out.printf("%s%n%n", semesters.getSemesterList());
    semesters.dropSemester("2023 Fall");
    System.out.printf("%s%n%n", semesters.getSemesterList());
    semesters.addSemester("2023 Fall");
    semesters.addSemester("2024 Spring");
    System.out.printf("%s%n%n", semesters.getSemesterList());
  
  }
}
