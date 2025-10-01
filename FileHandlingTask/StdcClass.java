
class StdcClass {
    String classId;
    String className;

    public StdcClass(String classId, String className) {
        this.classId = classId;
        this.className = className;
    }

    public String toCSV() { return classId + "," + className; }
    public static StdcClass fromCSV(String line) {
        String[] parts = line.split(",");
        return new StdcClass(parts[0], parts[1]);
    }

    public String toString() { return classId + " : " + className; }
}

