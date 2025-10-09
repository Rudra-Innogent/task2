//Solution of Deadlock using Lock interface and its implemented class ReentrantLock's methid tryLock(time,TimeUnit)
import java.util.concurrent.locks.*;
import java.util.concurrent.TimeUnit;
class A{
A(){}
	void show1(B b1){ 
	System.out.println(Thread.currentThread().getName()+" class B show1 method"); 
	b1.show2(); 
	} 
	void show2(){ 
	System.out.println(Thread.currentThread().getName()+" class B show2 method"); 
	}
}
class B{
B(){}
	void show1(A a1){ 
	System.out.println(Thread.currentThread().getName()+" class B show1 method"); 
	a1.show2(); 
	} 
	void show2(){ 
	System.out.println(Thread.currentThread().getName()+" class B show2 method"); 
	}
}
class C extends Thread {
    Lock lock;
    A a1;
    B b1;

    C(A a1, B b1, Lock lock) {
        this.a1=a1;
        this.b1=b1;
        this.lock=lock;
    }
    public void run() {
        this.setName("Thread-C");
        boolean firstLock=false;
        boolean secondLock=false;
        try {
            firstLock = lock.tryLock(1, TimeUnit.SECONDS);
            if (firstLock) {
                System.out.println(getName() + " acquired lock - performing A.show1()");
                a1.show1(b1);
                secondLock = lock.tryLock(1, TimeUnit.SECONDS);
                if (secondLock) {
                    System.out.println(getName() + " acquired lock again - performing B.show1()");
                    b1.show1(a1);
                } else {
                    System.out.println(getName() + " could not re-acquire lock (timeout) - skipping B.show1()");
                }
            } else {
                System.out.println(getName() + " could not acquire lock (timeout) - skipping work");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (secondLock) {
                lock.unlock();
                System.out.println(getName() + " released second lock");
            }
            if (firstLock) {
                lock.unlock();
                System.out.println(getName() + " released first lock");
            }
        }
    }
}
class D extends Thread {
    A a1;
    B b1;
    Lock lock;
    D(A a1, B b1, Lock lock) {
        this.a1=a1;
        this.b1=b1;
        this.lock=lock;
    }
    public void run() {
        this.setName("Thread-D");

        boolean firstLock=false;
        boolean secondLock=false;

        try {
            // Try to acquire first lock
            firstLock = lock.tryLock(1, TimeUnit.SECONDS);
            if (firstLock) {
                System.out.println(getName() + " acquired lock - performing B.show1()");
                b1.show1(a1);

                // Try to acquire lock again (reentrant)
                secondLock = lock.tryLock(1, TimeUnit.SECONDS);
                if (secondLock) {
                    System.out.println(getName() + " acquired lock again - performing A.show1()");
                    a1.show1(b1);
                } else {
                    System.out.println(getName() + " could not re-acquire lock (timeout) - skipping A.show1()");
                }
            } else {
                System.out.println(getName() + " could not acquire lock (timeout) - skipping work");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (secondLock) {
                lock.unlock();
                System.out.println(getName() + " released second lock");
            }
            if (firstLock) {
                lock.unlock();
                System.out.println(getName() + " released first lock");
            }
        }
    }
}

class DeadlockSolution {
    public static void main(String[] args) {
		Lock lock=new ReentrantLock();
		A a=new A();
		B b=new B();
		C c=new C(a,b,lock);
		D d=new D(a,b,lock);  
		c.start();
	    d.start();
		System.out.println("main method");
		
	}
	}