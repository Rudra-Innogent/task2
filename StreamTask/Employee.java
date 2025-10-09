import java.util.*;
import java.util.stream.Collectors;
public class Employee {
    int id;
    String name;
    String department;
    int salary;

    public Employee(int id, String name, String department, int salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {}

    public int getSalary() {
        return salary;
    }
    public void setSalary(int salary) {}

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", salary=" + salary +
                '}';
    }
	
	public static void main(String ar[]){
		Employee e1=new Employee(101,"Rajni","HR",60000);
		Employee e2=new Employee(102,"Jyoti","Management",80000);
		Employee e3=new Employee(103,"Raghav","HR",45000);
		Employee e4=new Employee(104,"Rudra","Development",60000);
		Employee e5=new Employee(105,"Saurabh","Management",50000);
		Employee e6=new Employee(106,"Arun","HR",65000);
		List<Employee> list = Arrays.asList(e1,e2,e3,e4,e5,e6);
		List<Employee> deptFilter = list.stream().filter((a)->a.department.equals("HR")).collect(Collectors.toList());
		deptFilter.forEach(System.out::println);
		System.out.println();
		List<Employee> nameFilter = list.stream().map((a)->new Employee(a.id,a.name.toUpperCase(),a.department,a.salary)).collect(Collectors.toList());
		nameFilter.forEach(System.out::println);
		System.out.println();
		Integer salarySum = list.stream().map(a->a.salary).reduce(0,(a,b)-> a + b );
		System.out.println("Total salary = "+salarySum);
	}
}

