import java.util.*;

class Producer implements Runnable {
     List<Integer> buffer;
     int capacity;
     Producer(List<Integer> buffer, int capacity) {
        this.buffer=buffer;
        this.capacity=capacity;
    }
    public void run() {
        try{
            for(int i=1;i<=capacity;i++){
                synchronized (buffer){
                    if(buffer.size()==capacity){
                        System.out.println("Buffer is full. Producer is waiting for consumption...");
                        buffer.wait();
                    }                  
                    buffer.add(i);
                    System.out.println("Producer produced: " +i);
                    buffer.notifyAll(); 
                }
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
class Consumer implements Runnable {
     List<Integer> buffer;

     Consumer(List<Integer> buffer) {
        this.buffer=buffer;
    }    
    public void run() {
        try {
            while (!buffer.isEmpty()) {
                synchronized (buffer){
                    if(buffer.isEmpty()){
                        System.out.println("Buffer is empty. Consumer is waiting for production...");
                        buffer.wait(1000); 
                    }
					int val=buffer.remove(0);
				   System.out.println("Consumer consumed: "+val);
                    buffer.notifyAll(); 
                }
                Thread.sleep(200);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
class Demo {
    public static void main(String[] args) {
        List<Integer> buffer=new ArrayList<>();
        int capacity=25;

        Producer producer=new Producer(buffer, capacity);
        Consumer consumer=new Consumer(buffer);

        Thread producerThread=new Thread(producer);
        Thread consumerThread=new Thread(consumer);

        producerThread.start();
        consumerThread.start();
    }
}

