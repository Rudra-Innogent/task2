import java.util.concurrent.Semaphore;

class Car {
    private final Semaphore semaphore;

    public Car(int numCars) { semaphore=new Semaphore(numCars); }
    public void parking(String name) {
        try 
		{
            semaphore.acquire(); 
            System.out.println(name + " is parking...");
            Thread.sleep(2000); 
            System.out.println(name + " is leaving.");
        } 
		catch (InterruptedException e) {
            e.printStackTrace();
        } 
		finally {
            semaphore.release(); 
        }
    }
}
public class SemaphoreExample {
    public static void main(String[] args) {
        Car car=new Car(3); 

        for (int i=1;i<=5;i++) {
            final String name="User-" + i;
            new Thread(() -> car.parking(name)).start();
        }
    }
}
