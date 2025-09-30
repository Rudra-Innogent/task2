import java.util.*;
class College 
{
    private StudentManagementSystem sms;
	
    public College(StudentManagementSystem sms) 
	{
		this.sms = sms; 
	}
    public boolean addStudent(Student s, Address a) 
	{
		return sms.addStudent(s, a); 
	}
	
    public boolean deleteStudent(int stdId)
	{ 
	return sms.deleteStudent(stdId); 
	}
	
    public Student getStudentById(int id) 
	{
		return sms.getStudentById(id); 
	}
	
    public List<Student> searchByName(String name) 
	{ 
	return sms.searchByName(name); 
	}
	
    public List<Student> searchByGender(String gender) 
	{
		return sms.searchByGender(gender); 
		}
	
    public List<Student> searchByCity(String city) 
	{ 
	return sms.searchByCity(city); 
	}
	
    public List<Student> searchByPincode(int pin)
	{
		return sms.searchByPincode(pin); 
	}
	
    public List<Student> paginateAndSort(List<Student> list, int start, int end, String orderBy) 
	{
        return sms.paginateAndSort(list, start, end, orderBy);
    }
    public void displayStudentFull(Student s) 
	{
		sms.displayStudentFull(s); 
	}
	
    public void displayAll() 
	{ 
	sms.displayAll(); 
	}
	
}