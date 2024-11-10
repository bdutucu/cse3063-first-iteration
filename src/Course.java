import java.util.List;
import java.util.ArrayList;

public class Course {
    private String courseName;
    private String courseCode;
    private List<CourseSection> sectionList;
    private CourseSection courseSection;
    private List<Course> prerequisites;
    private short semester;
    private short credit;

    public Course(String courseName, String courseCode, List<CourseSection> sectionList,
                  CourseSection courseSection, List<Course> prerequisites, short semester, short credit) {
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.sectionList = sectionList != null ? sectionList : new ArrayList<>();
        this.courseSection = courseSection;
        this.prerequisites = prerequisites;
        this.semester = semester;
        this.credit = credit;
    }
    public List<Course> getPrerequisites() {
        return prerequisites;
    }

    public String getcourseName() {
        return courseName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public short getSemester() {
        return semester;
    }

    public short getCredit() {
        return credit;
    }
}