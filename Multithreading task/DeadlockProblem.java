//classic deadlock condition example
class A{
A(){}
	void show1(B b1){
		System.out.println("class A show1 method");
		b1.show2();
	}
	void show2(){
		System.out.println("class A show method");
	}
}
class B{
B(){}
	void show1(A a1){
		System.out.println("class B show1 method");
		a1.show2();
	}
	void show2(){
		System.out.println("class B show method");
	}
}
class C extends Thread{
	A a1;
	B b1;
	C(A a1,B b1){
		this.a1=a1;
		this.b1=b1;
	}
	public void run(){
		synchronized(a1){//locking a1 object
			a1.show1(b1);
			synchronized(b1){//locking b1 object
				b1.show1(a1);
			}
		}
	}
}

class D extends Thread{
	A a1;
	B b1;
	D(A a1,B b1){
		this.a1=a1;
		this.b1=b1;
	}
	public void run(){
		synchronized(b1){//locking b1 object
			b1.show1(a1);
			synchronized(a1){//locking a1 object
				a1.show1(b1);
			}
		}
	}
}
class DeadlockProblem {
    public static void main(String[] args) {
		A a=new A();
		B b=new B();
		C c=new C(a,b);//passing a1 and b1 this way if C-thread runs first it will lock a1 and b1 object respectively
		D d=new D(a,b);// passing same a1 and b1 object, D-thread runs first it will lock a1 and b1 objects respectively  
		c.start();
	    d.start();
		System.out.println("main method");
		
	}
	}