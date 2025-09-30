import java.util.*;
import java.util.function.Predicate;

class StudentManagementSystem 
{
    Map<Integer, StdClass> classes = new HashMap<>();
    Map<Integer, List<Student>> studentsByClass = new HashMap<>();
    Map<Integer, Address> addresses = new HashMap<>();

    public StudentManagementSystem() 
	{
        classes.put(1, new StdClass(1, "BCA"));
        classes.put(2, new StdClass(2, "MCA"));
        classes.put(3, new StdClass(3, "BBA"));
        classes.put(3, new StdClass(3, "BSC"));
        classes.put(3, new StdClass(3, "B.tech"));
    }

    public Integer getClassIdByName(String name) 
	{
        for (StdClass c : classes.values()) 
		{
            if (c.getClassName().equalsIgnoreCase(name)) return c.getId();
        }
        return null;
    }

    public String getClassNameById(int id) 
	{
        StdClass c = classes.get(id);
        return c != null ? c.getClassName() : "Unknown";
    }

    public boolean addStudent(Student s, Address a) 
	{
        if (!classes.containsKey(s.getClassId())) return false;
		
        if (getStudentById(s.getStdId()) != null) return false;
		
        studentsByClass.computeIfAbsent(s.getClassId(), k -> new ArrayList<>()).add(s);
        addresses.put(s.getStdId(), a);
        
		assignRanks();
        return true;
    }

    public boolean deleteStudent(int stdId) 
	{
        for (Map.Entry<Integer, List<Student>> e : new ArrayList<>(studentsByClass.entrySet())) 
		{
            List<Student> list = e.getValue();
            Iterator<Student> itr = list.iterator();
            boolean removed = false;
            while (itr.hasNext()) 
			{
                Student s = itr.next();
                if (s.getStdId() == stdId) 
				{
                    itr.remove();
                    removed = true;
                    break;
                }
            }
            if (removed) 
			{
                if (list.isEmpty()) studentsByClass.remove(e.getKey());
                addresses.remove(stdId);
                assignRanks();
                return true;
            }
        }
        return false;
    }

    public Student getStudentById(int id) 
	{
        for (List<Student> list : studentsByClass.values()) 
		{
            for (Student s : list) if (s.getStdId() == id) return s;
        }
        return null;
    }

    public List<Student> filterStudents(Predicate<Student> predicate) 
	{
        List<Student> result = new ArrayList<>();
        for (List<Student> list : studentsByClass.values()) 
		{
            for (Student s : list) 
			{
                if (predicate.test(s)) result.add(s);
            }
        }
        return result;
    }

    public List<Student> searchByName(String name) 
	{
        return filterStudents(s -> s.getStdName().equalsIgnoreCase(name));
    }

    public List<Student> searchByGender(String gender) 
	{
        return filterStudents(s -> s.getGender().equalsIgnoreCase(gender));
    }

    public List<Student> searchByCity(String city) 
	{
        List<Student> result = new ArrayList<>();
        for (Address a : addresses.values()) 
		{
            if (a.getCity().equalsIgnoreCase(city)) 
			{
                Student s = getStudentById(a.getStdId());
                if (s != null) result.add(s);
            }
        }
        return result;
    }

    public List<Student> searchByPincode(int pincode) 
	{
        List<Student> result = new ArrayList<>();
        for (Address a : addresses.values()) 
		{
            if (a.getPincode() == pincode) 
			{
                Student s = getStudentById(a.getStdId());
                if (s != null) result.add(s);
            }
        }
        return result;
    }

    public void assignRanks() 
	{
        List<Student> all = new ArrayList<>();
        for (List<Student> list : studentsByClass.values()) all.addAll(list);
        Collections.sort(all, new Comparator<Student>() 
		{

            public int compare(Student s1, Student s2) 
			{
                return Integer.compare(s2.getMarks(), s1.getMarks());
            }
        });
        int rank = 1;
        for (int i = 0; i < all.size(); i++) 
		{
            if (i > 0 && all.get(i).getMarks() < all.get(i - 1).getMarks()) 
			{
                rank = i + 1;
            }
            all.get(i).setRank(rank);
        }
    }
	
    public Comparator<Student> comparatorGenderCityAge() 
	{
        return new Comparator<Student>() 
		{
  
            public int compare(Student a, Student b) 
			{
                int c = a.getGender().compareToIgnoreCase(b.getGender());
                if (c != 0) return c;
                Address aa = addresses.get(a.getStdId());
                Address bb = addresses.get(b.getStdId());
                String ca = aa != null && aa.getCity() != null ? aa.getCity() : "";
                String cb = bb != null && bb.getCity() != null ? bb.getCity() : "";
                c = ca.compareToIgnoreCase(cb);
                if (c != 0) return c;
                return Integer.compare(a.getAge(), b.getAge());
            }
        };
    }

    public List<Student> paginateAndSort(List<Student> input, int start, int end, String orderBy) 
	{
        List<Student> copy = new ArrayList<>(input);
		
        if ("name".equalsIgnoreCase(orderBy)) 
		{
            Collections.sort(copy, new Comparator<Student>() 
			{  
     public int compare(Student s1, Student s2) 
	{
             return s1.getStdName().compareToIgnoreCase(s2.getStdName());
    }
             } );
        } 
		
		else if ("marks".equalsIgnoreCase(orderBy)) 
		{
            Collections.sort(copy, new Comparator<Student>() 
			{             
                public int compare(Student s1, Student s2) 
				{
                    return Integer.compare(s2.getMarks(), s1.getMarks());
                }
            });
        } 
		
		else if ("genderCityAge".equalsIgnoreCase(orderBy)) 
		{
            Collections.sort(copy, comparatorGenderCityAge());
        } 
		
		else 
		{
            Collections.sort(copy, new Comparator<Student>() 
			{               
                public int compare(Student s1, Student s2) {
                    return Integer.compare(s1.getStdId(), s2.getStdId());
            }
            });
        }
        List<Student> page = new ArrayList<>();
        int s = Math.max(1, start);
        int e = Math.max(s, end);
        for (int i = s - 1; i < e && i < copy.size(); i++) page.add(copy.get(i));
        return page;
    }

    public void displayStudentFull(Student s) 
	{
        if (s == null) return;
        System.out.println(s);
        System.out.println(" ClassName => " + getClassNameById(s.getClassId()));
        Address a = addresses.get(s.getStdId());
        if (a != null) System.out.println(a);
    }

    public void displayAll() {
        for (List<Student> list : studentsByClass.values()) {
            for (Student s : list) {
                displayStudentFull(s);
                System.out.println();
            }
        }
    }
}