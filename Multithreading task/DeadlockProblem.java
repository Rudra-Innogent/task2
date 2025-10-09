//classic deadlock condition example
class A {
    A() {}
    void show1(B b1) {
        System.out.println(Thread.currentThread().getName() + " entered A.show1() and trying to call B.show2()");
        b1.show2();
    }
    void show2() {
        System.out.println(Thread.currentThread().getName() + " executing A.show2()");
    }
}
class B {
    B() {}
    void show1(A a1) {
        System.out.println(Thread.currentThread().getName() + " entered B.show1() and trying to call A.show2()");
        a1.show2();
    }
    void show2() {
        System.out.println(Thread.currentThread().getName() + " executing B.show2()");
    }
}
class C extends Thread {
    A a1;
    B b1;
    C(A a1, B b1) {
        this.a1=a1;
        this.b1=b1;
    }
    public void run() {
        this.setName("Thread-C");
        System.out.println(getName()+" started and trying to lock resource: A");
        synchronized (a1) {
            System.out.println(getName()+" locked resource: A");
            try { Thread.sleep(100); } catch (InterruptedException e) {}
            System.out.println(getName()+" now trying to lock resource: B");
            synchronized (b1) {
                System.out.println(getName()+" locked resource: B");
                b1.show1(a1);
                System.out.println(getName()+" finished using resources A & B");
            }
        }
    }
}

class D extends Thread {
    A a1;
    B b1;
    D(A a1, B b1) {
        this.a1=a1;
        this.b1=b1;
    }
    public void run() {
        this.setName("Thread-D");
        System.out.println(getName()+" started and trying to lock resource: B");
        synchronized (b1) {
            System.out.println(getName()+" locked resource: B");
            try { Thread.sleep(100); } catch (InterruptedException e) {}
            System.out.println(getName()+" now trying to lock resource: A");
            synchronized (a1) {
                System.out.println(getName()+" locked resource: A");
                a1.show1(b1);
                System.out.println(getName()+" finished using resources B & A");
            }
        }
    }
}
class DeadlockProblem {
    public static void main(String[] args) {
        A a = new A();
        B b = new B();
        C c = new C(a, b);
        D d = new D(a, b);
        c.start();
        d.start();
        System.out.println("main method");
    }
}
