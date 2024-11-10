import java.util.List;
import java.util.ArrayList;

public class Student extends Person {
    private List<Course> completedCourses;
    private List<Course> selectedCourses;
    private short semester;
    private short creditsTaken;
    private Advisor advisor;
    private boolean approved;
    private String advisorMessage;

    public Student(String name, int ID, String password, List<Course> completedCourses, short semester,
                   short creditsTaken, Advisor advisor) {
        super(name, ID, password);
        this.completedCourses = completedCourses != null ? completedCourses : new ArrayList<>();
        this.selectedCourses = new ArrayList<>();
        this.semester = semester;
        this.creditsTaken = creditsTaken;
        this.advisor = advisor;
        this.approved = false;
        this.advisorMessage = "";
    }

    @Override
    public boolean validateUser(int ID, String password) {
        return this.ID == ID && this.password.equals(password);
    }

    @Override
    public String getPersonName() {
        return this.name;
    }

    public void addCourse(Course course) {
        if (course != null && !selectedCourses.contains(course)) {
            selectedCourses.add(course);
        }
    }

    public List<Course> getCompletedCourses() {
        return completedCourses;
    }

    public short getCourseCount() {
        return (short) selectedCourses.size();
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public void setAdvisorMessage(String message) {
        this.advisorMessage = message;
    }

    public String getAdvisorMessage() {
        return advisorMessage;
    }

    public List<Course> getSelectedCourses() {
        return selectedCourses;
    }

    public void setSelectedCourses(List<Course> courses) {
        this.selectedCourses = courses != null ? courses : new ArrayList<>();
    }

    public short getSemester() {
        return semester;
    }

}