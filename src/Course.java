import java.util.List;

public class Course {
    private String courseName;
    private String courseCode;
    private List<CourseSection> courseSections;
    private List<Course> prerequisites;
    private short semester;
    private short credit;

    public Course(String courseName, String courseCode, List<CourseSection> courseSections,
                  List<Course> prerequisites, short semester, short credit) {
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.courseSections = courseSections;
        this.prerequisites = prerequisites;
        this.semester = semester;
        this.credit = credit;
    }

    public List<Course> getPrerequisites() {
        return prerequisites;
    }

}