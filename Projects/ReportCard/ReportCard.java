import java.util.ArrayList;
import java.util.Scanner;

public class ReportCard {
    public static void main(String[] args) {

        int rollNumber;
        int obtainedMarks = 0;
        String studentName;
        double percentMarks;
        String fatherName;
        ArrayList<String> subjectName = new ArrayList<>();
        ArrayList<Integer> subjectMarks = new ArrayList<>();
        boolean result = false;
        Scanner inputData = new Scanner(System.in);

        System.out.println("Enter Student Information:");
        System.out.println("\nStudent Name: ");
        studentName = inputData.nextLine();

        System.out.println("\nEnter Father's Name: ");
        fatherName = inputData.nextLine();

        System.out.println("\nEnter Roll Number: ");
        rollNumber = inputData.nextInt();

        System.out.println("\nEnter Number Of Subjects: ");
        int subjectCounter = inputData.nextInt();

        for (int index = 0; index < subjectCounter; index++) {
            System.out.println("\nEnter " + (index + 1) + " Subjects Details: ");

            System.out.println("\nEnter Subject " + (index + 1) + " Name: ");
            String subject = inputData.next();

            System.out.println("\nEnter Marks Obtained in " + subject + ": ");
            int marksObtain = inputData.nextInt();
            obtainedMarks += marksObtain;
            subjectName.add(subject);
            subjectMarks.add(marksObtain);
        }

        percentMarks = (obtainedMarks * 100) / (subjectName.size() * 100);
        Student student = new Student(rollNumber, obtainedMarks, studentName, percentMarks, fatherName, subjectName, subjectMarks);

        System.out.println("\n\n..........Report Card.........");
        System.out.println("\n\n" + student.getSchoolName());
        System.out.print("\nRoll No: " + student.getRollNumber());
        System.out.println("\t\t\tYear Of Passing: " + student.getPassingYear());
        System.out.print("\nStudent Name: " + student.getNameOfStudent());
        System.out.println("\t\t\tClass: " + student.getStandard());
        System.out.println("\nFather's Name: " + student.getFatherName());
        System.out.println("\nSubjects\t\tPassingMarks\t\tMarksObtained");

        for (int index = 0; index < student.getSubjectName().size(); index++)
            System.out.println("\n" + student.getSubjectName().get(index) + "\t\t\t" + student.getPassingMarks() + "\t\t\t" + student.getSubjectMarks().get(index));

        System.out.println("\nTotal Marks: " + student.getTotalMarksObtained() + "\nPercent Marks: " + student.getPercentMarks());

        for (int index = 0; index < student.getSubjectMarks().size(); index++) {
            if (student.getSubjectMarks().get(index) < student.getPassingMarks()) {
                result = false;
                break;
            } else
                result = true;
        }
        if (result)
            System.out.println("\nCongratulations You Passed The Exam!");
        else
            System.out.println("\nSorry You Could Not Pass The Exam!");

        System.out.print(student.toString());
    }
}

class Student {

    @Override
    public String toString() {
        return "\nStudent Data\n{\n" +
                "Name Of Student = '" + studentName + '\'' +
                ",\nFather's Name = '" + fatherName + '\'' +
                ",\nSchool Name = '" + schoolName + '\'' +
                ",\nStandard = " + standard +
                ",\nRoll Number = " + rollNumber +
                ",\nPassing Year = " + passingYear +
                ",\nPassing Marks = " + passingMarks +
                ",\nTotal Marks Obtained = " + totalMarksObtained +
                ",\nTotal Marks = " + totalMarks +
                ",\nPercent Marks = " + percentMarks +
                ",\nSubject Name = " + subjectName +
                ",\nSubject Marks = " + subjectMarks + "\n}\n";
    }

    final private String schoolName = "Rosary High School";
    final private int passingYear = 2017;
    final private int passingMarks = 33;
    final private int totalMarks = 100;
    final private char standard = 'X';

    private int rollNumber;
    private int totalMarksObtained;
    private String studentName;
    private double percentMarks;
    private String fatherName;
    private ArrayList<String> subjectName = new ArrayList<>();
    private ArrayList<Integer> subjectMarks = new ArrayList<>();

    Student(int rollNumber, int totalMarksObtained, String studentName, double percentMarks, String fatherName, ArrayList<String> subjectName, ArrayList<Integer> subjectMarks) {
        this.rollNumber = rollNumber;
        this.totalMarksObtained = totalMarksObtained;
        this.studentName = studentName;
        this.percentMarks = percentMarks;
        this.fatherName = fatherName;
        this.subjectName = subjectName;
        this.subjectMarks = subjectMarks;
    }

    String getSchoolName() {
        return schoolName;
    }

    int getPassingYear() {
        return passingYear;
    }

    int getPassingMarks() {
        return passingMarks;
    }

    char getStandard() {
        return standard;
    }

    int getRollNumber() {
        return rollNumber;
    }

    int getTotalMarksObtained() {
        return totalMarksObtained;
    }

    String getNameOfStudent() {
        return studentName;
    }

    double getPercentMarks() {
        return percentMarks;
    }

    String getFatherName() {
        return fatherName;
    }

    ArrayList<String> getSubjectName() {
        return subjectName;
    }

    ArrayList<Integer> getSubjectMarks() {
        return subjectMarks;
    }
}