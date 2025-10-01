
import java.util.*;
import java.util.function.Predicate;

public class Demo 
{
    public static void main(String rudra[]) 
	{
        Scanner sc = new Scanner(System.in);
        StudentManagementSystem sms = new StudentManagementSystem();
        College college = new College(sms);

        while (true) 
		{
            System.out.println("\n1.Add Student  2.Delete Student  3.Search by Name  4.Search by Gender  5.Search by City  6.Search by Pincode  7.Paginated Filtered Read  8.Display All  9.Exit");
            System.out.print("Enter choice: ");
            int choice;
            try { choice = Integer.parseInt(sc.nextLine().trim()); } catch (Exception ex) { System.out.println("Invalid input"); continue; }

            switch (choice) {
                case 1:
                    try {
                        System.out.print("Enter Student ID: ");
                        int stdId = Integer.parseInt(sc.nextLine().trim());
						
                        System.out.print("Enter Name: ");
                        String name = sc.nextLine().trim();
						
                        System.out.print("Enter Gender: ");
                        String gender = sc.nextLine().trim();
						
                        System.out.print("Enter Marks: ");
                        int marks = Integer.parseInt(sc.nextLine().trim());
						
                        System.out.print("Enter Age: ");
                        int age = Integer.parseInt(sc.nextLine().trim());
						
                        if (age > 20) { System.out.println("Age exceeds limit 20. Student not created."); break; }
                        System.out.print("Enter Class Name: ");
						
                        String className = sc.nextLine().trim();
                        Integer classId = sms.getClassIdByName(className);
						
                        if (classId == null) { System.out.println("Class not found."); break; }
                        Student student = new Student(stdId, name, classId, marks, age, gender);
						
                        System.out.print("Enter Address ID: ");
                        int addrId = Integer.parseInt(sc.nextLine().trim());
						
                        System.out.print("Enter Pincode: ");
                        int pincode = Integer.parseInt(sc.nextLine().trim());
						
                        System.out.print("Enter City: ");
                        String city = sc.nextLine().trim();
						
                        Address addr = new Address(addrId, pincode, city, stdId);
                        boolean added = college.addStudent(student, addr);
						
                        if (added) System.out.println("Student added successfully!"); else System.out.println("Failed to add student (duplicate id or class missing).");
                    } catch (IllegalArgumentException e) { System.out.println(e.getMessage()); }
                    break;

                case 2:
                    try {
                        System.out.print("Enter Student ID to delete: ");
                        int delId = Integer.parseInt(sc.nextLine().trim());
                        if (college.deleteStudent(delId)) System.out.println("Deleted successfully!"); else System.out.println("Student not found.");
                    } catch (Exception ex) { System.out.println("Invalid input"); }
                    break;

                case 3:
                    System.out.print("Enter Name to search: ");
                    String searchName = sc.nextLine().trim();
                    List<Student> byName = college.searchByName(searchName);
                    if (byName.isEmpty()) System.out.println("No students found.");
                    for (Student s : byName) college.displayStudentFull(s);
                    break;

                case 4:
                    System.out.print("Enter Gender to search: ");
                    String searchGender = sc.nextLine().trim();
                    List<Student> byGender = college.searchByGender(searchGender);
                    if (byGender.isEmpty()) System.out.println("No students found.");
                    for (Student s : byGender) college.displayStudentFull(s);
                    break;

                case 5:
                    System.out.print("Enter City to search: ");
                    String searchCity = sc.nextLine().trim();
                    List<Student> byCity = college.searchByCity(searchCity);
                    if (byCity.isEmpty()) System.out.println("No students found.");
                    for (Student s : byCity) college.displayStudentFull(s);
                    break;

                case 6:
                    try {
                        System.out.print("Enter Pincode to search: ");
                        int searchPin = Integer.parseInt(sc.nextLine().trim());
                        List<Student> byPin = college.searchByPincode(searchPin);
                        if (byPin.isEmpty()) System.out.println("No students found.");
                        for (Student s : byPin) college.displayStudentFull(s);
                    } catch (Exception ex) { System.out.println("Invalid input"); }
                    break;

                case 7:
                    System.out.println("Filter options:\n1. Gender only\n2. City only\n3. Gender + City\n4. All students");
                    System.out.print("Choose filter: ");
                    int f=0;
                    try { f = Integer.parseInt(sc.nextLine().trim()); } catch (Exception ex) { System.out.println("Invalid"); break; }
                    List<Student> base = new ArrayList<>();
                    if (f == 1) {
                        System.out.print("Enter Gender: ");
                        String g = sc.nextLine().trim();
                        base = college.searchByGender(g);
                    } else if (f == 2) {
                        System.out.print("Enter City: ");
                        String c = sc.nextLine().trim();
                        base = college.searchByCity(c);
                    } else if (f == 3) {
                        System.out.print("Enter Gender: ");
                        String g2 = sc.nextLine().trim();
                        System.out.print("Enter City: ");
                        String c2 = sc.nextLine().trim();
                        List<Student> tmp = college.searchByCity(c2);
                        for (Student s : tmp) if (s.getGender().equalsIgnoreCase(g2)) base.add(s);
                    } 
					else 
					{
                        for (List<Student> list : sms.studentsByClass.values()) base.addAll(list);
                    }
					
                    if (base.isEmpty()) 
					{ 
					System.out.println("No records founds"); 
					break; 
					}
				
                    System.out.println("Order by options: name / marks / genderCityAge");
                    System.out.print("Enter orderBy: ");
                    String orderBy = sc.nextLine().trim();
					
                    System.out.print("Enter start record (1-based): ");
                    int start = Integer.parseInt(sc.nextLine().trim());
					
                    System.out.print("Enter end record (1-based): ");
                    int end = Integer.parseInt(sc.nextLine().trim());
					
                    List<Student> page = college.paginateAndSort(base, start, end, orderBy);
                    if (page.isEmpty()) System.out.println("No records in that page range.");
                    for (Student s : page) college.displayStudentFull(s);
                    break;

                case 8:
                    college.displayAll();
                    break;

                case 9:
                    
                    System.out.println("Exiting...");
                   System.exit(0);

                default:
                    System.out.println("Invalid choice");
					System.exit(0);
            }
        }
    
	}
}
