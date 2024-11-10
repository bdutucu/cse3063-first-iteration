import java.util.ArrayList;
import java.util.List;

public class Advisor extends Person {
    private List<Student> advisees;

    public Advisor(String name, int ID, String password, List<Student> advisees) {
        super(name, ID, password);
        this.advisees = advisees != null ? advisees : new ArrayList<>();
    }

    public List<Student> getAdvisees() {
        return advisees;
    }

    @Override
    public boolean validateUser(int ID, String password) {
        return this.ID == ID && this.password.equals(password);
    }

    @Override
    public String getPersonName() {
        return this.name;
    }

}