class StdClass {
    private int id;
    private String className;
    public StdClass(int id, String className) 
	{ this.id = id; this.className = className; }
	
    public int getId() 
	{ return id; }
	
    public String getClassName()
	{ return className; }
	

    public String toString() 
	{ return "StdClass => [ id= " + id + "\t className= " + className + " ]"; }
}