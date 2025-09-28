import java.util.*;
class Employee implements Comparable<Employee>{
		
		String name;
		int id;
		String department;
		double salary;
		Employee(int id, String name, String department, double salary){
		
		this.name=name;
		this.id=id;
		this.department=department;
		this.salary=salary;
		}
		public String toString(){
		return "name = "+name+" id = "+id+" department = "+department +" salary = "+salary;
		}
		
		public int compareTo(Employee emp){
			if(this.salary < emp.salary) return 1;
				return -1;	
		}
}
class Solution{
public static void main(String rudra[]){
		Comparator<Employee> com= new Comparator<>(){
			public int compare(Employee i, Employee j){
				if(i.id < j.id) return 1;
				 else return -1;
			}
		};
	List<Employee> emp= new ArrayList<>();
	emp.add(new Employee(106,"Rudra","Development",5000));
	emp.add(new Employee(109,"Raghav","Testing",8000));	
	emp.add(new Employee(103,"Rajneesh","QA",2000));	
	emp.add(new Employee(102,"Jyoti","HR",10000));	
	emp.add(new Employee(105,"Saurabh","Management",3000));	
	System.out.println("before applying sorting => ");
	
		for(Employee emp1:emp){
			System.out.println(emp1);
		}
			Collections.sort(emp,com);
			System.out.println();
		System.out.println("after sorting by id using Comparator => ");
			
			for(Employee emp1:emp){
			
			System.out.println(emp1);
		}
			System.out.println();		
			Collections.sort(emp);
		System.out.println("after sorting by salary using Comparable => ");
					for(Employee emp1:emp){
			
			System.out.println(emp1);
		}
		
}
}