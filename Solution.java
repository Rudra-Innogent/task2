import java.util.*;
class Employee implements Comparable{
		
		static Employee e1;
		String name;
		int id;
		String department;
		double salary;
		Employee emp;
		
		Employee(int id, String name, String department, double salary){
		this.name=name;
		this.id=id;
		this.department=department;
		this.salary=salary;
		}
		
		public String getName() {
        return name;
         }

        public int getId() {
        return id;
         }
   
        public String getDepartment() {
        return department;
        }

        public double getSalary() {
        return salary;
        }
	
		public String toString(){
		return "[ name = "+name+"\t id = "+id+"\t department = "+department +"\t salary = "+salary+" ]";
		}
		
        public int compareTo(Object o) {
       if(o instanceof Employee)
	   {
		   emp=(Employee)o;
		   } 
        int deptCmp = this.department.compareTo(emp.department);
        if (deptCmp != 0) return deptCmp;


        int nameCmp = this.name.compareTo(emp.name);
        if (nameCmp != 0) return nameCmp;

        
        return Double.compare(emp.salary, this.salary);
    }
		
}
class Solution{
	
	public static void show(List<Employee> arrayList){
		Iterator<Employee> itr=arrayList.iterator();
		while(itr.hasNext()){
			System.out.println(itr.next());
		}
	}
	public static void main(String rudra[]){
        																						
	String names[]={"Jyoti","Raghav","Rudra","Jyoti","Raghav"};
	int ids[]={101,102,103,104,105};
	int salaries[]={10000,20000,15000,20000,5000};
	String departments[]={"Testing","Deployment","Development","Deployment","Development"};
	
	List<Employee> emp= new ArrayList<>();
	for(int i=0;i<ids.length;i++){
		emp.add(new Employee(ids[i],names[i],departments[i],salaries[i]));
	}
	
	System.out.println("before applying sorting => ");
	show(emp);
	System.out.println();
	
	System.out.println("after sorting using Comparator => ");
	emp.sort(CompareatorSorting.sortByAll);
	show(emp);	
	System.out.println();

    List<Employee> emp2= new ArrayList<>();
	for(int i=0;i<ids.length;i++){
		emp2.add(new Employee(ids[i],names[i],departments[i],salaries[i]));
	}
	System.out.println();

	System.out.println("before applying sorting => ");
	show(emp2);
	System.out.println();
	
	System.out.println("after sorting using Comparable => ");
	Collections.sort(emp2);
	show(emp2);

}
}
class CompareatorSorting{
	
			static final Comparator<Employee> sortByAll = new Comparator<Employee>() {
           
            public int compare(Employee i, Employee j) {
                
                int deptCompare = i.getDepartment().compareTo(j.getDepartment());
                if (deptCompare != 0) return deptCompare;

                int nameCompare = i.getName().compareTo(j.getName());
                if (nameCompare != 0) return nameCompare;

                if (i.getSalary() < j.getSalary()) return 1;
                if (i.getSalary() > j.getSalary()) return -1;
                return 0;
            }
        };
}
