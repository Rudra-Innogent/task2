import java.util.*;
import java.io.*;

class InvalidAgeException extends Exception {
    public InvalidAgeException(String message) { super(message); }
}

class InvalidMarksException extends Exception {
    public InvalidMarksException(String message) { super(message); }
}

class RecordNotFoundException extends Exception {
    public RecordNotFoundException(String message) { super(message); }
}


public class InputOutputTask {
    static HashMap<String, Student> students = new HashMap<>();
    static HashMap<String, StdcClass> classes = new HashMap<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        loadClasses();
        loadData();

        int choice = 0;
        do {
            System.out.println("\n1. Add Student\n2. Display Students\n3. Delete Student\n4. Display Top 5 Rank\n5. Exit");
            try {
                choice = sc.nextInt();
                sc.nextLine();
                switch(choice) {
                    case 1 -> addStudent();
                    case 2 -> displayStudents();
                    case 3 -> deleteStudent();
                    case 4 -> generateRank();
                    case 5 -> System.out.println("Exiting...");
                    default -> System.out.println("Invalid choice.");
                }
            } catch(InputMismatchException e) {
                System.out.println("Invalid input. Enter number only.");
                sc.nextLine();
            }
        } while(choice != 5);

        saveData();
    }

    static void loadClasses() {
        classes.put("C1", new StdcClass("C1", "Physics"));
        classes.put("C2", new StdcClass("C2", "Chemistry"));
        classes.put("C3", new StdcClass("C3", "Mathematics"));
    }

    static void loadData() {
        List<String> studentLines = FileHandler.readFromFile("students.txt");
        for(String line : studentLines) {
            try {
                Student s = Student.fromCSV(line);
                students.put(s.stdId, s);
            } catch(Exception e) {
                System.out.println("Skipping invalid student: " + e.getMessage());
            }
        }
    }

    static void saveData() {
        List<String> studentLines = new ArrayList<>();
        for(Student s : students.values()) studentLines.add(s.toCSV());
        FileHandler.saveToFile("students.txt", studentLines);
    }

    static void addStudent() {
        try {
            System.out.print("Enter Student ID: ");
            String id = sc.nextLine();
            System.out.print("Enter Name: ");
            String name = sc.nextLine();
            System.out.print("Enter Age: ");
            int age = sc.nextInt();
            sc.nextLine();
            System.out.print("Enter Gender (M/F): ");
            char gender = sc.nextLine().charAt(0);
            System.out.print("Enter Marks: ");
            double marks = sc.nextDouble();
            sc.nextLine();
            System.out.print("Enter Class ID (C1/C2/C3): ");
            String classId = sc.nextLine();

            if(!classes.containsKey(classId)) {
                System.out.println("Invalid Class ID.");
                return;
            }

            System.out.print("Enter City: ");
            String city = sc.nextLine();
            System.out.print("Enter Pincode: ");
            String pin = sc.nextLine();

            Address addr = new Address(city, pin);
            Student s = new Student(id, name, age, gender, marks, classId, addr);
            students.put(id, s);
            System.out.println("Student added successfully.");
        } catch(InvalidAgeException | InvalidMarksException e) {
            System.out.println("Error: " + e.getMessage());
        } catch(InputMismatchException e) {
            System.out.println("Invalid input type.");
            sc.nextLine();
        }
    }

    static void displayStudents() {
        if(students.isEmpty()) System.out.println("No students available.");
        else for(Student s : students.values()) System.out.println(s);
    }

    static void deleteStudent() {
        System.out.print("Enter Student ID to delete: ");
        String id = sc.nextLine();
        try {
            if(students.remove(id) == null) throw new RecordNotFoundException("Student not found.");
            else System.out.println("Student deleted successfully.");
        } catch(RecordNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    static void generateRank() {
        if(students.isEmpty()) {
            System.out.println("No students to rank.");
            return;
        }

        List<Student> list = new ArrayList<>();
        for(Student s : students.values()) list.add(s);

        Collections.sort(list, new Comparator<Student>() {
            public int compare(Student s1, Student s2) {
                return Double.compare(s2.marks, s1.marks);
            }
        });

        for(int i=0; i<list.size(); i++) list.get(i).rank = i+1;

        System.out.println("Top 5 Ranked Students:");
        for(int i=0; i<Math.min(5, list.size()); i++) System.out.println(list.get(i));

        List<String> top5Lines = new ArrayList<>();
        for(int i=0; i<Math.min(5, list.size()); i++) top5Lines.add(list.get(i).toCSV());
        FileHandler.saveToFile("rank.txt", top5Lines);
    }
}
