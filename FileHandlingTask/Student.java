class Student {
    String stdId;
    String stdName;
    int age;
    char gender;
    double marks;
    String classId;
    Address address;
    String status;
    int rank;

    public Student(String stdId, String stdName, int age, char gender, double marks, String classId, Address address) throws InvalidAgeException, InvalidMarksException {
        if(age > 20) throw new InvalidAgeException("Age cannot be greater than 20.");
        if(marks < 0 || marks > 100) throw new InvalidMarksException("Marks should be between 0 and 100.");
        this.stdId = stdId;
        this.stdName = stdName;
        this.age = age;
        this.gender = gender;
        this.marks = marks;
        this.classId = classId;
        this.address = address;
        this.status = (marks >= 33) ? "Pass" : "Fail";
    }

    public String toCSV() {
        return stdId + "," + stdName + "," + age + "," + gender + "," + marks + "," + classId + "," + address.city + "," + address.pincode + "," + status + "," + rank;
    }

    public static Student fromCSV(String line) throws InvalidAgeException, InvalidMarksException {
        String[] parts = line.split(",");
        Address addr = new Address(parts[6], parts[7]);
        Student s = new Student(parts[0], parts[1], Integer.parseInt(parts[2]), parts[3].charAt(0),
                Double.parseDouble(parts[4]), parts[5], addr);
        s.status = parts[8];
        s.rank = Integer.parseInt(parts[9]);
        return s;
    }

    public String toString() {
        return "Student => [ID=" + stdId + "\tName=" + stdName + "\tAge=" + age + "\tGender=" + gender +
                "\tMarks=" + marks + "\tClass=" + classId + "\tAddress=" + address + "\tStatus=" + status + "\tRank=" + rank + "]";
    }
}
