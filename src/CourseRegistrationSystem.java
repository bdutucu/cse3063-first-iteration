import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CourseRegistrationSystem {

    private List<Student> studentDatabase;

    private List<Advisor> advisorDatabase;

    private List<Course> courseDatabase;

    final short COURSE_LIMIT = 5;

    private Person currentUser;

    private Student currentStudent;

    private Advisor currentAdvisor;

    public Person logInUser(int userID, String password) {
        for (int i = 0; i < studentDatabase.size(); i++) {
            if (studentDatabase.get(i).validateUser(userID, password)) {
                currentStudent = studentDatabase.get(i);
                return currentStudent;
            }


        }

        for (int i = 0; i < advisorDatabase.size(); i++) {
            if (advisorDatabase.get(i).validateUser(userID, password)) {
                currentUser = advisorDatabase.get(i);
                return currentUser;
            }

        }

        return null;
    }


    public void importDatabase(){

    }


    public void updateDatabase(){

    }

    public void displayAdvisorMenu() {
        List<Student> advisees = ((Advisor) currentUser).getAdvisees();

        Scanner input2 = new Scanner(System.in);

        System.out.println("***\nWelcome dear Advisor " + currentUser.getPersonName() + " to our Course Registration System\n***");
        while (true) {
            System.out.println("Press 1 to display students who have not yet selected any course");
            System.out.println("Press 2 to display students awaiting approval");
            System.out.println("Press 3 to display students who have completed their course selection");
            System.out.println("Press 4 to exit the system");
            System.out.print("Enter your choice : ");

            int displayChoiceForAdvisor = input2.nextInt();


            if (displayChoiceForAdvisor == 1) {
                for (int i = 0; i < advisees.size(); i++) {
                    if (advisees.get(i).getSelectedCourses().isEmpty() && !(advisees.get(i).isApproved())) {
                        System.out.println("- " + advisees.get(i).getPersonName());
                    }
                }
                System.out.println();
                // buraya menuye geri donmesi icin bir scanner koyabilirsiniz.

            } // GetID eklensin unutmayın*********************************************************************************************************
            else if (displayChoiceForAdvisor == 2) {
                for (int i = 0; i < advisees.size(); i++) {
                    if (!(advisees.get(i).getSelectedCourses().isEmpty()) && !(advisees.get(i).isApproved())) {
                        System.out.println("- " + advisees.get(i).getPersonName() + "	" + advisees.get(i).ID); // ID yerine getID()
                    }
                }
                System.out.print("Please enter ID of the desired student: ");
                int selectedStudentID = input2.nextInt();
                for (int i = 0; i < advisees.size(); i++) {
                    if (selectedStudentID == advisees.get(i).ID) { // ID yerine getID()
                        approveCourse(advisees.get(i));
                        break;

                    }

                }


            } else if (displayChoiceForAdvisor == 3) {
                for (int i = 0; i < advisees.size(); i++) {
                    if (!(advisees.get(i).getSelectedCourses().isEmpty()) && advisees.get(i).isApproved()) {
                        System.out.println("- " + advisees.get(i).getPersonName());

                        for (int j = 0; j < advisees.get(i).getSelectedCourses().size(); j++) {
                            System.out.println("		" + advisees.get(i).getSelectedCourses().get(j).getCourseCode());
                        }
                    }
                }
                System.out.println();

            } else if (displayChoiceForAdvisor == 4) {
                return;
            } else {
                System.out.println("Error: Wrong choice. Please choose between 1 to 4\n");
                continue;
            }

        }

    }


    public void approveCourse(Student student) {
        Scanner input3 = new Scanner(System.in);
        StringBuilder advisorMessageStrBuilder = new StringBuilder();
        // 2 ways to exit while loop. 1) Approved once 2) All rejected
        while (true) {
            if (student.getSelectedCourses().isEmpty()) {
                student.setAdvisorMessage(advisorMessageStrBuilder.toString());
                return;
            }
            System.out.print("\nSelected Courses: ");
            for (int k = 0; k < student.getSelectedCourses().size(); k++) {
                System.out.print(student.getSelectedCourses().get(k).getCourseCode() + "\t");
            }
            System.out.print("\nDo you want to approve all requests? (y/n)");
            char approvalChoice = input3.next().charAt(0);
            if (approvalChoice == 'y' || approvalChoice == 'Y') {
                student.setApproved(true);
                if (!(advisorMessageStrBuilder.isEmpty())) { // If advisor did not reject any course then advisor message will not be send.
                    student.setAdvisorMessage(advisorMessageStrBuilder.toString());
                }
                return;
            } else if (approvalChoice == 'n' || approvalChoice == 'N') {
                System.out.print("Which course will you reject? :  ");
                String rejectCourseCode = input3.nextLine();
                for (int i = 0; i < student.getSelectedCourses().size(); i++) {
                    if (student.getSelectedCourses().get(i).getCourseCode().equals(rejectCourseCode)) {
                        student.getSelectedCourses().remove(student.getSelectedCourses().get(i));
                        System.out.print("Write rejection reason: ");
                        String rejectionReason = input3.nextLine();
                        advisorMessageStrBuilder.append(rejectCourseCode + " : ");
                        advisorMessageStrBuilder.append(rejectionReason + "\n");
                        break;

                    }
                }


            } else {
                System.out.println("Please choose 'y' or 'n' ");
            }
        }

    }


    public boolean prerequisiteCheck(Student student, Course course) {

        List<Course> completedCourses = student.getCompletedCourses();

        for (int i = 0; i < course.getPrerequisites().size(); i++) {

            if (!completedCourses.contains(course.getPrerequisites().get(i))) {
                return false;
            }
        }

        return true;

    }


    public void registerCourse(Student student, String courseChoice) {

        for (int i = 0; i < courseDatabase.size(); i++) {

            Course course = courseDatabase.get(i);


            boolean semesterCourse = !student.getCompletedCourses().contains(course) && course.getSemester() == student.getSemester();
            /* if student enters a course code which is available for them to select during that particular semester AND
            the course is not already in their course list  */
            if (semesterCourse && courseChoice.equals(course.getCourseCode())
            ) {

                if (!student.getSelectedCourses().contains(course)) {
                    student.addCourse(course);
                    return;
                } else {
                    System.out.println("This course is already in your course selection list! ");
                    return;
                }
            }

        }
        System.out.println("Course code you entered is incorrect or not available for you to enroll in this semester.");
    }


    public void displaySemesterCourse(Student student) {
        System.out.println("Courses available for this semester:");

        List<Course> completedCourses = student.getCompletedCourses();

        for (int i = 0; i < courseDatabase.size(); i++) {

            Course course = courseDatabase.get(i);

            if (!completedCourses.contains(course) && course.getSemester() == student.getSemester()) {
                System.out.println("---------------------------");
                System.out.printf("%-25s %-25s\n", "Course Name", "Course Code");
                System.out.printf("%-25s %-25s\n", course.getcourseName(), course.getCourseCode());
                System.out.println("---------------------------");
            }
        }
    }


    public void displayStudentMenu() {

        boolean approved = currentStudent.isApproved();
        Scanner scanner = new Scanner(System.in);
        List<Course> completedCourses = currentStudent.getCompletedCourses();
        List<Course> selectedCourses = currentStudent.getSelectedCourses();


        System.out.println("Welcome " + this.currentStudent.getPersonName() + "!");
        while (true) {
            System.out.println("Please choose one of the following options:");
            System.out.println("-----------------------------------------------");
            System.out.println("1 - Course Registration ");
            System.out.println("2 - Approval Status ");
            System.out.println("3 - See Completed Courses ");
            System.out.println("4 - Log out ");


            int choiceForStudentMenu = scanner.nextInt();


            if (choiceForStudentMenu == 1) {

                displaySemesterCourse(currentStudent);

                while (true) {
                    System.out.println("Enter the Course Code you want to enroll. enter \"exit\" to end the process. ");
                    String courseChoice = scanner.nextLine();
                    if (courseChoice.equalsIgnoreCase("exit")) {
                        break;
                    }
                    registerCourse(currentStudent, courseChoice);

                }


            }

            if (choiceForStudentMenu == 2) {
                System.out.println("---------------------------------------");
                System.out.println("Approval status: ");

                if (currentStudent.isApproved()) {
                    System.out.println(" Your course selection have been approved by your advisor.");
                } else {
                    System.out.println("Your course selection has been rejected by your advisor yet.");
                    System.out.println("Advisor Message: " + currentStudent.getAdvisorMessage());
                }

                System.out.println("Press Enter to return to main menu.");
                scanner.nextLine();

            }


            if (choiceForStudentMenu == 3) {
                System.out.println("Here are the list of your completed courses:");

                for (int i = 0; i < completedCourses.size(); i++) {

                    System.out.println(i + 1 + " - " + completedCourses.get(i).getCourseCode()
                            + " " + completedCourses.get(i).getcourseName());
                }

                System.out.println("Press Enter to return to main menu.");
                scanner.nextLine();
            }


            if (choiceForStudentMenu == 4) {

                return;
            }
        }


    }

    public void start() {
        Scanner input = new Scanner(System.in);
        // Print welcome paragraph

        int userID = input.nextInt();
        String password = input.nextLine();

        if (logInUser(userID, password) instanceof Student) {
            System.out.println("displayStudent should run now!");
        } else if (logInUser(userID, password) instanceof Advisor) {
            displayAdvisorMenu();
        }


    }


}
