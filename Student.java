class Student 
{
    private int stdId;
    private String stdName;
    private int classId;
    private int marks;
    private int age;
    private String gender;
    private int rank;
	private String status;
	
    public Student(int stdId, String stdName, int classId, int marks, int age, String gender)
	{
        if (age > 20) throw new IllegalArgumentException("Age cannot be greater than 20.");
		
        this.stdId = stdId;
        this.stdName = stdName;
        this.classId = classId;
        this.marks = marks;
        this.age = age;
        this.gender = gender;
		this.status = marks > 50 ? "pass" : "fail";
    }
    public int getStdId() 
	{
		return stdId; 
		}
	
    public String getStdName() 
	{ 
	return stdName; 
	}
	
    public int getClassId()
	{
		return classId; 
	}
	
    public int getMarks() 
	{
		return marks; 
	}
	
    public int getAge() 
	{
		return age; 
	}
	
    public String getGender() 
	{
		return gender; 
	}
	
    public int getRank() 
	{
		return rank; 
	}
	
    public void setRank(int rank) 
	{ 
	this.rank = rank; 
	}
	
    public String getResult() 
	{ 
	return status=this.marks>50 ? "Pass" : "Fail"; 
	}
	
  
    public String toString() 
	{
        return "Student => [ id= " + stdId +"\t name= " + stdName +", classId =" + classId +"\t marks =" + marks +
               "\t age =" + age +"\t gender =" + gender +"\t rank =" + rank + " ]";
    }
}